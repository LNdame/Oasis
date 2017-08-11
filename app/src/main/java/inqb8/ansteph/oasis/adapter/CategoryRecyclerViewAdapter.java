package inqb8.ansteph.oasis.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.model.Category;

/**
 * Created by loicstephan on 2017/08/09.
 */

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    private List<Category> categoryList;
    Context mContext;

    RecyclerViewClickListener recyclerViewClickListener;

    public CategoryRecyclerViewAdapter(List<Category> categoryList, Context context, RecyclerViewClickListener listener) {
        this.categoryList = categoryList;
        mContext = context;

        recyclerViewClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder .mTextView.setText(categoryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
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
