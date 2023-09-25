import model.*;

public class Main {
    public static void main(String []args)
    {

        int[][] result=split("010800007090176583700059026206573149930601" +
                "025000000000024358060300060400600004873",9);
        NineCells nineCells1=new NineCells(result[0]);
        NineCells nineCells2=new NineCells(result[1]);
        NineCells nineCells3=new NineCells(result[2]);
        NineCells nineCells4=new NineCells(result[3]);
        NineCells nineCells5=new NineCells(result[4]);
        NineCells nineCells6=new NineCells(result[5]);
        NineCells nineCells7=new NineCells(result[6]);
        NineCells nineCells8=new NineCells(result[7]);
        NineCells nineCells9=new NineCells(result[8]);

        NineCells[] nineCells=new NineCells[]{
                nineCells1,
                nineCells2,
                nineCells3,
                nineCells4,
                nineCells5,
                nineCells6,
                nineCells7,
                nineCells8,
                nineCells9
        };

        EightyOneCells sudoku=new EightyOneCells(nineCells);
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
