package com.pss.handlers;
import com.pss.interfaces.*;

import javax.vecmath.Vector3d;

public class StubGetProjectileDrag implements IGetProjectileDrag{
    public double getProjectileDrag() {
        return 1;
    }
}
