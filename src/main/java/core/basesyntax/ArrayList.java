package core.basesyntax;
import java.util.NoSuchElementException;
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_NUMERATOR = 3;
    private static final int GROWTH_DENOMINATOR = 2;
    private T[] elementData;
    private int size;
    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean add(T value) {
        ensureCapacity(size + 1);
        elementData[size()] = value;
        size++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        checkPositionIndexForAdd(index);
        ensureCapacity(size + 1);
        shiftElementsForInsert(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size(), 0, false);
        int index = 0;
        for (int i = size(); i < size() + list.size(); i++) {
            elementData[i] = list.get(index);
            index++;
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return elementData[index];
    }

    @Override
    public T set(T value, int index) {
        checkElementIndex(index);
        T oldElementData = elementData[index];
        elementData[index] = value;
        return oldElementData;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        T value = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size() - index - 1);
        size--;
        elementData[size()] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (element == null && elementData[i] == null) {
                index = i;
                break;
            }
            if (element != null && element.equals(elementData[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not found in list: " + element);
        }
        T value = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size() - index - 1);
        size--;
        elementData[size()] = null;
        return value;
    }

    @Override
    public int size() {
       return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkPositionIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " out of bounds for add with size " + size
            );
        }
    }
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (elementData.length >= minCapacity) {
            return; // resize не нужен
        }
        grow(minCapacity);
    }

    private void grow(int minCapacity) {
        int newCapacity = elementData.length * GROWTH_NUMERATOR / GROWTH_DENOMINATOR;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    private void shiftElementsForInsert(int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }
}
