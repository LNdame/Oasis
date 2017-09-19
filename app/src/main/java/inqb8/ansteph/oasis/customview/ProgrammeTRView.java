package inqb8.ansteph.oasis.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableRow;

import inqb8.ansteph.oasis.model.Programme;

/**
 * Created by loicstephan on 2017/09/15.
 */

public class ProgrammeTRView extends TableRow{


    private Context context;
    private Programme programme;

    public ProgrammeTRView(Context context, Programme programme) {
        super(context);
        this.context = context;
        this.programme = programme;
    }

    public ProgrammeTRView(Context context, AttributeSet attrs,  Programme programme) {
        super(context, attrs);
        this.context = context;
        this.programme = programme;
    }
}
