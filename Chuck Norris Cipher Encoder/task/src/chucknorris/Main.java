package chucknorris;
import java.util.Scanner;


public class Main {

    public static final int BLOCK_LENGTH = 7;

    public static String messageToBinary(String message) {
        String result = "";
        for (int i = 0; i < message.length(); ++i) {
            char ch = message.charAt(i);
            String binOrd = String.format("%7s", Integer.toBinaryString(ch)).replace(' ', '0');
            result += binOrd;
        }
        // System.out.println(result);
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

    public static String decode(String encoded) throws Exception {
        String result = "";
        char[] chars = encoded.toCharArray();
        for (char c: chars) {
            if (c != '0' && c != ' ') {
                throw new Exception("Encoded string is not valid.");
            }
        }
        String [] tokens = encoded.split("\\s");
        if (tokens.length % 2 != 0) {
            throw new Exception("Encoded string is not valid.");
        }
        for (int i = 0; i < tokens.length; i += 2) {
            if (tokens[i].length() == 1) {
                result += "1".repeat(tokens[i + 1].length());
            } else if (tokens[i].length() == 2) {
                result += "0".repeat(tokens[i + 1].length());
            } else {
                throw new Exception("Encoded string is not valid.");
            }
        }
        return result;
    }

    public static String binaryToMessage(String binString) throws Exception {
        String result = "";
        if (binString.length() % 7 != 0) {
            throw new Exception("Encoded string is not valid.");
        }
        for (int i = 0; i < binString.length(); i += BLOCK_LENGTH) {
            int ord = Integer.parseInt(binString.substring(i, i + BLOCK_LENGTH), 2);
            // System.out.println(ord);
            result += (char) ord;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String op = scanner.nextLine().strip();
            if ("encode".equals(op)) {
                System.out.println("Input string:");
                String s = scanner.nextLine();
                System.out.println("Encoded string:");
                System.out.println(encode(messageToBinary(s)));
            }  else if ("decode".equals(op)) {
                System.out.println("Input encoded string:");
                String s = scanner.nextLine();
                try {
                    String decoded = binaryToMessage(decode(s));
                    System.out.println("Decoded string:");
                    System.out.println(decoded);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

            } else if ("exit".equals(op)) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("There is no '" + op + "' operation");
            }

        }

    }
}