package chatroomDemo4.server;

import java.util.Queue;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-24
 * Description:
 * <p>
 * -----------------------
 */
public class CopyDataFromQueueToQueue implements Runnable {
    private Queue queue1,queue2;
    public CopyDataFromQueueToQueue(Queue queue1,Queue queue2){
        this.queue1 = queue1;
        this.queue2 = queue2;
    }

    @Override
    public void run() {
        while (true){
            queue2.addAll(queue1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}