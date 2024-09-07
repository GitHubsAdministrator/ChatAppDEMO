package top.certstone;

import javax.swing.*;

public class chatAppClient {
    // 客户端入口
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new LoginGUI()
        );
    }
}
