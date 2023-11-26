package deque;

import java.util.Iterator;

public class ArrayDeque<T>  implements Deque<T>, Iterable<T> {
    private final int MIN_SIZE = 8;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        this.items = (T[]) new Object[MIN_SIZE];
        this.size = 0;

        this.nextFirst = MIN_SIZE / 4;
        this.nextLast = this.nextFirst + 1;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int offset = capacity / 4;

        for (int i = 0; i < this.size; i++) {
            int idxFrom = getPos(this.nextFirst + 1 + i);
            int idxTo = getPos(offset + i, capacity);

            temp[idxTo] = this.items[idxFrom];
        }

        this.items = temp;
        this.nextFirst = offset - 1;
        this.nextLast = getPos(offset + this.size);
    }

    private boolean isFull() {
        return this.size == this.items.length;
    }

    private boolean isBig() {
        return (float) this.size / this.items.length < 0.25 && this.items.length > 8;
    }

    private int getPosAuto(boolean last, boolean next) {
        int pos = last ? this.nextLast : this.nextFirst;
        int offset = 1;

        if (last && !next || !last && next) {
            offset = -1;
        }

        return getPos(pos + offset);
    }

    private int getPos(int i) {
        return Math.floorMod(i, this.items.length);
    }

    private int getPos(int i, int itemsLength) {
        return Math.floorMod(i, itemsLength);
    }

    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(this.size * 2);
        }

        this.items[nextFirst] = item;
        this.size = this.size + 1;

        this.nextFirst = getPosAuto(false, true);
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(this.size * 2);
        }

        this.items[this.nextLast] = item;
        this.size = this.size + 1;

        this.nextLast = getPosAuto(true, true);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        System.out.print("{ ");

        for (int i = 0; i < this.size; i++) {
            int idx = getPos(i + nextFirst + 1);

            System.out.print(this.items[idx]);
            if (i + 1 < this.size) {
                System.out.print(" -> ");
            }
        }

        System.out.println(" }");
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int idx = getPosAuto(false, false);
        T removedItem = this.items[idx];

        this.items[idx] = null;
        this.nextFirst = idx;
        this.size = this.size - 1;

        if (isBig()) {
            resize(this.items.length / 2);
        }

        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int idx = getPosAuto(true, false);
        T removedItem = this.items[idx];

        this.items[idx] = null;
        this.nextLast = idx;
        this.size = this.size - 1;

        if (isBig()) {
            resize(this.items.length / 2);
        }

        return removedItem;
    }

    @Override
    public T get(int n) {
        if (n >= this.size || 0 > n) {
            return null;
        }

        int idx = getPos(this.nextFirst + 1 + n);
        return this.items[idx];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int current;

        ArrayDequeIterator() {
            this.current = 0;
        }

        @Override
        public boolean hasNext() {
            return this.current < size();
        }

        @Override
        public T next() {
            T nextItem = get(current);
            current = current + 1;
            return nextItem;
        }
    }
}
