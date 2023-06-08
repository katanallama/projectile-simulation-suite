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
    };

    private Projectile createProjectile() {
        // Todo make this set initial settings via configs
        return new Projectile();
    }
}
