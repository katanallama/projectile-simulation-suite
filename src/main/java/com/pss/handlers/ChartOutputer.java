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
    public void outputResults(Vector3d[] results) {
        boolean isHeadless = GraphicsEnvironment.isHeadless();
        boolean print = true;

            XYSeriesCollection dataset = new XYSeriesCollection();
            XYSeries positionSeries = new XYSeries("Position");

            for (int i = 0; i < results.length; i++) {
                Vector3d vector = results[i];
                positionSeries.add(i, vector.z);
            }

            dataset.addSeries(positionSeries);

            JFreeChart chart = ChartFactory.createXYLineChart(
                    "Position vs Time",
                    "Time (s)",
                    "Position (m)",
                    dataset);

        if (isHeadless || print) {
            // TODO get project path instead of hardcoding this
            String dir = "/home/bh/projects/projectile-simulation-suite/image.png";

            try {
                ChartUtils.saveChartAsPNG(new File(dir), chart, 1600, 900);
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
