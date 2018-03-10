package chatroomDemo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-10
 * Description:
 * <p>用于服务端连接客户端
 * -----------------------
 */
public class serverConnect extends Thread {
    private ServerSocket serverSocket;
    private boolean connected;//判断是否已经连接
    private newMessage news;

    public serverConnect(ServerSocket serverSocket,newMessage news){
        this.serverSocket = serverSocket;
        this.connected = false;
        this.news = news;
    }
    @Override
    public void run() {
        try {

            Socket socket = serverSocket.accept();
            System.out.println("连接客户端");
            connected = true;
            PrintWriter out1 = new PrintWriter(socket.getOutputStream(),true);
            out1.println("连接至服务端");


            printInput in = new printInput(socket,news);
            in.start();

            printout out = new printout(socket,news);
            out.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}

/**
 * 本客户端向别的客户端输入
 */
class printInput extends Thread{
    private Socket socket;
    private newMessage message;
    public printInput(Socket socket,newMessage message){
        this.socket = socket;
        this.message = message;
        this.message.time = new Date().getTime();
    }



    @Override
    public void run() {
        while (true){
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String news = in.readLine();
                message.setMessage(news);
                if (news != null){
                    message.time = new Date().getTime();
                }
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
 * 其他客户端向本客户端输入
 */
class printout extends Thread{
    private Socket socket;
    private newMessage news;
    long time;
    public printout(Socket socket,newMessage news){
        this.socket = socket;
        this.news = news;
        time = new Date().getTime();
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                if (time < news.time){
                    out.println(news.getMessage());
                    time = news.time;
                }
                Thread.sleep(50);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}