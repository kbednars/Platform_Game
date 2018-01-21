package Model;

/**
 * Klasa odpowiedzialna za tworzenie przeciwników.
 */
class Opponent extends GameObject {

    Opponent(int x, int y, int sp){
        this.x = x;
        this.y = y;
        id = 2;
        speed = sp;
        dx = 1;
        dy = 0;
        grav = 0.25;
        width = 26;
        height = 20;
        data = new int[5];
    }

    /**
     * Metoda odpowiedzialna, za poruszanie przeciwnika.
     */
    void oppMove() {
        gravity();
        if(collisionX){
            x += 2*dx;
            dx = -dx;
        }
        x += dx*speed;

        if(x < 0){
            x = 0;
            dx = -dx;
        }
        else if(x > (1024 - width)){
            dx = -dx;
            x = 1024 - width;
        }

        if(!collisionY){
            setJump();
            y += dy;
            if(y > (768 - height) ){
                y = 768 - height;
            }else if(y < 0){
                y = 0;
            }
        }
    }
}
