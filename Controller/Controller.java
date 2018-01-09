package Controller;

import Model.Brick;
import Model.Model;
import View.View;

import java.util.ArrayList;

public class Controller{

    public Model model;
    View view;
    private int lastFpsTime,fps;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.controller = this;
        view.controller = this;
    }

    public void sendKeys(){
        model.update(view.getKeys());
    }

    public ArrayList<Brick> getMap(){
        return model.getMap();
    }

    public int[] getPlayerData(){
        return model.getData();
    }

    public Thread gameLoop = new Thread() {
        public void run ()
        {
            long lastLoopTime = System.nanoTime();
            final int TARGET_FPS = 60;
            final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

            while (true) {
                long now = System.nanoTime();
                long updateLength = now - lastLoopTime;
                lastLoopTime = now;

                lastFpsTime += updateLength;
                fps++;

                if (lastFpsTime >= 1000000000) {
                    System.out.println("(FPS: " + fps + ")");
                    lastFpsTime = 0;
                    fps = 0;
                }
                sendKeys();
                model.move();
                view.render();

                try {
                    Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

}