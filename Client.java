import java.net.*;
import java.io.*;
public class Client {

    Socket socket;
    BufferedReader bf;
    PrintWriter out;
    public Client(){
        try{
            System.out.println("Sending request to the server");
            socket = new Socket("127.0.0.1",7777);
            System.out.println("Connection is done");

            bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void startReading(){
        Runnable t1 =()->{
            System.out.println("Reader started !");
            try{
            while(!socket.isClosed()){
                
                String msg = bf.readLine();
                if(msg.equals("exit")){
                    System.out.println("Server has terminated the chat");
                    socket.close();
                    break;
                }
                System.out.println("Server :"+msg);
                }
            }catch(Exception e){  //  e.printStackTrace();
                System.err.println("Connetion is closed");}
        };
        new Thread(t1).start();
    }
    public void startWriting(){
        Runnable t2 = ()->{
            System.out.println("Writer started");
            try{
            while(!socket.isClosed()){
               
                
                BufferedReader bf1 = new BufferedReader(new InputStreamReader(System.in));
                String content = bf1.readLine();
                out.println(content);
                out.flush();
                if(content.equals("exit")){
                    socket.close();
                    break;
                }

               }}catch(Exception e){
                 //  e.printStackTrace();
                 System.err.println("Connetion is closed");
               } 
            };

        new Thread(t2).start();
    }
    public static void main(String[] args) {
        System.out.println("This is client...");
        new Client();
    }
}
