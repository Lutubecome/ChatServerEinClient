import socketio.ServerSocket;
import socketio.Socket;

import java.io.IOException;
import java.util.Scanner;

public class ChatServer {

    private int port = 25565;
    private ServerSocket sSocket;
    private Socket cSocket;
    private int clientNr;

    ChatServer() throws IOException {

        sSocket = new ServerSocket(port);

        cSocket = sSocket.accept();
    }

    void kommunizieren() throws IOException {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("\nDu: ");
            String nachricht = sc.nextLine();

            cSocket.write(nachricht + "\n");

            if (nachricht.endsWith("over")) {
                System.out.println("\nVerbindung wird beendet...");
                break;
            }

            String antwort = cSocket.readLine();

            System.out.println("Client: " + antwort);
            System.out.println("--------------------------------------");
        }

    }

    void beenden() throws IOException {

        cSocket.close();
        sSocket.close();

    }

    static void main() throws IOException {

        ChatServer cs = new ChatServer();

        cs.kommunizieren();

        cs.beenden();

    }

}
