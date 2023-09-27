package model;


import java.util.ArrayList;
import java.util.List;

/**
 * 定义了一个 九宫行 类（ ThreeCellsLine ）
 *  --用来构造完整数独
 * */
public class NineCellsRow extends Cell{


    /**
     * 九宫行属性声明
     * */
    private ThreeCells[] parts;

    /**
     * 定义构造函数
     * */
    public NineCellsRow(ThreeCells[] parts) {
        setParts(parts);
        setLocation();
    }
    public NineCellsRow(ThreeCells partOne,ThreeCells partTwo,ThreeCells partThree) {
        setParts(new ThreeCells[]{partOne,partTwo,partThree});
        setLocation();
    }

    /**
     * 属性的set方法
     * */
    public void setParts(ThreeCells[] parts) {
        this.parts = parts;
    }

    private void setLocation(){
        for(ThreeCells three:parts){
            for(SingleCell cell:three.getCells()){
                cell.setRow(this);
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
    public SingleCell getCell(int col){
        //注意col是列数，是从1开始的
        if(col<4) {
            return getPartOne().getCells()[col-1];
        }
        if(col<7) {
            return getPartTwo().getCells()[col-4];
        }
        if(col<10) {
            return getPartThree().getCells()[col-7];
        }
        return null;
    }

    /**
     * 成员方法
     * */
    //打印方法
    public void println(){
        parts[0].print();
        parts[1].print();
        parts[2].print();
        System.out.println();
    }

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

    //更新九宫行待定值方法
    public void updateUndeterminedSets(int target){
        for(ThreeCells threeCells:parts){
            for(SingleCell cell:threeCells.getCells()){
                if(cell.isNotConfirmed()){
                    cell.getUndeterminedNums().remove(target);
                }
            }
        }
    }

    //更新九宫行除了三元格parts[part]之外另两个三元格的方法
    //part从一开始
    public void updateUndeterminedSetsExcept(int part,int target){

        for(int i=0;i<3;i++){
            //跳过指定的部分
            if(i==part-1){
                continue;
            }

            ThreeCells threeCells=parts[i];
            for(SingleCell cell:threeCells.getCells()){
                if(cell.isNotConfirmed()){
                    cell.getUndeterminedNums().remove(target);
                }
            }
        }
    }

}
