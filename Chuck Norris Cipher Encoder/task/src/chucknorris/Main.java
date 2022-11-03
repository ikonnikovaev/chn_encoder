package chucknorris;
import java.util.Scanner;

public class Main {

    public static final int BLOCK_LENGTH = 7;

    public static String messageToBinary(String message) {
        String result = "";
        for (int i = 0; i < message.length(); ++i) {
            char ch = message.charAt(i);
            String binOrd = String.format("%BLOCK_LENGTHs", Integer.toBinaryString(ch)).replace(' ', '0');
            result += binOrd;
        }
        return result;
    }

    public static String encode(String binString) {
        String result = "";
        for (int i = 0; i < binString.length(); ++i) {
            if (i == 0 || binString.charAt(i) != binString.charAt(i - 1)) {
                if (i != 0) {
                    result += " ";
                }
                if (binString.charAt(i) == '1') {
                    result += "0 0";
                } else {
                    result += "00 0";
                }
            } else {
                result += "0";
            }
        }
        return result;
    }

    public static String decode(String encoded) {
        String result = "";
        String [] tokens = encoded.split("\\s");
        for (int i = 0; i < tokens.length; i += 2) {
            if (tokens[i].length() == 1) {
                result += "1".repeat(tokens[i + 1].length());
            } else {
                result += "0".repeat(tokens[i + 1].length());
            }
        }
        return result;
    }

    public static String binaryToMessage(String binString) {
        String result = "";

        for (int i = 0; i < binString.length(); i += BLOCK_LENGTH) {
            int ord = Integer.parseInt(binString.substring(i, i + BLOCK_LENGTH), 2);
            // System.out.println(ord);
            result += (char) ord;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String s = scanner.nextLine();
        // System.out.println(decode(s));
        System.out.println("The result:");
        System.out.println(binaryToMessage(decode(s)));
        // System.out.println(encode(messageToBinary(s)));
    }
}