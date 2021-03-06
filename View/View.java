package View;

import Controller.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Klasa implementuje View w architekturze MVC
 * Odpowiedzialna za: implementację słuchaczy zdarzeń użytkownika,
 * decyzję aktualnie wyświetlanych paneli oraz za moment ich renderowanie.
 * Posiada referencję do {@link Controller}
 */
public class View {
    public Controller controller;
    private MainFrame mainFrame;
    private MainBoard mainBoard;
    private Menu menu;
    private boolean [] keys;
    boolean mapChanged;

    public View() {
        keys = new boolean[4];
        mainFrame = new MainFrame();
        mainBoard = new MainBoard();
        menu = new Menu(this);
        mainFrame.getContentPane().setBackground(Color.GRAY);
        mainFrame.setLayout(null);
        mainFrame.add(menu);
        mainFrame.add(mainBoard);
        mainBoard.setVisible(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.addKeyListener(new KeyAdapter());
    }

    /**
     * Zależnie od aktualnego stanu gry, uaktywnia odpowiednie panele w ramce,
     * poprzez wywoływanie metody setVisible(), a także wywołuje ich metodę renderującą
     * dany panel.
     */
    public void render() {
        mapChanged = controller.model.isMapChanged();
        if(controller.isMenu()){
            mainBoard.setVisible(false);
            menu.setVisible(true);
            menu.uiUpdate();
        }

        if(controller.isGame()){
            menu.setVisible(false);
            mainBoard.setVisible(true);
            mainBoard.paused = keys[3];
            getInfo();
            getPlayerData();
            mainBoard.repaint();
        }

        if(controller.isEndGame()){
            mainBoard.setVisible(false);
            menu.setVisible(true);
            menu.uiUpdate();
        }

        if(controller.isPlayerLose()){
            mainBoard.setVisible(false);
            menu.setVisible(true);
            menu.uiUpdate();
        }
        mainFrame.repaint();
    }

    /**
     * Klasa implementująca słuchacza zdarzeń klawiatury.
     */
    private class KeyAdapter implements KeyListener {

        @Override
        public void keyReleased(KeyEvent e) {
            keyValue(e,false);
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            keyValue(e,true);
        }
    }

    /**
     * Metoda odpowiedzialna za zapisanie zdarzenia w odpowiedniej tablicy.
     * @param e przechwycone zdarzenie
     * @param pressed określa czy przycisk wciśnięty cz nie
     */
    private void keyValue(KeyEvent e, boolean pressed) {
        int keyValue = e.getKeyCode();
        if (pressed){
            if (keyValue == KeyEvent.VK_A) {
                keys[0] = true;
            }
            if (keyValue == KeyEvent.VK_D) {
                keys[1] = true;
            }
            if (keyValue == KeyEvent.VK_SPACE){
                keys[2] = true;
            }
            if (keyValue == KeyEvent.VK_P){
                keys[3] = !keys[3];
            }
        }else{
            if (keyValue == KeyEvent.VK_A) {
                keys[0] = false;
            }
            if (keyValue == KeyEvent.VK_D) {
                keys[1] = false;
            }
            if (keyValue == KeyEvent.VK_SPACE){
                keys[2] = false;
            }
        }
    }

    /**
     * @return zwraca tablicę aktualnego stanu przycisków
     */
    public boolean[] getKeys() {
        return keys;
    }

    public void getInfo() {
        if(mapChanged){
            mainBoard.getInfoMap(controller.model.getInfoMap());
        }
        mainBoard.getInfoObjects(controller.model.getInfoObjects());
    }

    public void getPlayerData(){
        mainBoard.playerInfo = controller.getPlayerData();
    }
}