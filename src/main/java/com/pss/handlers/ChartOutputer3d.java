package com.pss.handlers;

import javax.vecmath.Vector3d;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.pss.interfaces.I3dOutputer;

import org.jzy3d.analysis.AWTAbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartFactory;
import org.jzy3d.chart.factories.AWTPainterFactory;
import org.jzy3d.chart.factories.IChartFactory;
import org.jzy3d.chart.factories.IPainterFactory;
import org.jzy3d.plot3d.primitives.LineStrip;
import org.jzy3d.plot3d.rendering.canvas.Quality;

public class ChartOutputer3d extends AWTAbstractAnalysis implements I3dOutputer {

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
        List<org.jzy3d.maths.Coord3d> points = new ArrayList<org.jzy3d.maths.Coord3d>();

        
        double maxVelocity = 0; // record highest velocity as reference for heatmap colors 
        for (int i = 0; i < results.length; i++) {
            Vector3d vector = results[i];
            points.add(new org.jzy3d.maths.Coord3d(vector.x, vector.y, vector.z));
            // TODO: update the results data to save the velocity as well as the position
            //          could use hashmap instead of array
            if (vector.lengthSquared() > maxVelocity) {
                maxVelocity = vector.lengthSquared();
            }
        }

        // TODO: possibly integrate into the prior for loop instead of reading all values twice
        for (int i = 0l i < results.length; i++) {
            Vector3d vector = results[i];
            // the Point type has fields of type Color (rgb), float (width), and Coord3d (xyz)
            // retrieve the velocity of the projectile result at i
<<<<<<< HEAD
            //  pass it into the getColorHeat method
            /*
            //  Color getColorHeat(Vector3d vector) {
                    // grab magnitude of vector
                    // convert magnitude into rgb floats (low values == blue, -> high values -> red)
                    //                                      purple, blue, green, yellow, orange, red
                    // create new Color(red-value, green-value, blue-value)
                }
            // points[i].setColor(getColorHeat(projectileVelocity))
            */
=======
            // Use constructor Point(Coord3d xyz, Color rgb)
            //      pass it into the getColorHeat method
            points[i].setColor(getColorHeat(projectileVelocity));
>>>>>>> cab5d6a (add comment-written plan for changes to plot color)
        }

        LineStrip lineStrip = new LineStrip(points);
        lineStrip.setWireframeColor(Color.BLACK); // TODO: replace with unique color values with each point
        lineStrip.setWireframeDisplayed(true);
        lineStrip.setWireframeWidth(3); // line width

        Quality q = Quality.Advanced();

        GLCapabilities c = new GLCapabilities(GLProfile.get(GLProfile.GL2));
        IPainterFactory p = new AWTPainterFactory(c);
        IChartFactory f = new AWTChartFactory(p);

        chart = f.newChart(q);
        chart.getScene().add(lineStrip);
    }
    
    Color getColorHeat(Vector3d vector) {
                    // grab magnitude of vector
                    double magnitude = vector.lengthSquared();
                    // convert magnitude into rgb floats (low values == blue, -> high values -> red)
                    //                                      purple, blue, green, yellow, orange, red
                    // red      -> ff0000
                    // orange   -> ff7700
                    // yellow   -> ffff00
                    // green    -> 00ff00
                    // blue     -> 0000ff
                    // purple   -> 770077
                    // 
                    // create new Color(red-value, green-value, blue-value)
                }
        
    


}
