/*
 * Created by JFormDesigner on Mon Aug 26 21:27:46 CST 2024
 */

package top.certstone;

import java.awt.*;
import javax.swing.*;

/**
 * @author CertStone
 */
public class ChatGUI extends JFrame implements Runnable{
    public ChatGUI(String ip, int port, String key, User user) {
        initComponents();

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        Nav = new JPanel();
        title = new JLabel();
        hSpacer1 = new JPanel(null);
        IOPanel = new JPanel();
        MessagePanel = new JScrollPane();
        messageList = new JList();
        InputPanel = new JPanel();
        functions = new JPanel();
        SendPanel = new JPanel();
        sendButton = new JButton();
        scrollPane3 = new JScrollPane();
        inputField = new JTextField();
        SidePanel = new JPanel();
        scrollPane2 = new JScrollPane();
        userList = new JList();

        //======== this ========
        setPreferredSize(new Dimension(750, 600));
        setTitle("Chatroom");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== Nav ========
        {
            Nav.setPreferredSize(new Dimension(88, 55));
            Nav.setLayout(new BorderLayout(3, 3));

            //---- title ----
            title.setText("Chatroom name");
            Nav.add(title, BorderLayout.CENTER);

            //---- hSpacer1 ----
            hSpacer1.setPreferredSize(new Dimension(20, 10));
            Nav.add(hSpacer1, BorderLayout.WEST);
        }
        contentPane.add(Nav, BorderLayout.NORTH);

        //======== IOPanel ========
        {
            IOPanel.setLayout(new BorderLayout());

            //======== MessagePanel ========
            {
                MessagePanel.setViewportView(messageList);
            }
            IOPanel.add(MessagePanel, BorderLayout.CENTER);

            //======== InputPanel ========
            {
                InputPanel.setLayout(new BorderLayout());

                //======== functions ========
                {
                    functions.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
                }
                InputPanel.add(functions, BorderLayout.NORTH);

                //======== SendPanel ========
                {
                    SendPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                    //---- sendButton ----
                    sendButton.setText("Send");
                    sendButton.setAlignmentY(-0.5F);
                    SendPanel.add(sendButton);
                }
                InputPanel.add(SendPanel, BorderLayout.SOUTH);

                //======== scrollPane3 ========
                {

                    //---- inputField ----
                    inputField.setPreferredSize(new Dimension(64, 175));
                    scrollPane3.setViewportView(inputField);
                }
                InputPanel.add(scrollPane3, BorderLayout.CENTER);
            }
            IOPanel.add(InputPanel, BorderLayout.SOUTH);
        }
        contentPane.add(IOPanel, BorderLayout.CENTER);

        //======== SidePanel ========
        {
            SidePanel.setPreferredSize(new Dimension(175, 45));
            SidePanel.setLayout(new GridBagLayout());
            ((GridBagLayout)SidePanel.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)SidePanel.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)SidePanel.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)SidePanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(userList);
            }
            SidePanel.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 1, 1));
        }
        contentPane.add(SidePanel, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel Nav;
    private JLabel title;
    private JPanel hSpacer1;
    private JPanel IOPanel;
    private JScrollPane MessagePanel;
    private JList messageList;
    private JPanel InputPanel;
    private JPanel functions;
    private JPanel SendPanel;
    private JButton sendButton;
    private JScrollPane scrollPane3;
    private JTextField inputField;
    private JPanel SidePanel;
    private JScrollPane scrollPane2;
    private JList userList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void run() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
