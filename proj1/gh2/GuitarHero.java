package gh2;


import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayDeque<GuitarString> notes = new ArrayDeque<>();

        for (int i = 0; i < keyboard.length(); i++) {
            double freq = 440 * Math.pow(2, (double) (i - 24) / 12);
            notes.addLast(new GuitarString(freq));
        }

        System.out.println(keyboard.charAt(24));
        System.out.println(keyboard.charAt(15));
        System.out.println(keyboard.charAt(21));

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                int noteIndex = keyboard.indexOf(key);

                if (noteIndex > -1) {
                    notes.get(noteIndex).pluck();
                }
            }

            double sample = 0.0;

            for (var string: notes) {
                sample += string.sample();
            }

            StdAudio.play(sample);

            for (var string: notes) {
                string.tic();
            }
        }
    }
}
