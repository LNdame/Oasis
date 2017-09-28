package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/31.
 */

public interface Organisation_SchoolColumns extends DataColumns {



   String   LOG_ID = "log_id";
    String  ORGANISATION_ID = "organisation_id";
    String  SCHOOL_ID= "school_id";
    String   SCHOOL_NAME= "school_name";
    String GEOGRAPHIC_AREA= "geographic_area";
    String  LEARNER_TARGET= "learner_target";
    String  TARGET= "target";
    String   FREQUENCY= "frequency";

    String [] PROJECTION = new String[]{LOG_ID,ORGANISATION_ID, SCHOOL_ID, SCHOOL_NAME, GEOGRAPHIC_AREA, LEARNER_TARGET,
            TARGET, FREQUENCY};








}
