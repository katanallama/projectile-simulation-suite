package com.pss.handlers;

import com.pss.interfaces.IOutputResults;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.vecmath.Vector3d;
import java.awt.*;

public class ConsoleOutputer implements IOutputResults {
    public void outputResults(Vector3d[] results) {
        boolean isHeadless = GraphicsEnvironment.isHeadless();

        if (isHeadless) {
            for (Vector3d vector : results) {
                System.out.println("position: \t" + formatVector(vector));
            }
        } else {
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
                    dataset
            );

            ChartFrame frame = new ChartFrame("Chart", chart);
            frame.pack();
            frame.setVisible(true);

            for (Vector3d vector : results) {
                System.out.println("position: \t" + formatVector(vector));
            }
        }
    }

    private String formatVector(Vector3d vector) {
        return "(" + formatDouble(vector.x) + ",\t" + formatDouble(vector.y) + ",\t" + formatDouble(vector.z) + ")";
    }

    private String formatDouble(double value) {
        return String.format("%.1f", value);
    }
}
