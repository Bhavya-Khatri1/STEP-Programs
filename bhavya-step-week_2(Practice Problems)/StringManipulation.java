import java.util.*;
public class StringManipulation
{
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a sentence in mixed formatting:");
        String str=sc.nextLine();
        String trimmed=str.trim();
        System.out.println("Trimmed string: "+trimmed);
        String replace=trimmed.replace(" ","_");
        System.out.println("String replace: "+replace);
        String noDig=trimmed.replaceAll("[0-9]","");
        System.out.println("Without digits: "+noDig);
        String words[]=trimmed.split("\\s+");
        System.out.println("Split array: "+Arrays.toString(words));
        String join=String.join(" | ",words);
        System.out.println("Joined string: "+join);
        System.out.println("No punctuation: "+removePunctuation(trimmed));
        System.out.println("Capitalized: "+capitalizeWords(trimmed));
        System.out.println("Reverse order: "+reverseWordOrder(trimmed));
        System.out.println("Word Frequency:");
        countWordFrequency(trimmed);
        sc.close();
    }
    public static String removePunctuation(String text)
    {
        return text.replaceAll("\\p{Punct}","");
    }
    public static String capitalizeWords(String text)
    {
        String[] words = text.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(String word : words)
        {
            if(!word.isEmpty())
            {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if(word.length()>1)
                {
                    sb.append(word.substring(1).toLowerCase());
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
    public static String reverseWordOrder(String text)
    {
        String words[]=text.split("\\s+");
        StringBuilder sb=new StringBuilder();
        for(int i=words.length-1;i>=0;i--)
        {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }
    public static void countWordFrequency(String text)
    {
        String words[]=text.toLowerCase().split("\\s+");
        boolean visited[]=new boolean[words.length];
        for(int i=0;i<words.length;i++)
        {
            if(visited[i])
            continue;
            int count=1;
            for(int j=i+1;j<words.length;j++)
            {
                if(words[i].equals(words[j]))
                {
                    count++;
                    visited[j]=true;
                }
            }
            System.out.println(words[i]+" : "+count);
        }
    }
}