package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2019/05/07.
 */

public interface Organisation_Work_Area_Columns {

    String   LOG_ID = "log_id";
    String  ORGANISATION_ID = "organisation_id";
    String  WORK_AREA_ID= "work_area_id";

    String [] PROJECTION = new String[]{LOG_ID,ORGANISATION_ID, WORK_AREA_ID};

}
