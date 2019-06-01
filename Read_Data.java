package example.dataAnalyse;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Read_Data {
    public ArrayList<String []> List = new ArrayList<String[]>(); //the data is collected in type string, should convert to Integer before generating image

    public String[] head;
    public double[][] csvData;
    public List<Integer> anyCol = new ArrayList<>();
    public String filePath;
    //ROW and COL of the .csv file
    public int ROW;
    public int COL;

    String[] prep = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
            "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27",
            "28","29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40","41", "42"};

    String[] colKeys;

    public Read_Data(){ }

    public Read_Data(String filePath) throws IOException {
        this.filePath = filePath ;
        GenerateData(filePath);
        colKeys=new String[ROW];
        for(int i = 0; i<ROW; i++){//所谓的根据不同长度的数据变化
            colKeys[i] = prep[i];
        }
    }


    public double[] getColumnData(int col){
        double[] colData= new double[ROW];
        for(int  j =0;j <ROW;j ++){
            colData[j] = csvData [j][col];
        }
        return colData;
    }

    public void changePath(String url) throws IOException {
        this.filePath = url ;
        GenerateData(url);
        colKeys=new String[ROW];
        for(int i = 0; i<ROW; i++){//所谓的根据不同长度的数据变化
            colKeys[i] = prep[i];
        }

    }

    public void GenerateData(String csvPath ) throws IOException { //将整个表存在double[][]里面最好

        String readerCsvFilePath = csvPath; //"/Users/tscheung/Desktop/dataAnalysisCode/test3.csv";
        CsvReader csvReader = new CsvReader(readerCsvFilePath, ',', Charset.forName("UTF-8"));
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        head = csvReader.getHeaders(); //获取表头


        //读取列数据
        while(csvReader.readRecord()) {//读取所有
            List.add(csvReader.getValues());
        }
//        System.out.println("row = "+  List.size()); //31 说明有32 行数据
//        System.out.println("Col = " + List.get(0).length); //38 说明有39列数据
        //实际上只有29 x 38的数据（R中）， 所以java中是28 x 37

        //确认不记 空 的行数列数
        int m = 0;//row

        for(int row =0 ;row < List.size(); row++){
            if(List.get(row)[1]==""){
                List.get(row)[0]=""; //把时间戳改成""
                continue; //这里不能写【0】 因为第一个是时间戳，不为空
            }
            m++;
        }
        ROW = m;
        COL= List.get(0).length - 1;

        int x=0;
        int y=0;
        csvData=new double[ROW][COL]; //28 是删掉两行空的， 37是删掉了第一列时间，本来是28X37

        for(int col=0;col<List.get(0).length-1 ;col++){

            for(int i= 0 ;i< List.size()-1 ;i++){ //忽略第一行时间轴
//                if(List.get(i)[col]=="")break;
//                else if(y<COL){
//                    csvData[x][y] = Double.parseDouble(List.get(i)[col+1]);
//                    System.out.println(csvData[x][y]);
//                    y++;
//                }
//                else{//走到了某一行的最后一列，要把y归零然后x++
//                    csvData[x][y] = Double.parseDouble(List.get(i)[col+1]);
//                    System.out.println("这是最后一列" + csvData[x][y]);
//                    y=0;
//                    x++;
//                }



                if(List.get(i)[col]=="")continue;

                else{
                    csvData[x][y] = Double.parseDouble(List.get(i)[col+1]);
                    x++;
                    if(x==ROW){
                        x=0;
                        y++;
                    }
                }

            }


        }

        csvReader.close();
//                for (int row = 0;row < List.size(); row++) {
//            int Length=List.get(row).length;
//            if(Length > 0){
//                for(int i=0;i<Length;i++){
//                    System.out.print(List.get(row)[i]+",");
//                }//for
//
//            }//if
//            System.out.println("");
//        }//for
    }


    /**
     * 直接把生成TP9， AF7等的方法写出来
     * col0: Delta_TP9,  col4: Theta_TP9,  col8: Alpha_TP9,  col12: Beta_TP9,  col16: Gamma_TP9
     * col1: Delta_AF7,  col5: Theta_AF7,  col9: Alpha_AF7,  col13: Beta_AF7,  col17: Gamma_AF7
     * col2: Delta_AF8,  col6: Theta_AF8,  col10:Alpha_AF8,  col14: Beta_AF8,  col18: Gamma_AF8
     * col3: Delta_TP10, col7: Theta_TP10, col11:Alpha_TP10, col15: Beta_TP10, col19: Gamma_TP10
     *
     */
    public CategoryDataset createTP9() throws IOException { //这里要改成能控制col选择的数目

        //GenerateData(filePath); //get csv file

        String[] rowKeys = {head[1], head[5], head[9], head[13],head[17] }; //这里改成可以修改的

        double[][] data = {getColumnData(0), getColumnData(4), getColumnData(8) ,getColumnData(12), getColumnData(16) };
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

    public CategoryDataset createAF7() throws IOException { //这里要改成能控制col选择的数目

        String[] rowKeys = {head[2], head[6], head[10], head[14],head[18] }; //这里改成可以修改的

        double[][] data = {getColumnData(1), getColumnData(5), getColumnData(9) ,getColumnData(13), getColumnData(17) };

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }


    public CategoryDataset createAF8() throws IOException { //这里要改成能控制col选择的数目

        String[] rowKeys = {head[3], head[7], head[11], head[15],head[19] }; //这里改成可以修改的

        double[][] data = {getColumnData(2), getColumnData(6), getColumnData(10) ,getColumnData(14), getColumnData(18) };

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

    public CategoryDataset createTP10() throws IOException { //这里要改成能控制col选择的数目

        String[] rowKeys = {head[4], head[8], head[12], head[16],head[20] }; //这里改成可以修改的

        double[][] data = {getColumnData(3), getColumnData(7), getColumnData(11) ,getColumnData(15), getColumnData(19) };

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

    public CategoryDataset createLines() throws IOException { //这里要改成能控制col选择的数目


        if(anyCol.size()==1){
            String[] rowKeys = {head[anyCol.get(0)+1] };
            double[][] data = {getColumnData(anyCol.get(0) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        if(anyCol.size()==2){
            String[] rowKeys = {head[anyCol.get(0)+1], head[anyCol.get(1)+1]  };
            double[][] data = {getColumnData(anyCol.get(0) ), getColumnData(anyCol.get(1) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        if(anyCol.size()==3){
            String[] rowKeys = {head[anyCol.get(0)+1], head[anyCol.get(1)+1], head[anyCol.get(2)+1]  };
            double[][] data = {getColumnData(anyCol.get(0) ), getColumnData(anyCol.get(1) ), getColumnData(anyCol.get(2) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        if(anyCol.size()==4){
            String[] rowKeys = {head[anyCol.get(0)+1], head[anyCol.get(1)+1], head[anyCol.get(2)+1],  head[anyCol.get(3)+1]  };
            double[][] data = {getColumnData(anyCol.get(0) ), getColumnData(anyCol.get(1) ), getColumnData(anyCol.get(2) ), getColumnData(anyCol.get(3) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        //max 5 cols
        else {
            String[] rowKeys = {head[anyCol.get(0)+1], head[anyCol.get(1)+1], head[anyCol.get(2)+1], head[anyCol.get(3)+1],head[anyCol.get(4)+1] };
            double[][] data = {getColumnData(anyCol.get(0)), getColumnData(anyCol.get(1)), getColumnData(anyCol.get(2)) ,getColumnData(anyCol.get(3)), getColumnData(anyCol.get(4)) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }

    }

    public CategoryDataset createPlot(int index){ //可处理某一条数据的直方图 Affichage d'Histogram
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int len = getColumnData(index).length;

        double[] val = getColumnData(index);

        for(int i=0; i<len; i++){
            dataset.addValue(val[i], head[index+1], Integer.toString(i)); //ici 3 input, val[i] est la valuer correspondant, head[index] est pour l'annotation, etle dernier est Axes x
            //最后一个输入的类似于横坐标，如果我全部输入一样的，他就只会显示一条线

        }


        return dataset;
    }


}

