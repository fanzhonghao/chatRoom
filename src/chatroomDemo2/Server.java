package chatroomDemo2;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-10
 * Description:
 * <p>服务端：
 * 用于创建多个客户端连接线程
 * -----------------------
 */
public class Server extends Thread{
    private ServerSocket serverSocket;
    public newMessage news = new newMessage();
    private int port;
    private int no;
    public Server(){
        this.port = 2017;
        no = 1;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                serverConnect s = new serverConnect(serverSocket,news);
                s.start();
                while (!s.isConnected()){//等待连接
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("连接数： " + (no++));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
