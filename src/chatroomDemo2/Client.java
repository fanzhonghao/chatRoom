package chatroomDemo2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-10
 * Description:
 * <p>客户端，用于建立与服务
 * 端的连接和发送与接收信息
 * -----------------------
 */
public class Client extends Thread{
    private Socket socket;
    private int port;
    private String address;
    private String name;
    public Client(){
        port = 2017;
        address = "localhost";
    }

    @Override
    public void run() {
        try {
            System.out.println("请输入用户名:");
            Scanner in = new Scanner(System.in);
            name = in.nextLine();
            socket = new Socket(address,port);
            new input(socket,name).start();
            new output(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }

}
/*
向别的客户端输出
 */
class input extends Thread{
    private Socket socket;
    private String news;
    private String name;
    public input(Socket socket,String name){
        this.socket = socket;
        this.name = name;
    }
    private Scanner in = new Scanner(System.in);

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            while (true){
                news = in.nextLine();
                out.println(name + ": " + news);
                Thread.sleep(50);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
/*
从服务端获取信息
 */
class output extends Thread{
    private Socket socket;
    private String news;

    public output(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                news = in.readLine();
                if (news != null){
                    System.out.println(news);
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