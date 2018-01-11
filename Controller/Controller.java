package Controller;

import Model.Model;
import View.View;

import java.io.IOException;

public class Controller {
    public Model model;
    public View view;
    private boolean paused;
    private boolean menu, game, endGame , playerLose;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.controller = this;
        view.controller = this;
        menu = true;
        game = false;
        endGame = false;
    }

    public void sendKeys() {
        if(view.getKeys()[4] && menu){
            game = true;
            menu = false;
            model.initGame();
        }
        else if(view.getKeys()[5] && endGame){
            menu = true;
            endGame = false;
            model.endGame();
        }
        else if(view.getKeys()[5] && playerLose){
            model.endGame();
            model.initGame();
            playerLose = false;
            game = true;
        }

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
        /*
            Timer t = new Timer(15, event -> {
                sendKeys();
                if (!paused) {
                    model.move();
                }
                view.render();
            });
            t.start();
*/
        };

    public boolean isMenu() {
        return menu;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isPlayerLose() {
        return playerLose;
    }

    public void setPlayerLose(boolean playerLose) {
        this.playerLose = playerLose;
    }
}

