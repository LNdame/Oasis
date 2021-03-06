package inqb8.ansteph.oasis.toolkit.fragitems;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.app.Constants;
import inqb8.ansteph.oasis.toolkit.Previewer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PartnershipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PartnershipFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public PartnershipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PartnershipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PartnershipFragment newInstance(String param1, String param2) {
        PartnershipFragment fragment = new PartnershipFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_partnership, container, false);

        Button btnMouTemp = (Button) rootView.findViewById(R.id.btnMouTemp);
        btnMouTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Previewer.class);
                intent.putExtra(Previewer.FILE, Constants.MOU_TEMPLATE);

                startActivity(intent);
            }
        });

        Button btnMouConsider = (Button) rootView.findViewById(R.id.btnMouConsider);
        btnMouConsider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Previewer.class);
                intent.putExtra(Previewer.FILE, Constants.MOU_CONSIDERATION);

                startActivity(intent);
            }
        });


        return rootView;
    }

}
