package model;

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
    public
}
