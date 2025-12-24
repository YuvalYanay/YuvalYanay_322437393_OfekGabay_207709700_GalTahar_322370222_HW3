package exe3KnockKnock;
import java.net.*;
import java.io.*;
import java.util.List;

public class RuppinServer {

    public static void main(String[] args) {

        System.out.println("Ruppin server is starting...");
        List<Client> clientState = java.util.Collections.synchronizedList(new java.util.ArrayList<>()); //In order to work with multiple threads
        int PORT2 = 4445;


        try(ServerSocket serverSocket = new ServerSocket(PORT2)) {

            while (true) {


                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, "RUPPIN", clientState)).start();
            }

        } catch (IOException e) {
            System.err.println("Error on port 4445: " + e.getMessage());
        }



    }
}
