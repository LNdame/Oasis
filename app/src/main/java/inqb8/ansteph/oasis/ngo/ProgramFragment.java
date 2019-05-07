package inqb8.ansteph.oasis.ngo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.adapter.ProgrammeRecyclerViewAdapter;
import inqb8.ansteph.oasis.model.Programme;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgramFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgramFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "Programmes";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private ArrayList<Programme> mProgrammes;

    RecyclerView recyclerView;
    RecyclerView.Adapter mProgrammeAdapter;

    TextView  txtNoProg;

    public ProgramFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param programmes Parameter 2.
     * @return A new instance of fragment ProgramFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgramFragment newInstance(String param1, ArrayList<Programme> programmes) {
        ProgramFragment fragment = new ProgramFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, programmes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mProgrammes =(ArrayList<Programme>) getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_program, container, false);

        if(mProgrammes.size()>0)
            ((TextView)rootView.findViewById(R.id.txtNoProg)).setVisibility(View.GONE);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        mProgrammeAdapter = new ProgrammeRecyclerViewAdapter(mProgrammes,getActivity());
        recyclerView.setAdapter(mProgrammeAdapter);

        return  rootView;
    }

}
