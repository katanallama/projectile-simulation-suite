import javax.vecmath.Vector3d;

public class StubGetProjectileForce implements IGetProjectileForce{
    public Vector3d getProjectileForce() {
        return new Vector3d(0, 0, 0);
    }
}
