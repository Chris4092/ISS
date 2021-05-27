package domain;
import java.io.Serializable;

public class Entity<ID> implements Serializable {

    public Entity() {
    }

    private static final long serialVersionUID = 7331115341259248461L;
    private ID id;
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}