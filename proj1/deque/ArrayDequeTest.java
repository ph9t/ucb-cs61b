package deque;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;

public class ArrayDequeTest {

    @Test
    public void testRandomly() {
        ArrayList<Integer> AL = new ArrayList<>();
        ArrayDeque<Integer> AD = new ArrayDeque<>();

        int N = 128000;
        for (int i = 0; i < N; i++) {
            int method = StdRandom.uniform(0, 5);

            int x, y;
            switch (method) {
                case 0: {
                    AL.add(i);
                    AD.addLast(i);
                    break;
                }
                case 1: {
                    AL.add(0, i);
                    AD.addFirst(i);
                    break;
                }
                case 2: {
                    if (AL.isEmpty()) break;

                    x = AL.remove(AL.size() - 1);
                    y = AD.removeLast();

                    assertEquals(x, y);
                    break;
                }
                case 3: {
                    if (AL.isEmpty()) break;

                    x = AL.remove(0);
                    y = AD.removeFirst();

                    assertEquals(x, y);
                    break;
                }
                case 4: {
                    if (AL.isEmpty()) break;

                    int randIdx = StdRandom.uniform(AL.size());
                    x = AL.get(randIdx);
                    y = AD.get(randIdx);
                    assertEquals(x, y);

                    break;
                }
            }
        }
    }
}
