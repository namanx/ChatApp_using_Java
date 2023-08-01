import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Server extends JFrame{

    ServerSocket server;
    Socket socket;

    BufferedReader bf;
    PrintWriter out;

    // declare the components
    private JLabel heading = new JLabel("Client Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    private Font font = new Font("Roboto", Font.PLAIN, 20);

    // Constructor
    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept the connection");
            System.out.println("Server is waiting for the connection");
            socket = server.accept();
            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            CreateGui();
            HandleEvents();
            startReading();
            // startWriting();
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
                        JOptionPane.showMessageDialog(this,"Client Terminated");
                        messageInput.setEnabled(false);
                        break;
                    }
                    messageArea.append("Client :" + msg + "\n");
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

    public void CreateGui() {
        // gui code

        this.setTitle("Server Messanger[END]");
        this.setSize(600, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);

        // heading attributes
        heading.setIcon(new ImageIcon("clogo.png"));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messageArea.setEditable(false);
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);

        // setting the frame of layout
        this.setLayout(new BorderLayout());

        // adding the components to the frame
        this.add(heading, BorderLayout.NORTH);
        JScrollPane jScrollPane = new JScrollPane(messageArea);
        this.add(jScrollPane, BorderLayout.CENTER);
        this.add(messageInput, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * 
     */
    public void HandleEvents() {
        messageInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                if (e.getKeyCode() == 10) {
                    String ContentToSend = messageInput.getText();
                    messageArea.append("Server(My End) : " + ContentToSend + "\n");
                    out.println(ContentToSend);
                    out.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();
                }

            }

        });
    }

    public static void main(String[] args) {
        System.out.println("This is server.. Server is going to start");
        new Server();
    }
}