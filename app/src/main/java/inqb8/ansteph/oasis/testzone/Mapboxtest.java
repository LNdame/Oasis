package inqb8.ansteph.oasis.testzone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import inqb8.ansteph.oasis.R;

public class Mapboxtest extends AppCompatActivity {

    private MapView mapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapboxtest);

       // original Mapbox.getInstance(this, "pk.eyJ1IjoiYW5zdGVwaCIsImEiOiJjajVoeG5qZ3QxbTY3MnhwbmN6ODE0bTA3In0.XZ6tlAzf1ynmBO7Lc_OK6A");
        Mapbox.getInstance(this, "pk.eyJ1IjoiYW5zdGVwaCIsImEiOiJjajVoeG5qZ3QxbTY3MnhwbmN6ODE0bTA3In0.XZ6tlAzf1ynmBO7Lc_OK6A");

       // mapbox://styles/ansteph/cj65fp5dt6a5p2rpdy1r7o6zf

        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.setStyle("mapbox://styles/ansteph/cj65fp5dt6a5p2rpdy1r7o6zf");
            }
        });


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
