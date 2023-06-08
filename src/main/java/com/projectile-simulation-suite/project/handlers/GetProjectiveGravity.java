public class GetProjectiveGravity implements GetProjectiveGravity {
    private IGetConfiguration _configurationHandler;
    private IGetProjectile _projectileHandler;
    
    public GetProjectiveGravity(IGetConfiguration configurationHandler, IGetProjectileGravity projectileHandler) {
        _configurationHandler = configurationHandler;
        _projectileHandler = projectileHandler;
    }

    
}
