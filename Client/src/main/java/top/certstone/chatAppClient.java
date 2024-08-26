package top.certstone;

import javax.swing.*;

public class chatAppClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new LoginGUI()
        );
    }
}
