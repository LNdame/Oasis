package inqb8.ansteph.oasis.ngo;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.adapter.CategoryRecyclerViewAdapter;
import inqb8.ansteph.oasis.api.ContentTypes;
import inqb8.ansteph.oasis.api.columns.WorkAreaColumns;
import inqb8.ansteph.oasis.app.GlobalRetainer;
import inqb8.ansteph.oasis.helper.DbHelper;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.mapping.NGOMap;
import inqb8.ansteph.oasis.mapping.SchoolMap;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.model.Category;
import inqb8.ansteph.oasis.model.WorkArea;
import inqb8.ansteph.oasis.registration.EmailPassword;
import inqb8.ansteph.oasis.school.SchoolList;
import inqb8.ansteph.oasis.toolkit.ToolKitLineView;

public class WorKAreaList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , RecyclerViewClickListener {

    public static final Uri CONTENT_URI = Uri.parse("content://inqb8.ansteph.oasis.contentprovider.workareacontentprovider/oasis");

    public DbHelper databhelper ;

    RecyclerView recyclerView;
    RecyclerView.Adapter mCatAdapter;

    private List<Category> mCategoryList;

    public static final String WORKAREA = "WORK_AREA";

    GlobalRetainer mGlobalRetainer;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wor_karea_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGlobalRetainer = (GlobalRetainer) getApplicationContext();

        try {
            databhelper= new DbHelper(getApplicationContext());
            databhelper.createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        mCategoryList = setupList();
        mCatAdapter = new CategoryRecyclerViewAdapter(mCategoryList,this,this);
        recyclerView.setAdapter(mCatAdapter);
    }


    ArrayList<Category> setupList()
    {
        ArrayList<Category>  catList = new ArrayList<>();

       // catList.add(new Category ("Academics",""));
      //  catList.add(new Category ("Something 1",""));

        //catList.add(new Category ("Something 2",""));

        // catList.add(new Category ("Something 3",""));


        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.WORKAREA_CONTENT_URI, WorkAreaColumns.PROJECTION, null,null,null);

        if(cursor.moveToFirst()){
            do{
                Category cat = new Category();

                cat.setId(((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0));

                cat.setName((cursor.getString(cursor.getColumnIndex(WorkAreaColumns.NAME))));
                cat.setDescription((cursor.getString(cursor.getColumnIndex(WorkAreaColumns.DESCRIPTION))));

               catList.add(cat);

            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        // String duration, String task_date, String start, String end, String project, String description, String realduration, String task_break) {
        return  catList;
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
        getMenuInflater().inflate(R.menu.wor_karea_list, menu);
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
           // startActivity(new Intent(getApplicationContext(), WorKAreaList.class));
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

    @Override
    public void onRecyclerViewItemClicked(View v, int position) {
        Intent i = new Intent(this,NGOList.class);

        i.putExtra(WORKAREA, mCategoryList.get(position) );
        // i.putExtra("book", mBookList.get(position) );
        mGlobalRetainer.setCategoryID(mCategoryList.get(position).getId());
        startActivity(i);
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
