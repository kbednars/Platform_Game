package View;

import javax.swing.*;
import java.awt.*;

/**
 * Klasa tworzy główną ramkę aplikacji i umiejscawia ją w odpowiednim miejscu na ekranie.
 */
public class MainFrame extends JFrame{

    public MainFrame() {
        super("Gra");
        setSize(1040, 808);
        setResizable(false);
        setFocusable(true);
        getContentPane().setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
