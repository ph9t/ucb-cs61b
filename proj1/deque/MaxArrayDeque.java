package deque;

import java.util.Comparator;

public class MaxArrayDeque<T extends Comparable<T>> extends ArrayDeque<T> {
    Comparator<T> comparator;

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


    private static class NaturalOrderComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    public static <E extends Comparable<E>> MaxArrayDeque<E> of(Comparator<E> c, E... args) {
        MaxArrayDeque<E> MAD = new MaxArrayDeque<>(c);

        for (E x: args) {
            MAD.addLast(x);
        }

        return MAD;
    }
}
