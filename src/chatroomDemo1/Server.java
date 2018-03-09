package chatroomDemo1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-9
 * Description:
 * <p>服务端：
 * 支持两个客户端同时连接，
 * 并且互相通信。
 * -----------------------
 */
public class Server extends Thread{
    private int port;
    private Socket socket1;
    private Socket socket2;
    public Server(){
        this.port = 2017;
    }

    @Override
    public void run() {
        try {
            System.out.println("服务端开启");
            ServerSocket serverSocket = new ServerSocket(port);
            socket1 = serverSocket.accept();
            System.out.println("服务端接收一个客户");
            BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            PrintWriter out1 = new PrintWriter(socket1.getOutputStream(),true);
            out1.println("等待对方上线");

            socket2 = serverSocket.accept();
            System.out.println("服务端接收另一个客户");
            BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            PrintWriter out2 = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()),true);
            out1.println("对象以上线，可以开始通话");
            out2.println("对方以上线，可以开始通话");

            new Thread(){
                @Override
                public void run() {
                    new printOut(socket1,socket2).start();
                    new printOut(socket2,socket1).start();
                }
            }.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}

/**
 * 提供向对方输出的方法类
 */
class printOut extends Thread{
    private Socket socket1;
    private Socket socket2;
    public printOut(Socket socket1,Socket socket2){
        this.socket1 = socket1;
        this.socket2 = socket2;
    }
    @Override
    public void run() {
        while (true){
            try {
                BufferedReader in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                PrintWriter out2 = new PrintWriter(new OutputStreamWriter(socket2.getOutputStream()),true);
                out2.println(in1.readLine());
                Thread.sleep(50);
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }catch (InterruptedException e){
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
}