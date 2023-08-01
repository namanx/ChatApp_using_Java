import java.net.*;
import java.io.*;

public class Server {

    ServerSocket server;
    Socket socket;

    BufferedReader bf;
    PrintWriter out;

    // Constructor
    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept the connection");
            System.out.println("Server is waiting for the connection");
            socket = server.accept();
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        } catch (Exception e) {
        }

    }

    public void startReading() {
        Runnable t1 = () -> {
            System.out.println("Reader started !");
            try {
                while (!socket.isClosed()) {

                    String msg = bf.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Client has terminated the chat");
                        break;
                    }
                    System.out.println("Client :" + msg);
                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.err.println("Connetion is closed");
            }
        };

        new Thread(t1).start();
    }

    public void startWriting() {
        Runnable t2 = () -> {
            System.out.println("Writer started");
            try {
                while (!socket.isClosed()) {

                    BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = bf1.readLine();

                    out.println(content);
                    out.flush();

                    if (content.equals("exit")) {
                        socket.close();
                        break;
                    }

                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.err.println("Connetion is closed");
            }
        };
        new Thread(t2).start();
    }

    public static void main(String[] args) {
        System.out.println("This is server.. Server is going to start");
        new Server();
    }
}