package chatroomDemo4.server;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>提供对数据库进行插入和
 * 查询操作
 * -----------------------
 */
public class dbOperate {
    private Connection con;
    public void connect(){
        con = new dbConnect().connection();
    }
    public void insert(long time,String data){
        new dbInsert(con).insert(time,data);
    }
    public ResultSet query(long time){
        return new dbQuery(con).query(time);
    }
}
