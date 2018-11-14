package interfaces;

import java.sql.SQLException;
import java.util.List;

public interface MainService<T> {
    public Integer add(T t) throws SQLException;
    public void set(T t) throws SQLException;
    public T get(Long id) throws SQLException;
    public void del(T t) throws SQLException;
    public void del(Long id) throws SQLException;
    public List<T> getAll() throws SQLException;
}
