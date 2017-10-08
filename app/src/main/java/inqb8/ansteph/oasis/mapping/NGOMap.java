package inqb8.ansteph.oasis.mapping;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.api.ContentTypes;
import inqb8.ansteph.oasis.api.columns.OrganisationColumns;
import inqb8.ansteph.oasis.model.Category;
import inqb8.ansteph.oasis.model.Organisation;
import inqb8.ansteph.oasis.model.School;
import inqb8.ansteph.oasis.model.WorkArea;
import inqb8.ansteph.oasis.ngo.CategoryList;
import inqb8.ansteph.oasis.ngo.NGOList;
import inqb8.ansteph.oasis.ngo.WorKAreaList;
import inqb8.ansteph.oasis.school.SchoolList;
import inqb8.ansteph.oasis.toolkit.ToolKitLineView;
import inqb8.ansteph.oasis.utils.GeoTagUtils;

public class NGOMap extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private MapView mapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngomap);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        final ArrayList<double[]> GeotagList =retrieveGeotag(retrieveList());

        Mapbox.getInstance(this, "pk.eyJ1IjoiYW5zdGVwaCIsImEiOiJjajVoeG5qZ3QxbTY3MnhwbmN6ODE0bTA3In0.XZ6tlAzf1ynmBO7Lc_OK6A");

        // mapbox://styles/ansteph/cj65fp5dt6a5p2rpdy1r7o6zf
        IconFactory iconFactory = IconFactory.getInstance(NGOMap.this);
        final Icon icon = iconFactory.fromResource(R.drawable.ngo_90marker);

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.setStyle("mapbox://styles/ansteph/cj67rrlzv0t232spntzi647iv");

                for(double[]tag:GeotagList)
                {
                    mapboxMap.addMarker(new MarkerViewOptions().position(new LatLng(tag[0], tag[1])).icon(icon));
                }
               // mapboxMap.addMarker(new MarkerViewOptions().position(new LatLng(-34.004441, 25.669534)).icon(icon));
            }
        });



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private ArrayList<double[]> retrieveGeotag(ArrayList<Organisation> organisations)
    {
        ArrayList<double[]> Geotags = new ArrayList<>();

        for (Organisation org:organisations)
        {
            double [] tag  = GeoTagUtils.stripGeotagOrg(org.getGeotag());
            Geotags.add(tag);
        }


        return Geotags;
    }

    private ArrayList<Organisation> retrieveList()
    {
        ArrayList<Organisation>  organisations = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.ORGANISATION_CONTENT_URI, OrganisationColumns.PROJECTION, null,null,null);

        if(cursor.moveToFirst()){
            do{
                Organisation organisation = new Organisation();

                organisation.set_id(((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0));

                organisation.setName((cursor.getString(cursor.getColumnIndex(OrganisationColumns.NAME))));
                organisation.setAddressline1((cursor.getString(cursor.getColumnIndex(OrganisationColumns.ADDRESS1))));
                organisation.setContactperson1Name((cursor.getString(cursor.getColumnIndex(OrganisationColumns.CONTACTPERSON1_NAME))));
                organisation.setContactperson1Position((cursor.getString(cursor.getColumnIndex(OrganisationColumns.CONTACTPERSON1_POSITION))));

                organisation.setContactperson2Name((cursor.getString(cursor.getColumnIndex(OrganisationColumns.CONTACTPERSON2_NAME))));
                organisation.setContactperson2Position((cursor.getString(cursor.getColumnIndex(OrganisationColumns.CONTACTPERSON2_POSITION))));

                // cat.setDescription((cursor.getString(cursor.getColumnIndex(OrganisationColumns.DESCRIPTION))));
                organisation.setGeotag((cursor.getString(cursor.getColumnIndex(OrganisationColumns.GEOTAG))));
                //organisation.setWorkArea(new WorkArea(cat.getId(),cat.getName(),cat.getDescription()));

                int genId = (cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID)))!=null ?
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID))):0;


                organisations.add(organisation);

            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return  organisations;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ngomap, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.action_home){
            startActivity(new Intent(getApplicationContext(), Welcome.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_welcome) {
            // Handle the camera action
            startActivity(new Intent(getApplicationContext(), Welcome.class));
        } else if (id == R.id.nav_school_map) {
            startActivity(new Intent(getApplicationContext(), SchoolMap.class));

        } else if (id == R.id.nav_school_list) {
            startActivity(new Intent(getApplicationContext(), SchoolList.class));

        } else if (id == R.id.nav_ngo_map) {
            startActivity(new Intent(getApplicationContext(), NGOMap.class));

        } else if (id == R.id.nav_ngo_list) {
            startActivity(new Intent(getApplicationContext(), WorKAreaList.class));
        } else if (id == R.id.nav_toolkit) {
            startActivity(new Intent(getApplicationContext(), ToolKitLineView.class));
        } else if (id == R.id.nav_feedback){
           // startActivity(new Intent(getApplicationContext(), SchoolMap.class));
        } else if (id == R.id.nav_logout){
           // startActivity(new Intent(getApplicationContext(), SchoolMap.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    public  void onViewListClicked (View v){
        startActivity(new Intent(getApplicationContext(), WorKAreaList.class));
    }

    public void DisplayMapMarker(){
        IconFactory iconFactory = IconFactory.getInstance(NGOMap.this);
       // Icon icon = iconFactory.fromResource(R.drawable.)
    }
}
