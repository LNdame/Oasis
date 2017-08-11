package inqb8.ansteph.oasis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import inqb8.ansteph.oasis.ngo.CategoryList;
import inqb8.ansteph.oasis.testzone.Mapboxtest;
import io.fabric.sdk.android.Fabric;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
    }


    public void ongomapclicked(View v)
    {
        startActivity(new Intent(getApplicationContext(), CategoryList.class));
    }

}
