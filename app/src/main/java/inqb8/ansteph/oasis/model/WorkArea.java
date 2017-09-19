package inqb8.ansteph.oasis.model;

import java.io.Serializable;

/**
 * Created by loicstephan on 2017/08/31.
 */

public class WorkArea implements Serializable{
        int _id;
    String name;

    String Description;

    public WorkArea() {
    }


    public WorkArea(int _id, String name, String description) {
        this._id = _id;
        this.name = name;
        Description = description;
    }

    public WorkArea(String name, String description) {
        this.name = name;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
