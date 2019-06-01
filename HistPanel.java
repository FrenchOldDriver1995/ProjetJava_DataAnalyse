package example.dataAnalyse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HistPanel  extends JPanel {

    /**
     * 用于添加一个Histogram的JPanel 到主JFrame当中
     */
    JLabel HistLabel;

    JMenuBar HistBar;

    JMenu DeltaMenu ;
    JMenu ThetaMenu ;
    JMenu AlphaMenu ;
    JMenu BetaMenu ;
    JMenu GammaMenu ;

    JMenuItem DTP9 ;
    JMenuItem DAF7 ;
    JMenuItem DAF8 ;
    JMenuItem DTP10 ;

    JMenuItem TTP9;
    JMenuItem TAF7 ;
    JMenuItem TAF8 ;
    JMenuItem TTP10 ;

    JMenuItem ATP9;
    JMenuItem AAF7;
    JMenuItem AAF8;
    JMenuItem ATP10;

    JMenuItem BTP9;
    JMenuItem BAF7;
    JMenuItem BAF8;
    JMenuItem BTP10;

    JMenuItem GTP9;
    JMenuItem GAF7 ;
    JMenuItem GAF8;
    JMenuItem GTP10;

    Read_Data rd;


    public HistPanel(){
         Settings();


    }

    public HistPanel(String filePath) throws IOException {

        Settings();
        chooseFile(filePath);
        ItemFunction();
    }


    public void Settings(){
        HistLabel = new JLabel("Histogram");
        HistBar = new JMenuBar();
        DeltaMenu = new JMenu("Delta");
        ThetaMenu = new JMenu("Theta");
        AlphaMenu = new JMenu("Alpha");
        BetaMenu = new JMenu("Beta");
        GammaMenu = new JMenu("Gamma");

        DTP9 = new JMenuItem("Delta_TP9");
        DAF7 = new JMenuItem("Delta_AF7");
        DAF8 = new JMenuItem("Delta_AF8");
        DTP10 = new JMenuItem("Delta_TP10");

        TTP9 = new JMenuItem("Theta_TP9");
        TAF7 = new JMenuItem("Theta_AF7");
        TAF8 = new JMenuItem("Theta_AF8");
        TTP10 = new JMenuItem("Theta_TP10");

        ATP9 = new JMenuItem("Alpha_TP9");
        AAF7 = new JMenuItem("Alpha_AF7");
        AAF8 = new JMenuItem("Alpha_AF8");
        ATP10 = new JMenuItem("Alpha_TP10");

        BTP9 = new JMenuItem("Beta_TP9");
        BAF7 = new JMenuItem("Beta_AF7");
        BAF8 = new JMenuItem("Beta_AF8");
        BTP10 = new JMenuItem("Beta_TP10");

        GTP9 = new JMenuItem("Gamma_TP9");
        GAF7 = new JMenuItem("Gamma_AF7");
        GAF8 = new JMenuItem("Gamma_AF8");
        GTP10 = new JMenuItem("Gamma_TP10");

        /**
         * 添加各种item进入Menu
         */
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

        HistBar.add(DeltaMenu);
        HistBar.add(ThetaMenu);
        HistBar.add(AlphaMenu);
        HistBar.add(BetaMenu);
        HistBar.add(GammaMenu);

        add(HistLabel);
        add(HistBar);
    }

    public void chooseFile(String filePath) throws IOException {
        rd = new Read_Data(filePath);
    }
}
