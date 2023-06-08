import javax.vecmath.Vector3d;

public class Projectile {
    private Vector3d Position;

    public Vector3d getPosition() {
        return Position;
    }

    public void setPosition(double x, double y, double z) {
        Position.setX(x);
        Position.setY(y);
        Position.setZ(z);
    }

    public void setPosition(Vector3d position) {
        Position = position;
    }

    private Vector3d Velocity;

    public Vector3d getVelocity() {
        return Velocity;
    }

    public void setVelocity(double x, double y, double z) {
        Velocity.setX(x);
        Velocity.setY(y);
        Velocity.setZ(z);
    }

    public void setVelocity(Vector3d velocity) {
        Velocity = velocity;
    }

    private double Weight;

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }
}
