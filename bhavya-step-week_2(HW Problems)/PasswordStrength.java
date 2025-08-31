import java.util.*;
public class PasswordStrength {
    public static int analyze(String pwd) {
        int upper=0, lower=0, digit=0, special=0;
        for (int i=0; i<pwd.length(); i++) {
            char ch = pwd.charAt(i);
            if (ch>='A' && ch<='Z') upper++;
            else if (ch>='a' && ch<='z') lower++;
            else if (ch>='0' && ch<='9') digit++;
            else special++;
        }
        int score = 0;
        if (pwd.length() > 8) score += (pwd.length()-8)*2;
        if (upper>0) score += 10;
        if (lower>0) score += 10;
        if (digit>0) score += 10;
        if (special>0) score += 10;
        if (pwd.contains("123") || pwd.contains("abc") || pwd.contains("qwerty")) score -= 10;
        return score;
    }
    public static String strengthLevel(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }
    public static String generatePassword(int len) {
        String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower="abcdefghijklmnopqrstuvwxyz";
        String digits="0123456789";
        String special="!@#$%^&*";
        String all = upper+lower+digits+special;
        Random r = new Random();
        StringBuilder pwd = new StringBuilder();
        pwd.append(upper.charAt(r.nextInt(upper.length())));
        pwd.append(lower.charAt(r.nextInt(lower.length())));
        pwd.append(digits.charAt(r.nextInt(digits.length())));
        pwd.append(special.charAt(r.nextInt(special.length())));
        while (pwd.length() < len) {
            pwd.append(all.charAt(r.nextInt(all.length())));
        }
        return pwd.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password:");
        String pwd = sc.nextLine();
        int score = analyze(pwd);
        System.out.println("Password: " + pwd + ", Score: " + score + ", Strength: " + strengthLevel(score));
        System.out.print("Generate strong password of length 12: ");
        System.out.println(generatePassword(12));
    }
}