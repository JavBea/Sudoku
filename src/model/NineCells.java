package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义了一个 九宫格 类（ NineCells ）
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
        //输入长度不为9
        if(singleCells.length!=9){
            System.out.println("Error:九宫格初始化必须有九个格子");
            return;
        }
        //检查输入的数字
        boolean isValid=check(singleCells);
        if(!isValid) {
            System.out.println("Wrong: 九宫格中出现了相同数字\t-----NineCells");
        }

        setSingleCells(singleCells);
        setRowsAndCols();
    }
    public NineCells(int[] nums) {
        //输入长度不为9
        if(nums.length!=9){
            System.out.println("Error:九宫格初始化必须有九个数");
            return;
        }
        singleCells=new SingleCell[9];
        for(int i=0;i<9;i++){
            singleCells[i]=new SingleCell(9,nums[i]);
        }

        //检查输入的数字
        boolean isValid=check(singleCells);
        if(!isValid) {
            System.out.println("Wrong: 九宫格中出现了相同数字\t-----NineCells");
        }

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
        rows=new ThreeCells[3];
        cols=new ThreeCells[3];
        for(Integer i=0;i<3;i++){
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
    public SingleCell getSingleCell(int index){
        return singleCells[index];
    }
    public ThreeCells[] getRows() {
        return rows;
    }
    public ThreeCells getRow(int i){return rows[i];}
    public ThreeCells[] getCols() {
        return cols;
    }
    public ThreeCells getCol(int i) {
        return cols[i];
    }

    /**
     * 成员方法
     * */

    //得到某一行的确定值，此时确认值不仅包括已经确认值，
    // 还包含半确认值（即虽未确定，但位置固定在九宫格的某一行或某一列）
    public List<Integer> getRowSet(Integer rowIndex){
        List<Integer> indexs=new ArrayList<Integer>();
        indexs.add(1);indexs.add(2);indexs.add(3);indexs.remove(rowIndex);

        List<Integer> setOne=rows[indexs.get(0)].getUndeterminedNumSet();
        List<Integer> setTwo=rows[indexs.get(1)].getUndeterminedNumSet();
        // 计算并集
        List<Integer> union = setTwo.stream().distinct().collect(Collectors.toList());
        union.addAll(setOne.stream().filter(item -> !setTwo.contains(item)).collect(Collectors.toList()));

        List<Integer> setThree=rows[rowIndex].getUndeterminedNumSet();
        setThree.removeAll(union);
        // 计算并集
        List<Integer> setFour = setThree.stream().distinct().collect(Collectors.toList());
        union.addAll(rows[rowIndex].getConfirmedNumSet().stream().filter(item -> !setThree.contains(item)).collect(Collectors.toList()));

        List<Integer> list = new ArrayList<Integer>();
        for (Integer value : setFour) {
            list.add(value);
        }
        return list;
    }

    //得到当前单元格已经确定的数字集
    public List<Integer> getConfirmedSet(){
        List<Integer> set=rows[0].getConfirmedNumSet();
        set=merge(set,rows[1].getConfirmedNumSet());
        set=merge(set,rows[2].getConfirmedNumSet());
        return set;
    }

    //检查一个九个单元格的确认数字是否重复
    public static boolean check(SingleCell []singleCells){
        boolean result=true;
        List<Integer> set=new ArrayList<Integer>();
        for(SingleCell cell:singleCells){
            int num=cell.getConfirmedNum();
            //如果为0
            if(num==0){
                continue;
            }
            //如果已经存在
            else if(set.indexOf(num)>=0){
                result=false;
                break;
            }
            set.add(num);
        }
        return result;
    }

    public void print(){
        for(ThreeCells threeCells:rows){
            for(SingleCell cell:threeCells.getCells())
            {
                System.out.print(cell.getConfirmedNum()+"\t");
            }
            System.out.println();
        }
    }
}
