package dao;

/**
 *
 * @author lucas
 */
public interface IDAO<T> {
    
    public void create(T value);
    public T findById(T id);
    public T update(T value);
    public T remove(final T value);
    
}
