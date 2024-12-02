import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter prime (p): ");
        BigInteger p = sc.nextBigInteger();
        System.out.print("Enter primitive root (g): ");
        BigInteger g = sc.nextBigInteger();
        System.out.print("Enter Alice's key: ");
        BigInteger a = sc.nextBigInteger();
        System.out.print("Enter Bob's key: ");
        BigInteger b = sc.nextBigInteger();

        BigInteger k = g.modPow(a, p).modPow(b, p); // Shared key
        System.out.println("Shared Key: " + k);

        String m = "sparsh singh";
        System.out.println("Original: " + m);

        String enc = process(m, k.intValue());
        System.out.println("Encrypted: " + enc);

        System.out.println("Decrypted: " + process(enc, -k.intValue()));
    }

    private static String process(String s, int k) {
        StringBuilder r = new StringBuilder();
        for (char c : s.toCharArray())
            r.append((char) (c + k));
        return r.toString();
    }
}
