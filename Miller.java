import java.util.Random;
import java.util.Scanner;

public class Miller {

    // Function to calculate (a^b) % m
    private static long power(long a, long b, long m) {
        long result = 1;
        a = a % m;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % m;
            }
            b = b / 2;
            a = (a * a) % m;
        }
        return result;
    }

    // Miller-Rabin primality test
    private static boolean millerTest(long n, long d) {
        Random rand = new Random();
        // Pick a random number 'a' in the range [2, n-2]
        long a = 2 + rand.nextInt((int) (n - 4));
        // Compute a^d % n
        long x = power(a, d, n);
        
        // If x is 1 or n-1, the number passes the test
        if (x == 1 || x == n - 1) {
            return true;
        }
        
        // Keep squaring x and check if it becomes n-1
        while (d != n - 1) {
            x = (x * x) % n;
            d = d * 2;
            if (x == n - 1) {
                return true;
            }
            if (x == 1) {
                return false;
            }
        }
        return false;
    }

    // Miller-Rabin primality test implementation
    private static boolean isPrime(long n, int k) {
        // Handle simple cases
        if (n <= 1 || n == 4) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        // Write n-1 as d * 2^r
        long d = n - 1;
        while (d % 2 == 0) {
            d /= 2;
        }

        // Try k iterations of the Miller-Rabin test
        for (int i = 0; i < k; i++) {
            if (!millerTest(n, d)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to check for primality: ");
        long number = scanner.nextLong();
        System.out.print("Enter the number of iterations (k) for the Rabin-Miller test: ");
        int k = scanner.nextInt();
        
        // Perform primality test
        if (isPrime(number, k)) {
            System.out.println(number + " is prime.");
        } else {
            System.out.println(number + " is composite.");
        }

        scanner.close();
    }
}
