package top.certstone;

import javax.swing.*;
import java.awt.*;
import java.sql.Time;

/**
 * 自定义的消息渲染器，用于渲染消息列表
 * the class is used to render the message list
 */

public class MassageRenderer extends JPanel implements ListCellRenderer<Massage> {
    private JLabel usernameLabel;
    private JTextArea massageArea;

    public MassageRenderer() {
        setLayout(new BorderLayout(5, 5));
        usernameLabel = new JLabel();
        massageArea = new JTextArea();
        massageArea.setLineWrap(true);
        massageArea.setWrapStyleWord(true);
        massageArea.setOpaque(false);
        massageArea.setEditable(false);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(usernameLabel);

        add(topPanel, BorderLayout.NORTH);
        add(massageArea, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Massage> list, Massage msg, int index, boolean isSelected, boolean cellHasFocus) {

        usernameLabel.setText(msg.getSender() == null ? "[Server]" : msg.getSender().getName() + " - " + msg.time);
        massageArea.setText(msg.getContent());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
