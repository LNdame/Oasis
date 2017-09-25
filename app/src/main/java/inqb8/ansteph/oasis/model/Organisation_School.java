package inqb8.ansteph.oasis.model;

import java.io.Serializable;

/**
 * Created by loicstephan on 2017/09/22.
 */

public class Organisation_School implements Serializable {

  int  log_id,  organisation_id,  school_id;

  String  school_name, geographic_area, learner_target, target, frequency;

    public Organisation_School() {
    }

    public Organisation_School(int log_id, int organisation_id, int school_id, String school_name, String geographic_area, String learner_target, String target, String frequency) {
        this.log_id = log_id;
        this.organisation_id = organisation_id;
        this.school_id = school_id;
        this.school_name = school_name;
        this.geographic_area = geographic_area;
        this.learner_target = learner_target;
        this.target = target;
        this.frequency = frequency;
    }

    public Organisation_School(int organisation_id, int school_id, String school_name, String geographic_area, String learner_target, String target, String frequency) {
        this.organisation_id = organisation_id;
        this.school_id = school_id;
        this.school_name = school_name;
        this.geographic_area = geographic_area;
        this.learner_target = learner_target;
        this.target = target;
        this.frequency = frequency;
    }


    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public int getOrganisation_id() {
        return organisation_id;
    }

    public void setOrganisation_id(int organisation_id) {
        this.organisation_id = organisation_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getGeographic_area() {
        return geographic_area;
    }

    public void setGeographic_area(String geographic_area) {
        this.geographic_area = geographic_area;
    }

    public String getLearner_target() {
        return learner_target;
    }

    public void setLearner_target(String learner_target) {
        this.learner_target = learner_target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
