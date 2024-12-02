import java.math.BigInteger;
import java.util.Scanner;

public class ElGamal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Key Generation
        BigInteger p = new BigInteger("101");  // A small prime number (lock)
        BigInteger g = new BigInteger("2");    // A number to help us lock the message
        BigInteger x = new BigInteger("10");    // Private key (secret number)
        BigInteger h = g.modPow(x, p);          // Public key (used to lock the message)

        // 2. Encryption (Locking the message)
        System.out.println("\nEnter a message to encrypt:");
        String message = scanner.nextLine(); 
        BigInteger k = new BigInteger("3");  // Random number (used to lock the message)
        BigInteger c1 = g.modPow(k, p);      // First part of the locked message
        BigInteger c2 = h.modPow(k, p).multiply(new BigInteger(message.getBytes())); // Second part of the locked message

        System.out.println("\nEncrypted Message (c1, c2):");

        // 3. Decryption (Unlocking the message)
        BigInteger s = c1.modPow(x, p);  // Using private key to unlock
        BigInteger sInverse = s.modInverse(p);  // Getting the inverse of s
        BigInteger decryptedMessage = c2.multiply(sInverse).mod(p);  // Unlock the message

        System.out.println("\nDecrypted Message (in BigInteger): " + decryptedMessage);
    }
}
