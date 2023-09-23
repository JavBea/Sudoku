package model;

/**
 * 定义了一个 九宫列 类（ ThreeCellsCol ）
 *  --用来构造完整数独
 * */
public class NineCellsCol {

    /**
     * 九宫列属性声明
     * */
    private ThreeCells[] parts;

    /**
     * 定义构造函数
     * */
    public NineCellsCol(ThreeCells[] parts) {
        setParts(parts);
    }
    public NineCellsCol(ThreeCells partOne,ThreeCells partTwo,ThreeCells partThree) {
        setParts(new ThreeCells[]{partOne,partTwo,partThree});
    }

    /**
     * 属性的set方法
     * */
    public void setParts(ThreeCells[] parts) {
        this.parts = parts;
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
}
