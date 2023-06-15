package com.pss.handlers;
import com.pss.interfaces.*;

import javax.vecmath.Vector3d;

public class StubGetProjectileDrag implements IGetProjectileDrag{
    public Vector3d getProjectileDrag() {
        return new Vector3d(0, 0, 0);
    }
}
