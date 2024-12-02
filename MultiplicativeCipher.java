
import java.util.Scanner;

public class MultiplicativeCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get the plaintext (lowercase letters only)
        System.out.print("Enter plaintext (lowercase letters only): ");
        String plaintext = scanner.nextLine().toLowerCase();

        // Step 2: Get the key (an integer coprime with 26)
        System.out.print("Enter key (coprime with 26): ");
        int key = scanner.nextInt();

        // Step 3: Encrypt the message
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted ciphertext (uppercase): " + ciphertext);

        // Step 4: Decrypt the message
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);

        // Step 5: Try brute force attack (try all possible keys)
        bruteForceAttack(ciphertext);
    }

    // Encryption method
    private static String encrypt(String plaintext, int key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLowerCase(ch)) {
                int value = ch-'a';
                int newValue=(value*key)%26;
                sb.append((char) (newValue+'A'));
            }
        }
        return sb.toString();
    }

    // Decryption method
    private static String decrypt(String ciphertext, int key) {
        StringBuilder decryptedText = new StringBuilder();
        int modInverse = modInverse(key, 26); // Find modular inverse of the key

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isUpperCase(ch)) {
                // Convert letter to number (0 = 'A', 1 = 'B', etc.)
                int charValue = ch - 'A';
                // Apply decryption formula (charValue * modInverse) % 26
                int decryptedValue = ((charValue * modInverse)+26) % 26;
                
                // Convert back to lowercase letter
                decryptedText.append((char) (decryptedValue + 'a'));
            }
        }
        return decryptedText.toString();
    }

    private static int modInverse(int n1, int n2) {
        n1 = n1 % n2;
        for (int i = 1; i < n2; i++) {
            if ((n1 * i) % n2 == 1) {
                return i; 
            }
        }
        return 1; 
    }

    private static void bruteForceAttack(String ciphertext) {
        for (int key = 1; key < 26; key++) {
            String decryptedText = decrypt(ciphertext, key);
            System.out.println("Key " + key + ": " + decryptedText);
        }
    }
}
