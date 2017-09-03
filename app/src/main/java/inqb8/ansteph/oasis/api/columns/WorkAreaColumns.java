package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/31.
 */

public interface WorkAreaColumns extends DataColumns {
    String DESCRIPTION = "description";
    String NAME = "name";
    String [] PROJECTION = new String[]{_ID,NAME,DESCRIPTION};
}
