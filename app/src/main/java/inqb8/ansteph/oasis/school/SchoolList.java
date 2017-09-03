package inqb8.ansteph.oasis.school;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.adapter.SchoolRecyclerViewAdapter;
import inqb8.ansteph.oasis.app.GlobalRetainer;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.mapping.NGOMap;
import inqb8.ansteph.oasis.mapping.SchoolMap;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.model.School;
import inqb8.ansteph.oasis.ngo.NGOList;

public class SchoolList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , RecyclerViewClickListener{

    RecyclerView recyclerView;
    RecyclerView.Adapter mSchoolAdapter;

    List<School> mSchoolList;
    GlobalRetainer mGlobalRetainer;

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

        mSchoolList = setupList();
        mSchoolAdapter = new SchoolRecyclerViewAdapter(mSchoolList,this,this);
        recyclerView.setAdapter(mSchoolAdapter);
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
            startActivity(new Intent(getApplicationContext(), NGOList.class));
        } else if (id == R.id.nav_toolkit) {
            startActivity(new Intent(getApplicationContext(), SchoolMap.class));
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
        // i.putExtra("book", mBookList.get(position) );
        startActivity(i);
    }
}
