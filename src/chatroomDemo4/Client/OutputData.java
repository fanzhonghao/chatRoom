package chatroomDemo4.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-17
 * Description:
 * <p>向其它客户端输出数据
 * -----------------------
 */
public class OutputData implements Runnable{
    private Socket socket;
    private String name;//客户端名字
    public OutputData(Socket socket,String name){
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            Scanner in = new Scanner(System.in);
            String data;
            while (true){
                data = in.nextLine();
                if (data != null){
                    out.println(name + ": " + data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
