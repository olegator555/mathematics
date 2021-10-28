import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static java.lang.Math.abs;
import static java.lang.Math.log;


public class Graph {
    private final double reference_value;
    private final Integrates integrates = new Integrates();
    private final Functions function;
    public Graph(double reference_value, Functions function) {
        this.function = function;
        this.reference_value = reference_value;
    }


    public ChartPanel plot()
    {
        XYSeries simpson_series = new XYSeries("Simpson");
        XYSeries trapezoid_series = new XYSeries("Trapezoid");
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (int i = 10; i <= 10e4; i+=10e3) {
            final double simpson_y = log(abs(reference_value-integrates.composite_simpson(3,9999,i,function)));
            simpson_series.add(Math.log(i),simpson_y);
            final double trapezoid_y = log(abs(reference_value-integrates.composite_trapezoid(3,9999,i,function)));
            trapezoid_series.add(Math.log(i),trapezoid_y);
        }
        dataset.addSeries(simpson_series);
        dataset.addSeries(trapezoid_series);
        final JFreeChart chart = ChartFactory.createXYLineChart("График", "log(x)", "log(y)", dataset, PlotOrientation.VERTICAL, true, true, true);
        final XYPlot xyPlot = (XYPlot)chart.getPlot();
        xyPlot.getRangeAxis().setAutoRange(true);
        ((NumberAxis)xyPlot.getRangeAxis()).setAutoRangeIncludesZero(false);
        return new ChartPanel(chart);
    }
}

