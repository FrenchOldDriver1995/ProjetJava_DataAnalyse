package example.dataAnalyse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import javax.swing.*;

//JFreeChart Line Chart
public class Windows extends JFrame {
    /**
     * create JFreeChart Line Chart
     * col0: Delta_TP9,  col4: Theta_TP9,  col8: Alpha_TP9,  col12: Beta_TP9,  col16: Gamma_TP9
     * col1: Delta_AF7,  col5: Theta_AF7,  col9: Alpha_AF7,  col13: Beta_AF7,  col17: Gamma_AF7
     * col2: Delta_AF8,  col6: Theta_AF8,  col10:Alpha_AF8,  col14: Beta_AF8,  col18: Gamma_AF8
     * col3: Delta_TP10, col7: Theta_TP10, col11:Alpha_TP10, col15: Beta_TP10, col19: Gamma_TP10
     *
     */
    public Read_Data rd;//for reading .csv file
    public JPanel jp;
    public String filePath;
    public HistPanel histPanel;


    public Windows() throws IOException, InterruptedException {
//        Read_Data rd = new Read_Data("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv");
//        //CategoryDataset dataset = createDataset("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv", cols);
//        // 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
//        JFreeChart freeChart = createChart(dataset);
          filePath="/Users/tscheung/Desktop/dataAnalysisCode/test3.csv";
          initial();
    }

    public Windows(String csvPath) throws IOException {
        this.filePath = csvPath ;
        initial();
    }

    public Windows(String csvPath, String title) throws IOException {
        this.filePath = csvPath ;
        initial();
        setTitle(title);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        new Windows();
    }

    public void initial() throws IOException {
        jp = new JPanel(); //总的panel
        CardLayout c = new CardLayout();
        jp.setLayout(c);
        JPanel changePerson = new JPanel();
        changePerson.setLayout(new GridLayout(5,1));

        //设置菜单拖拉栏 inital menu
        JMenuBar TotalBar = new JMenuBar();
        JMenu DeltaMenu = new JMenu("Delta");
        JMenu ThetaMenu = new JMenu("Theta");
        JMenu AlphaMenu = new JMenu("Alpha");
        JMenu BetaMenu = new JMenu("Beta");
        JMenu GammaMenu = new JMenu("Gamma");

//        DefaultCategoryDataset dcd = new DefaultCategoryDataset(); //用default这种可以直接添加数据，像add一样。。

        //file read
        rd = new Read_Data(filePath);//选择 choose file
        CategoryDataset dataset = rd.createTP9();
        JFreeChart freeChart_TP9 = createChart(dataset);
        CategoryDataset dataset2 = rd.createAF7();
        JFreeChart freeChart_AF7 = createChart(dataset2);
        CategoryDataset dataset3 = rd.createAF8();
        JFreeChart freeChart_AF8 = createChart(dataset3);
        CategoryDataset dataset4 = rd.createTP10();
        JFreeChart freeChart_TP10 = createChart(dataset4);

        JPanel imagePart = new ChartPanel(freeChart_TP9);
        JPanel imagePart2 = new ChartPanel(freeChart_AF7);
        JPanel imagePart3 = new ChartPanel(freeChart_AF8);
        JPanel imagePart4 = new ChartPanel(freeChart_TP10);

//        add Panels
        //jp.add(imagePart,"TP9"); //first Image
//        jp.add(imagePart2, "AF7");
//        jp.add(imagePart3, "AF8");
//        jp.add(imagePart4, "TP10");
        JPanel buttonPart = new JPanel();
        //buttonPart.setLayout(new GridLayout(5, 5));

        /**
         *
         *  Realisation of the buttons
         */
        Button b1 = new Button("TP9");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//改了filePath之后，这里会自动变回去，因为他直接添加imagePath
                jp.removeAll();
                jp.add(imagePart);
                jp.repaint();
                jp.revalidate();
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
                rd.anyCol = new ArrayList<>();
                jp.revalidate();
                jp.repaint();
            }
        });


        JMenuItem DTP9 = new JMenuItem("Delta_TP9");
        DTP9.addActionListener(new ImageListener(rd, jp, 0));

        JMenuItem DAF7 = new JMenuItem("Delta_AF7");
        DAF7.addActionListener(new ImageListener(rd, jp, 1));

        JMenuItem DAF8 = new JMenuItem("Delta_AF8");
        DAF8.addActionListener(new ImageListener(rd, jp, 2));

        JMenuItem DTP10 = new JMenuItem("Delta_TP10");
        DTP10.addActionListener(new ImageListener(rd, jp, 3));

        JMenuItem TTP9 = new JMenuItem("Theta_TP9");
        TTP9.addActionListener(new ImageListener(rd, jp, 4));

        JMenuItem TAF7 = new JMenuItem("Theta_AF7");
        TAF7.addActionListener(new ImageListener(rd, jp, 5));

        JMenuItem TAF8 = new JMenuItem("Theta_AF8");
        TAF8.addActionListener(new ImageListener(rd, jp, 6));

        JMenuItem TTP10 = new JMenuItem("Theta_TP10");
        TTP10.addActionListener(new ImageListener(rd, jp, 7));

        JMenuItem ATP9 = new JMenuItem("Alpha_TP9");
        ATP9.addActionListener(new ImageListener(rd, jp, 8));

        JMenuItem AAF7 = new JMenuItem("Alpha_AF7");
        AAF7.addActionListener(new ImageListener(rd, jp, 9));

        JMenuItem AAF8 = new JMenuItem("Alpha_AF8");
        AAF8.addActionListener(new ImageListener(rd, jp, 10));

        JMenuItem ATP10 = new JMenuItem("Alpha_ATP10");
        ATP10.addActionListener(new ImageListener(rd, jp, 11));

        JMenuItem BTP9 = new JMenuItem("Beta_TP9");
        BTP9.addActionListener(new ImageListener(rd, jp, 12));

        JMenuItem BAF7 = new JMenuItem("Beta_AF7");
        BAF7.addActionListener(new ImageListener(rd, jp, 13));

        JMenuItem BAF8 = new JMenuItem("Beta_AF8");
        BAF8.addActionListener(new ImageListener(rd, jp, 14));

        JMenuItem BTP10 = new JMenuItem("Beta_TP10");
        BTP10.addActionListener(new ImageListener(rd, jp, 15));

        JMenuItem GTP9 = new JMenuItem("Gamma_TP9");
        GTP9.addActionListener(new ImageListener(rd, jp, 16));

        JMenuItem GAF7 = new JMenuItem("Gamma_AF7");
        GAF7.addActionListener(new ImageListener(rd, jp, 17));

        JMenuItem GAF8 = new JMenuItem("Gamma_AF8");
        GAF8.addActionListener(new ImageListener(rd, jp, 18));

        JMenuItem GTP10 = new JMenuItem("Gamma_TP10");
        GTP10.addActionListener(new ImageListener(rd, jp, 19));

        Button test1 = new Button("Test1");
        test1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    new Windows("/Users/tscheung/Desktop/dataAnalysisCode/test1.csv", "test1");
//                    jp.removeAll();
//                    filePath = "/Users/tscheung/Desktop/dataAnalysisCode/test1.csv";
//                    System.out.println("path changed");
//                    initial();
//                    validate();
//                    repaint();
                    setTitle("test1");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        Button test2 = new Button("Test2");
        test2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    new Windows("/Users/tscheung/Desktop/dataAnalysisCode/test2.csv", "test2");
                    setTitle("test2");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        Button test3 = new Button("Test3");
        test3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Windows("/Users/tscheung/Desktop/dataAnalysisCode/test3.csv", "test3");
                    setTitle("test3");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        Button test4 = new Button("Test4");
        test4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Windows("/Users/tscheung/Desktop/dataAnalysisCode/test4.csv", "test4");
                    setTitle("test4");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        Button test5 = new Button("Test5");
        test5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Windows("/Users/tscheung/Desktop/dataAnalysisCode/test5.csv", "test5");
                    setTitle("test5");

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        /**
         * add buttons
         * add JMenuItem to the JMenu
         */
        buttonPart.add(b1);
        buttonPart.add(b2);
        buttonPart.add(b3);
        buttonPart.add(b4);
        buttonPart.add(clear);

        DeltaMenu.add(DTP9);
        DeltaMenu.add(DAF7);
        DeltaMenu.add(DAF8);
        DeltaMenu.add(DTP10);

        ThetaMenu.add(TTP9);
        ThetaMenu.add(TAF7);
        ThetaMenu.add(TAF8);
        ThetaMenu.add(TTP10);

        AlphaMenu.add(ATP9);
        AlphaMenu.add(AAF7);
        AlphaMenu.add(AAF8);
        AlphaMenu.add(ATP10);

        BetaMenu.add(BTP9);
        BetaMenu.add(BAF7);
        BetaMenu.add(BAF8);
        BetaMenu.add(BTP10);

        GammaMenu.add(GTP9);
        GammaMenu.add(GAF7);
        GammaMenu.add(GAF8);
        GammaMenu.add(GTP10);

        changePerson.add(test1);
        changePerson.add(test2);
        changePerson.add(test3);
        changePerson.add(test4);
        changePerson.add(test5);
        //add JMenu to JMenuBar
        TotalBar.add(DeltaMenu);
        TotalBar.add(ThetaMenu);
        TotalBar.add(AlphaMenu);
        TotalBar.add(BetaMenu);
        TotalBar.add(GammaMenu);
        //add Panels
        buttonPart.add(TotalBar);




        add(buttonPart, BorderLayout.NORTH);
        add(changePerson, BorderLayout.WEST);
        add(jp, BorderLayout.CENTER);


        Button pcaButton = new Button("pca");
        pcaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PCApanel();
            }
        });

        add(pcaButton, BorderLayout.EAST);

        HistPanelSettings();
        setBounds(0,0,1000,800);
        //pack();
        setVisible(true);
        //close all
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    class ImageListener implements ActionListener{ //Inner Class

        int numCol;
        Read_Data rd;
        JPanel jp;
        public ImageListener(Read_Data rd, JPanel jp, int numCol){
            this.rd = rd;
            this.numCol = numCol ;
            this.jp = jp;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(rd.anyCol.size()<=5){ //max 5 lines;
                jp.removeAll();
                rd.anyCol.add(numCol);
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
    }

    public void HistPanelSettings() throws IOException {
        histPanel = new HistPanel(filePath);
        add(histPanel, BorderLayout.SOUTH);
        /**
         * setting Items' funtions
         */
        histPanel.DTP9.addActionListener( new HistListener(0));
        histPanel.DAF7.addActionListener( new HistListener(1));
        histPanel.DAF8.addActionListener( new HistListener(2));
        histPanel.DTP10.addActionListener( new HistListener(3));

        histPanel.TTP9.addActionListener( new HistListener(4));
        histPanel.TAF7.addActionListener( new HistListener(5));
        histPanel.TAF8.addActionListener( new HistListener(6));
        histPanel.TTP10.addActionListener( new HistListener(7));

        histPanel.ATP9.addActionListener( new HistListener(8));
        histPanel.AAF7.addActionListener( new HistListener(9));
        histPanel.AAF8.addActionListener( new HistListener(10));
        histPanel.ATP10.addActionListener( new HistListener(11));

        histPanel.BTP9.addActionListener( new HistListener(12));
        histPanel.BAF7.addActionListener( new HistListener(13));
        histPanel.BAF8.addActionListener( new HistListener(14));
        histPanel.BTP10.addActionListener( new HistListener(15));

        histPanel.GTP9.addActionListener( new HistListener(16));
        histPanel.GAF7.addActionListener( new HistListener(17));
        histPanel.GAF8.addActionListener( new HistListener(18));
        histPanel.GTP10.addActionListener( new HistListener(19));

    }

    public class HistListener implements ActionListener { //在主函数里再对HistPanel的功能进行设定

        int col;
        public HistListener(int col){
            this.col = col;//indiquer la column
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            jp.removeAll(); //在当前wd里的jp上进行操作
            CategoryDataset dataset  = rd.createPlot(col);
            JFreeChart chart = ChartFactory.createBarChart3D(
                    "Hist", // 图表标题
                    "time", // 目录轴的显示标签
                    "value", // 数值轴的显示标签
                    dataset, // 数据集
                    PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                    true,           // 是否显示图例(对于简单的柱状图必须是false)
                    false,          // 是否生成工具
                    false           // 是否生成URL链接
            );
            JPanel temp = new ChartPanel(chart, true);
            jp.add(temp);
            validate();
            repaint();
        }
    }

}


