package Model;

class Player extends GameObject{

    private int health;
    private int protection;
    private int points;
    private boolean colXop, colYop;

    Player(){
        x = 0;
        y = 740;
        dy = 0;
        jump = true;
        grav = 0.25;
        index = 0;
        speed = 3;
        width = 20;
        height = 19;
        health = 3;
        protection = 0;
        points = 0;
        data = new int[8];
    }

    int[] getData() {
        data[0] = x;
        data[1] = y;
        data[2] = width;
        data[3] = height;
        data[4] = id;
        data[5] = health;
        data[6] = protection;
        data[7] = points;
        return data;
    }

    void move() {
        if(protection > 0){
            protection -= 1;
        }else{
            protection = 0;
        }
        gravity();
        if(!collisionX){
            x += dx*speed;
            if(x > (1024 - width) ){
                x = 1024 - width;
            }else if(x < 0){
                x = 0;
            }
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
                setJump();
                dy = -6;
            }
        }
    }

    public int getHealth() {
        return health;
    }

    void respawn(){
        setX(0);
        setY(721);
    }

    void setHealth() {
        if(colXop && protection == 0){
            this.health -= 1;
            protection = 180;
        }
    }

    int getProtection() {
        return protection;
    }

    void setOppCollision(boolean colX, boolean colY){
        colXop = colX;
        setHealth();
        colYop = colY;
    }
}
