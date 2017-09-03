package inqb8.ansteph.oasis.api;

import android.net.Uri;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class ContentTypes {

    public static final Uri WORKAREA_CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.workareacontentprovider/oasis");

    public static final Uri CITY_CONTENT_URI =Uri.parse("content://inqb8.ansteph.oasis.contentprovider.citycontentprovider/oasis");

    public static final Uri GENERALINFO_CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.generalinfocontentprovider/oasis");

    public static final Uri ORGANISATION_CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.organisationcontentprovider/oasis");

    public static final Uri PROVINCE_CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.provincecontentprovider/oasis");

    public static final Uri SCHOOL_CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.schoolcontentprovider/oasis");

    public static final Uri SUBURB_CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.suburbcontentprovider/oasis");


}
