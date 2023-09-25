import model.*;

public class Main {
    public static void main(String []args)
    {
        String target="010800007090176583700059026206573149930601025000000000024358060300060400600004873";
        int[][] result=split(target,9);
//        EightyOneCells sudoku=new EightyOneCells(nineCells);
        EightyOneCells sudoku=new EightyOneCells(result);
        sudoku.print();
        sudoku.initUndeterminedNumSet();
    }
    //将字符串转为int数组
    public static int [] transform(String target){
        int[] result=new int[target.length()];
        for(int i=0;i<target.length();i++){
            result[i]=target.charAt(i)-48;
        }
        return result;
    }
    //将字符串转为多个长度为len的int数组
    public static int [][] split(String target,int len){
        int left=len-target.length()%len;
        for(int i=0;i<left;i++)
        {
            target+="0";
        }
        int amount=target.length()/len;
        int[] [] result=new int[amount][len];
        for(int i=0;i<amount;i++){
            String current=target.substring(i*len,i*len+len);
            result[i]=transform(current);
        }
        return result;
    }
}
