package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        Node prev;
        Node next;
        T item;

        Node(T item) {
            this.item = item;
        }

        Node(T item, Node p, Node n) {
            this.item = item;
            this.prev = p;
            this.next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.next = sentinel.prev = sentinel;

        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newItem = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newItem;
        sentinel.next = newItem;

        this.size = this.size + 1;
    }

    @Override
    public void addLast(T item) {
        Node newItem = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newItem;
        sentinel.prev = newItem;

        this.size = this.size + 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        Node currentNode = sentinel.next;

        System.out.print("{ ");
        while (currentNode != sentinel) {
            System.out.print(currentNode.item);
            currentNode = currentNode.next;

            if (currentNode != sentinel) {
                System.out.print(" -> ");
            }
        }

        System.out.println(" }");
    }

    @Override
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }

        Node removedNode = sentinel.next;
        removedNode.prev.next = removedNode.next;
        removedNode.next.prev = sentinel;

        this.size = this.size - 1;

        return removedNode.item;
    }

    public T removeLast() {
        if (this.size == 0) {
            return null;
        }

        Node removedNode = sentinel.prev;
        removedNode.prev.next = sentinel;
        removedNode.next.prev = removedNode.prev;

        this.size = this.size - 1;

        return removedNode.item;
    }

    public T get(int index) {
        if (index >= this.size || 0 > index) {
            return null;
        }

        Node currentNode = sentinel.next;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.item;
    }

    public T getRecursive(int index) {
        if (index >= this.size || 0 > index) {
            return null;
        }

        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node node) {
        if (index == 0) {
            return node.item;
        }

        return getRecursive(index - 1, node.next);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Deque)) {
            return false;
        }

        Deque<T> comparisonDeque = (Deque<T>) obj;

        if (comparisonDeque.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (comparisonDeque.get(i) != this.get(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node currentNode;

        LinkedListDequeIterator() {
            currentNode = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return currentNode != sentinel;
        }

        @Override
        public T next() {
            T currentValue = currentNode.item;
            currentNode = currentNode.next;
            return currentValue;
        }
    }
}
