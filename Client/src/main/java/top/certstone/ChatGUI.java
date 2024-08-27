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
    public ChatGUI(String ip, int port, String key, String name) {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label1 = new JLabel();
        hSpacer1 = new JPanel(null);
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        textField1 = new JTextField();
        panel3 = new JPanel();
        scrollPane2 = new JScrollPane();
        list2 = new JList();

        //======== this ========
        setPreferredSize(new Dimension(750, 600));
        setTitle("Chatroom");
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel1 ========
        {
            panel1.setPreferredSize(new Dimension(88, 55));
            panel1.setLayout(new BorderLayout(3, 3));

            //---- label1 ----
            label1.setText("Chatroom name");
            panel1.add(label1, BorderLayout.CENTER);

            //---- hSpacer1 ----
            hSpacer1.setPreferredSize(new Dimension(20, 10));
            panel1.add(hSpacer1, BorderLayout.WEST);
        }
        contentPane.add(panel1, BorderLayout.NORTH);

        //======== panel2 ========
        {
            panel2.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(list1);
            }
            panel2.add(scrollPane1, BorderLayout.CENTER);

            //---- textField1 ----
            textField1.setPreferredSize(new Dimension(64, 175));
            panel2.add(textField1, BorderLayout.SOUTH);
        }
        contentPane.add(panel2, BorderLayout.CENTER);

        //======== panel3 ========
        {
            panel3.setPreferredSize(new Dimension(175, 45));
            panel3.setLayout(new GridBagLayout());
            ((GridBagLayout)panel3.getLayout()).columnWidths = new int[] {0, 0};
            ((GridBagLayout)panel3.getLayout()).rowHeights = new int[] {0, 0, 0};
            ((GridBagLayout)panel3.getLayout()).columnWeights = new double[] {0.0, 1.0E-4};
            ((GridBagLayout)panel3.getLayout()).rowWeights = new double[] {0.0, 0.0, 1.0E-4};

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(list2);
            }
            panel3.add(scrollPane2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 1, 1));
        }
        contentPane.add(panel3, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label1;
    private JPanel hSpacer1;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JList list1;
    private JTextField textField1;
    private JPanel panel3;
    private JScrollPane scrollPane2;
    private JList list2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    @Override
    public void run() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
