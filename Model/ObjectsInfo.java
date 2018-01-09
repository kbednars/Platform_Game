package Model;

import java.util.LinkedList;

public class ObjectsInfo{
    LinkedList <int []> info = new LinkedList<>();

    void addObject(GameObject obj){
        info.add(obj.getData());
    }
/*
    void setInfo(GameObject obj){
        for(int i = 0; i < info.size(); i++){
            info.set(i, obj).getData();
        }
    }
*/
    LinkedList <int[]> getInfo(){
        return info;
    }
}
