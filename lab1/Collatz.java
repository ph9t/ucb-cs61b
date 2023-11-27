/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {
    /** Identifies next number in a Collatz sequence, given N
     * @param number the nth number
     * */
    public static int nextNumber(int number) {
        boolean isEven = (number % 2 == 0);
        return  isEven ? (number / 2) : (3 * number + 1);
    }

    public static void main(String[] args) {
        int n = 5;

        System.out.print(n + " ");

        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }

        System.out.println();
    }
}

