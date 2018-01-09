package Model;

import java.awt.*;

public class Brick extends GameObject{

    Brick(int x, int y){
        data = new int[5];
        width = 32;
        height = 24;
        this.x = x * width;
        this.y = 744 - (y * height);
        id = 1;
    }

    int[] getData() {
        data[0] = x;
        data[1] = y;
        data[2] = width;
        data[3] = height;
        data[4] = id;
        return data;
    }

    public Rectangle getRec(int i, int change) {
        if(i == 0) {
            rec = new Rectangle(x + change, y, width, height);
            return rec;
        }
        else{
            rec = new Rectangle(x, y + change, width, height);
            return rec;
        }
    }
}
