package database;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public ArrayList<T> selectAll();
    public T selectById(int id);
    public int insert(T t);
    public int insertAll(ArrayList<T> list);
    public int delete(T t);
    public int deleteAll(ArrayList<T> list);
    public int update(T t);
}
