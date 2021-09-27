/**
 * @author Viktor Makarov
 */
public interface MyCollectionInterface<E> {
    int size();
    boolean isEmpty();
    boolean contains(E o);
    boolean add(E e);
    boolean remove(E o);
    void clear();
    E get(int index);
    E set(int index, E element);
    void add(int index, E element);
    E remove(int index);
}
