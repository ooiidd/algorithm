package test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.pow;

class XY implements Comparable<XY>{
    int x;
    int y;

    @Override
    public int compareTo(XY o) {
        if(this.y > o.y)
            return 1;
        else if(this.y==o.y){
            if(this.x==o.x){
                return 0;
            }
            else if(this.x>o.x){
                return 1;
            }
            else{
                return -1;
            }
        }
        else{
            return -1;
        }
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        return String.valueOf(this.x)+" "+String.valueOf(this.y);
    }
}
public class Test {

    public static void main(String[] args) {
        List<XY> list = new ArrayList<>();
        list.add(new XY(2,2));
        list.add(new XY(0,0));
        list.add(new XY(1,1));
        list.add(new XY(3,3));

        Comparator<XY> sort_tan = new Comparator<XY>() {
            @Override
            public int compare(XY o1, XY o2) {
                double tempA,tempB;
                tempA = Math.atan2(o1.y,o1.x);
                tempB = Math.atan2(o2.y,o2.x);
                if(tempA == tempB){
                    return ((pow(o1.x,2) + pow(o1.y,2)) - (pow(o2.x,2)+pow(o2.y,2))) <= 0 ? -1 : 1 ;
                }
                else{
                    return (tempA - tempB < 0) ? -1 : 1;
                }
            }
        };
        list.sort(sort_tan);
        System.out.println(list);
    }
}
