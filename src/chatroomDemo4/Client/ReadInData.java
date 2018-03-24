package chatroomDemo4.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-22
 * Description:
 * <p>
 * -----------------------
 */
public class ReadInData implements Runnable {
    private Socket socket;
    private KeepData keepData;
    public ReadInData(Socket socket, KeepData keepData){
        this.socket = socket;
        this.keepData = keepData;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                keepData.add(in.readLine());
                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
