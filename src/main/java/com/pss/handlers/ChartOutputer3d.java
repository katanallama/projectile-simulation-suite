package com.pss.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.pss.interfaces.IOutputResults;

import org.jzy3d.analysis.AWTAbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartFactory;
import org.jzy3d.chart.factories.AWTPainterFactory;
import org.jzy3d.chart.factories.IChartFactory;
import org.jzy3d.chart.factories.IPainterFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.rendering.canvas.Quality;

public class ChartOutputer3d extends AWTAbstractAnalysis implements IOutputResults {

    private LineStrip lineStrip;

    @Override
    public void outputResults(Vector3d[] results) {
        this.lineStrip = lineStripFromResults(results);

        // start the chart in a new thread
        new Thread(() -> {
            try {
                AnalysisLauncher.open(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    @Override
    public void init() {
        Quality q = Quality.Advanced();

        GLCapabilities c = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        IPainterFactory p = new AWTPainterFactory(c);
        IChartFactory f = new AWTChartFactory(p);

        chart = f.newChart(q);
        chart.getScene().add(lineStrip);
    }

    private LineStrip lineStripFromResults(Vector3d[] results) {
        List<org.jzy3d.maths.Coord3d> points = new ArrayList<>();

        for (Vector3d vector : results) {
            points.add(new org.jzy3d.maths.Coord3d(vector.x, vector.y, vector.z));
        }

        LineStrip lineStrip = new LineStrip(points);
        lineStrip.setWireframeColor(Color.BLACK); // TODO: replace with unique color values with each point
        lineStrip.setWireframeDisplayed(true);
        lineStrip.setWireframeWidth(3); // line width
        lineStrip.setWidth(3);

        return lineStrip;
    }

}
