package inqb8.ansteph.oasis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import inqb8.ansteph.oasis.R;

/**
 * Created by loicstephan on 2017/09/07.
 */

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.NGOItemViewHolder> {


    private static final int DEFAULT_ITEM_COUNT = 10;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private final List<Integer> mItems;
    private int mCurrentItemId=0;


    public LayoutAdapter(Context context, RecyclerView recyclerView){
        this(context, recyclerView, DEFAULT_ITEM_COUNT);
    }


    public LayoutAdapter(Context context, RecyclerView recyclerView, int itemCount){
        mContext = context;
        mItems = new ArrayList<>(itemCount);

        for(int i=0;i<itemCount; i++)
        {
            addItem(i);
        }

        mRecyclerView = recyclerView;
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public NGOItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.ngoviewlayout, parent, false);

        return new NGOItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NGOItemViewHolder holder, int position) {
        holder.title.setText(mItems.get(position).toString());

        final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            }
        });
        final int itemId = mItems.get(position);

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class NGOItemViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;

        public NGOItemViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.txtCategory);
        }
    }
}
