package chatroomDemo3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
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
public class Server extends Thread {
    private ServerSocket serverSocket;
    private int port;
    private boolean connected;
    private int num;
    public Server(ServerSocket serverSocket){
        port = 2017;
        num = 1;

        connected = false;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        try {
            Socket socket = serverSocket.accept();
            System.out.println("连接客户端");
            connected = true;
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("连接至服务端");
            new storeInMysql(socket).start();
            new takeOutData(socket).start();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
/*
将数据插入到数据库中
 */
class storeInMysql extends Thread{
    private Socket socket;
    public storeInMysql(Socket socket){
        this.socket = socket;
    }

    Connection con;


    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatRoomInfo",
                    "fan","@asd5211314");
            if (con == null){
                System.out.println("数据库连接失败");
                System.exit(-1);
            }

            Date date = new Date();
//            Statement statement = con.createStatement();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data;
            PreparedStatement preparedStatement;
            String sql = "insert into info(time,data) values(?,?)";

            long time;

            while (true){
                data = in.readLine();
                if (data != null){

                    time = date.getTime();
                    System.out.println("time: " + time + " data: " + data);
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setLong(1,time);
                    preparedStatement.setString(2,data);
                    preparedStatement.executeUpdate();
                }
                Thread.sleep(50);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){

        }catch (IOException e){

        }catch (InterruptedException e){

        }
    }

}
/*
从数据库中拿出数据
 */
class takeOutData extends Thread{
    private Socket socket;
    private long time;
    public takeOutData(Socket socket){
        this.socket = socket;
        time = new Date().getTime();
    }
    Connection con;

    @Override
    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatRoomInfo",
                    "fan","@asd5211314");
            if (con == null){
                System.out.println("数据库连接失败");
                System.exit(-1);
            }
//            Statement statement = con.createStatement();
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);

            String sql = "SELECT * FROM info WHERE TIME > ? ";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            List list = new ArrayList();
            ResultSet resultSet;

            while (true){//当为time赋值的时候会出错
                preparedStatement.setLong(1,time);
                resultSet = preparedStatement.executeQuery();

                System.out.println("time1: " + time);
                time = new Date().getTime();
                System.out.println("time2: " + time);

                while (resultSet.next()){
//                    out.println("get");
                    list.add(resultSet.getString(1));
                    list.add(resultSet.getString(2));
//                    System.out.println("result: "+resultSet.getString(1));
                }

                for (int i = 0;i < list.size();i++){
                    if (i%2 == 1){
                        out.println(list.get(i));
//                        time = new Date().getTime();
                    }
                }
//                time = new Date().getTime();
                Thread.sleep(2000);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}