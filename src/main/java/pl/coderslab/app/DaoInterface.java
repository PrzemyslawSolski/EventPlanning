package pl.coderslab.app;

import java.util.List;

public interface DaoInterface <T>  {


    public void create (T t);
    public void update(T t);
    public T findOne (long id);
    public List<T> findAll();
    public void delete(Long id);

}
