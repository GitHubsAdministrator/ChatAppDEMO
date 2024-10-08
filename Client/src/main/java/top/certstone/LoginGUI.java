/*
 * Created by JFormDesigner on Mon Aug 26 17:36:04 CST 2024
 */

package top.certstone;

import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.Arrays;
import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import net.miginfocom.swing.*;

import static java.lang.Thread.sleep;

/**
 * @author CertStone
 * @version 1.0
 * 登录界面，用于获取服务器IP、端口、密钥、昵称，并建立连接
 * Login interface, used to get server IP, port, key, nickname, and establish a connection
 */
public class LoginGUI extends JFrame implements Runnable {
    public LoginGUI() {
        initComponents();

    }

    // Function for the connect button
    private void conn(ActionEvent e) {

        // Get the server IP, port, key, and nickname from the input box
        String serverip = ipAddr.getText();
        int port = Integer.parseInt(this.port.getText());
        String key = new String(keyText.getPassword());
        String name = this.name.getText();

        // validly create a user
        if (name.equals("")){
            new WarnMassage(this, "Nickname can't be empty!");
            return;
        } else if (name.length() > 20){
            new WarnMassage(this, "Nickname can't be longer than 20 characters!");
            return;
        } else if ( !name.matches("^[a-zA-Z0-9_]+$")){
            new WarnMassage(this, "Nickname can't contain special characters!");
            return;
        }

        if (key.equals("")){
            key = null;
        }

        // Connect to the server
        try {
            Socket socket = new Socket(serverip, port);
            User user = new User(name);
            UserServiceThread userServiceThread = new UserServiceThread(socket, user, key);
            userServiceThread.start();

            this.dispose(); // Close the current window
        } catch (Exception ex) {
            new WarnMassage(this, "Connection failed!");
            ex.printStackTrace();
        }


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        ipAddr = new JTextField();
        label2 = new JLabel();
        port = new JTextField();
        label3 = new JLabel();
        keyText = new JPasswordField();
        label4 = new JLabel();
        name = new JTextField();
        conn = new JButton();

        //======== this ========
        setTitle("ChatAppDEMO Launcher");
        setPreferredSize(new Dimension(350, 236));
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "insets 2,hidemode 3,align center center,gap 5 10",
            // columns
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText(" Server IP");
        contentPane.add(label1, "cell 0 0");

        //---- ipAddr ----
        ipAddr.setText("certstone.top");
        contentPane.add(ipAddr, "cell 1 0,width 100");

        //---- label2 ----
        label2.setText(" Port");
        contentPane.add(label2, "cell 0 1");

        //---- port ----
        port.setText("61000");
        contentPane.add(port, "cell 1 1");

        //---- label3 ----
        label3.setText(" Key (Optional)");
        contentPane.add(label3, "cell 0 2");
        contentPane.add(keyText, "cell 1 2");

        //---- label4 ----
        label4.setText(" Nickname");
        contentPane.add(label4, "cell 0 3");

        //---- name ----
        name.setText("momo");
        contentPane.add(name, "cell 1 3");

        //---- conn ----
        conn.setText("Connect");
        conn.addActionListener(e -> conn(e));
        contentPane.add(conn, "cell 0 4 2 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField ipAddr;
    private JLabel label2;
    private JTextField port;
    private JLabel label3;
    private JPasswordField keyText;
    private JLabel label4;
    private JTextField name;
    private JButton conn;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void run(){
        FlatDarkLaf.setup();
        LoginGUI gui = new LoginGUI();
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
