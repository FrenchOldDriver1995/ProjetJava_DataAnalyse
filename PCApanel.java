package example.dataAnalyse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PCApanel extends JFrame {

    JPanel p1 ; //store buttons
    JPanel p2 ; //store images
    JLabel pcaLabel;
    JMenuBar PCAbar;
    JMenu PCA_country;
    JMenu PCA_name;
    JMenu PCA;

    JMenuItem country_complete;
    JMenuItem country_average;
    JMenuItem country_single;

    JMenuItem name_complete;
    JMenuItem name_average;
    JMenuItem name_single;

    JMenuItem pCountry;
    JMenuItem pName;

    public PCApanel(){
        Settings();
        function();
        setBounds(100,100, 1000,750);

        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void Settings(){
        p1 = new JPanel(); //store buttons
        p2 = new JPanel(); //store images

        pcaLabel = new JLabel("pca ");
        PCAbar = new JMenuBar();
        PCA_country = new JMenu("Clustering_country");
        PCA_name = new JMenu("Clustering_name");
        PCA = new JMenu("PCA_Analyse");

        country_complete = new JMenuItem("country_complete");
        country_average = new JMenuItem("country_average");
        country_single = new JMenuItem("country_single");

        name_complete = new JMenuItem("name_complete");
        name_average = new JMenuItem("name_average");
        name_single = new JMenuItem("name_single");

        pCountry = new JMenuItem("pca_analyse_country");
        pName = new JMenuItem("pca_analyse_name");

        PCA_country.add(country_complete);
        PCA_country.add(country_average);
        PCA_country.add(country_single);
        PCA_name.add(name_complete);
        PCA_name.add(name_average);
        PCA_name.add(name_single);

        PCA.add(pCountry);
        PCA.add(pName);


        PCAbar.add(PCA_country);
        PCAbar.add(PCA_name);
        PCAbar.add(PCA);

        p1.add(pcaLabel);
        p1.add(PCAbar);


        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.SOUTH);

    }

    public void function(){
        country_complete.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/Cluster_Country_Complete.jpeg"));
        country_average.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/Cluster_Country_Average.jpeg"));
        country_single.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/Cluster_Country_Single.jpeg"));
        name_complete.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/Cluster_Name_Complete.jpeg"));
        name_average.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/Cluster_Name_Average.jpeg"));
        name_single.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/Cluster_Name_Single.jpeg"));

        pCountry.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/pCountry.jpeg"));
        pName.addActionListener(new OpenImageListener("/Users/tscheung/Desktop/dataAnalysisCode/pName.jpeg"));

    }
    public class OpenImageListener implements ActionListener{

        public String filePath;
        public OpenImageListener(String filePath){
            this.filePath = filePath;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ImageIcon img = new ImageIcon(filePath);
            JLabel jl = new JLabel();
            jl.setIcon(img);
            p2.removeAll();
            p2.add(jl);
            validate();
            repaint();

        }
    }


}
