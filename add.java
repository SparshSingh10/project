import java.util.Scanner;

public class add {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Text");

        String text = sc.nextLine();
        System.out.println("Enter Key");
        int key = sc.nextInt();

        // Encrypt the text
        String encrypt = en(text, key);
        System.out.println("Encrypted Text: " + encrypt);

        // Decrypt the text
        String decrypt = de(encrypt, key);
        System.out.println("Decrypted Text: " + decrypt);
    }

    public static String en(String text, int key) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLowerCase(ch)) {
                // Corrected encryption formula
                char ch1 = (char) ((ch - 'a' + key) % 26 + 'A');
                sb.append(ch1);
            }
        }
        return sb.toString();
    }

    public static String de(String text, int key) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                // Corrected decryption formula
                char ch1 = (char) ((ch - 'A' - key + 26) % 26 + 'a');
                sb.append(ch1);
            }
        }
        return sb.toString();
    }
}
