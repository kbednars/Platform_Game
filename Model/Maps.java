package Model;

public class Maps {
    private GameBoard gameBoard;

    Maps(GameBoard board){
        gameBoard = board;
    }


   void Map_1(){
       gameBoard.createDoor(0,28);

       gameBoard.createOpponent(30,1, 2);
       gameBoard.createOpponent(10,1, -2);
       gameBoard.createOpponent(15,1,-2);
       gameBoard.createOpponent(10,10,-2);
       gameBoard.createOpponent(10,10,-2);
       gameBoard.createOpponent(23,12,3);
       //Warstwa 1
       gameBoard.createHorizontal(0,0,32);
       gameBoard.createVertical(1,1,1);
       gameBoard.createVertical(2,1,3);
       gameBoard.createVertical(3,1,5);
       gameBoard.createHorizontal(4,5,10);
       gameBoard.createVertical(20,3,6);
       gameBoard.createHorizontal(16,3,4);
       gameBoard.createHorizontal(27, 3, 2);
       gameBoard.createHorizontal(30, 6, 2);
       //Warstwa 2
       gameBoard.createHorizontal(0,9,28);
       gameBoard.createVertical(16,10,5);
       gameBoard.createHorizontal(21,11,5);
       gameBoard.createVertical(21,12,1);
       gameBoard.createVertical(25,12,1);
       gameBoard.createHorizontal(18,13,2);
       gameBoard.createHorizontal(2,13,2);
       gameBoard.createHorizontal(4,11,2);
       gameBoard.createHorizontal(1,16,2);
       //Warstwa 3
       gameBoard.createHorizontal(4,18,28);
       gameBoard.createHorizontal(29,21,2);
       gameBoard.createHorizontal(31,23,2);
       gameBoard.createHorizontal(29,25,2);
       //Warstwa 4
       gameBoard.createVertical(3,28,1);
       gameBoard.createHorizontal(0,27,28);



   }
    void Map_2(){
        gameBoard.createHorizontal(0,0,32);
    }
}
