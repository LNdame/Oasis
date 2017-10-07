package inqb8.ansteph.oasis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;

import inqb8.ansteph.oasis.intro.WelcomePage;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.ngo.CategoryList;
import inqb8.ansteph.oasis.testzone.Mapboxtest;
import io.fabric.sdk.android.Fabric;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);


        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    /**
                     * Call this function whenever you want to check user login
                     * This will redirect user to Login is he is not
                     * logged in
                     * */
                    startActivity(new Intent(getApplicationContext(), WelcomePage.class));
                    // Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    //startActivity(intent);
                }
            }
        };
        timerThread.start();

    }


    public void ongomapclicked(View v)
    {
        startActivity(new Intent(getApplicationContext(), WelcomePage.class));
    }

}
