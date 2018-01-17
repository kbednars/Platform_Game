package Model;

public class Maps {
    private GameBoard gameBoard;

    Maps(GameBoard board){
        gameBoard = board;
    }


   void Map_1(){
       gameBoard.createDoor(0,28);
       gameBoard.createOpponent(15,1,-2);
       gameBoard.createOpponent(10,10,-2);
       gameBoard.createOpponent(10,10,-2);
       gameBoard.createOpponent(23,12,3);
       //Warstwa 1
       gameBoard.createHorizontal(0,0,32);
       gameBoard.createVertical(1,1,1);
       gameBoard.createVertical(2,1,3);
       gameBoard.createVertical(3,1,5);
       gameBoard.createVertical(4,1,6);
       gameBoard.createHorizontal(5,6,10);
       gameBoard.createHorizontal(12,3,4);
       gameBoard.createHorizontal(8,2,4);
       gameBoard.createHorizontal(21,3,1);
       gameBoard.createOpponent(9,4,3);
       gameBoard.createVertical(8,3,1);
       gameBoard.createVertical(20,4,5);
       gameBoard.createVertical(23,1,6);
       gameBoard.createOpponent(25,1,3);
       gameBoard.createHorizontal(16,4,4);
       gameBoard.createHorizontal(27, 3, 2);
       gameBoard.createHorizontal(30, 6, 2);
       //Warstwa 2
       gameBoard.createHorizontal(0,9,28);
       gameBoard.createVertical(27,10,6);
       gameBoard.createHorizontal(28,9,1);
       gameBoard.createHorizontal(31,12,1);
       gameBoard.createVertical(8,12,6);
       gameBoard.createHorizontal(8,12,6);
       gameBoard.createHorizontal(13,13,1);
       gameBoard.createOpponent(12,13,2);
       gameBoard.createVertical(16,10,6);
       gameBoard.createHorizontal(11,15,5);
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
