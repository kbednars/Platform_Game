package View;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Klasa odpowiedzialna za: tworzenie panelu, na którym rysowane będą wszystkie obiekty,
 * które znajdują się aktualnie na mapie, a także za ich renderowanie.
 */
public class MainBoard extends JPanel {
    boolean paused;
    int[] playerInfo;
    LinkedList<int[]> infoMap;
    LinkedList<int[]> infoObjects;
    private BufferedImage imagePlayer, imageEnemy, imageBrick, imageBack, imageHealth, imageDoor;


    /**
     * Konstruktor tworzy panel oraz tworzy obiekty klasy Image.
     */
    public MainBoard() {
        playerInfo = new int[8];
        setBounds(0, 0, 1024, 768);
        try {
            imagePlayer = ImageIO.read(getClass().getResourceAsStream("/alienBlue_front.png"));
            imageEnemy = ImageIO.read(getClass().getResourceAsStream("/slimeBlock.png"));
            imageBrick = ImageIO.read(getClass().getResourceAsStream("/grassMid.png"));
            imageBack = ImageIO.read(getClass().getResourceAsStream("/blue_shroom.png"));
            imageHealth = ImageIO.read(getClass().getResourceAsStream("/hudHeart_full.png"));
            imageDoor = ImageIO.read(getClass().getResourceAsStream("/doorOpen_mid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    /**
     * Metoda, która wywołuje się po użyciu metody repaint()
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Metoda odpowiedzialna za zrenderowanie całego panelu.
     * @param g
     */
    private synchronized void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imageBack, null, 0, 0);
        for (int i = 0; i < infoMap.size(); i++) {
            if (infoMap.get(i)[4] == 1) {
                g2d.drawImage(imageBrick, infoMap.get(i)[0], infoMap.get(i)[1], infoMap.get(i)[2], infoMap.get(i)[3], null);
            } else if (infoMap.get(i)[4] == 3) {
                g2d.drawImage(imageDoor, infoMap.get(i)[0], infoMap.get(i)[1], infoMap.get(i)[2], infoMap.get(i)[3], null);
            }
        }

        for (int i = 0; i < infoObjects.size(); i++) {
            g2d.drawImage(imageEnemy, infoObjects.get(i)[0], infoObjects.get(i)[1], infoObjects.get(i)[2], infoObjects.get(i)[3], null);
        }

        if ((180 > playerInfo[6] && 165 < playerInfo[6]) || ((150 > playerInfo[6]) && 135 < playerInfo[6]) || ((120 > playerInfo[6]) && 105 < playerInfo[6]) || (90 > playerInfo[6] && 75 < playerInfo[6]) || (60 > playerInfo[6] && 45 < playerInfo[6]) || (30 > playerInfo[6] && 15 < playerInfo[6]) || playerInfo[6] == 0) {
            g2d.drawImage(imagePlayer, playerInfo[0], playerInfo[1], playerInfo[2], playerInfo[3], null);
        }
        for (int i = 0; i < playerInfo[5]; i++) {
            g2d.drawImage(imageHealth, 5 + (i * 25), 5, 30, 30, null);
        }
        if (paused) {
            g2d.setPaint(Color.RED);
            Font stringPause = new Font("PAUSED", Font.ROMAN_BASELINE, 40);
            g2d.setFont(stringPause);
            g2d.drawString("PAUSED", 400, 300);
        }
    }

    public void getInfoMap(LinkedList<int[]> mapInfo) {
        infoMap = mapInfo;
    }

    public void getInfoObjects(LinkedList<int[]> objectsInfo) {
        infoObjects = objectsInfo;
    }
}
