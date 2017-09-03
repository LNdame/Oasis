package inqb8.ansteph.oasis.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import inqb8.ansteph.oasis.api.Tables;
import inqb8.ansteph.oasis.api.columns.SchoolColumns;
import inqb8.ansteph.oasis.api.columns.SuburbColumns;
import inqb8.ansteph.oasis.helper.DbHelper;

public class SuburbContentProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.suburbcontentprovider/oasis");
    private SQLiteDatabase db;

    public DbHelper databhelper ;



    public SuburbContentProvider() {
    }



    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        try {
            databhelper= new DbHelper(getContext());
            databhelper.createDatabase();
        } catch (Exception e) {

            e.printStackTrace();
        }

        db = databhelper.getReadDatabase();

        return (db==null) ? false:true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int retVal = db.delete(Tables.SUBURB, selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return retVal;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues inValues) {
        ContentValues values = new ContentValues(inValues);

        long rowId = db.insert(Tables.SUBURB, SuburbColumns.NAME, values);

        if(rowId>0){
            Uri insertUri = ContentUris.withAppendedId(CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(insertUri, null);

            return insertUri;
        }else{
            throw new SQLException("Failed to insert row into "+ uri);
        }
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        String orderBy;
        if(TextUtils.isEmpty(sortOrder))
        {
            orderBy = SuburbColumns.NAME;
        }else {
            orderBy =sortOrder;
        }

        Cursor c = db.query(Tables.SUBURB, projection,selection,selectionArgs,null,null,orderBy);
        c.setNotificationUri(getContext().getContentResolver(), uri);

        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int retVal = db.update(Tables.SUBURB, values, selection,selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return retVal;
    }
}
