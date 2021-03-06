package inqb8.ansteph.oasis.ngo;


import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bluejamesbond.text.DocumentView;
import com.bluejamesbond.text.style.JustifiedSpan;
import com.bluejamesbond.text.style.TextAlignment;
import com.bluejamesbond.text.util.ArticleBuilder;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.app.Constants;
import inqb8.ansteph.oasis.model.Organisation;
import inqb8.ansteph.oasis.utils.GeoTagUtils;
import inqb8.ansteph.oasis.website.WebsiteView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "ORG";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Organisation mOrganisation;

    TextView txtAbout, txtAddress,txtContact1,txtContact2 , txtWebsite, txtEmail, txtTel, txtFax;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param org Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, Organisation org) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, org);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mOrganisation = (Organisation) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        txtAbout = (TextView) rootView.findViewById(R.id.txtabout);
        txtAddress = (TextView) rootView.findViewById(R.id.txtAddress);
        txtContact1 = (TextView) rootView.findViewById(R.id.txtContactP1);
        txtContact2 = (TextView) rootView.findViewById(R.id.txtContactP2);
        txtWebsite = (TextView) rootView.findViewById(R.id.txtWebsite);
        txtEmail= (TextView) rootView.findViewById(R.id.txtEmail);
        txtTel = (TextView) rootView.findViewById(R.id.txtTelnumber);
        txtFax= (TextView) rootView.findViewById(R.id.txtFax);

        setValue(mOrganisation);

        ArticleBuilder article = new ArticleBuilder().append(mOrganisation.getGeneralInfo().getSynopsis(), false,new RelativeSizeSpan(1f),
                new JustifiedSpan());


        AddDocumentView(article, DocumentView.PLAIN_TEXT, false, rootView);

        txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWebsite(v) ;
            }
        });

        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMap(v);
            }
        });

        return rootView;
    }



    public void AddDocumentView (CharSequence article, int type, boolean rtl, View rootView ){

        final DocumentView documentView = new DocumentView(getActivity(), type);
        documentView.getDocumentLayoutParams().setTextColor(0xff000000);
        documentView.getDocumentLayoutParams().setTextTypeface(Typeface.DEFAULT);
        documentView.getDocumentLayoutParams().setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        documentView.getDocumentLayoutParams().setTextAlignment(TextAlignment.JUSTIFIED);
        documentView.getDocumentLayoutParams().setInsetPaddingLeft(30f);
        documentView.getDocumentLayoutParams().setInsetPaddingRight(30f);
        documentView.getDocumentLayoutParams().setInsetPaddingTop(30f);
        documentView.getDocumentLayoutParams().setInsetPaddingBottom(30f);
        documentView.getDocumentLayoutParams().setLineHeightMultiplier(1f);
        documentView.getDocumentLayoutParams().setReverse(rtl);
       // documentView.getDocumentLayoutParams().setDebugging(debugging);
        documentView.setText(article);
       // documentView.setProgressBar((ProgressBar) findViewById(R.id.progressBar));
        documentView.setFadeInDuration(800);
        documentView.setFadeInAnimationStepDelay(30);
        documentView.setFadeInTween(new DocumentView.ITween() {
            @Override
            public float get(float t, float b, float c, float d) {
                return c * (t /= d) * t * t + b;
            }
        });

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
        linearLayout.addView(documentView);

        LinearLayout articleList = (LinearLayout) rootView.findViewById(R.id.articleList);
        articleList.addView(linearLayout);
    }



    public void gotoMap(View view)
    {

        double [] tag  = GeoTagUtils.stripGeotagOrg(mOrganisation.getGeotag());

        if(tag!=null)
        {
            String address  = "http://maps.google.com/maps?daddr=" + tag[0]+" , "+tag[1];

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse(address));
            startActivity(intent);

        }else{
        String address = "http://maps.google.co.in/maps?q=" + mOrganisation.getAddressline1();
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(address));
        startActivity(intent);
        }

    }


    public void gotoWebsite(View view)
    {
        Intent i = new Intent(getActivity(), WebsiteView.class);
        i.putExtra(Constants.WEB, mOrganisation.getGeneralInfo().getWebsiteurl());

        startActivity(i);
    }

    public void setValue(Organisation org)
    {
        txtAbout.setText(org.getGeneralInfo().getSynopsis());
        txtAddress.setText(org.getAddressline1());

       // org.getContactperson1Name() ==null? "":org.getContactperson1Name()
        //org.getContactperson1Position()==null?"":org.getContactperson1Position
        String c1 =org.getContactperson1Name() ==null|| TextUtils.isEmpty(org.getContactperson1Name())? "":org.getContactperson1Name();
        String p1 =org.getContactperson1Position()==null|| TextUtils.isEmpty(org.getContactperson1Position())?"":org.getContactperson1Position();
       // String contact1 = +" - " + org.getContactperson1Position()==null?"":org.getContactperson1Position();
        String c2 =org.getContactperson2Name() ==null|| TextUtils.isEmpty(org.getContactperson2Name())? "":org.getContactperson2Name();
        String p2 =org.getContactperson2Position()==null|| TextUtils.isEmpty(org.getContactperson2Position())?"":org.getContactperson2Position();

        txtContact1 .setText(c1+" - "+p1);
        txtContact2.setText( c2+" - "+p2);
        txtWebsite.setText(org.getGeneralInfo().getWebsiteurl());
        txtEmail.setText(org.getGeneralInfo().getEmail());
        txtTel.setText(org.getGeneralInfo().getTelephoneNumber());
        txtFax.setText(org.getGeneralInfo().getFaxNumber());
    }

}
