package Model;

/**
 * Klasa odpowiedzialna za tworzenie  map.
 */
public class GameBoard {

    Model model;
    Maps map;

    public GameBoard(Model model) {
        this.model = model;
        map = new Maps(this);
    }

    /**
     * Metoda pozwala na zmianę aktualnej mapy.
     * @param number numer wybranej mapy
     */
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

    /**
     * Metoda tworzy platformę poziomą o przekazanych wymiarach.
     * @param x pozycja X na mapie w podziale ekranu na bloki 32X32
     * @param y pozycja Y na mapie w podziale ekranu na bloki 32X32
     * @param height wysokość platformy podana w ilości bloków
     */
    public void createVertical(int x, int y, int height){
        for(int i = y; i<y+height; i++){
            model.addBrick(new Brick(x,i));
        }
    }

    /**
     * Metoda tworzy platformę pionową o przekazanych wymiarach.
     * @param x pozycja X na mapie w podziale ekranu na bloki 32X32
     * @param y pozycja Y na mapie w podziale ekranu na bloki 32X32
     * @param width szerokość platformy podana w ilości bloków
     */
    public void createHorizontal(int x, int y, int width){
        for(int i = x; i < x + width; i++){
            model.addBrick(new Brick(i,y));
        }
    }

    /**
     * Metoda tworzy przeciwnika w odpowiednim miejscu.
     * @param x pozycja X na mapie w podziale ekranu na bloki 32X32
     * @param y pozycja Y na mapie w podziale ekranu na bloki 32X32
     * @param sp predkość poruszania się przeciwnika
     */
    public void createOpponent(int x, int y, int sp){
        model.addOpponent(new Opponent(x*32,744 - (y*24), sp));
    }

    /**
     * Metoda tworzy drzwi w odpowiednim miejscu.
     * @param x pozycja X na mapie w podziale ekranu na bloki 32X32
     * @param y pozycja Y na mapie w podziale ekranu na bloki 32X32
     */
    public void createDoor(int x, int y){
        model.addDoor(new Door(x,y));
    }
}
