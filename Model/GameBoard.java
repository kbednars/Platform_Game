package Model;


public class GameBoard {

    Model model;
    Maps map;

    public GameBoard(Model model) {
        this.model = model;
        map = new Maps(this);
    }

    public void chooseMap(int number){
        switch (number){
            case 1:
                map.Map_1();
                break;
            case 2:
                map.Map_2();
                break;
        }
    }

    public void createVertical(int x, int y, int height){
        for(int i = y; i<y+height; i++){
            model.addBrick(new Brick(x,i));
        }
    }

    public void createHorizontal(int x, int y, int width){
        for(int i = x; i < x + width; i++){
            model.addBrick(new Brick(i,y));
        }
    }

    public void createOpponent(int x, int y, int sp){
        model.addOpponent(new Opponent(x*32,744 - (y*24), sp));
    }

    public void createDoor(int x, int y){
        model.addDoor(new Door(x,y));
    }
}
