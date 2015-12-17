import java.util.Scanner;

public class YahtzeeDriver
{
    /*
    1. Creates a new instance of the YahtzeeGame class
    2. Calls the playGame method on the Yahtzee object
    3. Asks user if they would like to play again
    4. When the user is done playing, prints the number of games played, min, max, and average score
    */
    public static void main(String [] args)
    {
        int score = 0;
        Scanner s = new Scanner(System.in);
        YahtzeeGame myGame=new YahtzeeGame();
        System.out.println("Welcome to Katrina's APCSA Yahtzee Game!");
        boolean playAgain = true;
        int gamesPlayed = 0;
        double totalScore = 0;
        int min = 999;
        int max = -999;

        while (playAgain) {
            if (gamesPlayed == 0){
                score = myGame.playGame();
            }else{
                myGame = new YahtzeeGame();
                score = myGame.playGame();
            }
            if (score < min){
                min = score;
            }if (score > max){
                max = score;
            }
            System.out.println("Would you like to play again? (yes/no) ");
            String answer = s.nextLine();
            if (answer.equals("no")){
                playAgain = false;
            }
            gamesPlayed++;
            totalScore += score;
        }
        System.out.println("Games Played: " + gamesPlayed);
        System.out.println("Minimum Score: " + min);
        System.out.println("Maximum Score: " + max);
        System.out.println("Average Score: " + totalScore/gamesPlayed);
    }
}
