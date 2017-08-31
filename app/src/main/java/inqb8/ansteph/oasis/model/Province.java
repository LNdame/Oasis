package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class Province {

    int _id;
    String provincename;

    public Province() {
    }


    public Province(int _id, String provincename) {
        this._id = _id;
        this.provincename = provincename;
    }

    public Province(String provincename) {
        this.provincename = provincename;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }
}
