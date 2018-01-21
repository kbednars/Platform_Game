package View;

import Model.Model;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;


public class Shape extends JPanel{
    private Model model;

    public Shape(Model model) {
        this.model = model;
    }
/*
    private void initModel() {
        setSize(400,400);
        setLocation(0,0);
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setVisible(true);
    }


 /*   @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setPaint(Color.YELLOW);
        g2d.fillRect(model.getX(),model.getY(),model.getWidth(),model.getLength());
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) { keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) { keyPressed(e);
        }
    }*/
}