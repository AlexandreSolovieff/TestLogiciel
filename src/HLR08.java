import java.util.ArrayList;
import java.util.List;

public class HLR08 {
    final int RA = 0;
    final int TA = 1;
    final int CT_DIST = 30; //NM

    final String RESOLUTION_ADVISORY = "RESOLUTION ADVISORY";
    final String TRAFFIC_ADVISORY = "TRAFFIC ADVISORY";
    final String CLOSE_TRAFFIC = "CLOSE TRAFFIC";

    private double Boundrie(double speed, int sec) {
        double boundrie = (speed * sec) / 3600; //km
        boundrie /= 1.852; //NM

        return boundrie;
    }

    private List<Double> Boundries(Plane self_p) {
        double speed = self_p.getSpeed();
        List<Double> boundries = new ArrayList<>();

        boundries.add(Boundrie(speed, 25));
        boundries.add(Boundrie(speed, 40));

        return boundries;
    }

    private double Distance(Plane self_p, Plane foreign_p) {
        double longitude_self = self_p.getLongitude();
        double latitude_self = self_p.getLatitude();
        double longitude_foreign = foreign_p.getLongitude();
        double latitude_foreign = foreign_p.getLatitude();

        //Haversine method
        double a = Math.sin((latitude_foreign - latitude_self) / 2)
                * Math.sin((latitude_foreign - latitude_self) / 2)
                + Math.cos(latitude_self) * Math.cos(latitude_self)
                * Math.sin((longitude_foreign - longitude_self) / 2)
                * Math.sin((longitude_foreign - longitude_self) / 2);

        double c = 2 * Math.atan(Math.sqrt(a) / Math.sqrt(1-a));

        double dist = 6371 * c; //km
        dist /= 1.852; //NM

        return dist;
    }

    public String Alert(Plane self_p, Plane foreign_p) {
        List<Double> boundries = Boundries(self_p);
        double dist = Distance(self_p, foreign_p);

        if (dist <= boundries.get(RA)) {
            return RESOLUTION_ADVISORY;
        } else if (dist <= boundries.get(TA)) {
            return TRAFFIC_ADVISORY;
        } else if (dist <= CT_DIST) {
            return CLOSE_TRAFFIC;
        }

        return null;
    }
}
