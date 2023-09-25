package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义了一个 单元格 类（ SingleCell ）
 *  --最基础的单元格类
 *  --用来构造其他格，四宫格、九宫格等
 * */
public class SingleCell  extends Cell{

    /**
     * 单元格属性声明
     * */
    private boolean isConfirmed;//单元格状态：确定为true, 待定为false;默认为false
    private Integer domain;//域：是单元格数字的值域,存储正整数的上限，即 0 < numInCell <=domain
    private Integer confirmedNum=0;//确认值：本单元格的数值，在单元格状态为”确定“（true）的时候有效,且取值在domain中;默认为0
    private List<Integer> undeterminedNums;//待定值：待选的数值，status为false时可用，取值在domain中
    private NineCellsRow row;//单元格所在行
    private NineCellsCol col;//单元格所在列


    /**
     * 定义构造函数
     * */
    public SingleCell(boolean isConfirmed,Integer domain,Integer confirmedNum,List<Integer> undeterminedNums) {
        setConfirmed(isConfirmed);
        setDomain(domain);
        setConfirmedNum(confirmedNum);
        setUndeterminedNums(undeterminedNums);
    }
    public SingleCell(Integer domain,List<Integer> undeterminedNums) {
        setConfirmed(false);
        setDomain(domain);
        setUndeterminedNums(undeterminedNums);
    }
    public SingleCell(Integer domain,Integer confirmedNum) {
        setConfirmed(true);
        setDomain(domain);
        setConfirmedNum(confirmedNum);
    }
    public SingleCell(Integer domain) {
        setDomain(domain);
    }


    /**
      * 属性的get方法
      * */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    public boolean isNotConfirmed() {
        return isConfirmed;
    }

    public Integer getDomain() {
        return domain;
    }

    public Integer getConfirmedNum() {
        //如果单元格值已确定,返回确定值
        if(isConfirmed || confirmedNum==0) {
            return confirmedNum;
        }
        //单元格的值未确定，返回0
        return 0;
    }

    public List<Integer> getUndeterminedNums() {
        //如果单元格值未确定,返回待定值
        if(isConfirmed) {
            return undeterminedNums;
        }
        //单元格的值已确定，返回null
        System.out.print("SingleCell Setup Error: Cell Confirmed already.\t-----SingleCell");
        return null;
    }

    public NineCellsRow getRow() {
        return row;
    }

    public NineCellsCol getCol() {
        return col;
    }

    /**
     * 属性的set方法
     * */
    public void setConfirmed(boolean confirmed) {
        this.isConfirmed = confirmed;
    }

    public void setDomain(Integer domain) {
        //检查是否为整数的二次幂
        Integer s=(int)(Math.sqrt((double)domain));
        if(s*s!=domain)
        {
            this.domain=9;
            System.out.println("SingleCell Setup Error: Domain should be the square of some positive Integereger." +
                    "\n default: domain=9\t-----SingleCell");
            return;
        }
        this.domain = domain;
    }

    public boolean setConfirmedNum(Integer confirmedNum) {

        //如果domain未初始化
        if(domain==0) {
            System.out.print("SingleCell Setup Error: Domain Uninitialized.\t-----SingleCell");
            return false;
        }

        //如果确认数不是正数
        if(confirmedNum<0) {
            System.out.print("SingleCell Setup Error: ConfirmedNum Is Not Positive.\t-----SingleCell");
            return false;
        }

        //如果确认数超出域
        if(confirmedNum>domain) {
            System.out.print("SingleCell Setup Error: ConfirmedNum out of domain.\t-----SingleCell");
            return false;
        }

        this.confirmedNum = confirmedNum;

        //如果输入的确认数为0，视为未确认
        if(confirmedNum==0){
            this.isConfirmed = false;
        }else{
            this.isConfirmed=true;
        }
        return true;
    }

    public boolean setUndeterminedNums(List<Integer> undeterminedNums) {
        //如果domain未初始化
        if(domain==0) {
            System.out.print("SingleCell Setup Error: Domain Uninitialized.\t-----SingleCell");
            return false;
        }

        //遍历待定数集
        for(Integer num:undeterminedNums) {

            //如果待定数不是正数
            if(num<=0) {
                System.out.print("SingleCell Setup Error: ConfirmedNum Is Not Positive.\t-----SingleCell");
                return false;
            }

            //如果待定数超出域
            if(num>domain) {
                System.out.print("SingleCell Setup Error: ConfirmedNum out of domain.\t-----SingleCell");
                return false;
            }
        }
        this.undeterminedNums=new ArrayList<>();
        for(int i:undeterminedNums)
        {
            this.undeterminedNums.add(i);
        }
        return true;
    }

    public void setRow(NineCellsRow row) {
        this.row = row;
    }

    public void setCol(NineCellsCol col) {
        this.col = col;
    }

}
