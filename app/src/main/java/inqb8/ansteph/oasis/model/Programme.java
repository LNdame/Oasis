package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/08/28.
 */

public class Programme {

    int _id;
    String name, duration, cost, learnerLevel, educatorParent, frequency;

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
}
