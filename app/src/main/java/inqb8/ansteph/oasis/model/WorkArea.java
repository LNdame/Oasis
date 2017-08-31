package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/31.
 */

public class WorkArea {
        int _id;

    String Description;

    public WorkArea() {
    }


    public WorkArea(int _id, String description) {
        this._id = _id;
        Description = description;
    }

    public WorkArea(String description) {
        Description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
