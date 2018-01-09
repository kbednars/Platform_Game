package Model;

import Controller.Controller;

import java.util.ArrayList;
import java.util.LinkedList;

public class Model {
    public Controller controller;
    private GameBoard board;
    private Player player;
    private ObjectsInfo objects;
    LinkedList<GameObject> bricks = new LinkedList<>();

    public Model() {
        board = new GameBoard(this);
        player = new Player();
        objects = new ObjectsInfo();
    }

    /** Metoda do aktualizowania pozycji
     * gracza
     * @param keys zmiana na osi x
     */
    public void update(boolean [] keys) {
        player.setChanges(keys);
        objects.info.clear();
        for(int i = 0; i<bricks.size();i++){
            objects.info.add(bricks.get(i).getData());
        }
    }

    public void move(){
        checkCollision(player.getDx(), player.getDy());
        player.move();
    }

    public int[] getData(){
        return player.getData();
    }


    public void checkCollision(int dx, int dy){
        boolean colX = false, colY = false;
        for(int i = 0; i < bricks.size(); i++){
            if(player.getRec(0,dx).intersects(bricks.get(i).x,bricks.get(i).y,bricks.get(i).width, bricks.get(i).length)){
                colX = true;
                break;
            }
        }

        for(int i = 0; i < bricks.size(); i++){
            if (player.getRec(1, dy).intersects(bricks.get(i).x,bricks.get(i).y, bricks.get(i).width, bricks.get(i).length)) {
                colY = true;
                break;
            }
        }

        if(colY && dy>0){
            player.setGround(true);
        }
        player.collision(colX,colY);
    }

    public ArrayList<Brick> getMap(){
        return board.getMap();
    }

    public void addBrick(GameObject obj){
        bricks.add(obj);
    }

    public void removeBricks(){
        bricks.clear();
    }

    public LinkedList<int[]> getInfo(){
        return objects.getInfo();
    }
}
