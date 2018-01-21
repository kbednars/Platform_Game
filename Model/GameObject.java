package Model;

import java.awt.*;

/**
 * Klasa podstawowa po, której dziedziczą wszystkie obiekty tworzone w grze.
 */
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

    /**
     * @return zwraca aktualny stan obiektu GameObject
     */
    int[] getData() {
        data[0] = x;
        data[1] = y;
        data[2] = width;
        data[3] = height;
        data[4] = id;
        return data;
    }

    /**
     * Metoda, która odpowiada za symulowanie grawitacji.
     */
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

    /**
     * Metoda pozwala na stworzenie prostokąta o wymiarach obiektu GameObject.
     * Używana do wykrywania kolizji, gdzie korzystam z metody intersect.
     * @param i
     * @param change
     * @return
     */
    Rectangle getRec(int i, int change) {
        if (i == 0) {
            rec = new Rectangle(x + change, y, width, height);
            return rec;
        } else {
            rec = new Rectangle(x, y + change, width, height);
            return rec;
        }
    }

    /**
     * Metoda zapisuje stan aktualnych kolizji.
     * @param X kolizja w osi X
     * @param Y kolizja w osi Y
     */
    void collision(boolean X, boolean Y){
        collisionX = X;
        collisionY = Y;
        if(Y && !ground){
            setDy(0);
        }
    }
}
