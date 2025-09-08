package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1, 0, false);
        elementData[size++] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkElementIndex(index);
        ensureCapacity(size + 1, index, true);
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
    public void set(T value, int index) {
        checkElementIndex(index);
        elementData[index] = value;
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
            throw new NoSuchElementException("No such element in the list with index " + index);
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

    public void checkElementIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + " out of array with size " + size());
        }
    }

    public void ensureCapacity(int minCapacity, int index, boolean shiftForInsert) {
        if (minCapacity < elementData.length) {
            if (shiftForInsert) {
                System.arraycopy(elementData, index, elementData, index + 1, size() - index);
            }
            return;
        }
        int minLength = elementData.length;
        while ((minLength + minLength / 2) < minCapacity) {
            minLength = minLength + minLength / 2;
        }

        T[] newArray = (T[]) new Object[minLength];

        if (shiftForInsert) {
            System.arraycopy(elementData, 0, newArray, 0, index);
            System.arraycopy(elementData, index, newArray, index + 1, size() - index);
        } else {
            System.arraycopy(elementData, 0, newArray, 0, size);
        }
        elementData = newArray;
    }
}
