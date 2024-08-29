/*
 * Created by JFormDesigner on Mon Aug 26 21:27:46 CST 2024
 */

package top.certstone;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import javax.swing.border.*;
import com.jgoodies.forms.factories.*;

/**
 * @author CertStone
 */
public class ChatGUI extends JFrame {

    DefaultListModel<Massage> messageListModel = new DefaultListModel<>();
    UserServiceThread service;
    User user;

    public ChatGUI(UserServiceThread service, User user) {
        initComponents();
        messageList.setCellRenderer(new MassageRenderer());
        inputField.setLineWrap(true);

        this.service = service;
        this.user = user;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Function for the send button
    private void send(ActionEvent e) {
        // Get the content of the input box
        String content = inputField.getText();
        if (content.equals("")) {
            return;
        }
        // Send the message
        Massage msg = new Massage(MassageType.TEXT, content, user);
        service.sendMsg(msg);
        inputField.setText("");
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
        inputField = new JTextArea();
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
                InputPanel.setBorder(new TitledBorder("Input Text Here"));
                InputPanel.setMinimumSize(new Dimension(92, 100));
                InputPanel.setPreferredSize(new Dimension(92, 240));
                InputPanel.setLayout(new BorderLayout());

                //======== functions ========
                {
                    functions.setMinimumSize(new Dimension(20, 20));
                    functions.setPreferredSize(new Dimension(20, 33));
                    functions.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
                }
                InputPanel.add(functions, BorderLayout.NORTH);

                //======== SendPanel ========
                {
                    SendPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

                    //---- sendButton ----
                    sendButton.setText("Send");
                    sendButton.setAlignmentY(-0.5F);
                    sendButton.addActionListener(e -> send(e));
                    SendPanel.add(sendButton);
                }
                InputPanel.add(SendPanel, BorderLayout.SOUTH);

                //======== scrollPane3 ========
                {
                    scrollPane3.setBorder(new EmptyBorder(5, 5, 5, 5));

                    //---- inputField ----
                    inputField.setPreferredSize(new Dimension(64, 175));
                    inputField.setBorder(null);
                    inputField.setBackground(new Color(0x3c3f41));
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
    private JTextArea inputField;
    private JPanel SidePanel;
    private JScrollPane scrollPane2;
    private JList userList;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public void addMessage(Massage msg) {
        SwingUtilities.invokeLater(() -> {
            messageListModel.addElement(msg);
            messageList.setModel(messageListModel);
        });
    }

}
