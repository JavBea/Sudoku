package model;

import java.util.List;

/**
 * 定义了一个 九宫列 类（ ThreeCellsCol ）
 *  --用来构造完整数独
 * */
public class NineCellsCol extends Cell{

    /**
     * 九宫列属性声明
     * */
    private ThreeCells[] parts;

    /**
     * 定义构造函数
     * */
    public NineCellsCol(ThreeCells[] parts) {
        setParts(parts);
        setLocation();
    }
    public NineCellsCol(ThreeCells partOne,ThreeCells partTwo,ThreeCells partThree) {
        setParts(new ThreeCells[]{partOne,partTwo,partThree});
        setLocation();
    }

    /**
     * 属性的set方法
     * */
    public void setParts(ThreeCells[] parts) {
        this.parts = parts;
    }

    //为单元格设置行坐标
    private void setLocation(){
        for(ThreeCells three:parts){
            for(SingleCell cell:three.getCells()){
                cell.setCol(this);
            }
        }
    }

    /**
     * 属性的get方法
     * */
    public ThreeCells[] getParts() {
        return parts;
    }
    public ThreeCells getPartOne(){
        return parts[0];
    }
    public ThreeCells getPartTwo(){
        return parts[1];
    }
    public ThreeCells getPartThree(){
        return parts[2];
    }

    /**
     * 成员方法
     * */

    //检查方法
    public boolean check(){
        List<Integer> set=parts[0].getConfirmedNumSet();
        for(int i:parts[1].getConfirmedNumSet()){
            if(set.indexOf(i)>0){
                return false;
            }else{
                set.add(i);
            }
        }
        for(int i:parts[2].getConfirmedNumSet()){
            if(set.indexOf(i)>0){
                return false;
            }else{
                set.add(i);
            }
        }
        return true;
    }
    //得到当前行的已确定数集合
    public List<Integer> getConfirmedNumSet(){
        List<Integer> set=parts[0].getConfirmedNumSet();
        set=merge(set,parts[1].getConfirmedNumSet());
        set=merge(set,parts[2].getConfirmedNumSet());
        return set;
    }
}
