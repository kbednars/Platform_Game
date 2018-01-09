package Model;


import java.util.ArrayList;


public class GameBoard {

    Model model;
    boolean isVisible;
    ArrayList <Brick> rectang = new ArrayList <>();

    public GameBoard(Model model) {
        this.model = model;
        initMap_1();
    }

    private void initMap_1(){
        int i, k;
        for(i = 0; i<16; i++){
            for(k = 15; k<16; k++){
                model.addBrick(new Brick(i,k));
            }
        }

        for(i = 6; i<8; i++){
            for(k = 5; k<6; k++){
                model.addBrick(new Brick(i,k));
            }
        }

        for(i = 3; i<5; i++){
            for(k = 7; k<8; k++){
                model.addBrick(new Brick(i,k));
            }
        }

        for(i = 8; i<9; i++){
            for(k = 9; k<10; k++){
                model.addBrick(new Brick(i,k));
            }
        }

        for(i = 10; i<13; i++){
            for(k = 3; k<4; k++){
                model.addBrick(new Brick(i,k));
            }
        }
        for(i = 11; i<13; i++){
            for(k = 12; k<13; k++){
                model.addBrick(new Brick(i,k));
            }
        }
    }

    ArrayList<Brick> getMap(){
        return rectang;
    }
/*
    Rectangle[] getRec(){
        int index = 0;
        Rectangle [] rec = new Rectangle[rectang.size()];
        for(int i = 0; i<16; i++){
            for(int k = 0; k<16; k++){
                if(rectang.get(i*16+k) != null) {
                    rec[index] = rectang.get(i*16+k).getRec(0,0);
                    index++;
                }
            }
        }
        return rec;
    }*/
}
