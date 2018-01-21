package Model;

/**
 * Klasa zajmuje się tworzeniem obiektów reprezentujących platformy na mapie
 */
public class Brick extends GameObject{

    Brick(int x, int y){
        data = new int[5];
        width = 32;
        height = 24;
        this.x = x * width;
        this.y = 744 - (y * height);
        id = 1;
    }
}
