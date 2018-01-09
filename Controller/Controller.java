package Controller;

import Model.Model;
import View.View;

import javax.swing.*;

public class Controller {

    private boolean paused;
    public Model model;
    public View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.controller = this;
        view.controller = this;
    }

    public void sendKeys() {
        if (view.getKeys()[3]) {
            paused = true;
        } else if (!view.getKeys()[3]) {
            paused = false;
        }
        model.update(view.getKeys());
    }

    public int[] getPlayerData() {
        return model.getData();
    }

    public Thread gameLoop = new Thread() {

        public void run() {
            Timer t = new Timer(13, event -> {
                sendKeys();
                if (!paused) {
                    model.move();
                }
                view.render();
            });
            t.start();

        }
    };
}

