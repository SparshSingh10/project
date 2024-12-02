import java.util.Scanner;

public class AffineCipher {

    private static int modInverse(int k1, int m) {
        for (int i = 1; i < m; i++) {
            if ((k1 * i) % m == 1) {
                return i; 
            }
        }
        return -1; // No modular inverse found
    }

    // Encryption function
    private static String encrypt(String plaintext, int k1, int k2) {
        StringBuilder ciphertext = new StringBuilder();
        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int x = ch - 'a'; 
                int encryptedChar = (k1 * x + k2) % 26;
                ciphertext.append((char) (encryptedChar + 'A')); 
            }
        }
        return ciphertext.toString();
    }

    // Decryption function
    private static String decrypt(String ciphertext, int k1, int k2) {
        StringBuilder decryptedText = new StringBuilder();
        int aInverse = modInverse(k1, 26); // Find modular inverse of k1
        if (aInverse == -1) {
            System.out.println("Invalid key. 'k1' and 26 must be coprime.");
            return "";
        }

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int y = ch - 'A'; 
                int decryptedChar = (aInverse * (y - k2 + 26)) % 26;
                decryptedText.append((char) (decryptedChar + 'a')); 
            }
        }
        return decryptedText.toString();
    }

    // Brute Force Attack function to try all keys
    private static void bruteForceAttack(String ciphertext) {
        System.out.println("\nBrute Force Attack: Trying all possible keys...");
        for (int k1 = 1; k1 < 26; k1++) {
            for (int k2 = 0; k2 < 26; k2++) {
                if (modInverse(k1, 26) != -1) { // Only try keys where k1 has an inverse mod 26
                    String decryptedText = decrypt(ciphertext, k1, k2);
                    System.out.println("k1 = " + k1 + ", k2 = " + k2 + ": " + decryptedText);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for plaintext, k1, and k2
        System.out.print("Enter plaintext (lowercase letters only): ");
        String plaintext = scanner.nextLine().toLowerCase(); // Convert to lowercase
        System.out.print("Enter the value of 'k1' (must be coprime with 26): ");
        int k1 = scanner.nextInt();
        System.out.print("Enter the value of 'k2': ");
        int k2 = scanner.nextInt();

        // Validate that k1 is coprime with 26
        if (k1 < 0 || k1 >= 26 || gcd(k1, 26) != 1) {
            System.out.println("Invalid value of 'k1'. It must be coprime with 26.");
            return;
        }

        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext, k1, k2);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText, k1, k2);
        System.out.println("Decrypted Text: " + decryptedText);

        // Brute Force Attack on ciphertext
        bruteForceAttack(encryptedText);
    }

    // Function to find the greatest common divisor (GCD)
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
