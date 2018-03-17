package chatroomDemo4.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>向客户端输出数据,消息
 * 队列用来存放将要输出的数据.
 * 此消息队列中不保存时间。
 * -----------------------
 */
public class OutputData implements Runnable{
    private Socket socket;
    private Queue queue;//消息队列
    public OutputData(Socket socket,Queue queue){
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            String data;
            while (true){
                while (!queue.isEmpty()){
                    data = (String) queue.poll();
                    out.println(data);
                }
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
