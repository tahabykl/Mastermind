package com.taha;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class Mastermind {
    int numDigit = 4; // Number of digits in the secret
    int guess; // Guess made by the player
    int secret; // The computer-generated random number
    int guessCount = 0; // Number of guesses made by the player
    int correctDigitCount; // Number of correct digits in the guess
    int difficulty; // Difficult level
    String strGuess; // The String version of the variable "guess". I used it to compare indices
    String strSecret; // The String version of the variable "secret". I used it to compare indices
    String correctGuess; // Takes the correct digits of the guess and passes them to the ArrayList named "correct"
    String game; // Determines if the game is starting or not
    Scanner randomSecret; // For the user input

    ArrayList<String> correctDigits = new ArrayList<>(); // The ArrayList containing the correct digits

    public Mastermind(){ // The constructor for the Mastermind class. Asks the user if they want to start a new Mastermind game or not.
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

    public void generateSecret(int numDigit){ // Generates a random integer that has numDigit digits

        randomSecret = new Scanner(System.in);
        Random rand = new Random();

        secret = rand.nextInt((int) Math.pow(10, numDigit));
        strSecret = String.valueOf(secret);
    }

    //setUpGame

    public void setUpGame() {
        Scanner diff = new Scanner(System.in);
        System.out.print("Select a difficulty level: \n \n 1-Easier Than Easy \n 2-Standart \n 3-Expert \n \n -> ");
        difficulty = Integer.parseInt(diff.nextLine());

        this.newGame(difficulty); // The newGame method is called here
    }

    public void newGame(int difficulty){

        if (difficulty == 1)
            numDigit = 3;
        else if (difficulty == 2)
            numDigit = 4;
        else if (difficulty == 3)
            numDigit = 5;

        generateSecret(numDigit);

        for (int i = 0; i < numDigit; i++) {
            correctDigits.add("X"); // Adds "X"s to the ArrayList correctDigits to indicate the digits that are not guessed correctly
        }

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

            guessCount ++; // Increments the guess count by one

            if (guess != secret) {
                for (int i = 0; i < numDigit; i++) {
                    if (strGuess.charAt(i) == strSecret.charAt(i)) { // If not all the digits are guessed correctly, the following lines will run to only print the correct digits
                        correctDigitCount ++; // Increment the correct digit count by one
                        correctGuess = String.valueOf(strGuess.charAt(i)); // Storing the correct digit in a variable called correctGuess
                        correctDigits.set(i, correctGuess); // Setting the value of  i'th index of the ArrayList named correct
                    }
                }

                System.out.println("You couldn't guess it all correct but you got " + correctDigitCount + " characters correct."); // Prints the number of digits guessed correctly

                correctDigitCount = 0;

                for (String i : correctDigits) // If the user did not guess all the digits correctly, prints out the digits that are guessed correctly
                {
                    System.out.print(i);
                }

                System.out.print("\n");

            }

            correctDigits.clear();

            if (guess == secret) { // checks if the guess count is lower than or equal to 3, or is in the interval (3,10], or greater than 10

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
