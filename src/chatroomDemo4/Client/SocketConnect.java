package chatroomDemo4.Client;

import java.io.IOException;
import java.net.Socket;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-17
 * Description:
 * <p>
 * -----------------------
 */
public class SocketConnect {
    private Socket socket;
    private String address;
    private int port;
    public SocketConnect(){
        this.port = 2017;
        this.address = "localhost";
    }
    public Socket connect(){
        try {
            socket = new Socket(address,port);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (socket == null){
                System.out.println("网络连接失败");
            }
            return socket;
        }
    }
}
