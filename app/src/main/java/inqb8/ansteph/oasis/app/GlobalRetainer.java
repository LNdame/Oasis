package inqb8.ansteph.oasis.app;

import android.app.Application;
import android.content.Context;

import com.mapbox.mapboxsdk.Mapbox;

//import inqb8.ansteph.oasis.R;

/**
 * Created by loicstephan on 2017/07/24.
 */

public class GlobalRetainer extends Application {
    private static GlobalRetainer mInstance;
    private static Context mAppContext;

    private int toolkitPosition;
    private int categoryID;

    private String toolkitTitle;

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalRetainer.mAppContext = getApplicationContext();
        mInstance = this;
        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoiYW5zdGVwaCIsImEiOiJjajVoejdtcmgxb3Q0MzNyeWlvdWhtYml5In0.hs9kEugjnqYXJMB41Fhxbw");
    }

    public static GlobalRetainer getInstance(){
        return mInstance;
    }


    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }


    public int getToolkitPosition() {
        return toolkitPosition;
    }

    public void setToolkitPosition(int toolkitPosition) {
        this.toolkitPosition = toolkitPosition;
    }


    public String getToolkitTitle() {
        return toolkitTitle;
    }

    public void setToolkitTitle(String toolkitTitle) {
        this.toolkitTitle = toolkitTitle;
    }
}
