package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义了一个 格 类（ Cell ）
 *  --抽象的格类
 *  --是其他格类（单元格、四宫格、九宫格）的超类
 * */
public abstract class Cell {


    //将两个List进行集合合并的方法
    public List<Integer> merge(List<Integer> listOne, List<Integer> listTwo){
        List<Integer> result=new ArrayList<>();

        //处理空值
        if(listOne==null){
            return listTwo;
        }
        if(listTwo==null){
            return listOne;
        }

        //合并到一个新数组中
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
