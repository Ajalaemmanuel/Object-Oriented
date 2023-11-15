import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        // Call the read method, encrypt the file contents, and then write to a new file
        String toEncrypt = readFile(inputFilePath);
        String toEncrypttext = encryptText(toEncrypt);
        writeFile(toEncrypttext, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        // Call the read method, decrypt the file contents, and then write to a new file
        String toDecrypt = readFile(messageFilePath);
        String todecryptText = decryptText(toDecrypt);
        writeFile(todecryptText, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(data);
        }
    }

    /**
     * Encrypts the given text using a simple Caesar cipher with the specified shift.
     *
     * @param text the text to be encrypted
     * @return the encrypted text
     */
    private String encryptText(String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (((c - 'A' + shift) % 26) + 'A');
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

    /**
     * Decrypts the given text using a simple Caesar cipher with the specified shift.
     *
     * @param text the text to be decrypted
     * @return the decrypted text
     */
    private String decryptText(String text) {
        StringBuilder decryptedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char decryptedChar = (char) (((c - 'A' - shift + 26) % 26) + 'A');
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(c);
            }
        }
        return decryptedText.toString();
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
