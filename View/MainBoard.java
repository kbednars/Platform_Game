package View;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainBoard extends JPanel{
    int [] playerInfo;
    LinkedList<int []> info;

    public MainBoard() {
        playerInfo = new int [4];
        setBackground(Color.BLACK);
        setBounds(55, 30,511,384);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.YELLOW);
        if(info!=null){
            for(int i = 0; i < info.size(); i++) {
                g2d.fillRect(info.get(i)[0],info.get(i)[1],info.get(i)[2],info.get(i)[3]);
            }
        }
        g2d.setPaint(Color.GREEN);
        g2d.fillRect(playerInfo[0],playerInfo[1],playerInfo[2],playerInfo[3]);
        g2d.setPaint(Color.RED);
    }

    public void getInfo(LinkedList<int []>objInfo){
        info = objInfo;
    }
}
