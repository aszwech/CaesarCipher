import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class CaesarCipher {

    public static void main(String... args) {

        String message = "Ala ma kota! A Basia ma 25 kotów i pszczółkę.\nZażółć gęślą jaźń";

        int key = 5;
        Map<Character, Character> cipher = generateCezarCipherPl(key);

        System.out.println("Message:");
        System.out.println(message);

        System.out.println("Encryption with key '" + key + "':");
        String encryptedMessage = encryptMessage(cipher, message);
        System.out.println(encryptedMessage);

        System.out.println("Base64 encoding:");
        Base64.Encoder encoder = Base64.getEncoder();
        String codeBase64 = encoder.encodeToString(encryptedMessage.getBytes(StandardCharsets.UTF_8));
        System.out.println(codeBase64);

        System.out.println("Base64 decoding:");
        Base64.Decoder decoder = Base64.getDecoder();
        String secretCode = new String(decoder.decode(codeBase64));
        System.out.println(secretCode);

        System.out.println("Decription - original message:");
        cipher = generateCezarCipherPl(30);
        System.out.println(encryptMessage(cipher, secretCode));
    }

    private static String encryptMessage(Map<Character, Character> cipher, String message) {
        StringBuilder sb = new StringBuilder();
        for (char c : message.toCharArray()) {
            sb.append(cipher.getOrDefault(c, c));
        }
        return sb.toString();
    }

    public static Map<Character, Character> generateCezarCipherPl(int key) {
        Map<Character, Character> cipher = new HashMap<>();
        char[] alphabetUpper = "AĄBCĆDEĘFGHIJKLŁMNŃOÓPQRSŚTUVWXYZŹŻ".toCharArray();
        char[] alphabetLower = "aąbcćdeęfghijklłmnńoópqrsśtuvwxyzźż".toCharArray();

        int length = alphabetLower.length;
        for (int i = 0; i < length; i++) {
            cipher.put(alphabetLower[i], alphabetLower[(i + key) % length]);
            cipher.put(alphabetUpper[i], alphabetUpper[(i + key) % length]);
        }
        return cipher;
    }
}
