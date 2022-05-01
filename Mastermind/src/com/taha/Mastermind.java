package com.taha;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class Mastermind {
    int numDigit = 4; // Number of digits in the secret
    int guess; // Guess made by the player
    int guessCount = 0; // Number of guesses made by the player
    int correctDigitCount; // Number of correct digits in the guess
    String strGuess; // The String version of the variable "guess". I used it to compare indices
    String strSecret; // The String version of the variable "secret". I used it to compare indices
    String correctGuess; // Takes the correct digits of the guess and passes them to the ArrayList named "correct"
    String game; // Determines if the game is starting or not

    ArrayList<String> correctDigits = new ArrayList<>(); // The ArrayList containing the correct digits

    public Mastermind(){
        Scanner startGame = new Scanner(System.in);
        System.out.print("Do you want to play MasterMind ❓ (y/N) ");
        game = startGame.nextLine();
        if (game.equals("y") || game.equals("Y")) {
            this.setUpGame();
        }
        else {
            System.out.println("Hope to see you play Mastermind next time!");
        }
    }

    public void resetVars(){
        numDigit = 4; // Clears the value of number of digits in the secret for the new game
        guess = 0; // Clears the value of guess made by the player for the new game
        guessCount = 0; // Clears the value of number of guesses made by the player for the new game
        correctDigitCount = 0; // Clears the value of number of correct digits in the guess for the new game
        correctDigits.clear(); // Clears the ArrayList containing the correct digits for the new game
    }

    public void playAgain(){
        resetVars();
        System.out.println();

        Scanner startGame = new Scanner(System.in);
        System.out.print("Do you want to play again ❓ (y/N) ");
        game = startGame.nextLine();
        if (game.equals("y") || game.equals("Y")) {
            this.setUpGame();
        }
        else {
            System.out.println("Goodbye!");
        }
    }

    //setUpGame

    public void setUpGame() {
        Scanner randomSecret = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter the number of digits of the secret: ");
        numDigit = Integer.parseInt(randomSecret.nextLine());

        for (int i = 0; i < numDigit; i++) {
            correctDigits.add("X"); // Adds "X"s to the ArrayList correctDigits to indicate the digits that are not guessed correctly
        }

        int secret = rand.nextInt((int) Math.pow(10, numDigit)); // Generates a random integer within the specified range
        strSecret = String.valueOf(secret);

        this.newGame(randomSecret, numDigit, secret); // The newGame method is called here
    }

    //newGame

    public void newGame(Scanner randomSecret, int numDigit, int secret){
        while (true) {
            while (true) {
                System.out.print("Guess the number: ");
                strGuess = randomSecret.nextLine();
                if (strGuess.length() == numDigit) {
                    guess = Integer.parseInt(strGuess);
                    break;
                } else {
                    System.out.println("Please enter a " + numDigit + " digit number.");
                }
            }

            guessCount ++;

            if (guess != secret) {
                for (int i = 0; i < numDigit; i++) {
                    if (strGuess.charAt(i) == strSecret.charAt(i)) { // If not all the digits are guessed correctly, the following lines will run to only print the correct digits
                        correctDigitCount ++; // Increment the correct digit count by one
                        correctGuess = String.valueOf(strGuess.charAt(i)); // Storing the correct digit in a variable called correctGuess
                        correctDigits.set(i, correctGuess); // Setting the value of  i'th index of the ArrayList named correct
                    }
                }

                System.out.println("You couldn't guess it all correct but you got " + correctDigitCount + " characters correct."); // Prints the number of digits guessed correctly

                for (String i : correctDigits) // If the user did not guess all the digits correctly, prints out the digits that are guessed correctly
                {
                    System.out.print(i);
                }

                System.out.print("\n");

            }

            if (guess == secret) {
                if (guessCount <= 3){
                    System.out.println("You've become a Mastermind!");
                    System.out.println("And wow! It took you only " + guessCount + " guesses! \uD83D\uDE0E");
                    break;
                }
                else if (guessCount <= 10){
                    System.out.println("You've become a Mastermind!");
                    System.out.println("It took you " + guessCount + " guesses! \uD83D\uDE0E Try harder next time!");
                    break;
                }
                else {
                    System.out.println("You've become a Mastermind!");
                    System.out.println("It took you " + guessCount + " guesses! What rotten luck! \uD83D\uDE10");
                    break;
                }

            }
        }

        playAgain();

    }
}
