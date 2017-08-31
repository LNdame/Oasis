package inqb8.ansteph.oasis.model;

import java.util.ArrayList;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class Organisation {

    int _id;

    String name, addressline1, contactperson1Name, contactperson2Name, contactperson1Position,contactperson2Position;

    ArrayList<Programme> programmes;

    GeneralInfo generalInfo;

    Suburb suburb;

    Province province;

    City city;

    byte [] imgLogo;

    Org_Type type;

    WorkArea workArea;

    public Organisation() {
    }


    public Organisation(int _id, String name, String addressline1, String contactperson1Name, String contactperson2Name, String contactperson1Position,
                        String contactperson2Position, ArrayList<Programme> programmes,
                        GeneralInfo generalInfo, Suburb suburb, Province province, City city, byte[] imgLogo, Org_Type type, WorkArea workArea) {
        this._id = _id;
        this.name = name;
        this.addressline1 = addressline1;
        this.contactperson1Name = contactperson1Name;
        this.contactperson2Name = contactperson2Name;
        this.contactperson1Position = contactperson1Position;
        this.contactperson2Position = contactperson2Position;
        this.programmes = programmes;
        this.generalInfo = generalInfo;
        this.suburb = suburb;
        this.province = province;
        this.city = city;
        this.imgLogo = imgLogo;
        this.type = type;
        this.workArea = workArea;
    }


    public Organisation(String name, String addressline1, String contactperson1Name, String contactperson2Name,
                        String contactperson1Position, String contactperson2Position, ArrayList<Programme> programmes,
                        GeneralInfo generalInfo, Suburb suburb, Province province, City city, byte[] imgLogo, Org_Type type, WorkArea workArea) {
        this.name = name;
        this.addressline1 = addressline1;
        this.contactperson1Name = contactperson1Name;
        this.contactperson2Name = contactperson2Name;
        this.contactperson1Position = contactperson1Position;
        this.contactperson2Position = contactperson2Position;
        this.programmes = programmes;
        this.generalInfo = generalInfo;
        this.suburb = suburb;
        this.province = province;
        this.city = city;
        this.imgLogo = imgLogo;
        this.type = type;
        this.workArea = workArea;
    }


    public Organisation(int _id, String name, String addressline1, String contactperson1Name, String contactperson2Name, String contactperson1Position, String contactperson2Position,
                        ArrayList<Programme> programmes, GeneralInfo generalInfo, Suburb suburb, Province province, City city, Org_Type type, WorkArea workArea) {
        this._id = _id;
        this.name = name;
        this.addressline1 = addressline1;
        this.contactperson1Name = contactperson1Name;
        this.contactperson2Name = contactperson2Name;
        this.contactperson1Position = contactperson1Position;
        this.contactperson2Position = contactperson2Position;
        this.programmes = programmes;
        this.generalInfo = generalInfo;
        this.suburb = suburb;
        this.province = province;
        this.city = city;
        this.type = type;
        this.workArea = workArea;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getContactperson1Name() {
        return contactperson1Name;
    }

    public void setContactperson1Name(String contactperson1Name) {
        this.contactperson1Name = contactperson1Name;
    }

    public String getContactperson2Name() {
        return contactperson2Name;
    }

    public void setContactperson2Name(String contactperson2Name) {
        this.contactperson2Name = contactperson2Name;
    }

    public String getContactperson1Position() {
        return contactperson1Position;
    }

    public void setContactperson1Position(String contactperson1Position) {
        this.contactperson1Position = contactperson1Position;
    }

    public String getContactperson2Position() {
        return contactperson2Position;
    }

    public void setContactperson2Position(String contactperson2Position) {
        this.contactperson2Position = contactperson2Position;
    }

    public ArrayList<Programme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(ArrayList<Programme> programmes) {
        this.programmes = programmes;
    }

    public GeneralInfo getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(GeneralInfo generalInfo) {
        this.generalInfo = generalInfo;
    }

    public Suburb getSuburb() {
        return suburb;
    }

    public void setSuburb(Suburb suburb) {
        this.suburb = suburb;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public byte[] getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(byte[] imgLogo) {
        this.imgLogo = imgLogo;
    }

    public Org_Type getType() {
        return type;
    }

    public void setType(Org_Type type) {
        this.type = type;
    }

    public WorkArea getWorkArea() {
        return workArea;
    }

    public void setWorkArea(WorkArea workArea) {
        this.workArea = workArea;
    }
}
