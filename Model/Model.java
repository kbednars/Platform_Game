package Model;

import Controller.Controller;

import java.util.LinkedList;

public class Model {
    public Controller controller;
    private GameBoard board;
    private Player player;
    private int mapNumber;
    private boolean mapChanged;

    private LinkedList <Door> doors = new LinkedList<>();
    private LinkedList <Brick> bricks = new LinkedList<>();
    private LinkedList <Opponent> opponents = new LinkedList<>();
    LinkedList <int []> infoMap = new LinkedList<>();
    LinkedList <int []> infoObjects = new LinkedList<>();

    public Model() {
        board = new GameBoard(this);
    }

    /** Metoda do aktualizowania pozycji
     * gracza
     * @param keys zmiana na osi x
     */
    public void update(boolean [] keys) {
        if(player.getHealth() == 0){
            controller.setPlayerLose(true);
            controller.setGame(false);
        }
        player.setChanges(keys);
    }

    public void initGame(){
        mapNumber = 1;
        board.chooseMap(mapNumber);
        player = new Player();
        mapChanged = true;
    }

    public void endGame(){
        removeBricks();
        removeOpponents();
        removeDoors();
    }

    public void move(){
        checkCollision(player.getDx(), player.getDy(), player);
        player.move();
        for(int i = 0; i < opponents.size(); i++){
            checkCollision(opponents.get(i).getDx(),opponents.get(i).getDy(), opponents.get(i));
            opponents.get(i).oppMove();
        }
        checkCollisionOpponents(player.getDx(), player.getDy(), player);
        checkDoors(player.getDx(), player);
    }

    public int[] getData(){
        return player.getData();
    }

    void checkCollision(int dx, int dy, GameObject object){
        boolean colX = false, colY = false;
        int k;

        if(dx > 0 && object == player){k = 2;}
        else if(dx < 0 && object == player){k = -2;}
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
            if (object.getRec(1, dy + k).intersects(bricks.get(i).x,bricks.get(i).y, bricks.get(i).width, bricks.get(i).height)) {
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

    void checkCollisionOpponents(int dx, int dy, Player player){
        boolean colX = false, colY = false;
        int k;
        if(dx > 0){k = 1;}
        else if(dx < 0){k = -2;}
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

    void checkDoors(int dx, Player pla){
        for(int i = 0; i < doors.size(); i++){
            if(pla.getRec(0,dx + 15).intersects(doors.get(i).x,doors.get(i).y,doors.get(i).width, doors.get(i).height)){
                player.respawn();
                if(++mapNumber == 2){
                    controller.setGame(false);
                    controller.setEndGame(true);
                    break;
                }
                changeMap();
                break;
            }
        }
    }

    public void addBrick(Brick obj){
        bricks.add(obj);
    }

    public void addOpponent(Opponent obj){
        opponents.add(obj);
    }

    public void addDoor(Door door){
        doors.add(door);
    }

    void removeBricks(){
        bricks.clear();
    }

    void removeOpponents(){
        opponents.clear();
    }

    void removeDoors(){
        doors.clear();
    }

    void changeMap(){
        mapNumber++;
        removeBricks();
        removeDoors();
        removeOpponents();
        board.chooseMap(mapNumber);
        mapChanged = true;
    }

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
