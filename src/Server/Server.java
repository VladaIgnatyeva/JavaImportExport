package Server;

import java.io.*;
import java.net.*;
import java.sql.Statement;

public class Server {

    public static void main(String[] args) {


        Server server = new Server();
        server.start(8000);
    }

    private int clientCounter = 0;
    ServerSocket serverSocket;

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Can't start the server on port " + port);
            System.exit(-1);
        }
        while (true) {
            Socket clientSocket;
            try {
                System.out.print("Waiting for a client...");
                clientSocket = serverSocket.accept();

                System.out.println("Client #" + (++clientCounter) + " connected ("
                        + clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort() + ")");

                new Thread(new ClientThread(clientSocket, clientCounter)).start();
            } catch (IOException e) {
                System.out.println("Can't accept client!");
                System.exit(-1);
            }
        }

    }


}
