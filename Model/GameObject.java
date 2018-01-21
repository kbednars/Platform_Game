package Model;

import java.awt.*;

class GameObject {
    int id;
    int x, y, dx, dy;
    double grav, index;
    boolean collisionX, collisionY;
    boolean jump, ground;
    int speed;
    int width, height;
    Rectangle rec;
    int data[];

    int[] getData() {
        data[0] = x;
        data[1] = y;
        data[2] = width;
        data[3] = height;
        data[4] = id;
        return data;
    }

    void gravity() {
        if (!ground) {
            jump = true;
            if (dy < 4) {
                index += grav;
                if (index >= 1) {
                    dy += index;
                    index = 0;
                }
            } else {
                dy = 4;
            }
        } else{
            jump = false;
            dy = 0;
            index = 0;
        }
    }

    void setJump(){
        ground = false;
        this.jump = true;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    int getDx() {
        return dx;
    }

    int getDy() {
        return dy;
    }

    void setDy(int dy) {
        this.dy = dy;
    }

    Rectangle getRec(int i, int change) {
        if (i == 0) {
            rec = new Rectangle(x + change, y, width, height);
            return rec;
        } else {
            rec = new Rectangle(x, y + change, width, height);
            return rec;
        }
    }

    void collision(boolean X, boolean Y){
        collisionX = X;
        collisionY = Y;
        if(Y && !ground){
            setDy(0);
        }
    }
}
