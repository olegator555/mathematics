import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;


public class Graph {
    Functions functions = new Functions();
    int[] x_from_table;
    double[] y_from_table;
    Graph(int[] x_from_table, double[] y_from_table){
        this.x_from_table=x_from_table;
        this.y_from_table=y_from_table;
    }
    public void plot()
    {
        XYSeries series = new XYSeries("Approximate nodes");
        XYSeries origin_series = new XYSeries("Origin nodes");
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int i = 0; i < x_from_table.length-2; i++) {
            double y = functions.qubic_spline(x_from_table[i],coefficient_matrix(i));
            series.add(i,y);
            origin_series.add(x_from_table[i],y_from_table[i]);
        }
        dataset.addSeries(series);
        dataset.addSeries(origin_series);
        JFreeChart chart = ChartFactory.createXYLineChart("P(x)", "x", "y", dataset, PlotOrientation.VERTICAL, true, true, true);
        JFrame frame = new JFrame("Spline approximation");

        frame.getContentPane().add(new ChartPanel(chart));

        frame.setSize(800,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
    void get_spline()
    {
        functions.qubic_spline_coeff(x_from_table,y_from_table);
    }
    double[] coefficient_matrix(int index) {
        double[] matrix = new double[4];
        matrix[0]=functions.coeff_a.get(index);
        matrix[1]=functions.coeff_b.get(index);
        matrix[2]=functions.coeff_c.get(index);
        matrix[3]=functions.coeff_d.get(index);
        return matrix;
    }
}

