public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("PERFORMANCE COMPARISON");
        long start = System.nanoTime();
        String result1 = concatenateWithString(5000);
        long end = System.nanoTime();
        System.out.println("String concatenation time: " + (end - start) + " ns");
        start = System.nanoTime();
        String result2 = concatenateWithStringBuilder(5000);
        end = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (end - start) + " ns");
        start = System.nanoTime();
        String result3 = concatenateWithStringBuffer(5000);
        end = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (end - start) + " ns");
        demonstrateStringBuilderMethods();
        compareStringComparisonMethods();
    }
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }
    public static void demonstrateStringBuilderMethods() {
        System.out.println("\nSTRINGBUILDER METHODS");
        StringBuilder sb = new StringBuilder("Hello World");
        sb.append("!!!");
        System.out.println("append: " + sb);
        sb.insert(5, " Java");
        System.out.println("insert: " + sb);
        sb.delete(5, 10);
        System.out.println("delete: " + sb);
        sb.deleteCharAt(5);
        System.out.println("deleteCharAt: " + sb);
        sb.reverse();
        System.out.println("reverse: " + sb);
        sb.reverse();
        sb.replace(0, 5, "Hey");
        System.out.println("replace: " + sb);
        sb.setCharAt(0, 'h');
        System.out.println("setCharAt: " + sb);
        System.out.println("capacity: " + sb.capacity());
        sb.ensureCapacity(50);
        System.out.println("after ensureCapacity(50): " + sb.capacity());
        sb.trimToSize();
        System.out.println("after trimToSize: " + sb.capacity());
    }
    public static void compareStringComparisonMethods() {
        System.out.println("\nSTRING COMPARISON");
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");
        System.out.println("== operator: " + (str1 == str2));
        System.out.println("== operator with new: " + (str1 == str3));
        System.out.println("equals(): " + str1.equals(str3));
        System.out.println("equalsIgnoreCase(): " + str1.equalsIgnoreCase("hello"));
        System.out.println("compareTo(): " + str1.compareTo(str3));
        System.out.println("compareToIgnoreCase(): " + str1.compareToIgnoreCase("HELLO"));
    }
}