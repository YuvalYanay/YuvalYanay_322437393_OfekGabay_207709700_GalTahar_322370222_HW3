package exe3KnockKnock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{

    private final Socket clientSocket;
    private final String port;
    private List<Client> clients;

    public  ClientHandler(Socket socket, String port, List<Client> clientState){

        clientSocket = socket;
        this.port = port;
        clients = clientState;

    }

    @Override
    public void run() {



        try(
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader( new InputStreamReader( (clientSocket.getInputStream()) ) );
                ) {

            String inputLine, outputLine;


            //If the client is on port 4444
            if (port.equals("KNOCK")){


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

                //If the client is on port 4445
            } else {

                RuppinRegistrationProtocol rrp = new RuppinRegistrationProtocol(clients);
                outputLine = rrp.inputProcessing(null);
                out.println(outputLine);

                while ((inputLine = in.readLine()) != null) {
                    if (inputLine.equals("q"))  break;

                    outputLine = rrp.inputProcessing(inputLine);
                    out.println(outputLine);


                }
                out.close();
                in.close();
                clientSocket.close();

            }


        } catch (IOException e) {
            System.err.println("Error handling client " + clientSocket.getPort() + ": " + e.getMessage());
        }
        finally {

            try{
                clientSocket.close();

                System.out.println("Client disconnected: " + clientSocket.getPort());

            } catch (IOException e) {

                System.err.println("Error closing socket " + clientSocket.getPort() + ": " + e.getMessage());
            }

        }

    }


}
