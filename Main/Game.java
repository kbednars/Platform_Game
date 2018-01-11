package Main;

import View.View;
import Controller.Controller;
import Model.Model;

import java.awt.*;

public class Game{
    private View view;
    private Controller controller;
    private Model model;

    public Game() {
        model = new Model();
        view = new View();
        controller = new Controller(model,view);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Game ex = new Game();
            ex.controller.gameLoop.start();
        });
    }
}
