import static org.junit.Assert.*;
import org.junit.Test;
/**
 * This class was provided by the instructor.
 * Testing methods for the PA3 ChuckALuck.calculatePayout method.
 * 
 */
public class ChuckALuckTest {

   public static final int SINGLE = 1;
   public static final int TRIPLE = 2;
   public static final int BIG = 3;
   public static final int SMALL = 4;
   public static final int FIELD = 5;

   public static final double EPSILON = .0001;

   // TESTS FOR SINGLE ----------------------------------

   @Test
   public void testCalculatePayoutSingleNoMatch() {
      Dice dice = new Dice(1, 2, 3);

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice, SINGLE, 4, 100.0), EPSILON);
      assertEquals(-200.5,
                  ChuckALuck.calculatePayout(dice, SINGLE, 5, 200.5), EPSILON);
      assertEquals(-300.0,
                  ChuckALuck.calculatePayout(dice, SINGLE, 6, 300.0), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice, SINGLE, 6, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutSingleOneMatch() {
      Dice dice1 = new Dice(4, 5, 6); // We'll try each of 4,5,6 as a single
      Dice dice2 = new Dice(1, 1, 2); // Confirm duplicates don't cause errors

      assertEquals(100.0,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 4, 100.0), EPSILON);
      assertEquals(200.25,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 5, 200.25),
                  EPSILON);
      assertEquals(300.0,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 6, 300.0), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 6, 0.0), EPSILON);
      assertEquals(300.0,
                  ChuckALuck.calculatePayout(dice2, SINGLE, 2, 300.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutSingleTwoMatch() {
      
      // Make sure we try all possible positions for the matches...
      Dice dice1 = new Dice(5, 5, 6);
      Dice dice2 = new Dice(2, 1, 1);
      Dice dice3 = new Dice(4, 1, 4);

      assertEquals(200.2,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 5, 100.1), EPSILON);
      assertEquals(400.2,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 5, 200.1), EPSILON);
      assertEquals(400.0,
                  ChuckALuck.calculatePayout(dice2, SINGLE, 1, 200.0), EPSILON);
      assertEquals(600.0,
                  ChuckALuck.calculatePayout(dice2, SINGLE, 1, 300.0), EPSILON);
      assertEquals(20.0,
                  ChuckALuck.calculatePayout(dice3, SINGLE, 4, 10.0), EPSILON);
      assertEquals(600.0,
                  ChuckALuck.calculatePayout(dice3, SINGLE, 4, 300.0), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice3, SINGLE, 4, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutSingleThreeMatch() {
      Dice dice1 = new Dice(5, 5, 5);
      Dice dice2 = new Dice(1, 1, 1);
      Dice dice3 = new Dice(4, 4, 4);

      assertEquals(1000.50,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 5, 100.05),
                  EPSILON);
      assertEquals(2000.0,
                  ChuckALuck.calculatePayout(dice1, SINGLE, 5, 200.0), EPSILON);
      assertEquals(2000.0,
                  ChuckALuck.calculatePayout(dice2, SINGLE, 1, 200.0), EPSILON);
      assertEquals(3000.0,
                  ChuckALuck.calculatePayout(dice2, SINGLE, 1, 300.0), EPSILON);
      assertEquals(100.0,
                  ChuckALuck.calculatePayout(dice3, SINGLE, 4, 10.0), EPSILON);
      assertEquals(3000.0,
                  ChuckALuck.calculatePayout(dice3, SINGLE, 4, 300.0), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice3, SINGLE, 4, 0.0), EPSILON);
   }

   // TESTS FOR ANY TRIPLE ----------------------------------

   @Test
   public void testCalculatePayoutAnyTripleLose() {
      Dice dice1 = new Dice(5, 5, 4); // first two match
      Dice dice2 = new Dice(1, 2, 3); // no matches
      Dice dice3 = new Dice(5, 4, 4); // last two match
      Dice dice4 = new Dice(6, 1, 6); // first and last match

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, TRIPLE, 5, 100.0), EPSILON);
      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, TRIPLE, -1, 100.0),
                  EPSILON);
      assertEquals(-200.0,
                  ChuckALuck.calculatePayout(dice2, TRIPLE, -1, 200.0),
                  EPSILON);
      assertEquals(-500.0,
                  ChuckALuck.calculatePayout(dice3, TRIPLE, -1, 500.0),
                  EPSILON);
      assertEquals(-1.5,
                  ChuckALuck.calculatePayout(dice4, TRIPLE, -1, 1.5), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice4, TRIPLE, -1, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutAnyTripleWin() {
      Dice dice1 = new Dice(2, 2, 2);
      Dice dice2 = new Dice(1, 1, 1);
      Dice dice3 = new Dice(6, 6, 6);

      assertEquals(3000.0,
                  ChuckALuck.calculatePayout(dice1, TRIPLE, 5, 100.0), EPSILON);
      assertEquals(3000.3,
                  ChuckALuck.calculatePayout(dice2, TRIPLE, -1, 100.01),
                  EPSILON);
      assertEquals(30.0,
                  ChuckALuck.calculatePayout(dice3, TRIPLE, -1, 1.0), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice3, TRIPLE, -1, 0.0), EPSILON);
   }

   // TESTS FOR BIG ----------------------------------

   @Test
   public void testCalculatePayoutBigLoseTooSmall() {
      Dice dice1 = new Dice(2, 4, 4); // 10
      Dice dice2 = new Dice(1, 1, 2); // 4

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, BIG, 5, 100.0), EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice2, BIG, -1, 100.01), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice2, BIG, -1, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutBigLoseTriple() {
      Dice dice1 = new Dice(6, 6, 6);
      Dice dice2 = new Dice(4, 4, 4);
      Dice dice3 = new Dice(1, 1, 1);

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, BIG, 5, 100.0), EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice2, BIG, -1, 100.01), EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice3, BIG, -1, 100.01), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice3, BIG, -1, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutBigWin() {
      Dice dice1 = new Dice(6, 4, 1); // 11
      Dice dice2 = new Dice(5, 2, 5); // 12

      assertEquals(100.0,
                  ChuckALuck.calculatePayout(dice1, BIG, 5, 100.0), EPSILON);
      assertEquals(100.01,
                  ChuckALuck.calculatePayout(dice2, BIG, -1, 100.01), EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice2, BIG, -1, 0.0), EPSILON);
   }

   // TESTS FOR SMALL ----------------------------------

   @Test
   public void testCalculatePayoutSmallLoseTooBig() {
      Dice dice1 = new Dice(3, 4, 4); // 11
      Dice dice2 = new Dice(5, 6, 6); // 17

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, SMALL, 5, 100.0), EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice2, SMALL, -1, 100.01),
                  EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice2, SMALL, -1, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutSmallLoseTriple() {
      Dice dice1 = new Dice(2, 2, 2);
      Dice dice2 = new Dice(6, 6, 6);
      Dice dice3 = new Dice(1, 1, 1);

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, SMALL, 5, 100.0), EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice2, SMALL, -1, 100.01),
                  EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice3, SMALL, -1, 100.01),
                  EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice3, SMALL, -1, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutSmallWin() {
      Dice dice1 = new Dice(5, 4, 1); // 10
      Dice dice2 = new Dice(4, 2, 4); // 10

      assertEquals(100.0,
                  ChuckALuck.calculatePayout(dice1, SMALL, 5, 100.0), EPSILON);
      assertEquals(100.01,
                  ChuckALuck.calculatePayout(dice2, SMALL, -1, 100.01),
                  EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice2, SMALL, -1, 0.0), EPSILON);
   }

   // TESTS FOR FIELD ----------------------------------

   @Test
   public void testCalculatePayoutFieldLose() {
      Dice dice1 = new Dice(1, 3, 4); // 8 (too big by one)
      Dice dice2 = new Dice(5, 6, 1); // 12 (too small by one)
      Dice dice3 = new Dice(4, 4, 4); // 12 (too small triple)

      assertEquals(-100.0,
                  ChuckALuck.calculatePayout(dice1, FIELD, 5, 100.0), EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice2, FIELD, -1, 100.01),
                  EPSILON);
      assertEquals(-100.01,
                  ChuckALuck.calculatePayout(dice3, FIELD, -1, 100.01),
                  EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice2, FIELD, -1, 0.0), EPSILON);
   }

   @Test
   public void testCalculatePayoutFieldWin() {
      Dice dice1 = new Dice(1, 3, 3); // 7 (just small enough)
      Dice dice2 = new Dice(5, 6, 2); // 13 (just big enough)
      Dice dice3 = new Dice(5, 5, 5); // 15 (big enough triple)

      assertEquals(100.0,
                  ChuckALuck.calculatePayout(dice1, FIELD, 5, 100.0), EPSILON);
      assertEquals(100.01,
                  ChuckALuck.calculatePayout(dice2, FIELD, -1, 100.01),
                  EPSILON);
      assertEquals(100.01,
                  ChuckALuck.calculatePayout(dice3, FIELD, -1, 100.01),
                  EPSILON);
      assertEquals(0.0,
                  ChuckALuck.calculatePayout(dice2, FIELD, -1, 0.0), EPSILON);
   }
   
   
   // TEST FOR BAD BET TYPE
   @Test
   public void testBadBetType() {
      Dice dice = new Dice(1, 3, 3); 
      assertEquals(-1.0,
                  ChuckALuck.calculatePayout(dice, -1, 5, 100.0), EPSILON);
   }
}
