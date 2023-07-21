package com.pss.handlers;

import com.pss.interfaces.IOutputResults;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.vecmath.Vector3d;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChartOutputer implements IOutputResults {
    public void outputResults(Vector3d[] results, double timeStep) {
        boolean isHeadless = GraphicsEnvironment.isHeadless();
        boolean print = false;

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries positionSeriesX = new XYSeries("Position X-axis");
        XYSeries positionSeriesY = new XYSeries("Position Y-axis");
        XYSeries positionSeriesZ = new XYSeries("Position Z-axis");

        for (int i = 0; i < results.length; i++) {
            Vector3d vector = results[i];
            positionSeriesX.add(i, vector.x);
            positionSeriesY.add(i, vector.y);
            positionSeriesZ.add(i, vector.z);
        }

        dataset.addSeries(positionSeriesX);
        dataset.addSeries(positionSeriesY);
        dataset.addSeries(positionSeriesZ);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Position vs Time",
                "Time Step (" + timeStep + "s)",
                "Position (m)",
                dataset);

        if (isHeadless || print) {
            String currentDir = System.getProperty("user.dir");
            String dir = currentDir + "/image.png";

            try {
                ChartUtils.saveChartAsPNG(new File(dir), chart, 1600, 1000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ChartFrame frame = new ChartFrame("Chart", chart);
            frame.pack();
            frame.setVisible(true);
        }
    }
}
