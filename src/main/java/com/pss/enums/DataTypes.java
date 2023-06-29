package com.pss.enums;

import javax.vecmath.Vector3d;

public enum DataTypes {
    Vector(Vector3d.class),
    Double(double.class);

    private DataTypes(Class<?> type) {
        _type = type;
    }

    private Class<?> _type;

    public Class<?> getRawType() {
        return _type;
    } 
}
