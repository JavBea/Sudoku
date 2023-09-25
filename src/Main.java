import model.*;

public class Main {
    public static void main(String []args)
    {


        NineCells nineCells1=new NineCells(new int[]{1,2,3,4,5,6,7,8,9});
        NineCells nineCells2=new NineCells(new int[]{2,3,4,5,6,7,8,9,1});
        NineCells nineCells3=new NineCells(new int[]{3,4,5,6,7,8,9,1,2});
        NineCells nineCells4=new NineCells(new int[]{4,5,6,7,8,9,1,2,3});
        NineCells nineCells5=new NineCells(new int[]{5,6,7,8,9,1,2,3,4});
        NineCells nineCells6=new NineCells(new int[]{6,7,8,9,1,2,3,4,5});
        NineCells nineCells7=new NineCells(new int[]{7,8,9,1,2,3,4,5,6});
        NineCells nineCells8=new NineCells(new int[]{8,9,1,2,3,4,5,6,7});
        NineCells nineCells9=new NineCells(new int[]{9,1,2,3,4,5,6,7,8});

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

        EightyOneCells sudoku=new EightyOneCells(nineCells);
        sudoku.print();
    }
}
