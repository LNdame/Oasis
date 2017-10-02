package inqb8.ansteph.oasis.utils;

/**
 * Created by loicstephan on 2017/09/30.
 */

public class GeoTagUtils {

    public static double [] stripGeotag(String geotag)
    {
        String [] tag = geotag.split(",");

        double []geolatlong = new double[2];
        geolatlong[0] = Double.parseDouble(tag[1]) ;
        geolatlong[1] = Double.parseDouble(tag[0]) ;

        return geolatlong;

    }


    public static double [] stripGeotagOrg(String geotag)
    {
        String [] tag = geotag.split(",");

        double []geolatlong = new double[2];
        geolatlong[0] = Double.parseDouble(tag[0]) ;
        geolatlong[1] = Double.parseDouble(tag[1]) ;

        return geolatlong;

    }
}
