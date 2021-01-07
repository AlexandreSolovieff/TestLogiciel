public class Plane {
    private double longitude;
    private double latitude;
    private double speed;   //knot

    public Plane(double longitude, double latitude, double speed) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getSpeed() {
        return speed;
    }
}
