package inqb8.ansteph.oasis.ngo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.model.Organisation_School;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchoolListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "school";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<Organisation_School> mOrganisation_schools;

    ListView mListView;

    public SchoolListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param org_school Parameter 2.
     * @return A new instance of fragment SchoolListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolListFragment newInstance(String param1, ArrayList<Organisation_School> org_school) {
        SchoolListFragment fragment = new SchoolListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, org_school);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mOrganisation_schools = (ArrayList<Organisation_School>)getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_school_list, container, false);

        if(mOrganisation_schools.size()>0)
            ((TextView)rootView.findViewById(R.id.txtNoSchool)).setVisibility(View.GONE);


        mListView = (ListView) rootView.findViewById(R.id.listview);
        String[] Schools = new String[mOrganisation_schools.size()];

        for(int i=0; i< mOrganisation_schools.size() ; i++)
        {
            Schools[i] = mOrganisation_schools.get(i).getSchool_name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Schools);
        mListView.setAdapter(adapter);


        return  rootView;
    }





}
