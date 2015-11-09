package com.HebertCarbonel;

import java.util.*;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Game_Crazy_Eight  {
    //Using my Deck class and a deck object
    private Deck deck = new Deck();
    // myhand for player or me
    private Hand myHand = new Hand();
    // computer hand
    private Hand computerHand = new Hand();

    //the discard table
    private Card discard;

    // this is going to be the pile on the table with all the cards played
    private ArrayList<Card> discardPile = new ArrayList<>();
    //to pick a random Card
    private Random rand = new Random();
    //Initialize to a blank space , so later can take any suit
    private char activeSuit = ' ';


    private int countHearts = 0 ;
    private int countDiammonds = 0 ;
    private int countSpades = 0 ;
    private int countClubs = 0 ;

    //Constructor
    public Game_Crazy_Eight() {

        //This will get 7 cards for the computer
        // it will be added to the computer hand
        for (int i = 0; i < 7; i++) {
            Card card1 = deal();
            myHand.add(card1);
            Card card2 = deal();

            computerHand.add(card2);

        }
        //turn up de discard
        discard = deal();
        // if discard is 8 , set the active suit
        //otherwise set it to a blank
        if (discard.getRank() == '8') {
            activeSuit = discard.getSuit();
        } else {
            activeSuit = ' ';
        }
        // This will be to take turns at the beginning of the game
        int turn = rand.nextInt(2);
        if (turn == 1) {
            System.out.println("Computer goes first");
            playComputerCard();
        } else {
            System.out.println("You go first ");
        }
        //play until either of us is out of cards
        boolean done = false;
        // While we still have cards
        while (!done) {
            playMyCard();
            //are there any cards lefts in my hands
            if (myHand.size() == 0) {
                done = true;
            } else {
                playComputerCard();
                ////are there any cards lefts in the computer hands ?
                if (computerHand.size() == 0) {
                    done = true;
                }
            }
        }
        System.out.println("---------------");
        //who played all their cards
        if (myHand.size() == 0) {
            System.out.println("Congrats ! you Won! ...the computer still had " + computerHand.size() + "cards .");
        } else {
            System.out.println("Sorry, You lost . You still had " + myHand.size() + " cards.");
            System.out.println("My hand : " + myHand);
            System.out.println("Discard : " + discard);
        }
    }
// this method it is going to return a card
    private Card deal() {
        // if end of the deck ,then reuse the discard pile and shuffle it
        if (deck.size() == 0) {
            // reuse the cards from the discard pile
            deck.reuse(discardPile);
            // shuffle
            deck.Shuffle();
            //Clear the discard pile
            discardPile.clear();
            System.out.println("\n Re-Shuffle the discard pile");
        }
        //Deal a card from the deck and return it
        Card card = deck.deal();
        return card;
    }
    // this method will display my hand and computer hands
    private void ShowStatus() {
        System.out.println("\n Computer has " + computerHand.size() + " cards.");
        System.out.println("My Hand : " + myHand);
        System.out.println("Discard :" + discard);
        if (discard.getRank() == '8') {
            System.out.println("Suit is " + activeSuit);
        }
    }

// This method is to Draw a card when you need it
    private void drawMyCard() {
        //deal a card from the deck and assign it to drew card
        //and printed nad added to my hand
        Card drewCard = deal();
        System.out.println("\n You drew " + drewCard);
        myHand.add(drewCard);

        // if I can play my draw card , play it
        // 'isValid'  only acept String as an argument ,
        // that is why I convert my drewCard into String to Check it
        if (isValidPLay(drewCard.toString())) {
            System.out.println("You played " + drewCard);
            //and discard the card
            discardMyCard(drewCard);
        }

    }
    // this method is to remove a card from the hand
    // this card wil be added to the discard pile
    private void discardMyCard(Card mycard) {
        //removing the card from my hand
        myHand.remove(mycard);
        //adding to card to a discard pile
        discardPile.add(discard);
        discard = mycard;
        // if the card  is an 8 , we prompt from the suit method
        if (mycard.getRank()=='8'){
            activeSuit= promptforSuit();
        }


    }
        // takes no parameters and return nothing
    private void playMyCard() {
        Scanner scanner = new Scanner(System.in);
        //Showing the status
        ShowStatus();
        boolean validPlay = false;
        //Asking the user to play a card or 'D' for draw a card from the deck
        while (!validPlay) {
            System.out.println("Which card do you want to play (or D to draw?)");
            //Get the player Card or Draw
            String rankSuit = scanner.nextLine();
            // To uppercase the card input
            rankSuit = rankSuit.toUpperCase();
            // if press D , draw a card
            if (rankSuit.equals("D")) {
                drawMyCard();
                validPlay = true;
            }
            //after draw a card
            //else play the card and added to my discard pile
            else if (isValidPLay(rankSuit)) {
                Card selectedCard = new Card(rankSuit);
                // it will be added to the discard pile
                discardMyCard(selectedCard);
                validPlay = true;
            }
        }
    }
    // this is like Validating the CARD
    // this method should return true or false depending is
    // the card is valid or not using isValid function
    private boolean isValidPLay(String rankSuit) {
        boolean validPlay = true;
        Card card = new Card(rankSuit);
        // is it valid (to chekc from the function)
        if (!card.isValid()) {
            System.out.println(rankSuit + " is not a valid Card");
            //NO VALID TO PLAY
            validPlay = false;

        }
        // if the card it's not in my card.....
        else if (!myHand.contains(card)) {
            System.out.println(rankSuit + " is not  your Hand ");
            //NO VALID TO PLAY
            validPlay = false;
        }
        // 8s are always valid . if the card is not an 8
        else if (card.getRank() != '8') {
            //Is the discard an 8?
            if (discard.getRank() == '8') {
                // does the card matching the active suit?
                if (card.getSuit() != activeSuit) {
                    System.out.println(rankSuit + " cannot be played on " + discard + " because the suit was set to " + activeSuit);
                    validPlay = false;
                }
            }
            //if the discard is not an 8
            // if the discard  does not match the rank or suit
            else if (card.getSuit() != discard.getSuit() && card.getRank() != discard.getRank()) {
                System.out.println(rankSuit + " cannot be played on " + discard);
                validPlay = false;
            }
        }

        return validPlay;
    }
    // this should take on parameter 'computercard'
    //Remove the computer card from the computer hand
    // and add it to the discard pile
    private void discardComputerCard(Card computerCard) {
        //Discard its card
        computerHand.remove(computerCard);
        //adding to the discard pile
        discardPile.add(discard);
        //and set discard to the computer card
        discard = computerCard;
        //Setting the active suit to the most cards in computer hands
        if (discard.getRank()=='8'){
            int highestCount = countHearts ;
            activeSuit= 'H';
            if (countDiammonds >highestCount){
                highestCount =  countDiammonds ;
                activeSuit = 'D' ;

            }
            if (countClubs> highestCount){
                highestCount = countClubs;
                activeSuit = 'C';

            }
            if (countSpades> highestCount){
                highestCount = countSpades;
                activeSuit = 'S';
            }
        }
    }
    // this method is for the computer to play a card
    private void playComputerCard() {
        // to display Computer Cards
        System.out.println("Computer Hand : " + computerHand);
        // Making an array list of playable cards
        ArrayList<Card> playableCards = new ArrayList<>();
        ArrayList<Card> eights = new ArrayList<>();
        countHearts = 0 ;
        countDiammonds = 0 ;
        countSpades = 0 ;
        countClubs = 0 ;
        //Count 8s and number of each suit
        for (int i=0 ; i<computerHand.size() ; i++){
            Card card = computerHand.cardAt(i);
            //if 8 , save it
            if (card.getRank() == '8'){
                eights.add(card);

            }
            //otherwise , count the number odf each suits
            else {
                switch (card.getSuit()){
                    case 'H':
                        countHearts++;
                        break;
                    case 'D':
                        countDiammonds++;
                        break;
                    case 'C':
                        countClubs++;
                        break;
                    case 'S':
                        countSpades++;
                        break;
                }
            }
        }
        //To  make a list of playable cards to check
        for (int i = 0; i < computerHand.size(); i++) {
            Card card = computerHand.cardAt(i);
            //if discard is an 8 , all cards of active suit are playable
            if (discard.getRank()=='8'){
                if (card.getSuit()==activeSuit){
                    playableCards.add(card);
                }
            }//else , if discard is not an 8
            // only CARDS same suit or ranked are playable
            else  if (card.getSuit() == (discard.getSuit()) || card.getRank() == (discard.getRank())) {
                playableCards.add(card);
                }
        }
        //to pick a Random playable card
        int numberOfPLayableCard = playableCards.size();
        if (numberOfPLayableCard > 0) {
            int pick = rand.nextInt(numberOfPLayableCard);
            Card playedCard = playableCards.get(pick);
            discardComputerCard(playedCard);
        }
        //otherwise  , if I have an 8 , play and 8
        else if (eights.size()>0){
            Card playedCard = eights.get(0);
            discardComputerCard(playedCard);
        }
        //if nothing could play , draw a card
        else {
            Card drewCard = deal();
            computerHand.add(drewCard);
            System.out.println("\n Computer drew a card");

            //if it plays , plays it
            if (drewCard.getSuit() == discard.getSuit() || drewCard.getRank() == discard.getRank()) {
                discardComputerCard(drewCard);
            }
        }
    }

    //This method will ask the user or computer is they have an eight
    //It will also return the char for the suit
    private char promptforSuit() {

        char suit = ' ';
        boolean validSuit = false;
        while (!validSuit) {
            //call the the method promptForChar from the class Change of suits
            suit = ChangeofSuits.promptForChar("Change the suit to H , D , C , or S?");
            //getting the user input and set it to the suit
            suit = Character.toUpperCase(suit);
            if (Card.isValidSuit(suit)) {
                validSuit = true;
            }
        }
        //retuning suit
        return suit;
    }


}


