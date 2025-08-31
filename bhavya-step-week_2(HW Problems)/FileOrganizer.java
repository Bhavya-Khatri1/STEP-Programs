import java.util.*;
public class FileOrganizer {
    public static String getCategory(String file) {
        if (file.endsWith(".txt") || file.endsWith(".doc")) return "Document";
        if (file.endsWith(".jpg") || file.endsWith(".png")) return "Image";
        if (file.endsWith(".mp3") || file.endsWith(".wav")) return "Audio";
        return "Other";
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of files:");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i=0; i<n; i++) {
            System.out.print("Enter filename: ");
            String file = sc.nextLine();
            System.out.println(file + " -> " + getCategory(file));
        }
    }
}