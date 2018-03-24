package chatroomDemo4.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.Scanner;

/**
 * ----------------------
 *
 * @Author:fan
 * @Date: 18-3-22
 * Description:
 * <p>
 * -----------------------
 */
public class Panel {
    private String name;
    public static void main(String[] args) {
        KeepData keepData = new KeepData();
        Panel panel = new Panel();
        Scanner in = new Scanner(System.in);
        System.out.println("请输入姓名：");
        while (panel.getName() == null){
            panel.name = in.nextLine();
        }
        System.out.println(panel.name);
        int i = 0;
        Frame f = new Frame("在线聊天室");
        JTextArea j1 = new JTextArea(2,15);
        j1.setSize(400,100);
        j1.setBackground(f.getBackground());
        j1.setEditable(false);
        f.add(j1);

        JScrollPane jScrollPane = new JScrollPane();

        JTextArea jTextArea = new JTextArea(20,6);
        jTextArea.setBackground(Color.white);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        jTextArea.setText(null);

        jScrollPane.setViewportView(jTextArea);

        jScrollPane.setPreferredSize(new Dimension(400,300));
        f.add(jScrollPane);

        JTextArea j2 = new JTextArea(1,50);
        j2.setSize(400,100);
        j2.setBackground(f.getBackground());
        j2.setVisible(true);
        j2.setEditable(false);
        f.add(j2);

        JTextArea j = new JTextArea(2,50);
        j.setBackground(Color.white);
        j.setLineWrap(true);
        j.setAutoscrolls(true);
        j.setSize(100,20);
        j.requestFocus();//自动聚焦

        f.add(j);

        f.setLayout(new FlowLayout());

        Button b = new Button("发送");
        b.setBounds(500,400,30,20);
        f.add(b);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Window w = e.getWindow();
                w.setVisible(false);
                w.dispose();
            }
        });

        SocketConnect socketConnect = new SocketConnect();
        Socket socket = socketConnect.connect();

        b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (j.getText() != null){
                    OutputData outputData = new OutputData(socket,panel.getName());
                    outputData.printData(j.getText());
                    jTextArea.append(panel.getName() + ": " + j.getText() + "\n");
                    j.setText(null);
                }

            }
        });

        f.setSize(460,450);
        f.setVisible(true);
        f.setResizable(false);

        new Thread(new ReadInData(socket,keepData)).start();

        new Thread(){//向面板输出信息
            @Override
            public void run() {
                String data;
                while (true){
                  while (keepData.size() > 0){
                      data = keepData.get();
                      if (data != null){

                          if (data.matches("^" + panel.getName() + ".*"))
                              continue;

                          jTextArea.append(data + "\n");
                      }
                  }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
