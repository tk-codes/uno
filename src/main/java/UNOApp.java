import ui.AppWindow;

import javax.swing.*;

public class UNOApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppWindow::new);
    }
}
