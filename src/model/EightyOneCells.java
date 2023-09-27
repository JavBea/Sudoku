package model;
import GUI.SudokuGUI;
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
    private SudokuGUI gameGUI;//所在的游戏窗口

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
    public EightyOneCells(String target) {
        if(target.length()!=81){
            System.out.println("Wrong:必须含有81格\t-----EightOneCells");
            return;
        }
        int[][] result=split(target,9);

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

        setNineCells(nineCells);
        setRowsAndCols();

        if(!check()){
            System.out.println("Wrong:出现重复数字！\t-----EightOneCells");
            return;
        }
    }
    public EightyOneCells(int[][] result) {
//        if(result.length!=9 || result[0].length!=9){
//            System.out.println("Wrong:必须含有81格\t-----EightOneCells");
//            return;
//        }

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

        setNineCells(nineCells);
        setRowsAndCols();

        if(!check()){
            System.out.println("Wrong:出现重复数字！\t-----EightOneCells");
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
                        nineCells[i].getCol(j)
                        ,nineCells[i+3].getCol(j)
                        ,nineCells[i+6].getCol(j)
                );
            }
        }
    }

    public void setGameGUI(SudokuGUI gameGUI) {
        this.gameGUI = gameGUI;
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

    public SudokuGUI getGameGUI() {
        return gameGUI;
    }

    /**
     * 成员方法
     * */
    //打印方法
    public void print(){
        for(NineCellsRow row:nineCellsRows){
            row.println();
        }
    }

    //检查方法
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
        for(NineCells nine:nineCells)
        {
            result=result && NineCells.check(nine.getSingleCells());
        }
        return result;
    }

    //得到某一个单元格的方法,按坐标
    public SingleCell getSingleCell(int row,int col){
        return nineCellsRows[row-1].getCell(col);//row需要减一
    }

    //得到某一个单元格的方法,按坐标
    public SingleCell getSingleCell(int index){
        int row=index/9;
        int col=index%9;
        return nineCellsRows[row].getCell(col+1);//此时row不需要减一,col需要加一
    }

    //为单元格设定确认值，并改变所在行、列、九宫格其他格的待定值的方法
    //提供两种参数输入方式，包括输入单元格坐标，和单元格引用
    //注意row和col都是从1开始的
    public boolean setConfirmed(int row,int col,int num){
        boolean result=true;
        if(num==0){
            //SingleCell的setConfirmedNum方法允许赋值0,提前单独处理
            System.out.println("Wrong:不能赋值0\t-----EightOneCells");
            return false;
        }
        //得到单元格
        SingleCell current=nineCellsRows[row].getCell(col);
        //设置确认数
        result=current.setConfirmedNum(num);

        //执行待定值更新方法
        //九宫格更新
        current.getNineCells().updateUndeterminedSets(num);
        //九宫行更新
        current.getRow().updateUndeterminedSets(num);
        //九宫列更新
        current.getCol().updateUndeterminedSets(num);

        return result;
    }
    public boolean setConfirmed(SingleCell current,int num){
        boolean result=true;
        if(num==0){
            //SingleCell的setConfirmedNum方法允许赋值0,提前单独处理
            System.out.println("Wrong:不能赋值0\t-----EightOneCells");
            return false;
        }
        //设置确认数
        result=current.setConfirmedNum(num);

        //执行待定值更新方法
        //九宫格更新
        current.getNineCells().updateUndeterminedSets(num);
        //九宫行更新
        current.getRow().updateUndeterminedSets(num);
        //九宫列更新
        current.getCol().updateUndeterminedSets(num);

        return result;
    }

    //初始化待定值方法
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
                List<Integer> undeterminedNumSet=new ArrayList<>(all);

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
                undeterminedNumSet.removeAll(unAvailNumSet);
                //设置待定值
//                nineCells[i].getSingleCell(j).setUndeterminedNums(undeterminedNumSet);
                current.setUndeterminedNums(undeterminedNumSet);
            }
        }
    }

    //简化待定集方法
    public void simplifyUndeterminedNumSet(){

    }

    //数独自动求解方法
    public boolean autoSolve(){
        //求解结果，成功求解为true，否则为false
        boolean result=false;

        return false;
    }


    /**
     * 待定值化简方法集
     *  --在已经初始化的基础上简化单元格的待定值集
     *  --方法simplifyUndeterminedNumSet会用到的化简方法集
     * */

    //当一个九宫格的某一个三元行的待定值合集有其他两行未有的值，则该值确定在该行
    public void numInOneRow(){
        //遍历九宫格集
        for(int i=0;i<9;i++){
            NineCells current=nineCells[i];
            for(int[] target:current.getRowHalfConfirmedNumSet()){
                //下面方法调用的路径：
                // 当前九宫格 => 九宫格的三元行 => 三元行的第一个单元格 => 单元格的所在九宫行 => 九宫行的更新方法
                //target的第一位是行数（1开始）,第二位是目标数
                current.getRow(target[0]-1).getCellOne().getRow().updateUndeterminedSetsExcept(i%3+1,target[1]);
            }
            for(int[] target:current.getColHalfConfirmedNumSet()){
                //下面方法调用的路径：
                // 当前九宫格 => 九宫格的三元行 => 三元行的第一个单元格 => 单元格的所在九宫行 => 九宫行的更新方法
                //target的第一位是行数（1开始）,第二位是目标数
                current.getCol(target[0]-1).getCellOne().getCol().updateUndeterminedSetsExcept(i%3+1,target[1]);
            }
        }
    }

    /**
     * 求解方法集
     *  --方法autoSolve会用到的求解方法集
     * */
    //寻找并确认只有一个待定值的单元格，此时其必为该值
    private void onlyPossibilityForOneCell(){
        for(NineCells nine:nineCells){
            for(SingleCell cell:nine.getSingleCells()){
                //单元格待定
                if(cell.isNotConfirmed()){
                    //单元格待定数组长度为0
                    if(cell.getUndeterminedNums().size()==1){
                        //设置确认值
                        setConfirmed(cell,cell.getUndeterminedNums().get(0));
                    }
                }
            }
        }
    }

}
