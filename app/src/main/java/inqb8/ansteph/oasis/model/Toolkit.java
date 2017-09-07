package inqb8.ansteph.oasis.model;

/**
 * Created by loicstephan on 2017/09/06.
 */

public class Toolkit {

    int id;
    String name, description;


    public Toolkit() {
    }

    public Toolkit(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Toolkit(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
