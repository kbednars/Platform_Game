package Model;

import java.awt.*;

class GameObject {
    int id;
    int x, y;
    int dx;
    int dy;
    double grav;
    double index;
    boolean collisionX, collisionY;
    boolean jump;
    boolean ground;
    int speed;
    int width;
    int length;
    Rectangle rec;
    int data[];

    int[] getData() {
        data[0] = x;
        data[1] = y;
        data[2] = width;
        data[3] = length;
        data[4] = id;
        return data;
    }

    void gravity() {
        if (collisionY && dy == 0){
            jump = false;
        }
        if (!collisionY && jump) {
            if (dy < 3) {
                index += grav;
                if (index >= 1) {
                    dy += index;
                    index = 0;
                }
            } else {
                dy = 3;
            }
        } else{
            dy = 0;
            index = 0;
        }
    }


    int getDx() {
        return dx;
    }

    int getDy() {
        return dy;
    }

    public int getId() {
        return id;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }

    public Rectangle getRec(int i, int change) {
        if (i == 0) {
            rec = new Rectangle(x + change, y, width, length);
            return rec;
        } else {
            rec = new Rectangle(x, y + change, width, length);
            return rec;
        }
    }
}
