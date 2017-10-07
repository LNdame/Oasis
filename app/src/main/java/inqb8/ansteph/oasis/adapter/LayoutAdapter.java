package inqb8.ansteph.oasis.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import inqb8.ansteph.oasis.R;
import inqb8.ansteph.oasis.model.Organisation;
import inqb8.ansteph.oasis.ngo.NGODetail;

/**
 * Created by loicstephan on 2017/09/07.
 */

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.NGOItemViewHolder> {


    private static final int DEFAULT_ITEM_COUNT = 10;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    //private final List<Integer> mItems;
    private final ArrayList<Organisation> organisationList;

    private int mCurrentItemId=0;




    public LayoutAdapter(Context context, RecyclerView recyclerView, ArrayList<Organisation> list){
        mContext = context;

        int count  = list.size();
        organisationList = new ArrayList<>(count);

        for(int i=0;i<count; i++)
        {
            addItem(i, list.get(i));
        }
        mRecyclerView = recyclerView;
    }

  /*

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
    }*/

    public void addItem(int position, Organisation org) {
        final int id = mCurrentItemId++;
       // mItems.add(position, id);
        organisationList.add(position,org);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        organisationList.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public NGOItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.ngoviewlayout, parent, false);

        return new NGOItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NGOItemViewHolder holder,final int position) {
        holder.title.setText(organisationList.get(position).getName());

        holder.txtCategory.setText("[" + organisationList.get(position).getWorkArea().getName()+"]" );
        final View itemView = holder.itemView;

        byte[]logo  = organisationList.get(position).getGeneralInfo().getLogo();
        Bitmap bmp = BitmapFactory.decodeByteArray(logo,0,logo.length);

        holder.ngoLogo.setImageBitmap(bmp);
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, NGODetail.class);
                i.putExtra("Org",organisationList.get(position ));

                mContext.startActivity(i);
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
               /* Intent i = new Intent(mContext, NGODetail.class);
                i.putExtra("Org",organisationList.get(position ));

                mContext.startActivity(i);*/
            }
        });
       // final int itemId = mItems.get(position);

    }

    @Override
    public int getItemCount() {
        return organisationList.size();
    }


    public static class NGOItemViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView txtCategory;
        public final ImageView ngoLogo;

        public final Button btnDetails;

        public NGOItemViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.txtNGOName);
            this.txtCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            this.ngoLogo = (ImageView) itemView.findViewById(R.id.ngoimglogo);
            this.btnDetails = (Button) itemView.findViewById(R.id.btnSeeMore);
        }
    }
}
