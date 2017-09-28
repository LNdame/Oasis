package inqb8.ansteph.oasis.ngo;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.api.ContentTypes;
import inqb8.ansteph.oasis.api.columns.OrganisationColumns;
import inqb8.ansteph.oasis.api.columns.Organisation_SchoolColumns;
import inqb8.ansteph.oasis.api.columns.ProgrammeColumns;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.model.Category;
import inqb8.ansteph.oasis.model.Organisation;
import inqb8.ansteph.oasis.model.Organisation_School;
import inqb8.ansteph.oasis.model.Programme;
import inqb8.ansteph.oasis.model.WorkArea;

public class NGODetail extends AppCompatActivity {

    private static final String TAG = NGODetail.class.getSimpleName();

    FragmentPagerAdapter adapterViewPager;
    ViewPager viewPager;
    TabLayout tabLayout;

    Organisation receivedOrg;
    TextView txtNGOName, txtCat;
    ImageView imgLogo;

    ArrayList<Programme> programmeArrayList;

    ArrayList<Organisation_School> OrganisationSchoolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ngodetail);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle b  = getIntent().getExtras();

        if(b!=null)
        {
            receivedOrg =(Organisation) b.getSerializable("Org");
            initViews(receivedOrg);
            setTitle(receivedOrg.getName());
        }

        programmeArrayList= retrieveList(receivedOrg.get_id());
        OrganisationSchoolList = retrieveOrgList(receivedOrg.get_id());


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapterViewPager = new ListingAdapter(getSupportFragmentManager(),programmeArrayList,receivedOrg,OrganisationSchoolList);
        viewPager.setAdapter(adapterViewPager);

        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);






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



    public void initViews(Organisation org)
    {
        txtCat = (TextView) findViewById(R.id.txtCategorydet);
        txtNGOName = (TextView) findViewById(R.id.txtNGONamedet);

        imgLogo = (ImageView) findViewById(R.id.imgLogdet);

        txtCat.setText("[" + org.getWorkArea().getName()+"]" );

        txtNGOName.setText(org.getName());

        byte[]logo  = org.getGeneralInfo().getLogo();
        Bitmap bmp = BitmapFactory.decodeByteArray(logo,0,logo.length);

       imgLogo.setImageBitmap(bmp);
    }



    private ArrayList<Organisation_School> retrieveOrgList(int org_id)
    {
        ArrayList<Organisation_School>  organisation_schools = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.ORGANISATION_SCHOOL_CONTENT_URI, Organisation_SchoolColumns.PROJECTION,
                Organisation_SchoolColumns.ORGANISATION_ID + "=?",new String[]{String.valueOf(org_id)},null);

        if(cursor.moveToFirst()){
            do{
                Organisation_School prog = new Organisation_School(
                        ((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0) ,
                        ((cursor.getString(1))!=null && !TextUtils.isEmpty(cursor.getString(1)) ? Integer.parseInt(cursor.getString(1)):0) ,
                        ((cursor.getString(2))!=null && !TextUtils.isEmpty(cursor.getString(2)) ? Integer.parseInt(cursor.getString(2)):0) ,

                        (cursor.getString(cursor.getColumnIndex(Organisation_SchoolColumns.SCHOOL_NAME))),

                        (cursor.getString(cursor.getColumnIndex(Organisation_SchoolColumns.GEOGRAPHIC_AREA))),

                        (cursor.getString(cursor.getColumnIndex(Organisation_SchoolColumns.LEARNER_TARGET))),

                        (cursor.getString(cursor.getColumnIndex(Organisation_SchoolColumns.TARGET))),

                        (cursor.getString(cursor.getColumnIndex(Organisation_SchoolColumns.FREQUENCY)))


                );




                organisation_schools.add(prog);

            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return  organisation_schools;
    }


    private ArrayList<Programme> retrieveList(int org_id)
    {
        ArrayList<Programme>  programmes = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(ContentTypes.PROGRAMME_CONTENT_URI, ProgrammeColumns.PROJECTION,
                ProgrammeColumns.ORGANISATION_ID + "=?",new String[]{String.valueOf(org_id)},null);

        if(cursor.moveToFirst()){
            do{
                Programme prog = new Programme(
                        ((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0) ,

                        (cursor.getString(cursor.getColumnIndex(ProgrammeColumns.NAME))),

                        (cursor.getString(cursor.getColumnIndex(ProgrammeColumns.DURATION_COST))),

                        (cursor.getString(cursor.getColumnIndex(ProgrammeColumns.PRIMARY_HIGHSCHOOL))),

                        (cursor.getString(cursor.getColumnIndex(ProgrammeColumns.EDUCATOR_PARENT))),

                        (cursor.getString(cursor.getColumnIndex(ProgrammeColumns.ONCEOFF_REGULAR))),

                        (cursor.getString(cursor.getColumnIndex(ProgrammeColumns.ORGANISATION_ID)))!=null ?
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProgrammeColumns.ORGANISATION_ID))):0
                );




              /*  prog.set_id(((cursor.getString(0))!=null ? Integer.parseInt(cursor.getString(0)):0));

                prog.setName((cursor.getString(cursor.getColumnIndex(ProgrammeColumns.NAME))));
                // cat.setDescription((cursor.getString(cursor.getColumnIndex(OrganisationColumns.DESCRIPTION))));



                int organisation_id = (cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID)))!=null ?
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(OrganisationColumns.GENERAL_ID))):0; */

               // organisation.setGeneralInfo(retrieveGenInfo(genId));


                programmes.add(prog);

            }while(cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return  programmes;
    }




    public static class ListingAdapter extends FragmentPagerAdapter{

        private static int NUM_ITEMS = 3;
        private String tabTitles[] = new String[]{"About", "Schools", "Programmes"};
        ArrayList<Organisation_School> OrganisationSchoolList;
        ArrayList<Programme> mProgList;
        Organisation mOrganisation;
        public ListingAdapter(FragmentManager fm , ArrayList<Programme> progList, Organisation organisation,ArrayList<Organisation_School> OrganisationSchools) {

            super(fm);
            mProgList =progList;
            mOrganisation = organisation;
            OrganisationSchoolList =OrganisationSchools;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0: return InfoFragment.newInstance("Page #1", mOrganisation);
                case 1: return SchoolListFragment.newInstance("Page #2", OrganisationSchoolList);
                case 2: return ProgramFragment.newInstance("Page #3", mProgList);
                default: return  null;
            }


        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

}
