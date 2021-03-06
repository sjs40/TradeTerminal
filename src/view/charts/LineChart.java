package view.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import model.chartdata.ChartData;
import model.stockdata.DataFunction;

public class LineChart {

  private DataFunction function;
  private String ticker;

  public LineChart(DataFunction function, String ticker) {
    this.function = function;
    this.ticker = ticker;
  }

  public JFreeChart createChart() {
    ChartData chartData = new ChartData(function, ticker);
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


    XYPlot plot = chart.getXYPlot();
    plot.setBackgroundPaint(Color.WHITE);
    plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
    plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
    plot.setDomainCrosshairVisible(true);
    plot.setRangeCrosshairVisible(false);

    return chart;
  }

}
