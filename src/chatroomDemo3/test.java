package chatroomDemo3;

import chatroomDemo2.newMessage;
import chatroomDemo2.serverConnect;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-12
 * Description:
 * <p>
 * -----------------------
 */
public class test extends Thread{
    private ServerSocket serverSocket;
    private int port;
    private int no;
    public test(){
        this.port = 2017;
        no = 1;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Server s = new Server(serverSocket);
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
        new test().start();
    }
}
