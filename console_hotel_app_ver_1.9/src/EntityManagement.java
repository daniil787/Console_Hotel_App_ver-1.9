public interface EntityManagement<T>
{
    void addEntity(T entity);
    void changeEntity();
    void deleteEntity(T entity);
}
