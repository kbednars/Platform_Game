package Model;

/**
 * Klasa zajmuje się tworzniem drzwi na mapie, które pozwalają
 * na przejście do kolejnego poziomu.
 */
public class Door extends GameObject{

    Door (int x, int y){
        data = new int[5];
        width = 32;
        height = 24;
        this.x = x * width;
        this.y = 744 - (y * height);
        id = 3;
    }
}
