package com.pss.handlers;

import javax.vecmath.Vector3d;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.EmulGLSkin;
import org.jzy3d.chart.factories.EmulGLChartFactory;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.rendering.canvas.Quality;

public class ChartOutputer3dNonACC extends AbstractOutputer3d {

    @Override
    public void outputResults(Vector3d[] results) {
        // start the chart in a new thread
        new Thread(() -> {
            try {
                LineStrip lineStrip = lineStripFromResults(results);
                displayChart(lineStrip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    protected void displayChart(LineStrip lineStrip) {
        Quality q = Quality.Advanced();
        q.setAnimated(false);

        Chart chart = new EmulGLChartFactory().newChart(q);
        chart.add(lineStrip);
        chart.open();
        chart.addMouseCameraController();

        EmulGLSkin skin = EmulGLSkin.on(chart);
        skin.getCanvas().setProfileDisplayMethod(true);
    }

}
