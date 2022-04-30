package com.taha;

public class Main extends Mastermind {

    public Main(){

    }

    public static String printBanner(){
            return """
                                          _                      _           _\s
                                         | |                    (_)         | |
                      _ __ ___   __ _ ___| |_ ___ _ __ _ __ ___  _ _ __   __| |
                     | '_ ` _ \\ / _` / __| __/ _ \\ '__| '_ ` _ \\| | '_ \\ / _` |
                     | | | | | | (_| \\__ \\ ||  __/ |  | | | | | | | | | | (_| |
                     |_| |_| |_|\\__,_|___/\\__\\___|_|  |_| |_| |_|_|_| |_|\\__,_|
                                                                              \s
                                                                              \s
                                        
          
                    """;

    }

    public static void main(String[] args) {
        System.out.println(printBanner()); // Prints the banner.
        Mastermind game = new Mastermind(); // Creates a new Mastermind object called game. This will run the constructor and the constructor of the Mastermind class and the constructor will set up a new game.
    }
}
