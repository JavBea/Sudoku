package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义了一个 九宫格 类（ ThreeCells ）
 *  --用来构造完整数独
 * */
public class NineCells extends Cell{


    /**
     * 九宫格属性声明
     * */
    private SingleCell []  singleCells;//共有九个单元格
    private ThreeCells [] cols;//有三列，且由刚才的单元格构成
    private ThreeCells [] rows;//有三行，且由刚才的单元格构成


    /**
     * 定义构造函数
     * */

    public NineCells(SingleCell[] singleCells) {
        setSingleCells(singleCells);
        setRowsAndCols();
    }

    /**
     * 属性的set方法
     * */
    public boolean setSingleCells(SingleCell[] singleCells) {
        //如果单元格集合为空，或数量不是9
        if(singleCells==null || singleCells.length!=9){
            return false;
        }
        this.singleCells = singleCells;
        return true;
    }

    private void setRowsAndCols() {
        for(int i=0;i<3;i++){
            //赋值行
            rows[i]=new ThreeCells(singleCells[i*3],singleCells[i*3+1],singleCells[i*3+2]);
            //赋值列
            cols[i]=new ThreeCells(singleCells[i],singleCells[i+3],singleCells[i+6]);
        }
    }

    /**
     * 属性的get方法
     * */
    public SingleCell[] getSingleCells() {
        return singleCells;
    }
    public ThreeCells[] getRows() {
        return rows;
    }
    public ThreeCells[] getCols() {
        return cols;
    }

    /**
     * 成员属性
     * */

    //得到某一行的确定值，此时确认值不仅包括已经确认值，
    // 还包含半确认值（即虽未确定，但位置固定在九宫格的某一行或某一列）
    public List<int> getRowSet(int rowIndex){
        List<int> indexs=new ArrayList<int>();
        indexs.add(1);indexs.add(2);indexs.add(3);indexs.remove(rowIndex);

        List<int> setOne=rows[indexs.get(0)].getUndeterminedNumSet();
        List<int> setTwo=rows[indexs.get(1)].getUndeterminedNumSet();
        // 计算并集
        List<Integer> union = setTwo.stream().distinct().collect(Collectors.toList());
        union.addAll(setOne.stream().filter(item -> !setTwo.contains(item)).collect(Collectors.toList()));

        List<int> setThree=rows[rowIndex].getUndeterminedNumSet();
        setThree.removeAll(union);
        // 计算并集
        List<Integer> setFour = setThree.stream().distinct().collect(Collectors.toList());
        union.addAll(rows[rowIndex].getConfirmedNumSet().stream().filter(item -> !setThree.contains(item)).collect(Collectors.toList()));

        List<int> list = new ArrayList<int>();
        for (Integer value : setFour) {
            list.add(value);
        }
        return list;
    }
}
