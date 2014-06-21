import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        print("Calculator v1.0 Copyright " + '\u00A9' + " 2014");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            double x = getNumber("Enter the first number: ");
            double y = getNumber("Enter the second number: ");

            print("Enter operation code: ");

            String st = scanner.nextLine();

            if (st.length() == 0) break;

            char op = st.charAt(0);

            if (op != '+' && op != '-' && op != '*' && op != '/') break;

            print("Result = " + calc(x, y, op));
        }
    }

    private static double calc(double x, double y, char op) {
        double summa;
        switch(op){
            case '+':
                summa = x + y;
                break;
            case '-':
                summa = x - y;
                break;
            case '*':
                summa = x * y;
                break;
            case '/':
                summa = x / y;
                break;
            default:
                summa = 0;
                break;
        }
        return summa;
    }

    private static void print(String st) {
        System.out.println(st);
    }

    private static double getNumber(String st){
        print(st);
        Scanner scanner = new Scanner(System.in);
        st = scanner.nextLine();

        return Double.valueOf(st);
    }
}
