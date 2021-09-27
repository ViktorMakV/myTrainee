import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * @author Viktor Makarov
 */
public class MyCollection<E> implements Iterable, MyCollectionInterface<E> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.setVisibility(objectMapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.ANY));
    }

    private final Class<E> eClass;

    private int size;

    private static int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private static final String objectWithoutFieldsMsg = "Unable to add object without fields.";

    transient Object[] elementData;

    public MyCollection(Class<E> eClass) {
        this(eClass, DEFAULT_CAPACITY);
    }

    public MyCollection(Class<E> eClass, int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal capacity " + capacity);
        }
        this.eClass = eClass;
    }

    public MyCollection(Class<E> eClass, Collection<? extends E> c) {
        Object[] a = c.toArray();
        this.eClass = eClass;
        if ((size = a.length) != 0) {
            elementData = new Object[size];

            for (int i = 0; i < size; i++) {
                try {
                    elementData[i] = objectMapper.readValue(
                            objectMapper.writeValueAsBytes(a[i]), eClass);
                } catch (IOException e) {
                    System.out.println(objectWithoutFieldsMsg);
                }
            }
        } else {
            elementData = EMPTY_ELEMENT_DATA;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E o) {
        for (Object elem : elementData) {
            if (elem.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        try {
            elementData[size++] = objectMapper.readValue(objectMapper.writeValueAsBytes(e), eClass);
        } catch (IOException ioException) {
            System.out.println(objectWithoutFieldsMsg);
            return false;
        }
        return true;
    }

    public boolean remove(E o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    public void clear() {
        elementData = EMPTY_ELEMENT_DATA;
        size = 0;
    }

    public E get(int index) {
        rangeCheck(index);
        E out = null;
        try {
            out = objectMapper.readValue(objectMapper.writeValueAsBytes(elementData[index]), eClass);
        } catch (IOException e) {
            System.out.println(objectWithoutFieldsMsg);
        }
        return out;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E out = (E) elementData[index];
        try {
            elementData[index] = objectMapper.readValue(objectMapper.writeValueAsBytes(element), eClass);
        } catch (IOException e) {
            System.out.println(objectWithoutFieldsMsg);
        }
        return out;
    }

    public void add(int index, E element) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        try {
            elementData[index] = objectMapper.readValue(objectMapper.writeValueAsBytes(element), eClass);
        } catch (IOException e) {
            System.out.println(objectWithoutFieldsMsg);
        }
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        E e = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;
        return e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
            sb.append(elementData[i].toString());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private static int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == EMPTY_ELEMENT_DATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    class Itr implements Iterator<E> {
        private int cursor;
        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = MyCollection.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            E out = null;
            try {
                out = objectMapper.readValue(objectMapper.writeValueAsBytes(elementData[lastRet = i]), eClass);
            } catch (IOException e) {
                System.out.println(objectWithoutFieldsMsg);
            }
            return out;
        }
    }
}
