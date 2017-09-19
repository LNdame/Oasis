package inqb8.ansteph.oasis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.model.Programme;

/**
 * Created by loicstephan on 2017/09/15.
 */

public class ProgrammeRecyclerViewAdapter extends RecyclerView.Adapter<ProgrammeRecyclerViewAdapter.ViewHolder> {

    List<Programme> programmeList ;
    Context mContext;

    public ProgrammeRecyclerViewAdapter(List<Programme> programmeList, Context mContext) {
        this.programmeList = programmeList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programme_tr, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtProgName.setText(programmeList.get(position).getName());
        holder.txtProgDuration.setText(programmeList.get(position).getDuration());
        holder.txtProgPrimary.setText(programmeList.get(position).getLearnerLevel());
        holder.txtProgEducator.setText(programmeList.get(position).getEducatorParent());
        holder.txtProgFreq.setText(programmeList.get(position).getFrequency());
    }

    @Override
    public int getItemCount() {
        return programmeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtProgName;
        public final TextView txtProgDuration;
        public final TextView txtProgPrimary;
        public final TextView txtProgEducator;
        public final TextView txtProgFreq;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
            this.txtProgName =(TextView) itemView.findViewById(R.id.txtProgName);
            this.txtProgDuration =(TextView) itemView.findViewById(R.id.txtProgDuration);
            this.txtProgPrimary =(TextView) itemView.findViewById(R.id.txtProgPrimary);
            this.txtProgEducator =(TextView) itemView.findViewById(R.id.txtProgEducator);
            this.txtProgFreq =(TextView) itemView.findViewById(R.id.txtProgFreq);


        }

        @Override
        public String toString() {
            return super.toString();
        }


    }
}
