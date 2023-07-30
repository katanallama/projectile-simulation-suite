package com.pss.handlers;

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.vecmath.Vector3d;

import com.pss.enums.State;
import com.pss.SimulatorState;
import com.pss.interfaces.IOutputResults;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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

        // SimulatorState.setCurrentState(State.PREPARE_PLOT_DATA);

        if (isHeadless || print) {
            String currentDir = System.getProperty("user.dir");
            String dir = currentDir + "/image.png";

            try {
                ChartUtils.saveChartAsPNG(new File(dir), chart, 1600, 1000);
                // SimulatorState.setCurrentState(State.SAVE_PLOT_AS_PNG);
            } catch (IOException e) {
                e.printStackTrace();
                // SimulatorState.setCurrentState(State.NOT_SAVE_PLOT_AS_PNG);
            }
        } else {
            ChartFrame frame = new ChartFrame("Chart", chart);
            frame.pack();
            frame.setVisible(true);
            // SimulatorState.setCurrentState(State.DISPLAY_PLOT_IN_GUI);
        }
    }
}
