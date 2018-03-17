package chatroomDemo4.server;


import java.sql.ResultSet;
import java.sql.SQLException;
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
public class getDataFromdb implements Runnable{
    private Queue queue;
    private dbOperate dboperate;
    private long time;
    public getDataFromdb(Queue queue){
        this.queue = queue;
        this.time = new Date().getTime();
        this.dboperate = new dbOperate();
    }

    @Override
    public void run() {
        dboperate.connect();
        try {
            while (true){
                ResultSet resultSet = dboperate.query(time);
                if (resultSet.next()){
                    try {
                        queue.add(resultSet.getString(2));
                        time = resultSet.getLong(1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                Thread.sleep(1000);
                queue.clear();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }


    }
}
