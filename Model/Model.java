package Model;

import Controller.Controller;

import java.util.LinkedList;

/**
 * Klasa implementuje Model w architekturze MVC
 * Odpowiedzialna za: przetrzymywanie informacji o istniejących obiektach,
 * inicjalizację gry oraz uaktualnianie stanu obiektów.
 */
public class Model {
    public Controller controller;
    private GameBoard board;
    private Player player;
    private int mapNumber;
    private boolean mapChanged;

    private LinkedList <Door> doors = new LinkedList<>();
    private LinkedList <Brick> bricks = new LinkedList<>();
    private LinkedList <Opponent> opponents = new LinkedList<>();
    private LinkedList <int []> infoMap = new LinkedList<>();
    private LinkedList <int []> infoObjects = new LinkedList<>();

    public Model() {
        board = new GameBoard(this);
    }

    /** Metoda przekazuje otrzymane od {@link Controller}
     * tablice stanu przycisków, sprawdza stan życia playera oraz
     * ustawia czas ochrony Playera
     * @param keys tablica stanu przycisków
     */
    public synchronized void update(boolean [] keys) {
        if(player.getHealth() == 0){
            controller.setPlayerLose(true);
            controller.setGame(false);
        }
        player.setChanges(keys);
        player.protectionTimer();
    }

    /**
     * Metoda odpowiedzialna za inicjalizacje gry.
     * Tworzy obiekty potrzebne do rozpoczęcia rozgrywki.
     */
    public synchronized void initGame(){
        mapNumber = 1;
        board.chooseMap(mapNumber);
        player = new Player();
        mapChanged = true;
    }

    /**
     * Metoda odpowiedzialna za zakończenie trwającej rozgrywki
     * i usunięcie wszystkich obiektów w grze.
     */

    public void endGame(){
        removeBricks();
        removeOpponents();
        removeDoors();
    }

    /**Metoda odpowiedzialna za sprawdzenie kolizji między obeiktami,
     * a także wywołania ich metod zmiany położenia.
     */
    public void move(){
        checkCollision(player.getDx(), player.getDy(), player);
        player.move();
        for(int i = 0; i < opponents.size(); i++){
            checkCollision(opponents.get(i).getDx(),opponents.get(i).getDy(), opponents.get(i));
            opponents.get(i).oppMove();
        }
        checkCollisionOpponents(player.getDx(), player.getDy());
        checkDoors(player.getDx());
    }

    /**Mrtoda zwraca aktualne informacje o playerze
     * @return tablica int[] aktualnego stanu gracza
     */
    public int[] getData(){
        return player.getData();
    }

    /**
     * Metoda sprawdza kolizje obiektu, który się porusza z obiektami Brick.
     * Po sprawdzeniu kolizji przekazuje informacje, wywołując metodę klasy GameObject.
     * @param dx zmiana położenia w osi X
     * @param dy zmiana położenia w osi Y
     * @param object obiekt, który się porusza
     */
    void checkCollision(int dx, int dy, GameObject object){
        boolean colX = false, colY = false;
        int k;

        if (dx > 0 && object.getClass() == Player.class){k = 2;}
        else if(dx < 0 && object.getClass() == Player.class){k = -2;}
        else {k = 0;}

        for(int i = 0; i < bricks.size(); i++){
            if(object.getRec(0,dx + k).intersects(bricks.get(i).x,bricks.get(i).y,bricks.get(i).width, bricks.get(i).height)){
                colX = true;
                break;
            }
        }

        if(dy < 0){k = -1;}
        else if(dy > 0){k = 1;}
        else {k = 1;}

        for(int i = 0; i < bricks.size(); i++){
            if (object.getRec(1, dy + k).intersects(bricks.get(i).x,bricks.get(i).y, bricks.get(i).width, bricks.get(i).height)){
                colY = true;
                if(dy>0){
                    object.ground = true;
                    object.setY(bricks.get(i).y - object.height);
                }else{
                    object.ground = false;
                }
                break;
            }
        }
        object.collision(colX,colY);
    }

    /**
     * Metoda sprawdza kolizje playera z obiektami klasy Opponent.
     * O zaistniałej kolizji informuje, wywołując metodę klasy Player.
     * @param dx zmiana położenia w osi X
     * @param dy zmiana położenia w osi Y
     */
    void checkCollisionOpponents(int dx, int dy){
        boolean colX = false, colY = false;
        int k;
        if(dx > 0){k = -2;}
        else if(dx < 0){k = 2;}
        else {k = 0;}

        for(int i = 0; i < opponents.size(); i++){
            if(player.getRec(0,dx + k).intersects(opponents.get(i).x,opponents.get(i).y,opponents.get(i).width, opponents.get(i).height)){
                colX = true;
                if(player.getProtection() == 0){
                    opponents.remove(i);
                }
                break;
            }
        }

        if(dy > 0){k = 1;}
        else if(dy < 0){k = -1;}
        else {k = 0;}

        for(int i = 0; i < opponents.size(); i++){

            if (player.getRec(1, dy + k).intersects(opponents.get(i).x,opponents.get(i).y, opponents.get(i).width, opponents.get(i).height)) {
                colY = true;
                if(player.getProtection() == 0){
                    opponents.remove(i);
                }
                break;
            }
        }
        player.setOppCollision(colX,colY);
    }

    /**
     * Metoda sprawdza kolizje playera z obiektami klasy Door.
     * O zaistniałej kolizji informuje, wywołując metodę klasy Player.
     * @param dx zmiana położenia w osi X
     */
    void checkDoors(int dx){
        for(int i = 0; i < doors.size(); i++){
            if(player.getRec(0,dx + 15).intersects(doors.get(i).x,doors.get(i).y,doors.get(i).width, doors.get(i).height)){
                player.respawn();
                if(++mapNumber == 3){
                    controller.setGame(false);
                    controller.setEndGame(true);
                    break;
                }
                changeMap();
                break;
            }
        }
    }

    /**
     * Metoda odpowiedzialna za dodanie obietku klasy Brick do kolekcji
     * zawierającej wszystkie obiekty tej klasy.
     * @param obj obiekt klasy Brick
     */
    public void addBrick(Brick obj){
        bricks.add(obj);
    }

    /**
     * Metoda odpowiedzialna za dodanie obietku klasy Opponent do kolekcji
     * zawierającej wszystkie obiekty tej klasy.
     * @param obj obiekt klasy Opponent
     */
    public void addOpponent(Opponent obj){
        opponents.add(obj);
    }

    /**
     * Metoda odpowiedzialna za dodanie obietku klasy Door do kolekcji
     * zawierającej wszystkie obiekty tej klasy.
     * @param obj obiekt klasy Door
     */
    public void addDoor(Door obj){
        doors.add(obj);
    }

    /**
     * Metoda odpowiedzialna za usunięcie wszystkich obietków klasy Brick z kolekcji
     */
    void removeBricks(){
        bricks.clear();
    }

    /**
     * Metoda odpowiedzialna za usunięcie wszystkich obietków klasy Opponent z kolekcji
     */
    void removeOpponents(){
        opponents.clear();
    }

    /**
     * Metoda odpowiedzialna za usunięcie wszystkich obietków klasy Door z kolekcji
     */
    void removeDoors(){
        doors.clear();
    }

    /**
     * Metoda odpowiedzialna za zmianę mapy na następną.
     */
    void changeMap(){
        mapNumber++;
        removeBricks();
        removeDoors();
        removeOpponents();
        board.chooseMap(mapNumber);
        player.addHealth();
        mapChanged = true;
    }

    /**
     * Metoda odpowiedzialna za zwrócenie informacji o obiektach aktualnej mapy
     * @return informacje o obiektach aktualnej mapy
     */
    public LinkedList<int[]> getInfoMap(){
        infoMap.clear();
        for(int i = 0; i < bricks.size(); i++){
            infoMap.add(bricks.get(i).getData());
        }
        for(int i = 0; i < doors.size(); i++){
            infoMap.add(doors.get(i).getData());
        }
        mapChanged = false;
        return infoMap;
    }

    /**
     * Metoda odpowiedzialna za zwrócenie informacji o obiektach klasy Opponent
     * @return informacje o obiektach klasy Opponent
     */
    public LinkedList<int[]> getInfoObjects() {
        infoObjects.clear();
        for(int i = 0; i < opponents.size(); i++){
            infoObjects.add(opponents.get(i).getData());
        }
        return infoObjects;
    }

    public boolean isMapChanged() {
        return mapChanged;
    }
}
