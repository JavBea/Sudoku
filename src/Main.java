import model.*;
import GUI.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String []args) throws InterruptedException {
//        try
//        {
//            System.setProperty("sun.java2d.noddraw","true");
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//            UIManager.put("RootPane.setupButtonVisible",false);
//        }
//        catch(Exception e)
//        {
//            //TODO exception
//        }
        String target="010800007090176583700059026206573149930601025000000000024358060300060400600004873";
        int[][] result=split(target,9);
        EightyOneCells sudoku=new EightyOneCells(result);
        sudoku.print();
        sudoku.initUndeterminedNumSet();

//        SwingUtilities.invokeLater(() -> {
//            SudokuGUI game=new SudokuGUI(sudoku);
//            sudoku.numInOneRow();
//        });

//        sleep(3000);
//        game.fresh();
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
    //将两个List进行集合合并的方法
    public static List<Integer> merge(List<Integer> listOne, List<Integer> listTwo){
        List<Integer> result=new ArrayList<Integer>();
        for(Integer i:listOne){
            if(result.indexOf(i)<0){
                result.add(i);
            }
        }
        for(Integer i:listTwo){
            if(result.indexOf(i)<0){
                result.add(i);
            }
        }
        return result;
    }
}
