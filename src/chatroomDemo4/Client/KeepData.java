package chatroomDemo4.Client;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-22
 * Description:
 * <p>
 * -----------------------
 */
public class KeepData {
    private Queue queue = new LinkedBlockingQueue();
    public synchronized void add(String data){
        queue.offer(data);
    }
    public synchronized String get(){
//        if (queue.size() > 0)
//            return (String) queue.peek();
//        else return null;
        return (String) queue.poll();
    }
    public int size(){
        return queue.size();
    }
}
