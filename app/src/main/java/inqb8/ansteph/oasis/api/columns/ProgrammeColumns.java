package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/09/15.
 */

public interface ProgrammeColumns extends DataColumns {


    String   NAME ="name";
    String  DURATION_COST ="duration_cost";
    String  PRIMARY_HIGHSCHOOL="primary_highschool";
    String  EDUCATOR_PARENT="educator_parent";
    String ONCEOFF_REGULAR="onceoff_regular";
    String ORGANISATION_ID="organisation_id";



    String [] PROJECTION = new String[]{_ID,NAME,DURATION_COST,PRIMARY_HIGHSCHOOL,EDUCATOR_PARENT,ONCEOFF_REGULAR,ORGANISATION_ID};



}
