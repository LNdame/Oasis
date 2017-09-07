package inqb8.ansteph.oasis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.model.Toolkit;

/**
 * Created by loicstephan on 2017/09/06.
 */

public class ToolKitRecyclerViewAdapter
        extends RecyclerView.Adapter<ToolKitRecyclerViewAdapter.ViewHolder> {

        private List<Toolkit> toolkitList;
        Context mContext;

        RecyclerViewClickListener recyclerViewClickListener;

    public ToolKitRecyclerViewAdapter(List<Toolkit> toolkitList, Context context, RecyclerViewClickListener listener) {
            this.toolkitList = toolkitList;
            mContext = context;

            recyclerViewClickListener = listener;
        }

        @Override
        public ToolKitRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

            ToolKitRecyclerViewAdapter.ViewHolder viewHolder = new ToolKitRecyclerViewAdapter.ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ToolKitRecyclerViewAdapter.ViewHolder holder, int position) {
            holder .mTextView.setText(toolkitList.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return toolkitList.size();
        }







    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.mTextView =(TextView) itemView.findViewById(R.id.txtCategory);
            mView.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        public void onClick(View v) {
            int position =getLayoutPosition();
            recyclerViewClickListener.onRecyclerViewItemClicked(v, position);
        }
    }
}
