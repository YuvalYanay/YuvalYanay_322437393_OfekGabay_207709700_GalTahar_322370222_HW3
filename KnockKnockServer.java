package exe3KnockKnock;
import java.net.*;
import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class KnockKnockServer {
    public static void main(String[] args) throws IOException {

        System.out.println("KnockKnock server is starting...");
//        List<Client> clientState = java.util.Collections.synchronizedList(new java.util.ArrayList<>()); //In order to work with multiple threads
        int PORT1 = 4444;

        //Running threads for ports

            try(ServerSocket serverSocket = new ServerSocket(PORT1)) {

                while (true) {

                    Socket socket = serverSocket.accept();
                    new Thread(new ClientHandler(socket,"KNOCK", null)).start();

                }

            } catch (IOException e) {

                System.err.println("Error on port 4444: " + e.getMessage());

            }



//        new Thread(() -> {
//
//            try(ServerSocket serverSocket = new ServerSocket(PORT2)) {
//
//                while (true) {
//
//                    Socket socket = serverSocket.accept();
//                    new Thread(new ClientHandler(socket,"RUPPIN", clientState)).start();
//
//                }
//
//            } catch (IOException e) {
//
//                System.err.println("Error on port 4445: " + e.getMessage());
//
//            }
//
//        }).start();

//        try {
//            serverSocket = new ServerSocket(PORT1);
//            System.out.println("Server is listening on port " + PORT1);
//
//            while (true){
//
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("New client connected - port: " + clientSocket.getPort());
//                Runnable task = new ClientHandler(clientSocket);
//                Thread thread = new Thread(task);
//                thread.start();
//
//            }

//        } catch (IOException e) {
//
//            System.err.println("Could not listen on port: 4444.");
//            System.exit(1);
//
//        }
        
	

/*while(true)
	{
        Socket clientSocket = null;
        try {

            clientSocket = serverSocket.accept();
            Runnable task = new ClientHandler(clientSocket);
            Thread thread = new Thread(task);
            thread.start();

        } catch (IOException e) {

            System.err.println("Accept failed.");
            System.exit(1);

        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
        String inputLine, outputLine;
        KnockKnockProtocol kkp = new KnockKnockProtocol();

        outputLine = kkp.processInput(null);
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
		if (inputLine.equals("q"))  break;
             outputLine = kkp.processInput(inputLine);
             out.println(outputLine);

        }
        out.close();
        in.close();
        clientSocket.close();
	} */
    }
}
