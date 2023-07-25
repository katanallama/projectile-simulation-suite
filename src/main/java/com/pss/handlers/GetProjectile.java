package com.pss.handlers;

import com.pss.enums.Settings;
import com.pss.interfaces.*;
import com.pss.models.*;

import javax.vecmath.Vector3d;

public class GetProjectile implements IGetProjectile {
    public GetProjectile(IGetConfiguration configurationHandler) {
        _configurationHandler = configurationHandler;
    }
    
    private IGetConfiguration _configurationHandler;

    private static Projectile _instance;
    
    public Projectile getProjectile() {
        if (_instance == null) {
            _instance = createProjectile();
        }

        return _instance;
    }

    private Projectile createProjectile() {
        // Todo make this set initial settings via configs
        Projectile projectile = new Projectile();
        
        projectile.setWeight(_configurationHandler.getSetting(Settings.Weight));
        Vector3d initialVelocity = new Vector3d(_configurationHandler.<Vector3d>getSetting(Settings.InitialDirection));
        initialVelocity.normalize();
        initialVelocity.scale(_configurationHandler.getSetting(Settings.MuzzleVelocity));
        projectile.setVelocity(initialVelocity);

        return projectile;
    }
}
