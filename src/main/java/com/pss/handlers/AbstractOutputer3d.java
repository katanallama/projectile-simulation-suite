package com.pss.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import com.pss.interfaces.IOutputResults;

import org.jzy3d.colors.Color;
import org.jzy3d.plot3d.primitives.LineStrip;

public abstract class AbstractOutputer3d implements IOutputResults {

    protected LineStrip lineStripFromResults(Vector3d[] results) {
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

    protected abstract void displayChart(LineStrip lineStrip);

}
