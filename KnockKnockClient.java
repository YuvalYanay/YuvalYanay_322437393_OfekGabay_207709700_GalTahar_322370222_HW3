package exe3KnockKnock;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class KnockKnockClient {

	public static void main(String[] args) throws IOException {

		Socket kkSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
        int port;

        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("Please enter your port: ");
            port = sc.nextInt();
            sc.nextLine();

            if (port == 4444 || port == 4445){
                break;
            }
            else {
                System.out.println("Invalid port!");
            }
        }


		try {
			kkSocket = new Socket("127.0.0.1", port);
			out = new PrintWriter(kkSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: your host.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: your host.");
			System.exit(1);
		}

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		String fromServer;
		String fromUser;

		while ((fromServer = in.readLine()) != null) {
			System.out.println("Server: " + fromServer);
			if (fromServer.equals("Bye."))
				break;


            String lowerServer = fromServer.toLowerCase();

            if (lowerServer.contains("registration completed") ||
                    lowerServer.contains("goodbye") ||
                    lowerServer.contains("information has been updated") ||
                    lowerServer.contains("remains the same")) {
                break;
            }

			fromUser = stdIn.readLine();
			if (fromUser != null) {
				System.out.println("Client: " + fromUser);
				out.println(fromUser);
			}
		}

		out.close();
		in.close();
		stdIn.close();
		kkSocket.close();
	}
}
