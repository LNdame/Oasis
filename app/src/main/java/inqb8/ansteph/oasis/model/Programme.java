package inqb8.ansteph.oasis.model;

import java.io.Serializable;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class Programme implements Serializable {

    int _id;
    String name, duration, cost, learnerLevel, educatorParent, frequency;
    int organisation_id;

    public Programme() {
    }

    public Programme(String name, String duration, String cost, String learnerLevel, String educatorParent, String frequency) {
        this.name = name;
        this.duration = duration;
        this.cost = cost;
        this.learnerLevel = learnerLevel;
        this.educatorParent = educatorParent;
        this.frequency = frequency;
    }

    public Programme(int _id, String name, String duration, String learnerLevel, String educatorParent, String frequency, int organisation_id) {
        this._id = _id;
        this.name = name;
        this.duration = duration;
        this.learnerLevel = learnerLevel;
        this.educatorParent = educatorParent;
        this.frequency = frequency;
        this.organisation_id = organisation_id;
    }

    public Programme(int _id, String name, String duration, String cost, String learnerLevel, String educatorParent, String frequency) {
        this._id = _id;
        this.name = name;
        this.duration = duration;
        this.cost = cost;
        this.learnerLevel = learnerLevel;
        this.educatorParent = educatorParent;
        this.frequency = frequency;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLearnerLevel() {
        return learnerLevel;
    }

    public void setLearnerLevel(String learnerLevel) {
        this.learnerLevel = learnerLevel;
    }

    public String getEducatorParent() {
        return educatorParent;
    }

    public void setEducatorParent(String educatorParent) {
        this.educatorParent = educatorParent;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getOrganisation_id() {
        return organisation_id;
    }

    public void setOrganisation_id(int organisation_id) {
        this.organisation_id = organisation_id;
    }
}
