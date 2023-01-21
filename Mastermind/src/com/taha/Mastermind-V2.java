package com.taha;

import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    private static final int EASIER_THAN_EASY = 3;
    private static final int STANDARD = 4;
    private static final int EXPERT = 5;
    private static final int MIN_NUM_DIGIT = EASIER_THAN_EASY;
    private static final int MAX_NUM_DIGIT = EXPERT;
    private int numDigit;
    private int secret;
    private int guessCount = 0;
    private int correctDigitCount;
    private int difficulty;
    private Scanner inputScanner;
    private Random rand = new Random();

    public Mastermind(){
        inputScanner = new Scanner(System.in);
        System.out.print("Do you want to play MasterMind ❓ (y/N) ");
        String game = inputScanner.nextLine();
        if (game.equalsIgnoreCase("y")) {
            this.setUpGame();
        } else {
            System.out.println("Hope to see you play Mastermind next time!");
        }
    }

    private void setUpGame() {
        System.out.print("Select a difficulty level: \n \n 1-Easier Than Easy \n 2-Standart \n 3-Expert \n \n -> ");
        difficulty = Integer.parseInt(inputScanner.nextLine());
        if (difficulty == 1) {
            numDigit = EASIER_THAN_EASY;
        } else if (difficulty == 2) {
        
        numDigit = STANDARD;

        } else if (difficulty == 3) {

        numDigit = EXPERT;

        } 

        generateSecret();
        startGame();

    }

    private void generateSecret(){
        secret = rand.nextInt((int) Math.pow(10, numDigit));
    }

    private void startGame(){
        while (true) {

            System.out.print("Guess the number: ");
            int guess = inputScanner.nextInt();
            if (guess < (int) Math.pow(10, numDigit) && guess >= 0) {
                checkGuess(guess);
                guessCount++;
                if (correctDigitCount == numDigit) {
                    System.out.println("You won! It took you " + guessCount + " tries.");
                    playAgain();
                    break;
                }
            } else {
                System.out.println("Invalid input. Please enter a " + numDigit + " digit number.");
            }
        }
    }

    private void checkGuess(int guess) {
        int[] secretArray = Integer.toString(secret).chars().map(c -> c - '0').toArray();
        int[] guessArray = Integer.toString(guess).chars().map(c -> c - '0').toArray();
        correctDigitCount = 0;

        for (int i = 0; i < numDigit; i++) {
            if (guessArray[i] == secretArray[i]) {
                correctDigitCount++;
            }
        }
    }

    private void playAgain(){
        inputScanner.nextLine();
        System.out.println();
        System.out.print("Do you want to play again ❓ (y/N) ");
        String game = inputScanner.nextLine();
        if (game.equalsIgnoreCase("y")) {
            setUpGame();
        } else {
            System.out.println("Goodbye!");
        }
    }

}
