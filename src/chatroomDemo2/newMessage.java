package chatroomDemo2;

import java.util.Date;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-10
 * Description:
 * <p>客户端之间传递的信息类
 * time用来标记这条信息是否
 * 已经被读。
 * -----------------------
 */
public class newMessage {

    private String message;//信息流
    private Date date = new Date();
    long time;//时间标记

    public synchronized String getMessage() {
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message = message;
        time = date.getTime();
    }

}
