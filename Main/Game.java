package Main;

import View.View;
import Controller.Controller;
import Model.Model;

import java.awt.*;

public class Game{
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Model model = new Model();
            View view = new View();
            Controller controller = new Controller(model,view);
            controller.gameLoop.start();
        });

    }
}
