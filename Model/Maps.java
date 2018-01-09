package Model;

public class Maps {
    private GameBoard gameBoard;

    Maps(GameBoard board){
        gameBoard = board;
    }
    String level;

   void Map_1(){
       level = "Poziom 1";
       gameBoard.createDoor(0,28);
       gameBoard.createOpponent(30,1, 2);
       gameBoard.createOpponent(10,1, -2);
       gameBoard.createOpponent(15,1,-2);
       gameBoard.createOpponent(10,10,-2);
       gameBoard.createOpponent(10,10,-2);
       gameBoard.createHorizontal(0,0,32);
       gameBoard.createVertical(3,1,1);
       gameBoard.createHorizontal(0,9,28);
       gameBoard.createHorizontal(4,18,28);
       gameBoard.createHorizontal(0,27,28);
       gameBoard.createVertical(16,10,5);
       gameBoard.createHorizontal(18,2,3);
       gameBoard.createHorizontal(15,4,2);
       gameBoard.createHorizontal(28,3,1);
       gameBoard.createHorizontal(30,6,1);
       gameBoard.createHorizontal(13,11,2);
       gameBoard.createHorizontal(19,13,2);
       gameBoard.createHorizontal(22,11,2);
       gameBoard.createHorizontal(2,13,2);
       gameBoard.createHorizontal(4,11,2);
       gameBoard.createHorizontal(1,16,2);
       gameBoard.createHorizontal(29,21,2);
       gameBoard.createHorizontal(31,23,2);
       gameBoard.createHorizontal(29,25,2);



   }
    void Map_2(){
        level = "Poziom 2";
        gameBoard.createHorizontal(0,0,32);
    }
}
