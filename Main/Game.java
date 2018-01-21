package Main;

import View.View;
import Controller.Controller;
import Model.Model;

/**
 * Glowna klasa programu odpowiedzialna za stworzenie
 * obiektow {@link Model} {@link View} i {@link Controller}
 * @author Krzysztof Bednarski
 */
public class Game{
    public static void main(String[] args) {
            Model model = new Model();
            View view = new View();
            Controller controller = new Controller(model,view);
            controller.gameLoop.start();
    }
}
