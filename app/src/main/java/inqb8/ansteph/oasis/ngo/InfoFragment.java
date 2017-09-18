package inqb8.ansteph.oasis.ngo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.model.Organisation;

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

        return rootView;
    }


    public void setValue(Organisation org)
    {
        txtAbout.setText(org.getGeneralInfo().getSynopsis());
        txtAddress.setText(org.getAddressline1());
        txtContact1 .setText( org.getContactperson1Name() +" - " + org.getContactperson1Position());
        txtContact2.setText( org.getContactperson2Name() +" - " + org.getContactperson2Position());
        txtWebsite.setText(org.getGeneralInfo().getWebsiteurl());
        txtEmail.setText(org.getGeneralInfo().getEmail());
        txtTel.setText(org.getGeneralInfo().getTelephoneNumber());
        txtFax.setText(org.getGeneralInfo().getFaxNumber());
    }

}
