package inqb8.ansteph.oasis.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import inqb8.ansteph.oasis.R;

/**
 * Created by loicstephan on 2017/09/28.
 */

public class TextViewLevelOne extends LinearLayout {

    private Context mContext;
    TextView txtInfo ;
    public TextViewLevelOne(Context context) {
        super(context);
        initViews(context);
    }

    public TextViewLevelOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public TextViewLevelOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }


    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.textview_level1, this);


    }

    private void initViews(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.textview_level1, this);

        // prevent render is in edit mode
        if( isInEditMode() )
            return;

        if( attrs != null ) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewLevelThree);

            txtInfo = (TextView) findViewById(R.id.txtInfo);
            String desc = getInfoDesc(array);

            txtInfo.setText(desc);

            array.recycle();


        }


    }


    protected String getInfoDesc(final TypedArray typedArray) {
        return typedArray.getString(R.styleable.TextViewLevelThree_infotext);
    }



}
