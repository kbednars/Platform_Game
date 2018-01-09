package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        super("Gra");
        setSize(640, 480);
        setResizable(false);
        setFocusable(true);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
