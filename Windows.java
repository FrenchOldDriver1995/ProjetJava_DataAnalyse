package example.dataAnalyse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.swing.*;

//JFreeChart Line Chart（折线图）
public class Windows extends JFrame {
    /**
     * 创建JFreeChart Line Chart（折线图）
     * col0: Delta_TP9,  col4: Theta_TP9,  col8: Alpha_TP9,  col12: Beta_TP9,  col16: Gamma_TP9
     * col1: Delta_AF7,  col5: Theta_AF7,  col9: Alpha_AF7,  col13: Beta_AF7,  col17: Gamma_AF7
     * col2: Delta_AF8,  col6: Theta_AF8,  col10:Alpha_AF8,  col14: Beta_AF8,  col18: Gamma_AF8
     * col3: Delta_TP10, col7: Theta_TP10, col11:Alpha_TP10, col15: Beta_TP10, col19: Gamma_TP10
     *
     */

    public Windows() throws IOException, InterruptedException { //从这里可以发现java这个布局是真的麻烦，没有一个直观的xml界面来显示
//        Read_Data rd = new Read_Data("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv");
//        //CategoryDataset dataset = createDataset("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv", cols);
//        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
//        JFreeChart freeChart = createChart(dataset);
          initial();
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        //initialImage();
        new Windows();
    }

    public void initial() throws IOException {
        JPanel jp = new JPanel(); //总的panel
        CardLayout c = new CardLayout();
        jp.setLayout(c);
        //读取
        Read_Data rd = new Read_Data("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv");//选择file
        CategoryDataset dataset = rd.createTP9();
        JFreeChart freeChart_TP9 = createChart(dataset);
        CategoryDataset dataset2 = rd.createAF7();
        JFreeChart freeChart_AF7 = createChart(dataset2);
        CategoryDataset dataset3 = rd.createAF8();
        JFreeChart freeChart_AF8 = createChart(dataset3);
        CategoryDataset dataset4 = rd.createTP10();
        JFreeChart freeChart_TP10 = createChart(dataset4);
//生成四个副panel
        JPanel imagePart = new ChartPanel(freeChart_TP9);
        JPanel imagePart2 = new ChartPanel(freeChart_AF7);
        JPanel imagePart3 = new ChartPanel(freeChart_AF8);
        JPanel imagePart4 = new ChartPanel(freeChart_TP10);
//加入主Panel里

        jp.add(imagePart,"TP9");
        jp.add(imagePart2, "AF7");
        jp.add(imagePart3, "AF8");
        jp.add(imagePart4, "TP10");
        JPanel buttonPart = new JPanel();
        //buttonPart.setLayout(new GridLayout(5, 5));


        Button b1 = new Button("TP10");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                jp.add(imagePart);
                jp.repaint();jp.revalidate();
            }
        });


        Button b2 = new Button("AF7");
        b2.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                jp.add(imagePart2);
                jp.repaint();
                jp.revalidate();
            }
        });
        Button b3 = new Button("AF8");

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                jp.add(imagePart3);
                jp.repaint();
                jp.revalidate();
            }
        });
        Button b4 = new Button("TP10");

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();
                jp.add(imagePart4);
                jp.revalidate();
                jp.repaint();
            }
        });
        Button clear = new Button("Wipe out");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.removeAll();

                jp.revalidate();
                jp.repaint();
            }
        });


        Button DTP9 = new Button("Delta_TP9");
        DTP9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(0);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }else{
                    //超过数量，弹出窗口显示请先wipe out
                }

            }
        });
        Button DAF7 = new Button("Delta_AF7");
        DAF7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(1);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });

        Button DAF8 = new Button("Delta_AF8");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(2);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });
        Button DTP10 = new Button("Delta_TP10");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(3);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });
        Button TTP9 = new Button("Theta_TP9");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(4);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });

        Button TAF7 = new Button("Theta_AF7");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(5);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });
        Button TAF8 = new Button("Theta_AF8");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(6);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });
        Button TTP10 = new Button("Theta_TP10");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(7);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });
        Button ATP9 = new Button("Alpha_TP9");
        DAF8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //add this col to the exist arr (Attribute of Read_Data),但是要判断是否这一条线已经在col里了。。。
                if(rd.anyCol.size()<=5){ //max 5 lines;
                    jp.removeAll();
                    rd.anyCol.add(8);
                    try {
                        CategoryDataset dataset = rd.createLines();
                        JFreeChart freeChartLocal = createChart(dataset);
                        JPanel images = new ChartPanel(freeChartLocal);
                        jp.add(images);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    jp.revalidate();
                    jp.repaint();
                }

            }
        });
        //add Buttons
        buttonPart.add(b1);
        buttonPart.add(b2);
        buttonPart.add(b3);
        buttonPart.add(b4);
        buttonPart.add(clear);
        buttonPart.add(DTP9);
        buttonPart.add(DAF7);
        buttonPart.add(DAF8);
        buttonPart.add(DTP10);
        buttonPart.add(TTP9);
        buttonPart.add(TAF7);
        buttonPart.add(TAF8);
        buttonPart.add(TTP10);
        buttonPart.add(ATP9);


        //add Panels
        add(buttonPart);
        add(jp, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public static void initialImage() throws IOException {
        // 步骤1：创建CategoryDataset对象（准备数据）
        int[] cols = {0,1};
        CategoryDataset dataset = new Read_Data("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv").createTP10();
        //CategoryDataset dataset = createDataset("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv", cols);
        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
        JFreeChart freeChart = createChart(dataset);
        // 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
        saveAsFile(freeChart, "/Users/tscheung/Desktop/dataAnalysisCode/testImage.jpg", 600, 400);
    }
    // 保存为文件
    public static void saveAsFile(JFreeChart chart, String outputPath,
                                  int weight, int height) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            // ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
            // 保存为JPEG
            ChartUtilities.writeChartAsJPEG(out, chart, 800, 600);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    // 根据CategoryDataset创建JFreeChart对象
    public static JFreeChart createChart(CategoryDataset categoryDataset) {
        // 创建JFreeChart对象：ChartFactory.createLineChart
        JFreeChart jfreechart = ChartFactory.createLineChart("Brainwave analyse", // 标题
                "Time", // categoryAxisLabel （category轴，横轴，X轴标签）
                "Value", // valueAxisLabel（value轴，纵轴，Y轴的标签）
                categoryDataset, // dataset
                PlotOrientation.VERTICAL, true, // legend
                false, // tooltips
                false); // URLs
        // 使用CategoryPlot设置各种参数。以下设置可以省略。
        CategoryPlot plot = (CategoryPlot)jfreechart.getPlot();
        // 背景色 透明度
        plot.setBackgroundAlpha(0.5f);
        // 前景色 透明度
        plot.setForegroundAlpha(0.5f);
        // 其他设置 参考 CategoryPlot类
        LineAndShapeRenderer renderer = (LineAndShapeRenderer)plot.getRenderer();
        renderer.setBaseShapesVisible(true); // series 点（即数据点）可见
        renderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
        renderer.setItemLabelsVisible(false); //不显示具体值，在数据点过多时可用
        renderer.setUseSeriesOffset(true); // 设置偏移量(让标的数字不是正好在点上方）
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        return jfreechart;
    }

    /**
     * 创建CategoryDataset对象
     *
     *
     */

    public static CategoryDataset createDataset(String filePath, int[] cols) throws IOException { //这里要改成能控制col选择的数目
        String[] colKeys = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27"}; //横坐标
        Read_Data rd = new Read_Data();
        rd.GenerateData(filePath); //get csv file

        String[] rowKeys = {rd.head[cols[0]+1], rd.head[cols[1]+1] }; //这里改成可以修改的

        double[][] data = {rd.getColumnData(cols[0]), rd.getColumnData(cols[1])};
        // 或者使用类似以下代码
        // DefaultCategoryDataset categoryDataset = new
        // DefaultCategoryDataset();
        // categoryDataset.addValue(10, "rowKey", "colKey");

        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
    }


}


