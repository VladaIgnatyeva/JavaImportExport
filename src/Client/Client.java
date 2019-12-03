package Client;
import Client.View.windowEntrance;

import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws ParseException, IOException{

        JFrame new_window = new windowEntrance();
        new_window.setVisible(true);

        Service service = Service.getInstance();

        Scanner consoleIn = new Scanner(System.in, "UTF-8");

        String request = consoleIn.nextLine();
        System.out.println(request);
        String answer = service.request(request, String.class);

        service.closeSocket();
        System.out.println(answer + "\n");

    }
}
