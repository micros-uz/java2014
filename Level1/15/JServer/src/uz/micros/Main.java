package uz.micros;

public class Main {

    public static void main(String[] args) {
        //new Server().run();


        String name = "John";

        switch (name){
            case "Nick":
                System.out.println("NO");
                break;
            case "John":
                System.out.println("yes");
                break;
            default:
                System.out.println("NOT FOUND");
                break;
        }
    }
}
