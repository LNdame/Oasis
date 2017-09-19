package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/28.
 */

public interface OrganisationColumns extends DataColumns {




  String  NAME ="name";
    String  ADDRESS1 ="Address1";
    String  CONTACTPERSON1_NAME ="contactperson1_name";
    String  CONTACTPERSON2_NAME ="contactperson2_name";
    String  CONTACTPERSON1_POSITION ="contactperson1_position";
    String  CONTACTPERSON2_POSITION ="contactperson2_position";
    String   SUBURB_ID ="suburb_id";
    String    CITY_ID ="city_id";
    String   PROVINCE_ID ="province_id";
    String           WORK_AREA_ID ="work_area_id";
  String GENERAL_ID = "general_id";





    String [] PROJECTION = new String[]{_ID,NAME, ADDRESS1, CONTACTPERSON1_NAME, CONTACTPERSON2_NAME, CONTACTPERSON1_POSITION,
            CONTACTPERSON2_POSITION, SUBURB_ID, CITY_ID,PROVINCE_ID , WORK_AREA_ID, GENERAL_ID};

}
