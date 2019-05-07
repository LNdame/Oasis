package inqb8.ansteph.oasis.ngo;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.lsjwzh.widget.recyclerviewpager.RecyclerViewPager;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.adapter.LayoutAdapter;
import inqb8.ansteph.oasis.api.ContentTypes;
import inqb8.ansteph.oasis.api.columns.GeneralInfoColumns;
import inqb8.ansteph.oasis.api.columns.OrganisationColumns;
import inqb8.ansteph.oasis.api.columns.WorkAreaColumns;
import inqb8.ansteph.oasis.app.GlobalRetainer;
import inqb8.ansteph.oasis.helper.DbHelper;
import inqb8.ansteph.oasis.mapping.NGOMap;
import inqb8.ansteph.oasis.mapping.SchoolMap;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.model.Category;
import inqb8.ansteph.oasis.model.GeneralInfo;
import inqb8.ansteph.oasis.model.Organisation;
import inqb8.ansteph.oasis.model.WorkArea;
import inqb8.ansteph.oasis.registration.EmailPassword;
import inqb8.ansteph.oasis.school.SchoolList;
import inqb8.ansteph.oasis.toolkit.ToolKitLineView;

public class NGOList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected RecyclerViewPager mRecyclerView;

    Category  mCurrentCategory ;

    GlobalRetainer mGlobalRetainer;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngolist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGlobalRetainer = (GlobalRetainer) getApplicationContext();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null)
                {
                    //User is signed in
                    // Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                }else{
                    // Log.d(TAG, "onAuthStateChanged:signed_out");
                    startActivity(new Intent(getApplicationContext(), EmailPassword.class));

                }

            }
        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Bundle bundle = getIntent().getExtras();

        if(bundle !=null)
        {
            mCurrentCategory =(Category) bundle.getSerializable(WorKAreaList.WORKAREA);
        }

        if(mCurrentCategory!=null)
        {
            ArrayList<Organisation> orgList=retrieveList(mCurrentCategory);// databhelper.retrieveAllOrganisations();//

            initViewPager(mCurrentCategory, orgList);

            setTitle(mCurrentCategory.getName());

        }else{
           mCurrentCategory = retrieveCategory(mGlobalRetainer.getCategoryID());

            ArrayList<Organisation> orgList=retrieveList(mCurrentCategory);// databhelper.retrieveAllOrganisations();//

            initViewPager(mCurrentCategory, orgList);

            setTitle(mCurrentCategory.getName());
        }





    }





    private ArrayList<Organisation> retrieveList(Category cat)
    {
        ArrayList<Organisation>  organisations = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        //this is it // the thing that need to change
        Cursor cursor = resolver.query(ContentTypes.ORGANISATION_CONTENT_URI, OrganisationColumns.PROJECTION,
                OrganisationColumns.WORK_AREA_ID + "=?",new String[]{String.valueOf(cat.getId())},null);

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
                organisation.setWorkArea(new WorkArea(cat.getId(),cat.getName(),cat.getDescription()));

                int genId = (cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID)))!=null ?
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID))):0;

                organisation.setGeneralInfo(retrieveGenInfo(genId));


                organisations.add(organisation);

            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return  organisations;
    }



    private GeneralInfo retrieveGenInfo(int id){

        GeneralInfo generalInfo = new GeneralInfo();
        ContentResolver resolver  = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.GENERALINFO_CONTENT_URI, GeneralInfoColumns.PROJECTION,
                GeneralInfoColumns._ID + "=?", new String[]{String.valueOf(id)}, null );


        if(cursor.moveToFirst())
        {
            do{
                try {

                    generalInfo.set_id(((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0));
                    generalInfo.setTelephoneNumber((cursor.getString(cursor.getColumnIndex(GeneralInfoColumns.TELEPHONE))));
                    generalInfo.setFaxNumber((cursor.getString(cursor.getColumnIndex(GeneralInfoColumns.FAX))));
                    generalInfo.setEmail((cursor.getString(cursor.getColumnIndex(GeneralInfoColumns.EMAIL))));
                    generalInfo.setWebsiteurl((cursor.getString(cursor.getColumnIndex(GeneralInfoColumns.WEBSITE_URL))));
                    generalInfo.setSynopsis((cursor.getString(cursor.getColumnIndex(GeneralInfoColumns.SYNOPSIS))));
                    generalInfo.setLogo(cursor.getBlob(cursor.getColumnIndex(GeneralInfoColumns.LOGO)));

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }while (cursor.moveToNext());
        }


        return  generalInfo;
    }



    public Category retrieveCategory(int id)
    {
        Category cat = new Category();

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.WORKAREA_CONTENT_URI, WorkAreaColumns.PROJECTION, WorkAreaColumns._ID + "=?",
                new String[]{String.valueOf(id)},null);

        if(cursor.moveToFirst()){
            do{
                cat.setId(((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0));

                cat.setName((cursor.getString(cursor.getColumnIndex(WorkAreaColumns.NAME))));
                cat.setDescription((cursor.getString(cursor.getColumnIndex(WorkAreaColumns.DESCRIPTION))));


            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return cat;
    }



    protected void  initViewPager(Category category, ArrayList<Organisation> organisations) {

        mRecyclerView =(RecyclerViewPager) findViewById(R.id.recyclerviewNgo);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new LayoutAdapter(this,mRecyclerView,organisations));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLongClickable(true);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int childCount = mRecyclerView.getChildCount();
                int width =mRecyclerView.getChildAt(0).getWidth();
                int padding = (mRecyclerView.getWidth()-width)/2;

                for(int j =0; j<childCount; j++)
                {
                    View v = recyclerView.getChildAt(j);

                    float rate =0;
                    if (v.getLeft() <= padding) {
                        if (v.getLeft() >= padding - v.getWidth()) {
                            rate = (padding - v.getLeft()) * 1f / v.getWidth();
                        } else {
                            rate = 1;
                        }
                        v.setScaleY(1 - rate * 0.1f);
                        v.setScaleX(1 - rate * 0.1f);

                    } else {
                        //往右 从 padding 到 recyclerView.getWidth()-padding 的过程中，由大到小
                        if (v.getLeft() <= recyclerView.getWidth() - padding) {
                            rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                        }
                        v.setScaleY(0.9f + rate * 0.1f);
                        v.setScaleX(0.9f + rate * 0.1f);
                    }
                }
            }
        });
        mRecyclerView.addOnPageChangedListener(new RecyclerViewPager.OnPageChangedListener() {
            @Override
            public void OnPageChanged(int oldPosition, int newPosition) {
                Log.d("test", "oldPosition:" + oldPosition + " newPosition:" + newPosition);
            }
        });

        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (mRecyclerView.getChildCount() < 3) {
                    if (mRecyclerView.getChildAt(1) != null) {
                        if (mRecyclerView.getCurrentPosition() == 0) {
                            View v1 = mRecyclerView.getChildAt(1);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        } else {
                            View v1 = mRecyclerView.getChildAt(0);
                            v1.setScaleY(0.9f);
                            v1.setScaleX(0.9f);
                        }
                    }
                } else {
                    if (mRecyclerView.getChildAt(0) != null) {
                        View v0 = mRecyclerView.getChildAt(0);
                        v0.setScaleY(0.9f);
                        v0.setScaleX(0.9f);
                    }
                    if (mRecyclerView.getChildAt(2) != null) {
                        View v2 = mRecyclerView.getChildAt(2);
                        v2.setScaleY(0.9f);
                        v2.setScaleX(0.9f);
                    }
                }

            }
        });
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
        getMenuInflater().inflate(R.menu.ngolist, menu);
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
            signOut();
            // startActivity(new Intent(getApplicationContext(), SchoolMap.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signOut() {
        mAuth.signOut();
        //updateUI(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthStateListener!=null)
            mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mAuthStateListener!=null)
            mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
