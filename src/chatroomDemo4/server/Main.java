package chatroomDemo4.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>提供调度功能
 * -----------------------
 */
public class Main {
    private Queue InputQueue;//输入消息队列
    private Queue OutputQueue;//输出消息队列
    private ServerSocket serverSocket;
    private int port;

    public Main() {
        this.InputQueue = new LinkedBlockingQueue();
        this.OutputQueue = new LinkedBlockingQueue();
        port = 2017;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("服务端开启失败");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        SocketConnect s = new SocketConnect(m.serverSocket,m.InputQueue,m.OutputQueue);
        s.socketConnect();

    }
}
