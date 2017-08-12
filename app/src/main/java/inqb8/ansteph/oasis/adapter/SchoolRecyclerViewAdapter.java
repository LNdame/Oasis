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
import inqb8.ansteph.oasis.model.School;

/**
 * Created by loicstephan on 2017/08/09.
 */

public class SchoolRecyclerViewAdapter extends RecyclerView.Adapter<SchoolRecyclerViewAdapter.ViewHolder> {


    private List<School> schoolList;

    Context mContext;

    RecyclerViewClickListener recyclerViewClickListener;

    public SchoolRecyclerViewAdapter(List<School> schoolList, Context mContext, RecyclerViewClickListener recyclerViewClickListener) {
        this.schoolList = schoolList;
        this.mContext = mContext;
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schoolcardview, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder .txtName.setText(schoolList.get(position).getName());
        holder .txtAddress.setText(schoolList.get(position).getAddress());
        holder .txtTel.setText(schoolList.get(position).getTelephone());


    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final View mView;
        public final TextView txtAddress;
        public final TextView txtName;
        public final TextView txtTel;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.txtName =(TextView) itemView.findViewById(R.id.txtName);
            this.txtAddress =(TextView) itemView.findViewById(R.id.txtAddress);
            this.txtTel =(TextView) itemView.findViewById(R.id.txtTel);

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
