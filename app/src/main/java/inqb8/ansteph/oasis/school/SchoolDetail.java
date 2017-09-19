package inqb8.ansteph.oasis.school;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.mapping.SchoolMap;
import inqb8.ansteph.oasis.model.School;
import inqb8.ansteph.oasis.website.WebsiteView;

public class SchoolDetail extends AppCompatActivity {

    private MapView mapView = null;
    TextView txtaddress, txtName, txtLearnerLevel,txtGeotag, txtTelephone, txtFax, txtEmail, txtWebsite;
    ImageView imgLogo;
    public static String SCHOOL_PARAM = "school";

    public School receivedSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);
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

        Bundle b = getIntent().getExtras();

        if(b!=null)
        {
            receivedSchool =(School) b.getSerializable(SCHOOL_PARAM);
        }


        setTitle(receivedSchool.getName());
        setValues(receivedSchool);

        Mapbox.getInstance(this, "pk.eyJ1IjoiYW5zdGVwaCIsImEiOiJjajVoeG5qZ3QxbTY3MnhwbmN6ODE0bTA3In0.XZ6tlAzf1ynmBO7Lc_OK6A");

        // mapbox://styles/ansteph/cj65fp5dt6a5p2rpdy1r7o6zf
        IconFactory iconFactory = IconFactory.getInstance(SchoolDetail.this);
        final Icon icon = iconFactory.fromResource(R.drawable.edu_marker);


        mapView = (MapView) findViewById(R.id.Schoolmapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.setStyle("mapbox://styles/ansteph/cj67sdtdk0slv2smtp8xawfgm");
                mapboxMap.addMarker(new MarkerViewOptions().position(new LatLng(-34.004441, 25.669534)).icon(icon));
            }
        });


    }


    public void setValues(School school)
    {
        txtName = (TextView) findViewById(R.id.txtSchoolName) ;
        txtaddress = (TextView) findViewById(R.id.txtSchoolAddress) ;
        txtWebsite = (TextView) findViewById(R.id.txtWebsiteUrl) ;
        txtTelephone = (TextView) findViewById(R.id.txtSchoolCall) ;
        txtFax = (TextView) findViewById(R.id.txtFax) ;
        imgLogo = (ImageView) findViewById(R.id.imgLogoDet);
       // txtaddress = (TextView) findViewById(R.id.txtsc) ;
       // txtFax = (TextView) findViewById(R.id.txtFax) ;

        byte[]logo  = school.getImg();
        Bitmap bmp = BitmapFactory.decodeByteArray(logo,0,logo.length);
        imgLogo.setImageBitmap(bmp);

        txtaddress.setText(school.getAddress());
        txtName.setText(school.getName());
        txtWebsite.setText(school.getWebsite_url());
        txtTelephone.setText(school.getTelephone());

        txtFax.setText(school.getFax());


    }

    public void gotoMap(View view)
    {

        String address = "http://maps.google.co.in/maps?q=154 Admiralty Way, Summerstrand, Port Elizabeth, 6001";
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(address));
        startActivity(intent);

    }


    public void gotoWebsite(View view)
    {
        startActivity(new Intent(getApplicationContext(), WebsiteView.class));
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

}
