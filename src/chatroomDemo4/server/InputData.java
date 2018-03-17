package chatroomDemo4.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Queue;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>从客户端向服务端输入数据
 * -----------------------
 */
public class InputData implements Runnable{
    private Socket socket;
    private Queue queue;//消息队列
    public InputData(Socket socket,Queue queue){
        this.socket = socket;
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                String data = in.readLine();
//                System.out.println("data: " + data);
                if (data != null){
                    queue.offer(data);//消息队列中存放数据模式为 data
                    System.out.println("InputData queue: " + queue.peek());
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
