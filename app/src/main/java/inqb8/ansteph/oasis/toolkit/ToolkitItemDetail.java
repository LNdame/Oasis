package inqb8.ansteph.oasis.toolkit;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.inqb8.ansteph.android_pdf_viewer.util.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.mapping.Welcome;

public class ToolkitItemDetail extends AppCompatActivity {

    public static final String SAMPLE_FILE = "ohs_regulation.pdf";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolkit_item_detail);
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


}
