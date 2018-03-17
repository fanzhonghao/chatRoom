package chatroomDemo4.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>
 * -----------------------
 */
public class dbInsert {
    private Connection con;
    public dbInsert(Connection con){
        this.con = con;
    }
    public void insert(long time,String data){
        String sql = "insert into info values(?,?)";
        try {
            if (data == null) return;
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1,time);
            preparedStatement.setString(2,data);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
