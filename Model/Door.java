package Model;

public class Door extends GameObject{

    Door (int x, int y){
        data = new int[5];
        width = 32;
        height = 24;
        this.x = x * width;
        this.y = 744 - (y * height);
        id = 3;
    }
    int[] getData() {
        data[0] = x;
        data[1] = y;
        data[2] = width;
        data[3] = height;
        data[4] = id;
        return data;
    }
}
