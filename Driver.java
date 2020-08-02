  import java.util.Scanner;

/**
 * This class was provided by the instructor.
 * Simple command-line version of the Chuck-a-luck game.
 * 
 * @author Nathan Sprague & Farzana Rahman
 * @version 9/20/2016
 *
 */
public class Driver {

   /**
    * The main keeps collecting bets from the user as long as he or she wants to
    * play. Total winnings are tracked and reported after each bet.
    * 
    * @param args - Command line arguments. Not used.
    */
   public static void main(String[] args) {

      Scanner scan;
      String keepPlaying;
      double totalWinnings;

      keepPlaying = "y";
      totalWinnings = 0;
      scan = new Scanner(System.in);

      System.out.println("Welcome to Chuck-a-luck!");

      while (keepPlaying.equals("y")) {
         totalWinnings += oneBet(scan);
         System.out.printf("Your total winnings are $%,.2f\n", totalWinnings);
         System.out.printf("Play again? (y/n) ");
         keepPlaying = scan.nextLine();
      }

      System.out.println("Thanks for playing!");
   }

   /**
    * Prompt the user for an integer value and return the result.
    * 
    * @param prompt The prompt to display
    * @param scan An initialized scanner object
    * @return The int value entered by the user
    */
   public static int readInt(String prompt, Scanner scan) {
      int input;
      System.out.print(prompt);
      input = scan.nextInt();
      scan.nextLine();
      return input;
   }

   /**
    * Handle one betting interaction with the user. This method will ask the
    * user what bet they want to place, and how much they want to wager.
    * 
    * @param scan -  Initialized scanner object
    * @return Amount that the player won or lost on the bet
    */
   public static double oneBet(Scanner scan) {
      Dice dice;
      dice = new Dice();
      int betType;
      int number;
      String betPrompt;
      double betAmount;
      double payout;

      number = -1; // This will be ignored unless the bet is SINGLE

      betPrompt = "\nWhat is your bet?\n"
                  + ChuckALuck.SINGLE + "- Single\n"
                  + ChuckALuck.TRIPLE + "- Triple\n"
                  + ChuckALuck.BIG + "- Big\n"
                  + ChuckALuck.SMALL + "- Small\n"
                  + ChuckALuck.FIELD + "- Field\n"
                  + "Bet: ";

      betType = readInt(betPrompt, scan);

      if (betType == ChuckALuck.SINGLE) {
         number = readInt("Which number will you bet? (1-6) ", scan);
      }

      System.out.print("How much do you want to bet? ");
      betAmount = scan.nextDouble();
      scan.nextLine();

      System.out.println("You rolled: " + dice.getFirst() + " "
                  + dice.getSecond() + " " + dice.getThird());

      payout = ChuckALuck.calculatePayout(dice, betType, number, betAmount);

      if (payout <= 0) {
         System.out.println("Better luck next time!");
      } else {
         System.out.printf("Congratulations! You won $%,.2f!\n", payout);
      }

      return payout;
   }

}
