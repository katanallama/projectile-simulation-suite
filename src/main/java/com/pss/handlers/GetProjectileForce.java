package com.pss.handlers;
import com.pss.interfaces.*;

import javax.vecmath.Vector3d;

public class GetProjectileForce implements IGetProjectileForce{
    
    private IGetConfiguration _configurationHandler;
    private IGetProjectile _projectileHandler;

    public GetProjectileForce(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        _configurationHandler = configurationHandler;
        _projectileHandler = projectileHandler;
    }

    public Vector3d getProjectileForce() {
        Vector3d additionalForce = _projectileHandler.getProjectile().getVelocity();

        // TODO: possibly refactor this if-check to occur on initialization instead of every frame
        Vector3d zeroVector = new Vector3d(0, 0, 0);
        if (additionalForce.equals(zeroVector))
            return zeroVector;
        
        additionalForce.normalize(); // has issues if additionalForce is a zero-vector
        additionalForce.scale((double)(_configurationHandler.getSetting("continualForce")));

        return additionalForce;
        
    }
}
