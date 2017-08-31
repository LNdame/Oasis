package inqb8.ansteph.oasis.api.columns;

/**
 * Created by loicstephan on 2017/08/28.
 */

public interface GeneralInfoColumns extends DataColumns {

    String TELEPHONE ="telephone";

    String FAX ="fax";
    String EMAIL ="email";
    String WEBSITE_URL ="website_url";
    String LOGO = "logo";
    String SYNOPSIS ="synopsis";


    String [] PROJECTION = new String []{_ID,TELEPHONE,FAX,EMAIL, WEBSITE_URL, LOGO, SYNOPSIS };

}
