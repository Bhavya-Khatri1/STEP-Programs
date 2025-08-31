import java.util.*;
public class CSVAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV data (end with blank line):");
        StringBuilder data = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            data.append(line).append("\n");
        }
        String[] rows = data.toString().split("\n");
        for (String row : rows) {
            String[] cols = row.split(",");
            for (String c : cols) {
                System.out.print(c.trim() + "\t");
            }
            System.out.println();
        }
    }
}