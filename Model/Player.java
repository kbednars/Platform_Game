package Model;

/**
 * Klasa odpowiedzialna za reprezentację gracza.
 */
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
        health = 4;
        protection = 0;
        points = 0;
        data = new int[8];
    }

    /**
     * @return zwraca aktualny stan obiektu gracza
     */
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

    /**
     * Odpowiada za zmianę pozycji gracza.
     */
    void move() {
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
                dy = 0;
            }
        }
    }

    /**
     * Metoda odpowiedzialna, za odliczanie ochrony przeciwnika, po
     * zderzeniu z przeciwnikiem.
     */
    void protectionTimer(){
        if(protection > 0){
            protection -= 1;
        }else{
            protection = 0;
        }
    }

    /**
     * Metoda odpowiedzialna za zmianę stanu gracza, wskutek aktualnie
     * wciśniętych przycisków.
     * @param keys tablica reprezentująca aktualnie wciśnięte.
     */
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

    private void setHealth() {
        if(colXop && protection == 0){
            this.health -= 1;
            protection = 180;
        }
    }

    void addHealth(){
        health++;
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
