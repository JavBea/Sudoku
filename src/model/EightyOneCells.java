package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义了一个 八十一宫行 类（ EightOneCells ）
 *  --即一个完整的九乘九数独
 * */
public class EightyOneCells extends Cell{


    /**
     * 八十一宫格属性声明
     * */
    private NineCells[] nineCells;
    private NineCellsRow[] nineCellsRows;
    private NineCellsCol[] nineCellsCols;

    /**
     * 定义构造函数
     * */
    public EightyOneCells(NineCells[] nineCells) {
        if(nineCells.length!=9){
            System.out.println("Wrong:必须为九个九宫格\t-----EightOneCells");
            return;
        }

        setNineCells(nineCells);
        setRowsAndCols();

        if(!check()){
            System.out.println("Wrong:出现重复数字！\t-----EightOneCells");
            return;
        }
    }

    /**
     * 属性的set方法
     * */
    public void setNineCells(NineCells[] nineCells) {
        nineCellsRows=new NineCellsRow[9];
        nineCellsCols=new NineCellsCol[9];
        this.nineCells=nineCells;
    }
    private void setRowsAndCols(){
        nineCellsRows=new NineCellsRow[9];
        nineCellsCols=new NineCellsCol[9];
        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {

                nineCellsRows[i*3+j]=new NineCellsRow(
                         nineCells[i*3].getRow(j)
                        ,nineCells[i*3+1].getRow(j)
                        ,nineCells[i*3+2].getRow(j)
                );

                nineCellsCols[i*3+j]=new NineCellsCol(
                        nineCells[i*3].getCol(j)
                        ,nineCells[i*3+1].getCol(j)
                        ,nineCells[i*3+2].getCol(j)
                );
            }
        }
    }

    /**
     * 属性的get方法
     * */
    public NineCells[] getNineCells() {
        return nineCells;
    }

    public NineCellsRow[] getNineCellsRows() {
        return nineCellsRows;
    }

    public NineCellsCol[] getNineCellsCols() {
        return nineCellsCols;
    }

    /**
     * 成员方法
     * */
    public void print(){
        for(NineCellsRow row:nineCellsRows){
            row.println();
        }
    }

    public boolean check(){
        boolean result=true;
        for(NineCellsRow row:nineCellsRows)
        {
            result=result && row.check();
        }
        for(NineCellsCol col:nineCellsCols)
        {
            result=result && col.check();
        }
        return result;
    }

    public void initUndeterminedNumSet(){
        //设置全集
        List<Integer> all=new ArrayList<>();
        all.add(1);all.add(2);all.add(3);
        all.add(4);all.add(5);all.add(6);
        all.add(7);all.add(8);all.add(9);

        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                SingleCell current=nineCells[i].getSingleCell(j);
                //如果单元格已经确定了
                if(current.isConfirmed()) {
                    continue;
                }
                //得到九宫格确认值
                List<Integer> unAvailNumSet=nineCells[i].getConfirmedSet();
                //得到九宫行确认值
                unAvailNumSet=merge(unAvailNumSet,current.getRow().getConfirmedNumSet());
                //得到九宫列确认值
                unAvailNumSet=merge(unAvailNumSet,current.getCol().getConfirmedNumSet());
                //移除确认值
                all.removeAll(unAvailNumSet);
                //设置待定值
                current.setUndeterminedNums(all);
            }
        }
    }

}
