package Main;

import View.View;
import Controller.Controller;
import Model.Model;

public class Game{
    public static void main(String[] args) {
            Model model = new Model();
            View view = new View();
            Controller controller = new Controller(model,view);
            controller.gameLoop.start();
    }
}
