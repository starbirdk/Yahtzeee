import java.util.Scanner;

public class YahtzeeGame
{
	/* instance data should include the five yahtzee dice, a scoreboard, and a CONSTANT (static final) variable NUM_SIDES
	which should be set to six (the number of sides on a yahtzee die) */
    private YahtzeeDie die1;
    private YahtzeeDie die2;
    private YahtzeeDie die3;
    private YahtzeeDie die4;
    private YahtzeeDie die5;
    private YahtzeeScorecard card;
    private static final int NUM_SIDES = 6;

    Scanner s = new Scanner(System.in);


    /* initializes the dice and scoreboard */
    public YahtzeeGame() {
		die1 = new YahtzeeDie(NUM_SIDES);
        die2 = new YahtzeeDie(NUM_SIDES);
        die3 = new YahtzeeDie(NUM_SIDES);
        die4 = new YahtzeeDie(NUM_SIDES);
        die5 = new YahtzeeDie(NUM_SIDES);
        card = new YahtzeeScorecard();
    }

    /* check to see if the game is over, and while the game is not over call the method takeTurn()
    once the game ends (hint: there are 13 turns in a game of Yahtzee), the method should print a
    final scorecard and return the grand total */
    public int playGame()
    {
		for(int i = 0; i < 13; i++){
            takeTurn();
        }
        card.printScoreCard();
        return card.getGrandTotal();
    }

    /* 	1. call rollDice()
        2. call printDice()
        3. Ask the user if they are satisfied with their roll, or if they would like to roll again.
           If they are not satisfied continue, otherwise call markScore()
        4. call chooseFrozen()
        5. call rollDice()
        6. call printDice()
        7. Ask the user if they are satisfied with their roll, or if they would like to roll again.
           If they are not satisfied continue, otherwise call markScore()
        8. call chooseFrozen()
        9. call rollDice()
        10. call printDice()
        11. call markScore()
    */
    private void takeTurn()
    {
		rollDice();
        printDice();
        System.out.println("Are you satisfied with your roll, or would you like to roll again? (s/r)");
        String sr = s.nextLine();
        int i = 1;
        while (sr.equals("r") && (i < 3)){
            chooseFrozen();
            rollDice();
            printDice();
            if (i < 2){
                System.out.println("Are you satisfied with your roll, or would you like to roll again? (s/r)");
                sr = s.nextLine();
            }
            i++;
        }
        markScore();
        card.printScoreCard();
    }

    /* Rolls all unfrozen dice.  Also resets all dice to the unfrozen state after the roll */
    private void rollDice()
    {
		if (!die1.isFrozen()){
            die1.rollDie();
        } if (!die2.isFrozen()){
            die2.rollDie();
        }if (!die3.isFrozen()){
            die3.rollDie();
        }if (!die4.isFrozen()){
            die4.rollDie();
        }if (!die5.isFrozen()){
            die5.rollDie();
        }

        die1.unfreezeDie();
        die2.unfreezeDie();
        die3.unfreezeDie();
        die4.unfreezeDie();
        die5.unfreezeDie();
    }

    /* Asks the user which dice should be frozen 1-5 (should be read in in one line) */
    private void chooseFrozen()
    {
        boolean runAgain = false;
        do{
            System.out.println("Which die would you like to be frozen? ");
            String dieChosen = s.nextLine();
            if(dieChosen.contains("1")){
                die1.freezeDie();
            } if(dieChosen.contains("2")){
                die2.freezeDie();
            } if(dieChosen.contains("3")){
                die3.freezeDie();
            } if(dieChosen.contains("4")){
                die4.freezeDie();
            } if (dieChosen.contains("5")){
                die5.freezeDie();
            } else if (!dieChosen.contains("1") && !dieChosen.contains("2") && !dieChosen.contains("3")
                    && !dieChosen.contains("4") && !dieChosen.contains("5") && !dieChosen.equals("")){
                System.out.println("please select a valid die");
                runAgain = true;
            }
        }while (runAgain);
    }

    /* Should print the value of all five dice separated by a tab (\t) to the console */
    private void printDice()
    {
        System.out.println(die1.getValue() + "\t" + die2.getValue() + "\t" + die3.getValue() + "\t" + die4.getValue() + "\t" + die5.getValue());
    }

    /* 1. Print a scoreboard
       2. Ask the user where they would like to mark their score.
       3. Call appropriate function
       4. If false is returned the user entered an invalid number, so ask the user to try again	*/
    private void markScore()
    {
		card.printScoreCard();
        boolean tryAgain = false;
        do{
            System.out.println("1.ones \t 2.twos \t 3.threes \t 4.fours \n" +
                    "5.fives \t 6.sixes \t 7.three of a kind \n 8.four of a kind \t" +
                    "9.full house \t 10.small straight \n 11.large straight \t 12.yahtzee \t 13.chance");
            int ans = s.nextInt();
            s.nextLine();
            switch (ans){
                case 1: card.markOnes(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 2: card.markTwos(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 3: card.markThrees(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 4: card.markFours(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 5: card.markFives(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 6: card.markSixes(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 7: card.markThreeOfAKind(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 8: card.markFourOfAKind(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 9: card.markFullHouse(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 10: card.markSmallStraight(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 11: card.markLargeStraight(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 12: card.markYahtzee(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                case 13: card.markChance(die1.getValue(), die2.getValue(), die3.getValue(), die4.getValue(), die5.getValue());break;
                default:
                    System.out.println("invalid option, please try again ");
                    tryAgain = true; break;
            }
        }while (tryAgain);
    }
}