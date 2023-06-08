public class MakeProjectileSimulator {
    public ProjectileSimulator createProjectileSimulator(IGetConfiguration configurationHandler) {
        var projectileHandler = new GetProjectile(configurationHandler);
        
        var projectileGravityHandler = getProjectileGravityHandler(configurationHandler, projectileHandler);
        var projectileDragHandler = getProjectileDragHandler(configurationHandler, projectileHandler);
        var projectileForceHandler = getProjectileForceyHandler(configurationHandler, projectileHandler);

        return new ProjectileSimulator(projectileHandler, projectileDragHandler, projectileForceHandler, projectileGravityHandler);
    }

    private IGetProjectileGravity getProjectileGravityHandler(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        return new GetProjectiveGravity(configurationHandler, projectileHandler);
    }
    
    private IGetProjectileDrag getProjectileDragHandler(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        return new StubGetProjectileDrag();
    }
    
    private IGetProjectileForce getProjectileForceyHandler(IGetConfiguration configurationHandler, IGetProjectile projectileHandler) {
        return new StubGetProjectileForce();
    }
}
