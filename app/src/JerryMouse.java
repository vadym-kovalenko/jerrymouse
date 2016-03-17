import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vadim on 16.03.2016.
 */
public class JerryMouse {

    public static int DEFAULT_PORT = 8989;

    public static void main(String[] args) {
        JerryMouse jerryMouse = new JerryMouse(args);
        jerryMouse.start();
    }

    private final int port;
    private RequestProcessor requestProcessor;

    public JerryMouse(String[] args) {
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = DEFAULT_PORT;
        }
        requestProcessor = new RequestProcessor();
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {
                Socket client = serverSocket.accept();
                requestProcessor.processRequest(client.getInputStream(), client.getOutputStream());
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
