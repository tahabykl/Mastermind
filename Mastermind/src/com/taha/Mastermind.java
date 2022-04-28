package com.taha;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;

public class Mastermind {
    int numDigit = 4; // Number of digits in the secret
    int guess; // Guess made by the player
    int guessCount = 0; // Number of guesses made by the player
    int correctCharCount; // Number of correct digits in the guess
    String strGuess; // The String version of the variable "guess". I used it to compare indices
    String strSecret; // The String version of the variable "secret". I used it to compare indices
    String correctGuess; // Takes the correct digits of the guess and passes them to the ArrayList named "correct"
    String game; // Determines if the game is starting or not
    ArrayList<String> correct = new ArrayList<String>(); // The ArrayList containing the correct digits

    public Mastermind(){
        Scanner startGame = new Scanner(System.in);
        System.out.print("Do you wanna play MasterMind ❓ (y/N) ");
        game = startGame.nextLine();
        if (game.equals("y") || game.equals("Y")) {
            this.newGame();
        }
        else {
            System.out.println("Hope to see you play Mastermind next time!");
        }
    }

    public void newGame(){
        Scanner randomSecret = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Enter the number of digits of the secret: ");
        numDigit = Integer.parseInt(randomSecret.nextLine());

        for (int i = 0; i < numDigit; i++){
            correct.add("X");
        }

        int secret = rand.nextInt((int)Math.pow(10, numDigit)); // Generates a random integer within the specified range
        strSecret = String.valueOf(secret);

        while (true) {
            System.out.print("Guess the number: ");
            strGuess = randomSecret.nextLine();
            guess = Integer.parseInt(strGuess);
            guessCount ++;

            if (guess != secret) {
                for (int i = 0; i < numDigit; i++) {
                    if (strGuess.charAt(i) == strSecret.charAt(i)) {
                        correctCharCount ++;
                        correctGuess = String.valueOf(strGuess.charAt(i));
                        correct.set(i, correctGuess);
                    }
                }

                System.out.println("You couldn't guess it all correct but you got " + correctCharCount + " characters correct."); // Prints the number of digits guessed correctly

                for (String i : correct) // If the user did not guess all the digits correctly, prints out the digits that are guessed correctly
                {
                    System.out.print(i);
                }

                System.out.print("\n");

            }

            if (guess == secret) {
                System.out.println("You've become a Mastermind! It took you " + guessCount + " guesses.");
                break;
            }
        }
    }
}
