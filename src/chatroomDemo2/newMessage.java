package chatroomDemo2;

import java.util.Date;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-10
 * Description:
 * <p>
 * -----------------------
 */
public class newMessage {

    private String message;
    private Date date = new Date();
    long time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        time = date.getTime();
    }

}
