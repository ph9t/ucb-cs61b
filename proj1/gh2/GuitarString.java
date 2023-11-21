package gh2;

import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayDeque<>();

        int capacity = (int) Math.round(SR / frequency);

        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int capacity = buffer.size();

        for (int i = 0; i < capacity; i++) {
            double noise = Math.random() - 0.5;

            buffer.removeLast();
            buffer.addFirst(noise);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       **Do not call StdAudio.play().**

        double removedFront = buffer.removeFirst();
        double front = buffer.get(0);

        buffer.addLast((removedFront + front) / 2 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
