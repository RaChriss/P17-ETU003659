package Utils;

import java.sql.Connection;

public abstract class BaseObject {
    protected int id;

    public BaseObject() {
    }

    public BaseObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void save(Connection conn) throws Exception;

    public abstract void delete(Connection conn) throws Exception;

    public abstract void update(Connection conn) throws Exception;

    public abstract BaseObject[] getAll(Connection conn) throws Exception;
}
