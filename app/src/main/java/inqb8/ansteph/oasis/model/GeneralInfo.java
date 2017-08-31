package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class GeneralInfo {

    int _id;

    String telephoneNumber, faxNumber, email, websiteurl, synopsis;

    byte [] logo;


    public GeneralInfo() {
    }


    public GeneralInfo(int _id, String telephoneNumber, String faxNumber, String email, String websiteurl, String synopsis, byte[] logo) {
        this._id = _id;
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.websiteurl = websiteurl;
        this.synopsis = synopsis;
        this.logo = logo;
    }


    public GeneralInfo(String telephoneNumber, String faxNumber, String email, String websiteurl, String synopsis, byte[] logo) {
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.websiteurl = websiteurl;
        this.synopsis = synopsis;
        this.logo = logo;
    }

    public GeneralInfo(int _id, String telephoneNumber, String faxNumber, String email, String websiteurl, String synopsis) {
        this._id = _id;
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.websiteurl = websiteurl;
        this.synopsis = synopsis;
    }

    public GeneralInfo(String telephoneNumber, String faxNumber, String email, String websiteurl, String synopsis) {
        this.telephoneNumber = telephoneNumber;
        this.faxNumber = faxNumber;
        this.email = email;
        this.websiteurl = websiteurl;
        this.synopsis = synopsis;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteurl() {
        return websiteurl;
    }

    public void setWebsiteurl(String websiteurl) {
        this.websiteurl = websiteurl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
}
