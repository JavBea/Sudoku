package GUI;

import model.EightyOneCells;
import model.NineCells;
import model.SingleCell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SudokuGUI {
    private JFrame frame;
    private JPanel [] panels;
    private JPanel [] mids;
    private JPanel main;
    private JLabel [] labels;
    private EightyOneCells sudoku;

    public SudokuGUI(EightyOneCells sudoku){
        sudoku.setGameGUI(this);
        this.sudoku=sudoku;

        frame=new JFrame("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panels=new JPanel[81];
        mids=new JPanel[9];
        main=new JPanel();
        labels=new JLabel[81];

        main=new JPanel(new GridLayout(3,3));
        main.setBorder(new LineBorder(Color.BLACK,1));

        for(int i=0;i<9;i++){
            mids[i]=new JPanel(new GridLayout(3,3));
            NineCells nineCells=sudoku.getNineCells()[i];

            boolean colorFlag=false;
            if(i%2==0){
                colorFlag=true;
            }

            for(int j=0;j<9;j++){
                SingleCell current=nineCells.getSingleCell(j);
                JLabel label=new JLabel(current.toString());
                if(current.isNotConfirmed()){
                    label.setForeground(new Color(0xFF1C8E91, true));
                }
                else{
                    label.setFont(new Font("Arial",Font.PLAIN,35));
                }
                labels[i*9+j]=label;
                JPanel panel=new JPanel();
                panels[i*9+j]=panel;
                panel.setBorder(new LineBorder(Color.gray,1));

                if(colorFlag){
                    panel.setBackground(new Color(0x76E1DBDB, true));
                }

                panel.add(label);
                panel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.setAlignmentY(Component.CENTER_ALIGNMENT);

                mids[i].add(panel);
            }
            mids[i].setBackground(new Color(0x49888686, true));
            main.add(mids[i]);
        }

        frame.add(main);
        frame.setSize(new Dimension(600,600));
        frame.setVisible(true);
    }
    public void fresh(){

        for(int i=0;i<9;i++){
            NineCells nineCells=sudoku.getNineCells()[i];

            boolean colorFlag=false;
            if(i%2==0){
                colorFlag=true;
            }

            for(int j=0;j<9;j++){
                SingleCell current=nineCells.getSingleCell(j);
                JLabel label=labels[i*9+j];
                label.setText(current.toString());
                if(current.isNotConfirmed()){
                    label.setForeground(new Color(0xFF1C8E91, true));
                }
                else{
                    label.setFont(new Font("Arial",Font.PLAIN,35));
                }
            }
        }
    }
}
