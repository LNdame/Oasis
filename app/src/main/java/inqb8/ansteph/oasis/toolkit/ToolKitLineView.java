package inqb8.ansteph.oasis.toolkit;

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
import inqb8.ansteph.oasis.adapter.TimeLineAdapter;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.timelinemodel.OrderStatus;
import inqb8.ansteph.oasis.timelinemodel.Orientation;
import inqb8.ansteph.oasis.timelinemodel.TimeLineModel;

public class ToolKitLineView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , RecyclerViewClickListener {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private Orientation mOrientation;
    private boolean mWithLinePadding;

    public final static String EXTRA_ORIENTATION = "EXTRA_ORIENTATION";
    public final static String EXTRA_WITH_LINE_PADDING = "EXTRA_WITH_LINE_PADDING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_kit_line_view);
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

        mOrientation = Orientation.VERTICAL;
        mWithLinePadding = false;


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        initView();
    }


    private LinearLayoutManager getLinearLayoutManager() {
        if (mOrientation == Orientation.HORIZONTAL) {
            return new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        }
    }

    private void initView() {
        setDataListItems();
        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding, this);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }


    private void setDataListItems(){
        mDataList.add(new TimeLineModel("Pre-Planning", "Before you start!",  OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Developing a Plan", "Next",  OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Initial Communication", "Next", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Partnership Agreement", "Next", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Introducing the Programme", "Next", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Programme Implementation", "Next", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Monitoring and Evaluation", "Next", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Exiting a Program", "Can restart the process from the top", OrderStatus.COMPLETED));

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
        getMenuInflater().inflate(R.menu.tool_kit_list, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRecyclerViewItemClicked(View v, int position) {
        Intent i = new Intent(this,ToolkitItemDetail.class);
        // i.putExtra("book", mBookList.get(position) );
        startActivity(i);
    }
}
