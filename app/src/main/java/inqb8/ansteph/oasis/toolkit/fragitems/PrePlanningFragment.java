package inqb8.ansteph.oasis.toolkit.fragitems;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.toolkit.data.BaseItem;
import inqb8.ansteph.oasis.toolkit.data.DataProvider;
import inqb8.ansteph.oasis.toolkit.data.GroupItem;
import pl.openrnd.multilevellistview.ItemInfo;
import pl.openrnd.multilevellistview.MultiLevelListAdapter;
import pl.openrnd.multilevellistview.MultiLevelListView;
import pl.openrnd.multilevellistview.OnItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrePlanningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrePlanningFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static  String TAG = PrePlanningFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MultiLevelListView mListView;
    private boolean mAlwaysExpandend;

    public PrePlanningFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrePlanningFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrePlanningFragment newInstance(String param1, String param2) {
        PrePlanningFragment fragment = new PrePlanningFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_pre_planning, container, false);


        mListView = (MultiLevelListView) rootView.findViewById(R.id.listView);
        ListAdapter listAdapter = new ListAdapter();

        mListView.setAdapter(listAdapter);
        mListView.setOnItemClickListener(mOnItemClickListener);

        listAdapter.setDataItems(DataProvider.getInitialItems());
        return rootView;
    }


    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        private void showItemDescription(Object object, ItemInfo itemInfo) {
            StringBuilder builder = new StringBuilder("\"");
            builder.append(((BaseItem) object).getName());
            builder.append("\" clicked!\n");
            builder.append(getItemInfoDsc(itemInfo));

           // Toast.makeText(DataActivity.this, builder.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }

        @Override
        public void onGroupItemClicked(MultiLevelListView parent, View view, Object item, ItemInfo itemInfo) {
            showItemDescription(item, itemInfo);
        }
    };


    private class ListAdapter extends MultiLevelListAdapter {

        private class ViewHolder {
            TextView nameView;
            TextView infoView;
            ImageView arrowView;

        }

        @Override
        public List<?> getSubObjects(Object object) {
            return DataProvider.getSubItems((BaseItem) object);
        }

        @Override
        public boolean isExpandable(Object object) {
            return DataProvider.isExpandable((BaseItem) object);
        }

        @Override
        public View getViewForObject(Object object, View convertView, ItemInfo itemInfo) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.data_item, null);
                viewHolder.infoView = (TextView) convertView.findViewById(R.id.dataItemInfo);
                viewHolder.nameView = (TextView) convertView.findViewById(R.id.dataItemName);
                viewHolder.arrowView = (ImageView) convertView.findViewById(R.id.dataItemArrow);
               // viewHolder.levelBeamView = (LevelBeamView) convertView.findViewById(R.id.dataItemLevelBeam);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameView.setText(((BaseItem) object).getName());
            viewHolder.infoView.setText(getItemInfoDsc(itemInfo));

            if (itemInfo.isExpandable() && !mAlwaysExpandend) {
                viewHolder.arrowView.setVisibility(View.VISIBLE);
                viewHolder.arrowView.setImageResource(itemInfo.isExpanded() ?
                        R.drawable.ic_arrow_up_black_24dp : R.drawable.ic_arrow_down_black_24dp);
            } else {
                viewHolder.arrowView.setVisibility(View.GONE);
            }

           // viewHolder.levelBeamView.setLevel(itemInfo.getLevel());

            return convertView;
        }
    }


    public  List<BaseItem> generateItems(BaseItem baseItem)
    {
        if (!(baseItem instanceof GroupItem)) {
            throw new IllegalArgumentException("GroupItem required");
        }


        GroupItem groupItem = (GroupItem)baseItem;
        if(groupItem.getLevel() >= 2){
            return null;
        }

        List<BaseItem> result = new ArrayList<>();


        return result;
    }



    private String getItemInfoDsc(ItemInfo itemInfo) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("level[%d], idx in level[%d/%d]",
                itemInfo.getLevel() + 1, /*Indexing starts from 0*/
                itemInfo.getIdxInLevel() + 1 /*Indexing starts from 0*/,
                itemInfo.getLevelSize()));

        if (itemInfo.isExpandable()) {
            builder.append(String.format(", expanded[%b]", itemInfo.isExpanded()));
        }
        return builder.toString();
    }

}
