package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class City {

    int _id;

   String cityName;

    Province province;

    public City() {
    }


    public City(int _id, String cityName, Province province) {
        this._id = _id;
        this.cityName = cityName;
        this.province = province;
    }


    public City(String cityName, Province province) {
        this.cityName = cityName;
        this.province = province;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
