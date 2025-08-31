import java.util.*;
public class EmailAnalyzer {
    public static boolean isValid(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && at == lastAt && dot > at && dot < email.length() - 1;
    }
    public static void analyze(String email) {
        if (!isValid(email)) {
            System.out.println(email + " -> Invalid");
            return;
        }
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        String domainName = email.substring(at + 1, dot);
        String extension = email.substring(dot + 1);
        System.out.println(email + " -> Username: " + username + ", Domain: " + domain + ", Ext: " + extension);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of emails: ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter email " + (i + 1) + ": ");
            String email = sc.nextLine();
            analyze(email);
        }
    }
}