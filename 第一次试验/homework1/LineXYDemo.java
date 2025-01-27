
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class LineXYDemo extends ApplicationFrame {
    // 该构造方法中完成了数据集、图表对象和显示图表面板的创建工作
    public LineXYDemo(String title){
        super(title);
        XYDataset dataset = createDataset();             // 创建记录图中坐标点的数据集
        JFreeChart chart = createChart(dataset);         // 使用上一步已经创建好的数据集生成一个图表对象
        ChartPanel chartPanel = new ChartPanel(chart);   // 将上一步已经创建好的图表对象放置到一个可以显示的Panel上
        // 设置GUI面板Panel的显示大小
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);                      // 这是JavaGUI的步骤之一，不用过于关心，面向对象课程综合训练的视频中进行了讲解。
    }

    private JFreeChart createChart(XYDataset dataset) {
        // 使用已经创建好的dataset生成图表对象
        // JFreechart提供了多种类型的图表对象，本次实验是需要使用XYLine型的图表对象
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Sorting algorithm performance testing and comparison",      // 图表的标题
                "X(2^)",                       // 横轴的标题名
                "log(Y)(ns)",                       // 纵轴的标题名
                dataset,                       // 图表对象中使用的数据集对象
                PlotOrientation.VERTICAL,      // 图表显示的方向
                true,                          // 是否显示图例
                false,                         // 是否需要生成tooltips
                false                          // 是否需要生成urls
        );
        // 下面所做的工作都是可选操作，主要是为了调整图表显示的风格
        // 同学们不必在意下面的代码
        // 可以将下面的代码去掉对比一下显示的不同效果
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 6.0));
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        renderer.setDefaultShapesFilled(true);
        return chart;
    }

    private XYDataset createDataset() {
        // 本样例中想要显示的是三组数据的变化图
        // X数组是三组数据共同拥有的x坐标值；Y1、Y2和Y3数组分别存储了三组数据对应的y坐标值
        double[] X = {8, 9, 10, 11, 12, 13, 14, 15,16};
        double[][] Y = getDoubles();
        // jfreechart中使用XYSeries对象存储一组数据的(x,y)的序列，因为有三组数据所以创建三个XYSeries对象
        XYSeries[] series = {new XYSeries("Insertion"), new XYSeries("Selection"), new XYSeries("Shell1"),new XYSeries("Shell2"),new XYSeries("Shell3"),new XYSeries("Quick"),new XYSeries("Merge"),};
        int N = X.length;
        int M = series.length;
        for(int i = 0; i < M; i++)
            for(int j = 0; j < N; j++)
                series[i].add(X[j],Math.log(Y[i][j]));
        // 因为在该图表中显示的数据序列不止一组，所以在jfreechart中需要将多组数据序列存放到一个XYSeriesCollection对象中
        XYSeriesCollection dataset = new XYSeriesCollection();
        for(int i = 0; i < M; i++)
            dataset.addSeries(series[i]);

        return dataset;
    }

    private static double[][] getDoubles() {
        double[] Y1 = {173320.0000 ,394240.0000 ,754660.0000 ,823660.0000 ,2566460.0000 ,10309000.0000,37734140.0000 ,197334560.0000, 679669280.0000 };
        double[] Y2 = {420040.0000, 581240.0000, 2832640.0000, 6405900.0000, 9486860.0000, 37591480.0000, 156444620.0000, 609666540.0000 ,2637630920.0000 };
        double[] Y3 = {123860.0000 ,163420.0000 ,296200.0000 ,267080.0000 ,601520.0000 ,3195740.0000 ,5257600.0000 ,5039880.0000 ,11301820.0000 };
        double[] Y4 = {121660.0000,141940.0000 ,282500.0000, 225180.0000, 448220.0000 ,1360120.0000, 4869900.0000, 5419300.0000, 7151720.0000 };
        double[] Y5 = {93160.0000, 108460.0000, 227660.0000 ,217800.0000, 402840.0000, 1082260.0000 ,2526220.0000 ,4464800.0000, 9686420.0000 };
        double[] Y6 = {113480.0000, 68820.0000, 101820.0000 ,207440.0000, 930260.0000 ,2105520.0000, 988800.0000, 2417220.0000, 4982320.0000 };
        double[] Y7 = {183680.0000, 66200.0000, 127800.0000 ,300360.0000 ,762240.0000 ,3341540.0000 ,8967980.0000 ,6088160.0000 ,7147600.0000 };
        double[][] Y = {Y1, Y2, Y3,Y4, Y5, Y6,Y7};
        return Y;
    }

    public static void main(String[] args) {
        LineXYDemo demo = new LineXYDemo("Test");
        demo.pack();
        demo.setVisible(true);
    }
}
