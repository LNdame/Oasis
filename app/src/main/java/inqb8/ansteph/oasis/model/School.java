package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/12.
 */

public class School {


    int _id;

    String name, learnerLevel, educationParent, frequency , address , geotag, telephone;
    String  fax, email, website_url, synopsys;

    byte [] img;

    //add a province object

    public School() {
    }

    public School(int id, String name, String learnerLevel, String educationParent, String frequency) {
        this._id = id;
        this.name = name;
        this.learnerLevel = learnerLevel;
        this.educationParent = educationParent;
        this.frequency = frequency;
    }


    public School(String name, String learnerLevel, String educationParent, String frequency) {
        this.name = name;
        this.learnerLevel = learnerLevel;
        this.educationParent = educationParent;
        this.frequency = frequency;
    }


    // for testing


    public School(String name, String address, String telephone) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite_url() {
        return website_url;
    }

    public void setWebsite_url(String website_url) {
        this.website_url = website_url;
    }

    public String getSynopsys() {
        return synopsys;
    }

    public void setSynopsys(String synopsys) {
        this.synopsys = synopsys;
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

    public String getLearnerLevel() {
        return learnerLevel;
    }

    public void setLearnerLevel(String learnerLevel) {
        this.learnerLevel = learnerLevel;
    }

    public String getEducationParent() {
        return educationParent;
    }

    public void setEducationParent(String educationParent) {
        this.educationParent = educationParent;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeotag() {
        return geotag;
    }

    public void setGeotag(String geotag) {
        this.geotag = geotag;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
