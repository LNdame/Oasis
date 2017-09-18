package inqb8.ansteph.oasis.model;

import java.io.Serializable;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class Org_Type implements Serializable{
    int _id;
    String description;

    public Org_Type() {
    }

    public Org_Type(String description) {
        this.description = description;
    }

    public Org_Type(int _id, String description) {
        this._id = _id;
        this.description = description;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
