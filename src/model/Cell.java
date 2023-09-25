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
