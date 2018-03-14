package chatroomDemo3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-12
 * Description:
 * <p>
 * -----------------------
 */
public class jdbcDemo {
    public static void main(String[] args) {
        try {
            Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatRoomInfo",
                    "root","@asd5211314");
            if (con == null){
                System.out.println("数据库连接失败");
                System.exit(-1);
            }

            //查找数据
            String sql;
            long time = 4;
            Statement statement = con.createStatement();
            sql = "SELECT * FROM info WHERE TIME > ? ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setLong(1,time);
            ResultSet resultSet = preparedStatement.executeQuery();
            time = 1520934399134L;
//            ResultSet resultSet = statement.executeQuery(sql);
            List list = new ArrayList();
            while (resultSet.next()){
                list.add(resultSet.getString(1));
                list.add(resultSet.getString(2));
            }
            for (int i = 0;i < list.size();i++){
                System.out.println(list.get(i));
            }

            System.out.println("下一波");
            preparedStatement.setLong(1,time);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(resultSet.getString(1));
                list.add(resultSet.getString(2));
            }
            for (int i = 0;i < list.size();i++){
                System.out.println(list.get(i));
            }


            //有变量名地插入数据
//            String info = "hello";
//            int no = 4;
//            sql = "insert into info(TIME ,DATA ) VALUES (?,?)";//有变量的不能直接进行执行操作
//
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setInt(1,no);
//            pst.setString(2,info);
//            int n = pst.executeUpdate();
//            if (n > 0) System.out.println("成功");


//            pst.executeUpdate();
//            Statement statement = con.createStatement();
//            statement.executeUpdate(sql);//执行普通更新语句

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){

        }
    }
}
