package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/12.
 */

public class School {


    int id;

    String name, learnerLevel, educationParent, frequency , address , geotag, telephone;

    byte [] img;

    //add a province object

    public School() {
    }

    public School(int id, String name, String learnerLevel, String educationParent, String frequency) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
