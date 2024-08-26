/*
 * Created by JFormDesigner on Mon Aug 26 20:07:24 CST 2024
 */

package top.certstone;

import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.*;

/**
 * @author CertStone
 */
public class WarnMassage extends JDialog {
    public WarnMassage(Window owner, String text) {
        super(owner);
        initComponents();
        this.text.setText(text);
        this.okButton.addActionListener(e -> this.dispose());
        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label2 = new JLabel();
        vSpacer1 = new JPanel(null);
        label1 = new JLabel();
        text = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        setTitle("Warning");
        setPreferredSize(new Dimension(410, 200));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                    "insets 2,hidemode 3,align center center,gap 2 2",
                    // columns
                    "[fill]" +
                    "[fill]",
                    // rows
                    "[]" +
                    "[]" +
                    "[]"));
                contentPanel.add(label2, "cell 0 0");
                contentPanel.add(vSpacer1, "cell 0 0");

                //---- label1 ----
                label1.setIcon(new ImageIcon(getClass().getResource("/warning.png")));
                label1.setMinimumSize(new Dimension(10, 10));
                label1.setPreferredSize(new Dimension(160, 128));
                label1.setMaximumSize(new Dimension(158, 128));
                contentPanel.add(label1, "cell 0 2");
                contentPanel.add(text, "cell 1 2");
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[button,fill]",
                    // rows
                    null));

                //---- okButton ----
                okButton.setText("OK");
                buttonBar.add(okButton, "cell 0 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label2;
    private JPanel vSpacer1;
    private JLabel label1;
    private JLabel text;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on


}
