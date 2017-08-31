package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class Suburb {


    int _id;
    String suburbName, postalCode;
    City city;
    public Suburb() {
    }

    public Suburb(int _id, String suburbName, String postalCode, City city) {
        this._id = _id;
        this.suburbName = suburbName;
        this.postalCode = postalCode;
        this.city = city;
    }


    public Suburb(String suburbName, String postalCode, City city) {
        this.suburbName = suburbName;
        this.postalCode = postalCode;
        this.city = city;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSuburbName() {
        return suburbName;
    }

    public void setSuburbName(String suburbName) {
        this.suburbName = suburbName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
