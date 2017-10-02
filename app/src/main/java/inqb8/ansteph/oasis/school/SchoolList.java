package inqb8.ansteph.oasis.school;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.adapter.SchoolRecyclerViewAdapter;
import inqb8.ansteph.oasis.api.ContentTypes;
import inqb8.ansteph.oasis.api.columns.OrganisationColumns;
import inqb8.ansteph.oasis.api.columns.SchoolColumns;
import inqb8.ansteph.oasis.app.GlobalRetainer;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.mapping.NGOMap;
import inqb8.ansteph.oasis.mapping.SchoolMap;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.model.Category;
import inqb8.ansteph.oasis.model.Organisation;
import inqb8.ansteph.oasis.model.School;
import inqb8.ansteph.oasis.model.WorkArea;
import inqb8.ansteph.oasis.ngo.NGOList;
import inqb8.ansteph.oasis.ngo.WorKAreaList;
import inqb8.ansteph.oasis.toolkit.ToolKitLineView;

public class SchoolList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , RecyclerViewClickListener, SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter mSchoolAdapter;

    List<School> mSchoolList;
    List<School> mFilteredList;
    GlobalRetainer mGlobalRetainer;
    private static final int REQUEST_CODE = 1;
    public SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        mSchoolList = retrieveList();//setupList();
        mFilteredList =mSchoolList;
        mSchoolAdapter = new SchoolRecyclerViewAdapter(mSchoolList,this,this);
        recyclerView.setAdapter(mSchoolAdapter);

        //search view

        searchView = (SearchView)findViewById(R.id.search);
        searchView.setOnQueryTextListener(this);


    }


    private ArrayList<School> retrieveList()
    {
        ArrayList<School>  schools = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.SCHOOL_CONTENT_URI, SchoolColumns.PROJECTION, null,null,null);

        if(cursor.moveToFirst()){
            do{
                School school = new School();

                school.set_id(((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0));

                school.setName((cursor.getString(cursor.getColumnIndex(SchoolColumns.NAME))));
                school.setAddress((cursor.getString(cursor.getColumnIndex(SchoolColumns.ADDRESS))));
                school.setLearnerLevel((cursor.getString(cursor.getColumnIndex(SchoolColumns.LEARNER_LEVEL))));

                school.setGeotag((cursor.getString(cursor.getColumnIndex(SchoolColumns.GEOTAG))));
                school.setTelephone((cursor.getString(cursor.getColumnIndex(SchoolColumns.TELEPHONE))));
                school.setEmail((cursor.getString(cursor.getColumnIndex(SchoolColumns.EMAIL))));
                school.setSynopsys((cursor.getString(cursor.getColumnIndex(SchoolColumns.SYNOPSIS))));
                school.setFax((cursor.getString(cursor.getColumnIndex(SchoolColumns.FAX))));

                school.setWebsite_url((cursor.getString(cursor.getColumnIndex(SchoolColumns.WEBSITE_URL))));
                school.setImg((cursor.getBlob(cursor.getColumnIndex(SchoolColumns.LOGO))));



               /* int genId = (cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID)))!=null ?
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID))):0;*/

               // organisation.setGeneralInfo(retrieveGenInfo(genId));


                schools.add(school);

            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return  schools;
    }




//Dummy data to be removed or retrofitted once the database is live
    ArrayList<School> setupList()
    {
        ArrayList<School>  schools = new ArrayList<>();

        schools.add(new School ("Academics High chool","1 somewhere in some place"," 07252229998"));
        schools.add(new School ("Sonia Marais High School for Girl","1 somewhere in some place"," 07252229998"));

        schools.add(new School ("Lycee Joss","1 somewhere in some place"," 07252229998"));

        schools.add(new School ("Prudence High Tech for Boys","1 somewhere in some place"," 07252229998"));


        // String duration, String task_date, String start, String end, String project, String description, String realduration, String task_break) {
        return  schools;
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
        getMenuInflater().inflate(R.menu.school_list, menu);
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
          //  startActivity(new Intent(getApplicationContext(), SchoolList.class));

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
    public void onRecyclerViewItemClicked(View v, int position) {
        Intent i = new Intent(this,SchoolDetail.class);
        i.putExtra(SchoolDetail.SCHOOL_PARAM, mFilteredList.get(position) );
        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        query = query.toLowerCase();
        mFilteredList = new ArrayList<>();

        for(int i=0; i<mSchoolList.size(); i++)
        {
            final String text = mSchoolList.get(i).getName().toLowerCase();
            if(text.contains(query)){
                mFilteredList.add(mSchoolList.get(i));
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(SchoolList.this));
        mSchoolAdapter = new SchoolRecyclerViewAdapter(mFilteredList,SchoolList.this,this);
        recyclerView.setAdapter(mSchoolAdapter);
        mSchoolAdapter.notifyDataSetChanged(); //data set changed
        return true;

    }
}
