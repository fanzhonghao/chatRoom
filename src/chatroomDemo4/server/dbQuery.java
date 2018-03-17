package chatroomDemo4.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-16
 * Description:
 * <p>数据库查询
 * -----------------------
 */
public class dbQuery {
    private Connection con;
    public dbQuery(Connection con){
        this.con = con;
    }
    public ResultSet query(long time){
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        String sql = "select * from info where time > ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1,time);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
