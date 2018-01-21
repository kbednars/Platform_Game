package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Klasa odpowiedzialan za tworzenie panelu, na którym wyświetlane jest menu użytkownika.
 */
public class Menu extends JPanel{
    private View view;
    private BufferedImage startButton, menuButton, tryAgainButtton;
    private BufferedImage imageBack, title, lose, win;
    private JButton start, returnToMenu, tryAgain;

    /**
     * Konstruktor tworzy panel, przyciski oraz tworzy obiekty klasy Image.
     * @param view {@link View}
     */
    Menu (View view) {
        setLayout(null);
        this.view = view;
        setBounds(0, 0, 1024, 768);
        start = new JButton();
        returnToMenu = new JButton();
        tryAgain = new JButton();
        start.addMouseListener(new MouseAdapter());
        returnToMenu.addMouseListener(new MouseAdapter());
        tryAgain.addMouseListener(new MouseAdapter());
        try {
            win = ImageIO.read(getClass().getResourceAsStream("/win.png"));
            lose = ImageIO.read(getClass().getResourceAsStream("/lose.png"));
            title = ImageIO.read(getClass().getResourceAsStream("/mrblue.png"));
            startButton = ImageIO.read(getClass().getResourceAsStream("/startButton.png"));
            menuButton = ImageIO.read(getClass().getResourceAsStream("/returnMenu.png"));
            tryAgainButtton = ImageIO.read(getClass().getResourceAsStream("/tryAgain.png"));
            imageBack = ImageIO.read(getClass().getResourceAsStream("/blue_land.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        start.setIcon(new ImageIcon(startButton));
        returnToMenu.setIcon(new ImageIcon(menuButton));
        tryAgain.setIcon(new ImageIcon(tryAgainButtton));
        start.setBounds(417,400, start.getIcon().getIconWidth(), start.getIcon().getIconHeight());
        returnToMenu.setBounds(417,400, returnToMenu.getIcon().getIconWidth(), returnToMenu.getIcon().getIconHeight());
        tryAgain.setBounds(417,400, tryAgain.getIcon().getIconWidth(), tryAgain.getIcon().getIconHeight());
    }

    /**
     * Zmienia aktualnie wyświetlane menu.
     */
    void uiUpdate(){
        if(view.controller.isMenu()){
            removeAll();
            add(start);
        }
        else if(view.controller.isEndGame()){
            removeAll();
            add(returnToMenu);
        }
        else if(view.controller.isPlayerLose()){
            removeAll();
            add(tryAgain);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imageBack, null, 0, 0);
        if(view.controller.isMenu()){
            g2d.drawImage(title,null, 290,200);
        }else if(view.controller.isPlayerLose()){
            g2d.drawImage(lose,null, 270, 200);
        }else if(view.controller.isEndGame()){
            g2d.drawImage(win, null, 290,200);
        }
    }

    /**
     * Klasa implementująca słuchacza zdarzeń myszki.
     */
    class MouseAdapter implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) { }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource() == start){
                view.controller.setGame(true);
            }
            else if(e.getSource() == returnToMenu){
                view.controller.setMenu(true);
                view.controller.setEndGame(false);
            }
            else if(e.getSource() == tryAgain){
                view.controller.setGame(true);
                view.controller.setPlayerLose(false);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }


}
