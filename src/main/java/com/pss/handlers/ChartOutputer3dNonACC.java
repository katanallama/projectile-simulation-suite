package com.pss.handlers;
import com.pss.interfaces.IOutputResults;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import java.util.Random;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.EmulGLSkin;
import org.jzy3d.chart.factories.EmulGLChartFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.rendering.canvas.Quality;


public class ChartOutputer3dNonACC implements IOutputResults {
    private Vector3d[] results;

    @Override
    public void outputResults(Vector3d[] results) {
        Quality q = Quality.Advanced();
        q.setAnimated(false);

        Chart chart = new EmulGLChartFactory().newChart(q);
        chart.add(linestrip(results));
        chart.open();
        chart.addMouseCameraController();

        EmulGLSkin skin = EmulGLSkin.on(chart);
        skin.getCanvas().setProfileDisplayMethod(true);

    }

  private static LineStrip linestrip(Vector3d[] results) {
        List<org.jzy3d.maths.Coord3d> points = new ArrayList<org.jzy3d.maths.Coord3d>();

        for (int i = 0; i < results.length; i++) {
            Vector3d vector = results[i];
            points.add(new org.jzy3d.maths.Coord3d(vector.x, vector.y, vector.z));
        }

    LineStrip linestrip = new LineStrip(points);
         linestrip.setWireframeColor(Color.BLACK); // TODO: replace with unique color values with each point
        linestrip.setWireframeDisplayed(true);
        linestrip.setWireframeWidth(3); // line width
   linestrip.setWidth(3);
    return linestrip;
  }

}
