package view.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import model.chartdata.ChartData;
import model.stockdata.DataFunction;

public class LineChart extends JFrame {

  private ChartData chartData;
  private DataFunction function;
  private String ticker;

  public LineChart(DataFunction function, String ticker) {
    super(ticker);
    this.function = function;
    this.ticker = ticker;

    setPreferredSize(new Dimension(1000, 750));
    setSize(1000, 750);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //getContentPane().setLayout(new BorderLayout());

    JFreeChart chart = createChart();
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setHorizontalAxisTrace(true);
    chartPanel.setVerticalAxisTrace(true);
    setContentPane(chartPanel);
    setVisible(true);
  }

  private JFreeChart createChart() {
    chartData = new ChartData(function, ticker);
    XYDataset dataset = chartData.createDataset();
    JFreeChart chart = ChartFactory.createTimeSeriesChart("Price",
            "Date",
            "Price",
            dataset);

    XYItemRenderer renderer = chart.getXYPlot().getRenderer();
    StandardXYToolTipGenerator g = new StandardXYToolTipGenerator(
            StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
            new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")
    );
    renderer.setToolTipGenerator(g);

    chart.setBackgroundPaint(Color.white);
    //TimeSeries movingAverage = MovingAverage.createMovingAverage(timeSeries, "20SMA", 20, 0);
    //System.out.println(movingAverage.getDataItem(5).getValue());
    //TimeSeriesCollection movingAverageDataset = new TimeSeriesCollection();
    //movingAverageDataset.addSeries(movingAverage);


    XYPlot plot = chart.getXYPlot();
    //plot.setDataset(1, movingAverageDataset);
    //plot.setRenderer(1, new StandardXYItemRenderer());
    //System.out.println(plot.getDataset(0).getYValue(1, 1));
    //System.out.println(plot.getDataset(1).getYValue(1, 1));
    plot.setBackgroundPaint(Color.WHITE);
    plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
    plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
    plot.setDomainCrosshairVisible(true);
    plot.setRangeCrosshairVisible(false);

//    DateAxis axis = (DateAxis) plot.getDomainAxis();
//    if (range.equals("1d")) {
//      axis.setDateFormatOverride(new SimpleDateFormat("hh:mma"));
//    } else {
//      axis.setDateFormatOverride(new SimpleDateFormat("yy:MM:dd"));
//    }
    return chart;
  }

}
