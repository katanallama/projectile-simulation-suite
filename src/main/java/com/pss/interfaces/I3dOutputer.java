package com.pss.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import org.jzy3d.colors.Color;
import org.jzy3d.plot3d.primitives.LineStrip;

public interface I3dOutputer extends IOutputResults {
    default LineStrip lineStripFromResults(Vector3d[] results) {
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

    default void displayChart(LineStrip lineStrip) {
    }

}
