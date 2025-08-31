public class StringBuiltInMethods
{
    public static void main(String args[])
    {

        String sampletext="  Java Programming is Fun and Challenging!  ";
        System.out.println("Original length: "+sampletext.length());
        String newtext=sampletext.trim();
        System.out.println("Trimmed Length: "+newtext.length());
        System.out.println("Character at index 5: "+sampletext.charAt(5));
        System.out.println("Substring 'programming': "+sampletext.substring(7,18));
        System.out.println("Index of 'Fun': "+sampletext.indexOf("Fun"));
        System.out.println("Contains 'Java': "+sampletext.contains("Java"));
        System.out.println("Trimmed String starts with 'Java': "+newtext.startsWith("Java"));
        System.out.println("Trimmed String ends with '!': "+newtext.endsWith("!"));
        System.out.println("Uppercase: "+sampletext.toUpperCase());
        System.out.println("Lowercase: "+sampletext.toLowerCase());
        System.out.println("Total No. of vowels: "+countVowels(sampletext));
        System.out.println("Occurrences of 'a' are: ");
        findAllOccurrences(sampletext,'a');
    }
    public static int countVowels(String text)
    {
        int vowels=0;
        for(int i=0;i<text.length();i++)
        {
            char ch=text.charAt(i);
            if(ch>='A' && ch<='Z')
            {
                ch=(char)(ch+32);
            }
            if(ch>='a' && ch<='z')
            {
                if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
                {
                    vowels++;
                }
            }
        }
        return vowels;
    }
    public static void findAllOccurrences(String text,char target)
    {
        for(int i=0;i<text.length();i++)
        {
            if(text.charAt(i)==target)
            {
                System.out.print(i+" ");
            }
        }
        System.out.println("");
    }
}