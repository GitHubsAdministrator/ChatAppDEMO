package top.certstone;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义的列表渲染器，用于渲染用户列表
 * the class is used to render the user list
 */

class CustomListCellRenderer extends JLabel implements ListCellRenderer<User> {
    public CustomListCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {
        setText(value.toString());

        // 将cell的高度设置为两倍的字体高度
        setPreferredSize(new Dimension(list.getWidth(), (int) (list.getFontMetrics(list.getFont()).getHeight() * 2)));

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