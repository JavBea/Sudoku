package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义了一个 三元格 类（ ThreeCells ）
 *  --用来构造九宫格的行和列
 * */
public class ThreeCells extends Cell{

    /**
     * 三元格属性声明
     * */
    private SingleCell CellOne,CellTwo,CellThree;


    /**
     * 属性的get方法
     * */
    public ThreeCells(SingleCell cellOne,SingleCell cellTwo,SingleCell cellThree) {
        setCellOne(cellOne);
        setCellTwo(cellTwo);
        setCellThree(cellThree);
    }

    /**
     * 属性的get方法
     * */
    public SingleCell getCellOne() {
        return CellOne;
    }

    public SingleCell getCellTwo() {
        return CellTwo;
    }

    public SingleCell getCellThree() {
        return CellThree;
    }

    /**
     * 属性的set方法
     * */
    public void setCellOne(SingleCell cellOne) {
        CellOne = cellOne;
    }

    public void setCellTwo(SingleCell cellTwo) {
        CellTwo = cellTwo;
    }

    public void setCellThree(SingleCell cellThree) {
        CellThree = cellThree;
    }

    /**
     * 设置成员方法
     * */
    //将单元格设置为确认值
    public boolean confirmCellOne(int num) {
        //尝试给单元格一设置确认值
        boolean result=CellOne.setConfirmedNum(num);
        return result;
    }
    public boolean confirmCellTwo(int num) {
        //尝试给单元格二设置确认值
        boolean result=CellTwo.setConfirmedNum(num);
        return result;
    }
    public boolean confirmCellThree(int num) {
        //尝试给单元格三设置确认值
        boolean result=CellThree.setConfirmedNum(num);
        return result;
    }

    //返回这个三元组确定值的集合
    public List<int> getConfirmedNumSet(){
        List<int> set=new ArrayList<int>();
        int num=0;

        //确认返回值不会是0
        num=CellOne.getConfirmedNum();
        if(num!=0) {
            set.add(num);
        }
        num=CellTwo.getConfirmedNum();
        if(num!=0) {
            set.add(num);
        }
        num=CellThree.getConfirmedNum();
        if(num!=0) {
            set.add(num);
        }

        return set;
    }

    //返回这个三元组待定值的集合
    public List<int> getUndeterminedNumSet(){
        List<int> set=new ArrayList<int>();
        List<int> nums;

        nums=CellOne.getUndeterminedNums();
        // 计算并集
        List<Integer> union = set.stream().distinct().collect(Collectors.toList());
        union.addAll(nums.stream().filter(item -> !set.contains(item)).collect(Collectors.toList()));

        nums=CellTwo.getUndeterminedNums();
        // 计算并集
        union = set.stream().distinct().collect(Collectors.toList());
        union.addAll(nums.stream().filter(item -> !set.contains(item)).collect(Collectors.toList()));

        nums=CellThree.getUndeterminedNums();
        // 计算并集
        union = set.stream().distinct().collect(Collectors.toList());
        union.addAll(nums.stream().filter(item -> !set.contains(item)).collect(Collectors.toList()));

        return set;
    }
}
