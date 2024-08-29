/*
 * Created by JFormDesigner on Thu Aug 29 21:22:36 CST 2024
 */

package top.certstone;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author CertStone
 */
public class PrivateChatGUI extends JFrame {

    DefaultListModel<Massage> messageListModel = new DefaultListModel<>();
    UserServiceThread service;
    User selfUser;
    User targetUser;

    public PrivateChatGUI(UserServiceThread service, User targetUser) {
        initComponents();
        messageList.setCellRenderer(new MassageRenderer());
        inputField.setLineWrap(true);
        this.title.setText(targetUser.getName());
        this.service = service;
        this.selfUser = service.getUser();
        this.targetUser = targetUser;
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void send(ActionEvent e) {
        // the same as the send function in ChatGUI
        String content = inputField.getText();
        if (content.equals("")) {
            return;
        }
        Massage msg = new Massage(MassageType.TEXT, content, selfUser, targetUser, true);
        service.sendMsg(msg);
        inputField.setText("");
    }

    private void inputFieldEnterPressed(KeyEvent e) {
        // the same as the inputFieldEnterPressed function in ChatGUI
        if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
            send(null);
        }
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

        //======== this ========
        setPreferredSize(new Dimension(515, 580));
        setTitle("PrivateChat");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== Nav ========
        {
            Nav.setPreferredSize(new Dimension(88, 55));
            Nav.setLayout(new BorderLayout(3, 3));

            //---- title ----
            title.setText("Username");
            title.setFont(title.getFont().deriveFont(title.getFont().getStyle() & ~Font.BOLD, title.getFont().getSize() + 3f));
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
                    inputField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            inputFieldEnterPressed(e);
                        }
                    });
                    scrollPane3.setViewportView(inputField);
                }
                InputPanel.add(scrollPane3, BorderLayout.CENTER);
            }
            IOPanel.add(InputPanel, BorderLayout.SOUTH);
        }
        contentPane.add(IOPanel, BorderLayout.CENTER);
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public void addMessage(Massage msg) {
        messageListModel.addElement(msg);
        messageList.setModel(messageListModel);
        messageList.ensureIndexIsVisible(messageListModel.size() - 1);
    }

}
