package inqb8.ansteph.oasis.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import inqb8.ansteph.oasis.R;

/**
 * Created by loicstephan on 2017/09/28.
 */

public class TextViewLevelThree extends LinearLayout {
    private Context mContext;

    TextView txtInfo ;
    public TextViewLevelThree(Context context) {
        super(context);
        initViews(context);
    }

    public TextViewLevelThree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context, attrs);
    }

    public TextViewLevelThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context, attrs);
    }


    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.textview_level3, this);


    }

    private void initViews(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.textview_level3, this);

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


   /* private void initViews(Context context)
    {
        LayoutInflater.from(context).inflate(R.layout.questionauditcattitle, this);


        quesTitle = (TextView) findViewById(R.id.txtTitle);

        if(!TextUtils.isEmpty(getQuesTitle()) )
            quesTitle.setText(getQuesTitle());



    }*/
