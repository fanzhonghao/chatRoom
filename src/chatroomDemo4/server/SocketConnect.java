package chatroomDemo4.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>用于提供客户端与服务端
 * 进行连接的Socket
 * -----------------------
 */
public class SocketConnect {
    private ServerSocket serverSocket;
    private Queue InputQueue;//输入消息队列
    private Queue OutputQueue;//输出消息队列
    private int no;
    public SocketConnect(ServerSocket serverSocket,Queue InputQueue,Queue OutputQueue){
        this.serverSocket = serverSocket;
        this.InputQueue = InputQueue;
        this.OutputQueue = OutputQueue;
        no = 1;
    }
    public void socketConnect(){
        new Thread(new connect()).start();
    }
    class connect implements Runnable{
        Socket socket;
        @Override
        public void run() {
            new Thread(new getDataFromdb(OutputQueue)).start();//从数据库取出消息,不需要,只需一个
            new Thread(new PutInsertDataIndb(InputQueue)).start();//只需一个
            while (true){
                try {
                    Queue queue = new LinkedBlockingQueue();

                    socket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                    out.println("欢迎连接到服务端");
                    System.out.println("已连接客户端个数： " + no++);
                    new Thread(new InputData(socket,InputQueue)).start();//启动该socket的数据输入线程
                    new Thread(new OutputData(socket,queue)).start();//向客户端输出数据
                    new Thread(new CopyDataFromQueueToQueue(OutputQueue,queue)).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
