package chatroomDemo1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-9
 * Description:
 * <p>
 * -----------------------
 */
public class Client extends Thread {
    private Socket socket;
    private int port;
    private String serverAddress;
    public Client(){
        this.port = 2017;
        this.serverAddress = "localhost";
    }

    @Override
    public void run() {
        try {
            socket = new Socket(serverAddress,port);

            new printOut1(socket).start();
            new printIn(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
        System.out.println("客户端启动");
    }
}
class printOut1 extends Thread{
    Socket socket;
    public printOut1(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        while (true){
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                Scanner input = new Scanner(System.in);
                out.println(input.nextLine());
                Thread.sleep(50);
            } catch (IOException e) {
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}

/**
 * 输入类
 */
class printIn extends Thread{
    Socket socket;
    public printIn(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println(in.readLine());

                Thread.sleep(50);
            } catch (IOException e) {
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}