package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/28.
 */

public interface SchoolColumns extends DataColumns {


    String  NAME = "name";
    String  LEARNER_LEVEL= "learner_level";
    String  EDUCATOR_PARENT= "educator_parent";
    String  FREQUENCY = "frequency";
    String  GENERAL_INFO_ID= "general_info_id";

    String   ADDRESS= "address";
    String   TELEPHONE = "telephone";
    String   FAX = "fax";
    String   EMAIL = "email";
    String   WEBSITE_URL = "website_url";
    String   LOGO = "logo";
    String   SYNOPSIS= "synopsis";



    String  SUBURB_ID 	= "suburb_id";
    String CITY_ID	= "city_id";
    String PROVINCE_ID= "province_id";
    String        GEOTAG = "geotag";


    String [] PROJECTION = new String[]{_ID,NAME,LEARNER_LEVEL,EDUCATOR_PARENT,FREQUENCY,
            GENERAL_INFO_ID,SUBURB_ID,CITY_ID,PROVINCE_ID,GEOTAG , ADDRESS,TELEPHONE,FAX, EMAIL,WEBSITE_URL,LOGO, SYNOPSIS };



}
