package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        int maxIdx = 0;

        for (int i = 0; i < this.size(); i++) {
            T maxElement = this.get(maxIdx);
            T currentElement = this.get(i);

            if (c.compare(currentElement, maxElement) > 0) {
                maxIdx = i;
            }
        }

        return this.get(maxIdx);
    }


    private static class NaturalOrderComparator<E extends Comparable<E>> implements Comparator<E> {
        @Override
        public int compare(E o1, E o2) {
            return o1.compareTo(o2);
        }
    }
}
