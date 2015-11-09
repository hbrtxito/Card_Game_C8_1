package com.HebertCarbonel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Deck {
    //Array List to hold my deck of Cards (made of The CARD class)
    private ArrayList<Card> cards = new ArrayList<>();
    //Random number to shuffle my deck
    private Random rn_number =  new Random();


    public Deck(){
        // this is a loop to create 52 cards and adding to my array list
        for (int i =0 ; i<52 ; i++){
            Card card = new Card(i);
            cards.add(card);
        }
        // it is going to shuffle at the end of a deck
        Shuffle();

    }
    public String toString(){
        //This method is going to loop from my size of the array list and
        // add a Space beetwen them
        String deckString = "";
        for (int i =0 ; i<cards.size() ; i++){
            Card card = cards.get(i) ;
            deckString += card + " ";
        }
        return deckString ;
    }
    //to Shuffle the deck
    public void Shuffle (){
        ArrayList <Card> shuffle = new ArrayList<>();
        // to get how many time is going to loop
        int numberOfTimes = cards.size();

        for (int i = 0 ; i<numberOfTimes ; i++){
            int deckSize = cards.size();
            int pick = rn_number.nextInt(deckSize);
            // for each card in deck  , remove a random card and add to my shuffle deck
            Card card = cards.remove(pick);
            shuffle.add(card);
        }
        //set cards to the value of shuffle
        cards = shuffle ;
    }
    //TO remove a card of deck and returned
    public  Card deal () {
        Card card = cards.remove(0);
        return card ;
    }
    //This method will reuse the first discard pile and make into a deck
    public void reuse (ArrayList <Card> newCards){

        cards = newCards;
    }

    // This is just to show how many cards are in the deck
    public int size(){
        return cards.size();
    }
}
