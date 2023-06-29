package com.pss.handlers;
import com.pss.enums.Settings;
import com.pss.interfaces.*;

import javax.vecmath.Vector3d;
public class GetProjectileGravity implements IGetProjectileGravity{
    private IGetConfiguration _configurationHandler;
    private IGetProjectile _projectileHandler;
    
    public GetProjectileGravity(IGetConfiguration configurationHandler, IGetProjectile projectileHander) {
        _configurationHandler = configurationHandler;
        _projectileHandler = projectileHander;
    }

    public Vector3d getProjectileGravity() {
        Vector3d down = new Vector3d(0, 0, -1);

        down.scale((double)(_configurationHandler.getSetting(Settings.Gravity)) * _projectileHandler.getProjectile().getWeight());
        
        return down;
    }
}
