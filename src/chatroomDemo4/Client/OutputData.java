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
public class OutputData{
    private Socket socket;
    private String name;//客户端名字
    private String data;
    public OutputData(Socket socket,String name){
        this.socket = socket;
        this.name = name;
    }
    public void printData(String data){
        this.data = name + ": " + data;
        outData();
    }

    public void outData() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(data);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}

