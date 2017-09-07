package inqb8.ansteph.oasis.mapping;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.ngo.NGOList;
import inqb8.ansteph.oasis.registration.EmailPassword;
import inqb8.ansteph.oasis.school.SchoolList;
import inqb8.ansteph.oasis.toolkit.Previewer;
import inqb8.ansteph.oasis.toolkit.ToolKitList;

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String TAG = Welcome.class.getSimpleName();

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public static final  int RC_SIGN_IN =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
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

        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null)
                {
                    //User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                }else{
                    Log.d(TAG, "onAuthStateChanged:signed_out");

                    startActivity(new Intent(getApplicationContext(), EmailPassword.class));
                   /* startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(true)
                                    .setProviders(AuthUI.EMAIL_PROVIDER,
                                            AuthUI.GOOGLE_PROVIDER  )
                                    .build(),
                            RC_SIGN_IN);*/
                }

            }
        };
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
        getMenuInflater().inflate(R.menu.welcome, menu);
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
         //   startActivity(new Intent(getApplicationContext(), Welcome.class));
        } else if (id == R.id.nav_school_map) {
            startActivity(new Intent(getApplicationContext(), SchoolMap.class));

        } else if (id == R.id.nav_school_list) {
            startActivity(new Intent(getApplicationContext(), SchoolList.class));

        } else if (id == R.id.nav_ngo_map) {
            startActivity(new Intent(getApplicationContext(), NGOMap.class));

        } else if (id == R.id.nav_ngo_list) {
            startActivity(new Intent(getApplicationContext(), NGOList.class));
        } else if (id == R.id.nav_toolkit) {
            startActivity(new Intent(getApplicationContext(), ToolKitList.class));
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

    public void onViewNGOClicked (View v)
    {
       startActivity(new Intent(getApplicationContext(), NGOMap.class));
    }

    public void onViewSchoolClicked (View v)
    {
        startActivity(new Intent(getApplicationContext(), SchoolMap.class));
    }
}
