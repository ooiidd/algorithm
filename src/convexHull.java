import java.util.*;

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
public class convexHull {
    public static int left(XY a, XY b, XY c) {
        return a.x*b.y - a.y*b.x + b.x*c.y - b.y*c.x + c.x*a.y - c.y*a.x;
    }
    public static void main(String[] args) {
        List<XY> ve = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        XY minVal = new XY(40001,40001);

        int N = scan.nextInt();
        for(int i=0;i<N;i++){
            int a,b;
            a = scan.nextInt();
            b = scan.nextInt();
            XY cur = new XY(a,b);
            ve.add(cur);
            if(ve.get(i).compareTo(minVal) < 0){
                minVal = ve.get(i);
            }
        }
        for(int i=0;i<N;i++){
            if(minVal != ve.get(i)) {
                ve.get(i).y -= minVal.y;
                ve.get(i).x -= minVal.x;
            }
        }
        minVal.y = 0;
        minVal.x = 0;
//        System.out.println(ve);

        //sort
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
        ve.sort(sort_tan);
//        System.out.println(ve);

        //convex
        List<XY> stack = new ArrayList<>();
        for(int i=0;i<ve.size();i++){
            if(stack.size()<2){
                stack.add(ve.get(i%ve.size()));
            }
            else{
                XY prev2 = stack.get(stack.size() -2);
                XY prev1 = stack.get(stack.size()-1);
                if(left(prev2,prev1, ve.get(i%ve.size())) > 0){
                    stack.add(ve.get(i%ve.size()));
                }
                else{
                    stack.remove(stack.size()-1);
                    i--;
                }
            }
        }

//        System.out.println(stack);
        System.out.println(stack.size());
    }
}
