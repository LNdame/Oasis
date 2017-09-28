package inqb8.ansteph.oasis.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.listener.RecyclerViewClickListener;
import inqb8.ansteph.oasis.timelinemodel.OrderStatus;
import inqb8.ansteph.oasis.timelinemodel.Orientation;
import inqb8.ansteph.oasis.timelinemodel.TimeLineModel;
import inqb8.ansteph.oasis.utils.DateTimeUtils;
import inqb8.ansteph.oasis.utils.VectorDrawableUtils;

/**
 * Created by loicstephan on 2017/09/26.
 */

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>{

    private List<TimeLineModel> mFeedList;
    private Context mContext;
    private Orientation mOrientation;
    private boolean mWithLinePadding;
    private LayoutInflater mLayoutInflater;
    RecyclerViewClickListener recyclerViewClickListener;

    public TimeLineAdapter(List<TimeLineModel> feedList, Orientation orientation, boolean withLinePadding,RecyclerViewClickListener listener) {
        mFeedList = feedList;
        mOrientation = orientation;
        mWithLinePadding = withLinePadding;

        recyclerViewClickListener = listener;
    }


    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        View view;

        if(mOrientation == Orientation.HORIZONTAL) {
            view = mLayoutInflater.inflate(mWithLinePadding ? R.layout.item_timeline_horizontal_line_padding : R.layout.item_timeline_horizontal, parent, false);
        } else {
            view = mLayoutInflater.inflate(mWithLinePadding ? R.layout.item_timeline_line_padding : R.layout.item_timeline, parent, false);
        }

        return new TimeLineViewHolder(view, viewType);
    }


    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        TimeLineModel timeLineModel = mFeedList.get(position);

        int drawableImg ;

        switch (position)
        {
            case 0: drawableImg =R.drawable.ic_round_play_button; break;
            case 1: drawableImg =R.drawable.ic_one; break;
            case 2: drawableImg =R.drawable.ic_two; break;
            case 3: drawableImg =R.drawable.ic_three; break;
            case 4: drawableImg =R.drawable.ic_four; break;
            case 5: drawableImg =R.drawable.ic_five; break;
            case 6: drawableImg =R.drawable.ic_six; break;
            case 7: drawableImg =R.drawable.ic_stop; break;
            default: drawableImg =R.drawable.ic_stop; break;


        }



        if(timeLineModel.getStatus() == OrderStatus.INACTIVE) {
           // holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_inactive, android.R.color.darker_gray));
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, drawableImg, android.R.color.darker_gray));
        } else if(timeLineModel.getStatus() == OrderStatus.ACTIVE) {
           // holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorPrimary));
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, drawableImg, R.color.colorPrimary));
        } else {
            // holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.ic_one), ContextCompat.getColor(mContext, R.color.colorPrimary));
            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, drawableImg));

        }

        if(!timeLineModel.getDate().isEmpty()) {
            holder.mDate.setVisibility(View.VISIBLE);
            //holder.mDate.setText(DateTimeUtils.parseDateTime(timeLineModel.getDate(), "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy"));
            holder.mDate.setText(timeLineModel.getDate());
        }
        else
            holder.mDate.setVisibility(View.GONE);

        holder.mMessage.setText(timeLineModel.getMessage());
    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }



    public class TimeLineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mDate;
        TextView mMessage;
        TimelineView mTimelineView;
        public final View mView;
        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            this.mView = itemView;
            this.mDate = (TextView) itemView.findViewById(R.id.text_timeline_date);
            this.mMessage = (TextView) itemView.findViewById(R.id.text_timeline_title);
            this.mTimelineView =(TimelineView) itemView.findViewById(R.id.time_marker);

            this.mTimelineView.initLine(viewType);

            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position =getLayoutPosition();
            recyclerViewClickListener.onRecyclerViewItemClicked(v, position);
        }
    }

}
