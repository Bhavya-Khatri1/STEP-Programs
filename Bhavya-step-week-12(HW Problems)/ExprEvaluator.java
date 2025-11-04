import java.util.*;

/**
 * Single-file solution for "Full Expression Evaluator with Ternary & Canonicalization"
 * - Tokenizer
 * - Precedence-climbing parser producing an AST
 * - RPN (postfix) generation
 * - Canonical (fully parenthesized) infix generation
 * - Evaluation with strict typing (int vs boolean), variables from env map
 *
 * Usage:
 *   ExprResult res = ExprEvaluator.evaluate(exprString, envMap);
 *   if (res == null) -> an ERROR occurred (or method may throw). On error prints "ERROR".
 */
public final class ExprEvaluator {

    // --- Result container ---
    public static class ExprResult {
        public final String value;     // evaluated value (string literal: integer or "true"/"false")
        public final String rpn;       // space-separated RPN (ternary emitted as ?:)
        public final String canonical; // fully parenthesized canonical infix
        public ExprResult(String value, String rpn, String canonical) {
            this.value = value;
            this.rpn = rpn;
            this.canonical = canonical;
        }
        public String toString() {
            return "value=" + value + ", rpn=\"" + rpn + "\", canonical=\"" + canonical + "\"";
        }
    }

    // --- Tokenizer ---
    private enum TokType { INT, IDENT, BOOL, OP, PAREN, COMMA, QMARK, COLON, EOF }
    private static final class Token {
        final TokType type;
        final String text;
        Token(TokType type, String text) { this.type = type; this.text = text; }
        public String toString() { return type + ":" + text; }
    }

    private static class Tokenizer {
        private final String s;
        private final int n;
        private int i = 0;
        Tokenizer(String s) { this.s = s; this.n = s.length(); }

        Token next() {
            while (i < n && Character.isWhitespace(s.charAt(i))) i++;
            if (i >= n) return new Token(TokType.EOF, "");
            char c = s.charAt(i);

            // Numbers (signed integers allowed as part of tokenizing as unary handled in parser)
            if (Character.isDigit(c)) {
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j))) j++;
                String t = s.substring(i, j);
                i = j;
                return new Token(TokType.INT, t);
            }

            // Identifier or boolean literal or function
            if (Character.isLetter(c) || c == '_') {
                int j = i;
                while (j < n && (Character.isLetterOrDigit(s.charAt(j)) || s.charAt(j) == '_')) j++;
                String t = s.substring(i, j);
                i = j;
                if (t.equals("true") || t.equals("false")) return new Token(TokType.BOOL, t);
                return new Token(TokType.IDENT, t);
            }

            // Two-char operators: == != <= >=
            if (i + 1 < n) {
                String two = s.substring(i, i+2);
                if (two.equals("==") || two.equals("!=") || two.equals("<=") || two.equals(">=")) {
                    i += 2;
                    return new Token(TokType.OP, two);
                }
            }

            // single char tokens
            i++;
            switch (c) {
                case '+': case '-': case '*': case '/': case '%': case '^':
                case '<': case '>': case '!': case '&': case '|':
                    // note: &,| may form && or ||
                    if ((c == '&' || c == '|') && i < n && s.charAt(i) == c) {
                        i++; // consume second
                        return new Token(TokType.OP, "" + c + c);
                    }
                    return new Token(TokType.OP, String.valueOf(c));
                case '(':
                case ')':
                    return new Token(TokType.PAREN, String.valueOf(c));
                case ',':
                    return new Token(TokType.COMMA, ",");
                case '?':
                    return new Token(TokType.QMARK, "?");
                case ':':
                    return new Token(TokType.COLON, ":");
                default:
                    throw new IllegalArgumentException("Invalid character in expression: '" + c + "'");
            }
        }
    }

    // --- AST Definitions ---
    private static abstract class Node {
        abstract Type evalType(Map<String,String> env) throws EvalException;
        abstract Value eval(Map<String,String> env) throws EvalException;
        abstract void buildRPN(StringBuilder sb);       // append tokens (space separated)
        abstract String canonical();                    // fully parenthesized
    }

    private enum Type { INT, BOOL }
    private static final class Value {
        final Type type;
        final long intVal; // used if INT
        final boolean boolVal; // used if BOOL
        Value(long v) { this.type = Type.INT; this.intVal = v; this.boolVal = false; }
        Value(boolean b) { this.type = Type.BOOL; this.intVal = 0; this.boolVal = b; }
        public String toString() { return type == Type.INT ? Long.toString(intVal) : Boolean.toString(boolVal); }
    }

    private static final class EvalException extends Exception {
        EvalException(String m) { super(m); }
    }

    private static final class LiteralNode extends Node {
        final Type type;
        final long intVal;
        final boolean boolVal;
        LiteralNode(long v) { type = Type.INT; intVal = v; boolVal = false; }
        LiteralNode(boolean b) { type = Type.BOOL; intVal = 0; boolVal = b; }
        Type evalType(Map<String,String> env) { return type; }
        Value eval(Map<String,String> env) { return type == Type.INT ? new Value(intVal) : new Value(boolVal); }
        void buildRPN(StringBuilder sb) {
            sb.append(type == Type.INT ? Long.toString(intVal) : Boolean.toString(boolVal)).append(' ');
        }
        String canonical() {
            return type == Type.INT ? Long.toString(intVal) : Boolean.toString(boolVal);
        }
    }

    private static final class VarNode extends Node {
        final String name;
        VarNode(String name) { this.name = name; }
        Type evalType(Map<String,String> env) throws EvalException {
            String v = env.get(name);
            if (v == null) throw new EvalException("Unknown variable: " + name);
            if (v.equals("true") || v.equals("false")) return Type.BOOL;
            try {
                Long.parseLong(v);
                return Type.INT;
            } catch (NumberFormatException e) {
                throw new EvalException("Invalid variable literal: " + name);
            }
        }
        Value eval(Map<String,String> env) throws EvalException {
            String v = env.get(name);
            if (v == null) throw new EvalException("Unknown variable: " + name);
            if (v.equals("true") || v.equals("false")) return new Value(Boolean.parseBoolean(v));
            try {
                long x = Long.parseLong(v);
                return new Value(x);
            } catch (NumberFormatException e) {
                throw new EvalException("Invalid variable literal: " + name);
            }
        }
        void buildRPN(StringBuilder sb) {
            sb.append(name).append(' ');
        }
        String canonical() {
            return name;
        }
    }

    private static final class UnaryNode extends Node {
        final String op;
        final Node child;
        UnaryNode(String op, Node child) { this.op = op; this.child = child; }
        Type evalType(Map<String,String> env) throws EvalException {
            Type t = child.evalType(env);
            if (op.equals("!")) {
                if (t != Type.BOOL) throw new EvalException("! requires boolean");
                return Type.BOOL;
            } else { // + or -
                if (t != Type.INT) throw new EvalException(op + " requires integer");
                return Type.INT;
            }
        }
        Value eval(Map<String,String> env) throws EvalException {
            Value v = child.eval(env);
            if (op.equals("!")) {
                if (v.type != Type.BOOL) throw new EvalException("! requires boolean");
                return new Value(!v.boolVal);
            } else if (op.equals("+")) {
                if (v.type != Type.INT) throw new EvalException("+ requires integer");
                return new Value(v.intVal);
            } else { // '-'
                if (v.type != Type.INT) throw new EvalException("- requires integer");
                return new Value(-v.intVal);
            }
        }
        void buildRPN(StringBuilder sb) {
            child.buildRPN(sb);
            sb.append(op).append(' ');
        }
        String canonical() { return "(" + op + child.canonical() + ")"; }
    }

    private static final class BinaryNode extends Node {
        final String op;
        final Node left, right;
        BinaryNode(String op, Node l, Node r) { this.op = op; this.left = l; this.right = r; }
        Type evalType(Map<String,String> env) throws EvalException {
            Type lt = left.evalType(env), rt = right.evalType(env);
            switch (op) {
                case "||": case "&&": case "==": case "!=":
                case "<": case "<=": case ">": case ">=":
                    // relational: allow int-int comparisons -> boolean; ==/!= allow both bool-bool and int-int
                    if (op.equals("==") || op.equals("!=")) {
                        if (lt == rt) return Type.BOOL;
                        throw new EvalException("==/!= require same types");
                    }
                    if (lt == Type.INT && rt == Type.INT) return Type.BOOL;
                    if ((op.equals("<")||op.equals("<=")||op.equals(">")||op.equals(">=")) && lt==Type.INT && rt==Type.INT) return Type.BOOL;
                    if (op.equals("||")||op.equals("&&")) {
                        if (lt==Type.BOOL && rt==Type.BOOL) return Type.BOOL;
                        throw new EvalException(op + " requires booleans");
                    }
                    throw new EvalException("Invalid types for operator " + op);
                default:
                    // arithmetic: + - * / % ^ require ints
                    if (lt == Type.INT && rt == Type.INT) return Type.INT;
                    throw new EvalException("Arithmetic operator " + op + " requires integers");
            }
        }
        Value eval(Map<String,String> env) throws EvalException {
            Value a = left.eval(env), b = right.eval(env);
            switch (op) {
                case "+": return new Value(a.intVal + b.intVal);
                case "-": return new Value(a.intVal - b.intVal);
                case "*": return new Value(a.intVal * b.intVal);
                case "/":
                    if (b.intVal == 0) throw new EvalException("Divide by zero");
                    return new Value(a.intVal / b.intVal);
                case "%":
                    if (b.intVal == 0) throw new EvalException("Modulo by zero");
                    return new Value(a.intVal % b.intVal);
                case "^":
                    // right-assoc handled in parse; implement pow with non-negative exponent assumption? but exponent can be negative - treat as integer pow (if negative, error)
                    long exp = b.intVal;
                    if (exp < 0) throw new EvalException("^ negative exponent");
                    long res = 1;
                    long base = a.intVal;
                    while (exp > 0) {
                        if ((exp & 1) == 1) res = Math.multiplyExact(res, base);
                        base = Math.multiplyExact(base, base);
                        exp >>= 1;
                    }
                    return new Value(res);
                case "==":
                    if (a.type != b.type) throw new EvalException("== types mismatch");
                    if (a.type == Type.INT) return new Value(a.intVal == b.intVal);
                    else return new Value(a.boolVal == b.boolVal);
                case "!=":
                    if (a.type != b.type) throw new EvalException("!= types mismatch");
                    if (a.type == Type.INT) return new Value(a.intVal != b.intVal);
                    else return new Value(a.boolVal != b.boolVal);
                case "<": return new Value(a.intVal < b.intVal);
                case "<=": return new Value(a.intVal <= b.intVal);
                case ">": return new Value(a.intVal > b.intVal);
                case ">=": return new Value(a.intVal >= b.intVal);
                case "&&":
                    return new Value(a.boolVal && b.boolVal);
                case "||":
                    return new Value(a.boolVal || b.boolVal);
                default: throw new EvalException("Unknown binary op: " + op);
            }
        }
        void buildRPN(StringBuilder sb) {
            left.buildRPN(sb);
            right.buildRPN(sb);
            sb.append(op).append(' ');
        }
        String canonical() {
            return "(" + left.canonical() + op + right.canonical() + ")";
        }
    }

    private static final class TernaryNode extends Node {
        final Node cond, left, right;
        TernaryNode(Node cond, Node left, Node right) { this.cond = cond; this.left = left; this.right = right; }
        Type evalType(Map<String,String> env) throws EvalException {
            Type ct = cond.evalType(env);
            if (ct != Type.BOOL) throw new EvalException("Ternary condition must be boolean");
            Type lt = left.evalType(env), rt = right.evalType(env);
            if (lt != rt) throw new EvalException("Ternary branches must have same type");
            return lt;
        }
        Value eval(Map<String,String> env) throws EvalException {
            Value cv = cond.eval(env);
            if (cv.type != Type.BOOL) throw new EvalException("Ternary condition must be boolean");
            if (cv.boolVal) return left.eval(env);
            else return right.eval(env);
        }
        void buildRPN(StringBuilder sb) {
            cond.buildRPN(sb);
            left.buildRPN(sb);
            right.buildRPN(sb);
            sb.append("?: ").append(' ');
        }
        String canonical() {
            return "(" + cond.canonical() + " ? " + left.canonical() + " : " + right.canonical() + ")";
        }
    }

    private static final class FuncNode extends Node {
        final String name;
        final List<Node> args;
        FuncNode(String name, List<Node> args) { this.name = name; this.args = args; }
        Type evalType(Map<String,String> env) throws EvalException {
            switch (name) {
                case "min":
                case "max":
                    if (args.size() != 2) throw new EvalException(name + " requires 2 args");
                    for (Node n: args) if (n.evalType(env) != Type.INT) throw new EvalException(name + " requires integer args");
                    return Type.INT;
                case "abs":
                case "sgn":
                    if (args.size() != 1) throw new EvalException(name + " requires 1 arg");
                    if (args.get(0).evalType(env) != Type.INT) throw new EvalException(name + " requires integer arg");
                    return Type.INT;
                default:
                    throw new EvalException("Unknown function: " + name);
            }
        }
        Value eval(Map<String,String> env) throws EvalException {
            if (name.equals("min")) {
                long a = args.get(0).eval(env).intVal, b = args.get(1).eval(env).intVal;
                return new Value(Math.min(a,b));
            } else if (name.equals("max")) {
                long a = args.get(0).eval(env).intVal, b = args.get(1).eval(env).intVal;
                return new Value(Math.max(a,b));
            } else if (name.equals("abs")) {
                long a = args.get(0).eval(env).intVal;
                return new Value(Math.abs(a));
            } else if (name.equals("sgn")) {
                long a = args.get(0).eval(env).intVal;
                if (a > 0) return new Value(1L);
                if (a < 0) return new Value(-1L);
                return new Value(0L);
            } else {
                throw new EvalException("Unknown function: " + name);
            }
        }
        void buildRPN(StringBuilder sb) {
            for (Node n: args) n.buildRPN(sb);
            sb.append(name).append(' ').append(' ');
        }
        String canonical() {
            StringBuilder b = new StringBuilder();
            b.append(name).append('(');
            for (int i = 0; i < args.size(); ++i) {
                if (i > 0) b.append(',');
                b.append(args.get(i).canonical());
            }
            b.append(')');
            return b.toString();
        }
    }

    // --- Parser (precedence climbing) ---
    private static class Parser {
        private final List<Token> tokens;
        private int pos = 0;
        Parser(List<Token> tokens) { this.tokens = tokens; }

        private Token peek() { return pos < tokens.size() ? tokens.get(pos) : new Token(TokType.EOF, ""); }
        private Token consume() { return pos < tokens.size() ? tokens.get(pos++) : new Token(TokType.EOF, ""); }
        private boolean match(TokType t, String text) {
            if (peek().type == t && (text == null || peek().text.equals(text))) { consume(); return true; }
            return false;
        }

        // Precedence mapping: higher number -> higher precedence
        // Ternary handled specially (lowest precedence)
        private static final Map<String, Integer> BINPREC = Map.ofEntries(
            Map.entry("||", 2),
            Map.entry("&&", 3),
            Map.entry("==", 4), Map.entry("!=", 4),
            Map.entry("<", 5), Map.entry("<=", 5), Map.entry(">", 5), Map.entry(">=", 5),
            Map.entry("+", 6), Map.entry("-", 6),
            Map.entry("*", 7), Map.entry("/", 7), Map.entry("%", 7),
            Map.entry("^", 8) // right-associative
        );

        // unary precedence (higher than ^)
        private static final int UNARY_PREC = 9;

        Node parseExpression() throws EvalException {
            Node node = parseUnaryOrPrimary();
            return parseBinaryRight(node, 1); // start with precedence 1 (ternary reserved below)
        }

        // parse binary operators using precedence climbing
        private Node parseBinaryRight(Node left, int minPrec) throws EvalException {
            while (true) {
                Token t = peek();
                // ternary?
                if (t.type == TokType.QMARK) {
                    // ternary has lowest precedence; bind only if minPrec <= 1
                    if (minPrec > 1) break;
                    consume(); // consume '?'
                    Node trueExpr = parseExpression(); // parse full expression for true branch
                    if (peek().type != TokType.COLON) throw new EvalException("Dangling ?");
                    consume(); // consume ':'
                    Node falseExpr = parseExpression();
                    left = new TernaryNode(left, trueExpr, falseExpr);
                    continue;
                }
                if (t.type != TokType.OP) break;
                String op = t.text;
                Integer precObj = BINPREC.get(op);
                if (precObj == null) break;
                int prec = precObj;
                boolean rightAssoc = op.equals("^");
                int nextMin = rightAssoc ? prec : prec + 1;
                if (prec < minPrec) break;
                consume(); // consume operator
                Node right = parseUnaryOrPrimary();
                right = parseBinaryRight(right, nextMin);
                left = new BinaryNode(op, left, right);
            }
            return left;
        }

        private Node parseUnaryOrPrimary() throws EvalException {
            Token t = peek();
            if (t.type == TokType.OP && (t.text.equals("+") || t.text.equals("-") || t.text.equals("!"))) {
                consume();
                Node operand = parseUnaryOrPrimary();
                return new UnaryNode(t.text, operand);
            }
            return parsePrimary();
        }

        private Node parsePrimary() throws EvalException {
            Token t = peek();
            if (t.type == TokType.INT) {
                consume();
                try {
                    long v = Long.parseLong(t.text);
                    return new LiteralNode(v);
                } catch (NumberFormatException e) {
                    throw new EvalException("Integer literal out of range");
                }
            }
            if (t.type == TokType.BOOL) {
                consume();
                return new LiteralNode(Boolean.parseBoolean(t.text));
            }
            if (t.type == TokType.IDENT) {
                // function call or variable
                Token nameTok = consume();
                if (peek().type == TokType.PAREN && peek().text.equals("(")) {
                    consume(); // '('
                    List<Node> args = new ArrayList<>();
                    if (!(peek().type == TokType.PAREN && peek().text.equals(")"))) {
                        while (true) {
                            args.add(parseExpression());
                            if (peek().type == TokType.COMMA) { consume(); continue; }
                            break;
                        }
                    }
                    if (!(peek().type == TokType.PAREN && peek().text.equals(")"))) throw new EvalException("Missing closing ) in function call");
                    consume(); // ')'
                    return new FuncNode(nameTok.text, args);
                } else {
                    return new VarNode(nameTok.text);
                }
            }
            if (t.type == TokType.PAREN && t.text.equals("(")) {
                consume();
                Node inside = parseExpression();
                if (!(peek().type == TokType.PAREN && peek().text.equals(")"))) throw new EvalException("Missing closing )");
                consume();
                return inside;
            }
            throw new EvalException("Unexpected token: " + t);
        }
    }

    // --- Public evaluate method ---
    public static ExprResult evaluate(String expr, Map<String,String> env) {
        try {
            // Tokenize
            Tokenizer tz = new Tokenizer(expr);
            List<Token> toks = new ArrayList<>();
            while (true) {
                Token tk = tz.next();
                toks.add(tk);
                if (tk.type == TokType.EOF) break;
            }
            // Remove EOF token from parse list
            toks.remove(toks.size()-1);

            // Parser
            Parser p = new Parser(toks);
            Node root = p.parseExpression();
            if (p.peek().type != TokType.EOF) throw new EvalException("Extra tokens after expression");

            // Build RPN
            StringBuilder rpnBuilder = new StringBuilder();
            root.buildRPN(rpnBuilder);
            String rpn = rpnBuilder.toString().trim().replaceAll("\\s+", " ");

            // Canonical
            String canonical = root.canonical();

            // Evaluate
            Value v = root.eval(env);
            String valueStr = v.toString();

            return new ExprResult(valueStr, rpn, canonical);
        } catch (EvalException | IllegalArgumentException | ArithmeticException e) {
            System.out.println("ERROR");
            return null;
        }
    }

    // --- Demo main with a few tests ---
    public static void main(String[] args) {
        Map<String,String> env = new HashMap<>();
        env.put("a", "-4");
        String expr = "max(3, 1+2) * (a>0 ? 5 : 2)";
        ExprResult res = evaluate(expr, env);
        if (res != null) System.out.println(res);

        // a positive case
        env.put("a", "1");
        res = evaluate(expr, env);
        if (res != null) System.out.println(res);

        // NextGreater example tests (just to try other forms)
        String ex2 = "sgn(-5) + abs(-3) * (2 ^ 3) == 1 ? 100 : 200";
        res = evaluate(ex2, Collections.emptyMap());
        if (res != null) System.out.println(res);

        // Error cases
        String exErr = "1 + true";
        res = evaluate(exErr, Collections.emptyMap()); // should print ERROR
    }
}