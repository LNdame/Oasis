package inqb8.ansteph.oasis.toolkit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.inqb8.ansteph.android_pdf_viewer.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import inqb8.ansteph.oasis.Manifest;
import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.app.Constants;
import inqb8.ansteph.oasis.app.GlobalRetainer;
import inqb8.ansteph.oasis.mapping.Welcome;
import inqb8.ansteph.oasis.ngo.ProgramFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.DevelopFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.ExitFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.InitComFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.IntroProgFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.MonitorFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.PartnershipFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.PrePlanningFragment;
import inqb8.ansteph.oasis.toolkit.fragitems.ProgImpFragment;

public class ToolkitItemDetail extends AppCompatActivity {

    private  static  final String TAG  = ToolkitItemDetail.class.getSimpleName();

    public static final String SAMPLE_FILE = "ohs_regulation.pdf";

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    GlobalRetainer mGlobalRetainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolkit_item_detail);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        requestPermission();

        Bundle bundle = getIntent().getExtras();

        if(bundle !=null)
        {

           int position  = bundle.getInt("position");

            String title  =bundle.getString("title");
            setTitle(title);

            Fragment fragment = null;

            switch (position)
            {
                case 0: fragment  = new PrePlanningFragment();break;
                case 1: fragment  = new DevelopFragment();break;
                case 2: fragment  = new InitComFragment();break;
                case 3: fragment  = new PartnershipFragment();break;
                case 4: fragment  = new IntroProgFragment();break;
                case 5: fragment  = new ProgImpFragment();break;
                case 6: fragment  = new MonitorFragment();break;
                case 7: fragment  = new ExitFragment();break;
                default: fragment  = new PrePlanningFragment();break;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();



        }else {


            int position  = mGlobalRetainer.getToolkitPosition();

            String title  =mGlobalRetainer.getToolkitTitle();
            setTitle(title);

            Fragment fragment = null;

            switch (position)
            {
                case 0: fragment  = new PrePlanningFragment();break;
                case 1: fragment  = new DevelopFragment();break;
                case 2: fragment  = new InitComFragment();break;
                case 3: fragment  = new PartnershipFragment();break;
                case 4: fragment  = new IntroProgFragment();break;
                case 5: fragment  = new ProgImpFragment();break;
                case 6: fragment  = new MonitorFragment();break;
                case 7: fragment  = new ExitFragment();break;
                default: fragment  = new PrePlanningFragment();break;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

        }


        File filepub  = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"oasisfilepub");
        if(!filepub.mkdirs()){
            Log.e(ToolkitItemDetail.class.getSimpleName(),"Directory not created");
        }


        File file1  = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES),"oasisfileext");
        if(!file1.mkdirs()){
            Log.e(ToolkitItemDetail.class.getSimpleName(),"Directory not created");
        }


    }


    public void onEmailClicked(View view)
    {
        try{
            String email ="ls20045@gmail.com";
            String subject = "H & S Audit Report";
            String msg ="Please find attached the Health and Safety report sent for your consideration";


           /* File toolkitfolder = new File (Environment.getExternalStorageDirectory(), "Oasis_Toolkit");

            if(!toolkitfolder.exists())   //this what should be used
            {
                toolkitfolder.mkdir();
               // Log.i(TAG, "PDF 2 directory created");
            }
            */

            File toolkitfolder = new File (Environment.getExternalStorageDirectory(), "Oasis_Toolkit");

            if(!toolkitfolder.exists())   //this what should be used
            {
                toolkitfolder.mkdir();
                // Log.i(TAG, "PDF 2 directory created");
            }



            /*String filename = "myfile";
            String string = "Hello world!";
            FileOutputStream outputStream;

            try {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/









        //    copyFromAsset();


           File file = new File(copyanasset());





           // File file = FileUtils.fileFromAsset(this,SAMPLE_FILE );
           // Uri uri = Uri.parse("assets://"+SAMPLE_FILE);

            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if(file.exists())
            {
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            }


           // emailIntent.putExtra(Intent.EXTRA_STREAM, uri);

            emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
            startActivityForResult(Intent.createChooser(emailIntent, "Sending email..."), 1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void onPreviewClicked(View view)
    {

    }

    public void onDownloadClicked(View view)
    {

    }

    public void copyFromAsset(){

        Context context = getApplicationContext();

        String path = Environment.getExternalStorageDirectory()+"/oasis/";
        File file = new File(path+ SAMPLE_FILE);

        if(!file.exists())
        {
            file.mkdirs();
            copyFile2(context,path);

        }

    }


    private void copyFile2(Context context, String path)
    {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream in = assetManager.open(SAMPLE_FILE);
            OutputStream outFile = new FileOutputStream(path + SAMPLE_FILE);
            byte [] buffer = new byte[1024];
            int read = in.read(buffer);

            while (read != -1)
            {
                outFile.write(buffer,0,read);
                read = in.read(buffer);
            }

        }catch (Exception e)
        {
            e.getMessage();
        }
    }


    private String copyanasset()
    {
        AssetManager assetManager = getAssets();
        InputStream in = null;
        OutputStream out = null;

        File outFile = null;

        File toolkitfolder = new File (Environment.getExternalStorageDirectory(), "Oasis_Toolkit");

        if(!toolkitfolder.exists())   //this what should be used
        {
            toolkitfolder.mkdir();
            // Log.i(TAG, "PDF 2 directory created");
        }



        try{
            in = assetManager.open(SAMPLE_FILE);

           outFile = new File(toolkitfolder+File.separator+SAMPLE_FILE);
            out = new FileOutputStream(outFile);
            copyFile(in, out);

        }catch (IOException e)
        {
            Log.e("tag", "Failed to copy asset file: " + SAMPLE_FILE, e);
        }
        finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // NOOP
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // NOOP
                }
            }
        }


        return outFile.getPath();
    }


    private void copyAssets() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("");
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = assetManager.open(filename);
                File outFile = new File(getExternalFilesDir(null), filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
            } catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
            finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        // NOOP
                    }
                }
            }
        }
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
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


    private void requestPermission()
    {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE_ASK_PERMISSIONS);
        }
    }


    /**
     * Listener for response to user permission request
     *
     * @param requestCode  Check that permission request code matches
     * @param permissions  Permissions that requested
     * @param grantResults Whether permissions granted
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode)
        {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT)
                            .show();
                }else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT)
                            .show();
                }break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }



       /* if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.i(TAG, "Permission " +permissions[0]+ " was " +grantResults[0]);
        }*/
    }

}
