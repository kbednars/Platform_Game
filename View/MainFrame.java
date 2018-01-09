package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public MainFrame() {
        super("Gra");
        setSize(1152, 864);
        setResizable(false);
        setFocusable(true);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
