package example.dataAnalyse;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.jfree.data.category.CategoryDataset;
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

    public Read_Data(){ }

    public Read_Data(String filePath) throws IOException {
        this.filePath = filePath ;
        GenerateData(filePath);
    }


    public double[] getColumnData(int col){
        double[] colData= new double[28];
        for(int  j =0;j <28;j ++){
            colData[j] = csvData [j][col];
        }
        return colData;
    }

    public void GenerateData(String csvPath ) throws IOException { //将整个表存在double[][]里面最好

        String readerCsvFilePath = csvPath; //"/Users/tscheung/Desktop/dataAnalysisCode/test3.csv";
        CsvReader csvReader = new CsvReader(readerCsvFilePath, ',', Charset.forName("UTF-8"));
        csvReader.readHeaders(); // 跳过表头   如果需要表头的话，不要写这句。
        head = csvReader.getHeaders(); //获取表头
        csvData=new double[28][37]; //28 是删掉两行空的， 37是删掉了第一列时间，本来是28X37

        //读取列数据
        while(csvReader.readRecord()) {//读取所有
            List.add(csvReader.getValues());
        }
//        for (int row = 0;row < List.size(); row++) {
//            int Length=List.get(row).length;
//            if(Length > 0){
//                for(int i=0;i<Length;i++){
//                    System.out.print(List.get(row)[i]+",");
//                }//for
//
//            }//if
//            System.out.println("");
//        }//for

        for(int col=0;col<37;col++){
            for(int i= 0 ;i<30;i++){ //28 is the length after deleting the null data
                if(i==2 || i==3)continue;
                //System.out.println(List.get(i)[col]+",");
                if(i==0 || i ==1)csvData[i][col] = Double.parseDouble(List.get(i)[col+1]);
                if(i>3)csvData[i-2][col] = Double.parseDouble(List.get(i)[col+1]);
            }
        }
        //按行读取所有数据
//        while (csvReader.readRecord())
//        {
//            for (int i = 0; i < head.length; i++)
//            {
//                System.out.println(head[i] + ":" + csvReader.get(head[i]));
//            }
//
//        }
        csvReader.close();
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
        String[] colKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"}; //横坐标

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
        String[] colKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"}; //横坐标

        String[] rowKeys = {head[2], head[6], head[10], head[14],head[18] }; //这里改成可以修改的

        double[][] data = {getColumnData(1), getColumnData(5), getColumnData(9) ,getColumnData(13), getColumnData(17) };

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }


    public CategoryDataset createAF8() throws IOException { //这里要改成能控制col选择的数目
        String[] colKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"}; //横坐标

        String[] rowKeys = {head[3], head[7], head[11], head[15],head[19] }; //这里改成可以修改的

        double[][] data = {getColumnData(2), getColumnData(6), getColumnData(10) ,getColumnData(14), getColumnData(18) };

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

    public CategoryDataset createTP10() throws IOException { //这里要改成能控制col选择的数目
        String[] colKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"}; //横坐标

        String[] rowKeys = {head[4], head[8], head[12], head[16],head[20] }; //这里改成可以修改的

        double[][] data = {getColumnData(3), getColumnData(7), getColumnData(11) ,getColumnData(15), getColumnData(19) };

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }

    public CategoryDataset createLines() throws IOException { //这里要改成能控制col选择的数目
        String[] colKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"}; //横坐标

        if(anyCol.size()==1){
            String[] rowKeys = {head[anyCol.get(0)+1] };
            double[][] data = {getColumnData(anyCol.get(0) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        else if(anyCol.size()==2){
            String[] rowKeys = {head[anyCol.get(0)+1], head[anyCol.get(1)+1]  };
            double[][] data = {getColumnData(anyCol.get(0) ), getColumnData(anyCol.get(1) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        else if(anyCol.size()==3){
            String[] rowKeys = {head[anyCol.get(0)+1], head[anyCol.get(1)+1], head[anyCol.get(2)+1]  };
            double[][] data = {getColumnData(anyCol.get(0) ), getColumnData(anyCol.get(1) ), getColumnData(anyCol.get(2) ) };
            return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        }
        else if(anyCol.size()==4){
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

}

