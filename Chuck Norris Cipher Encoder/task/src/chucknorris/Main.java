package chucknorris;
import java.util.Scanner;

public class Main {

    public static String messageToBinary(String message) {
        String result = "";
        for (int i = 0; i < message.length(); ++i) {
            char ch = message.charAt(i);
            int ord = (int) ch;
            String binOrd = String.format("%7s", Integer.toBinaryString(ord)).replace(' ', '0');
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String s = scanner.nextLine();
        System.out.println("The result:");
        System.out.println(encode(messageToBinary(s)));
    }
}