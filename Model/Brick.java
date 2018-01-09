package Model;

import java.awt.*;

public class Brick extends GameObject{

    Brick(int x, int y){
        data = new int[5];
        width = 32;
        length = 24;
        this.x = x * width;
        this.y = y * length;
        id = 1;
    }

    public Rectangle getRec(int i, int change) {
        if(i == 0) {
            rec = new Rectangle(x + change, y, width, length);
            return rec;
        }
        else{
            rec = new Rectangle(x, y + change, width, length);
            return rec;
        }
    }
}
