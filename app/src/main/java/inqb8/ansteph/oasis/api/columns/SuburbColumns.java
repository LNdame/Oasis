package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/28.
 */

public interface SuburbColumns extends DataColumns {

  String NAME=  "name"	;
   String POSTALCODE = "postalcode";
  String CITY_ID = "city_id" ;


    String [] PROJECTION = new String[]{_ID,NAME,POSTALCODE,CITY_ID};
}
