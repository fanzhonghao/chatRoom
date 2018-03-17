package chatroomDemo4.server;

import java.util.Date;
import java.util.Queue;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>
 * -----------------------
 */
public class PutInsertDataIndb implements Runnable {
    private dbOperate dboperate;
    private Queue queue;
    public PutInsertDataIndb(Queue queue){
        this.queue = queue;
        this.dboperate = new dbOperate();
    }

    @Override
    public void run() {
        dboperate.connect();
        while (true){
            while (!queue.isEmpty()){
                System.out.println("insert: " + queue.peek());
                dboperate.insert(new Date().getTime(),(String) queue.poll());
            }
        }
    }
}
