import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Calculator v1.0 Copyright " + '\u00A9' + " 2014");

        double x, y;

        System.out.print("Enter the first number: ");
        Scanner scanner = new Scanner(System.in);
        String st = scanner.nextLine();

        x = Double.valueOf(st);

        System.out.print("Enter the second number: ");
        st = scanner.nextLine();
        y = Double.valueOf(st);


        System.out.print("Enter operation code: ");
        st = scanner.nextLine();

        char op = st.charAt(0);
        double summa = 0;

        if (op == '+'){
            summa = x + y;
        }

        if (op == '-'){
            summa = x - y;
        }



        System.out.println("Result = " + summa);
    }
}
