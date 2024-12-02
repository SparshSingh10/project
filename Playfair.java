import java.util.Scanner;

public class Playfair {
    private static char[][] keyMatrix;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the keyword: ");
        String keyword = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        
        generateKeyMatrix(keyword);
        System.out.println("Key Matrix:");
        printKeyMatrix();
        
        String encryptedMessage = encrypt(message);
        System.out.println("Encrypted Message: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    private static void generateKeyMatrix(String keyword) {
        keyMatrix = new char[5][5];
        String key = keyword + "ABCDEFGHIKLMNOPQRSTUVWXYZ";  // 'J' is omitted
        key = key.replaceAll("J", "I");  // Replacing 'J' with 'I'
        
        boolean[] used = new boolean[26];
        int row = 0, col = 0;
        
        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                keyMatrix[row][col] = c;
                used[c - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    private static void printKeyMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(keyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String formatMessage(String message) {
        message = message.replaceAll("J", "I");
        StringBuilder formattedMessage = new StringBuilder();
        
        for (int i = 0; i < message.length(); i += 2) {
            formattedMessage.append(message.charAt(i));
            if (i + 1 < message.length()) {
                if (message.charAt(i) == message.charAt(i + 1)) {
                    formattedMessage.append('X');
                    i--;  // Repeat the current character
                } else {
                    formattedMessage.append(message.charAt(i + 1));
                }
            } else {
                formattedMessage.append('X');
            }
        }
        
        return formattedMessage.toString();
    }

    private static String encrypt(String message) {
        message = formatMessage(message);
        StringBuilder encryptedMessage = new StringBuilder();
        
        for (int i = 0; i < message.length(); i += 2) {
            char c1 = message.charAt(i);
            char c2 = message.charAt(i + 1);
            
            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);
            
            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];
            
            if (row1 == row2) {
                encryptedMessage.append(keyMatrix[row1][(col1 + 1) % 5]);
                encryptedMessage.append(keyMatrix[row2][(col2 + 1) % 5]);
            } else if (col1 == col2) {
                encryptedMessage.append(keyMatrix[(row1 + 1) % 5][col1]);
                encryptedMessage.append(keyMatrix[(row2 + 1) % 5][col2]);
            } else {
                encryptedMessage.append(keyMatrix[row1][col2]);
                encryptedMessage.append(keyMatrix[row2][col1]);
            }
        }
        
        return encryptedMessage.toString();
    }

    private static String decrypt(String message) {
        StringBuilder decryptedMessage = new StringBuilder();
        
        for (int i = 0; i < message.length(); i += 2) {
            char c1 = message.charAt(i);
            char c2 = message.charAt(i + 1);
            
            int[] pos1 = findPosition(c1);
            int[] pos2 = findPosition(c2);
            
            int row1 = pos1[0];
            int col1 = pos1[1];
            int row2 = pos2[0];
            int col2 = pos2[1];
            
            if (row1 == row2) {
                decryptedMessage.append(keyMatrix[row1][(col1 + 4) % 5]);
                decryptedMessage.append(keyMatrix[row2][(col2 + 4) % 5]);
            } else if (col1 == col2) {
                decryptedMessage.append(keyMatrix[(row1 + 4) % 5][col1]);
                decryptedMessage.append(keyMatrix[(row2 + 4) % 5][col2]);
            } else {
                decryptedMessage.append(keyMatrix[row1][col2]);
                decryptedMessage.append(keyMatrix[row2][col1]);
            }
        }
        
        return decryptedMessage.toString();
    }

    private static int[] findPosition(char c) {
        int[] position = new int[2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return position;
    }
}
