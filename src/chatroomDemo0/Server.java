package chatroomDemo0;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-9
 * Description:
 * <p>服务端：
 * 只支持一个客户端与服务端通话
 * -----------------------
 */
public class Server implements Runnable {
    private int port;
    private Server(){this.port = 2017;}
    public void run(){
        try {
            ServerSocket server = new ServerSocket(port);
            Socket connection;
            connection = server.accept();
            System.out.println("Welcome");

            BufferedReader in =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println(in.readLine());
            PrintWriter out = new PrintWriter(connection.getOutputStream(),true);
            out.println("connection");
            Scanner enter = new Scanner(System.in);

            while (true){
                String input,output;
                input = in.readLine();
                System.out.println(input);
                if (input.equals(".")) break;
                output = enter.nextLine();
                if(output.equals("exit")) break;
                out.println(output);
            }

            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }


}
