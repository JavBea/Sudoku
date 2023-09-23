import model.*;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String []args)
    {
        SingleCell c1=new SingleCell(9,1);
        SingleCell c2=new SingleCell(9,2);
        SingleCell c3=new SingleCell(9,9);
        SingleCell c4=new SingleCell(9,8);
        SingleCell c5=new SingleCell(9,7);
        SingleCell c6=new SingleCell(9,4);
        SingleCell c7=new SingleCell(9,3);
        SingleCell c8=new SingleCell(9,6);
        SingleCell c9=new SingleCell(9,5);

        SingleCell []cells=new SingleCell[9];
        cells[0]=c1;
        cells[1]=c2;
        cells[2]=c3;
        cells[3]=c4;
        cells[4]=c5;
        cells[5]=c6;
        cells[6]=c7;
        cells[7]=c8;
        cells[8]=c9;

        NineCells nineCells=new NineCells(cells);
        nineCells.print();
    }
}
