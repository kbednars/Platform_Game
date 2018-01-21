package Controller;

import Model.Model;
import View.View;

/**
 * Klasa implementuje Controller w architekturze MVC
 * Odpowiedzialna, za komunikaację pomiędzy {@link View} i {@link Model},
 * przesyłanie, także akcji użytkownika, a także za tworzenie pętli gry.
 */
public class Controller {
    public Model model;
    public View view;
    private boolean paused;
    private boolean menu, game, endGame , playerLose;

    /**
     * Konstruktor otrzymuje referencję do modelu i view.
     * @param model {@link Model}
     * @param view {@link View}
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.controller = this;
        view.controller = this;
        menu = true;
        game = false;
        endGame = false;
    }

    /**
     * Metoda pobiera tablice aktualnego stanu wcięsniętych przycisków
     * i przekazuje ją do {@link Model} wywołując odpowiednią metodę.
     */
    public void sendKeys() {
        if (view.getKeys()[3]) {
            paused = true;
        } else if (!view.getKeys()[3]) {
            paused = false;
        }

        if(!paused && game){
            model.update(view.getKeys());
        }
    }

    public int[] getPlayerData() {
        return model.getData();
    }

    /**
     * Wątek odpowiedzialny za tworzenie pętli gry.
     * Niezależnie aktualizowane są stany obiektów w grze od renderowania
     * ich w GUI.
     */
    public Thread gameLoop = new Thread(){
        public void run() {

            int UPS = 60;
            int FPS = 60;
            long initialTime = System.nanoTime();
            final double timeU = 1000000000 / UPS;
            final double timeF = 1000000000 / FPS;
            double deltaU = 0, deltaF = 0;
            int frames = 0, ticks = 0;
            long timer = System.currentTimeMillis();

            while (true) {
                long currentTime = System.nanoTime();
                deltaU += (currentTime - initialTime) / timeU;
                deltaF += (currentTime - initialTime) / timeF;
                initialTime = currentTime;
                if (deltaU >= 1) {
                    sendKeys();
                    if(game && !paused){ model.move();}
                    ticks++;
                    deltaU--;
                }

                if (deltaF >= 1) {
                    view.render();
                    frames++;
                    deltaF--;
                }

                if (System.currentTimeMillis() - timer > 1000) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                    frames = 0;
                    ticks = 0;
                    timer += 1000;
                }
            }
        }
    };

    /**
     * @return zwraca informację czy uruchomione jest menu
     */
    public boolean isMenu() {
        return menu;
    }

    /**
     * Uruchamia lub dezaktywuje menu.
     * @param menu
     */
    public void setMenu(boolean menu) {
        this.menu = menu;
    }

    /**
     * @return zwraca informację czy uruchomiona jest gra
     */
    public boolean isGame() {
        return game;
    }

    /**
     * Uruchamia lub dezaktywuje gre.
     * @param game
     */
    public void setGame(boolean game) {
        if(game){
            menu = false;
            model.initGame();
        }
        else {
            model.endGame();
        }
        this.game = game;
    }

    /**
     * @return zwraca informację czy gra zakończona
     */
    public boolean isEndGame() {
        return endGame;
    }

    /**
     * Pozwala na zakończenie gry.
     * @param endGame
     */
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
        if(!endGame){
            menu = true;
        }
    }

    /**
     * @return zwraca informację, czy gracz przegrał rozgrywkę
     */
    public boolean isPlayerLose() {
        return playerLose;
    }

    /**
     * Ustawia informację o tym czy gracz przegrał.
     * @param playerLose
     */
    public void setPlayerLose(boolean playerLose) {
        this.playerLose = playerLose;
    }
}

