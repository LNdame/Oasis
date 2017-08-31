package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/28.
 */

public interface CityColumns extends DataColumns {

    String NAME  = "name";
    String PROVINCE_ID = "province_id";

    String []PROJECTION = new String[]{_ID, NAME, PROVINCE_ID};
}
