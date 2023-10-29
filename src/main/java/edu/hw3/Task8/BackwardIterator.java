package edu.hw3.Task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<T> implements Iterator<T> {
    private List<T> data;
    private int curIndx;

    public BackwardIterator(List<T> data) {
        this.data = data;
        this.curIndx = data.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return curIndx >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T element = data.get(curIndx);
        curIndx -= 1;

        return element;
    }
}

