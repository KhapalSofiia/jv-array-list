package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_OF_ARRAY_LIST = 10;
    public T[] array;
    int currentSize;

    public ArrayList() {
        array = (T[]) new Object[SIZE_OF_ARRAY_LIST];
        currentSize = 0;
    }
    @Override
    public void add(T value) {
        int index = size();
        if (index < array.length) {
            array[index] = value;
            currentSize++;
        } else {
            T[] newArray = (T[]) new Object[array.length + array.length / 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
            array[index] = value;
            currentSize++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > currentSize) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        if (size() == array.length) {
                T[] newArray = (T[]) new Object[array.length + array.length / 2];
                System.arraycopy(array, 0, newArray, 0, index);
                System.arraycopy(array, index, newArray, index + 1, size() - index);
                array = newArray;
        } else {
            System.arraycopy(array, index, array, index + 1, currentSize - index);
        }
        array[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size() >= array.length) {
            int length  = array.length;
            while (list.size() + size() >= length) {
                length = length + length / 2;
            }
            T[] newArray = (T[]) new Object[length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            int index = 0;
            for (int i = size(); i < size() + list.size(); i++) {
                newArray[i] = list.get(index);
                index++;
            }
            array = newArray;
        } else {
            int index = 0;
            for (int i = size(); i < size() + list.size(); i++) {
                array[i] = list.get(index);
                index++;
            }
        }
        currentSize += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        for (int i = 0; i < SIZE_OF_ARRAY_LIST; i++) {
            if (index == i) {
                return array[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        T value = array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        currentSize--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (element == null && array[i] == null) {
                index = i;
                break;
            }
            if (element != null && element.equals(array[i])) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("No such element");
        }
        T value = array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        currentSize--;
        return value;
    }

    @Override
    public int size() {
       return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
