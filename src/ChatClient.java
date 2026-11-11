import socketio.Socket;

import java.io.IOException;
import java.util.Scanner;

public class ChatClient {

    private String host = "localhost";
    private int port = 25565;
    private Socket cSocket;

    ChatClient() throws IOException {

        cSocket = new Socket(host, port);
    }

    void verbinden(){

        cSocket.connect();

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

            System.out.println("Server: " + antwort);
            System.out.println("--------------------------------------");
        }
    }

    boolean beenden(String beendenKeyword){

        try {
            cSocket.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    static void main() throws IOException {

        ChatClient cc = new ChatClient();

        cc.verbinden();
        cc.kommunizieren();
        cc.beenden("");

    }
}
