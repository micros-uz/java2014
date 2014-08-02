package uz.micros;

import uz.micros.core.ChatManager;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(args.length);

        if (args.length > 0)
            System.out.println(args[0]);

        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();

        for (UIManager.LookAndFeelInfo lf : looks){
            System.out.println(lf.getName());
            if ("Metal".equals(lf.getName()))
                try {
                    UIManager.setLookAndFeel(lf.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
        }

        new ChatManager().start();

        System.out.println("uz.micros.Main Thread exit");
    }
}
