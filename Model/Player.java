package Model;

class Player extends GameObject{

    Player(){
        x = 0;
        y = 0;
        dy = 0;
        data = new int[5];
        jump = true;
        grav = 0.1;
        index = 0;
        speed = 2;
        width = 20;
        length = 20;
    }

    void move() {
        gravity();
        if(!collisionX){
            x += dx*speed;
            if(x > (511 - width) ){
                x = 511 - width;
            }else if(x < 0){
                x = 0;
            }
        }

        if(!collisionY){
            y += dy;
            if(y > (384 - length) ){
                y = 384 - length;
            }else if(y < 0){
                y = 0;
            }
        }
    }

    void setChanges(boolean [] keys) {
        if (keys[0]) {
            dx = -1;
        }else if (keys[1]) {
            dx = 1;
        }
        else{
            dx = 0;
        }

        if (keys[2]){
            if(!jump){
                jump = true;
                ground = false;
                dy = -4;
            }
        }
    }

    void collision(boolean X, boolean Y){
        if(ground) {
            collisionX = X;
            collisionY = Y;
        }
        else{
            collisionX = false;
            collisionY = false;
        }
    }

}
