package inqb8.ansteph.oasis.toolkit;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.inqb8.ansteph.android_pdf_viewer.PDFView;
import com.inqb8.ansteph.android_pdf_viewer.listener.OnLoadCompleteListener;
import com.inqb8.ansteph.android_pdf_viewer.listener.OnPageChangeListener;
import com.inqb8.ansteph.android_pdf_viewer.scroll.DefaultScrollHandle;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.app.Constants;

public class Previewer extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {


    private static final String TAG = Previewer.class.getSimpleName();

    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;

    public static final String SAMPLE_FILE = "ohs_regulation.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

    public static final String FILE  = "file";

    PDFView pdfView;

    Uri uri;
    Integer pageNumber = 0;

    String pdfFileName;
    String mSentPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Sending to email", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                SendEmailReport();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle b = getIntent().getExtras();
        String fileName = "";
        if(b!=null)
        {
            fileName = b.getString(FILE);
           // mSentPath = b.getString("path");
            //   Log.i("sent path",mSentPath);
        }else{
            fileName = Constants.INFO_PRINCIPAL;
        }

      //  File newdoc = new File(mSentPath);

      //  uri= Uri.fromFile(newdoc);
        pdfView = (PDFView) findViewById(R.id.pdfView) ;


         uri = null;

        afterViews(fileName);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }



    // @AfterViews
    void afterViews(String assetFile) {
        if (uri != null) {
            displayFromUri(uri);
        } else {
            displayFromAsset(assetFile);
        }
        setTitle(pdfFileName);
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(pdfFileName)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    private void displayFromUri(Uri uri) {
        pdfFileName = getFileName(uri);

        pdfView.fromUri(uri)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    void launchPicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            //alert user that file manager not working
            Toast.makeText(this, R.string.toast_pick_file_error, Toast.LENGTH_SHORT).show();
        }
    }

//    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
//        for (PdfDocument.Bookmark b : tree) {
//
//            // Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
//
//            if (b.hasChildren()) {
//                printBookmarksTree(b.getChildren(), sep + "-");
//            }
//        }
//    }


    @Override
    public void loadComplete(int nbPages) {
//        PdfDocument.Meta meta = pdfView.getDocumentMeta();
//        Log.e(TAG, "title = " + meta.getTitle());
//        Log.e(TAG, "author = " + meta.getAuthor());
//        Log.e(TAG, "subject = " + meta.getSubject());
//        Log.e(TAG, "keywords = " + meta.getKeywords());
//        Log.e(TAG, "creator = " + meta.getCreator());
//        Log.e(TAG, "producer = " + meta.getProducer());
//        Log.e(TAG, "creationDate = " + meta.getCreationDate());
//        Log.e(TAG, "modDate = " + meta.getModDate());
//
//        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            displayFromUri(uri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchPicker();
            }
        }
    }


    public void SendEmailReport (){


        try{
            String email =" ";
            String subject = "Toolkit Template Document";
            String msg ="Please find attached the toolkit template document sent for your consideration";

            File toolkitfolder = new File (Environment.getExternalStorageDirectory(), "Oasis_Toolkit");

            if(!toolkitfolder.exists())   //this what should be used
            {
                toolkitfolder.mkdir();
                // Log.i(TAG, "PDF 2 directory created");
            }


            File file = new File(copyanasset());


            final Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

            if(file.exists())
            {
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            }

            emailIntent.putExtra(Intent.EXTRA_TEXT, msg);
            startActivity(Intent.createChooser(emailIntent, "Sending email..."));
        }catch (Exception e){
            e.printStackTrace();
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
            in = assetManager.open(pdfFileName);

            outFile = new File(toolkitfolder+File.separator+pdfFileName);
            out = new FileOutputStream(outFile);
            copyFile(in, out);

        }catch (IOException e)
        {
            Log.e("tag", "Failed to copy asset file: " + pdfFileName, e);
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


    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }




}

