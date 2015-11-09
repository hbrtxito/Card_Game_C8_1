package com.HebertCarbonel;

import java.util.ArrayList;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Hand {

    private ArrayList<Card> cards= new ArrayList<>();

    //This method is to arrange  the card from small to large
    public void add (Card card){
        int index = 0 ;
        boolean done = false ;

        //this is to compare a card to your card in your hand
        // a loop that goes through all the card until it finds it
        while (!done && index <cards.size()){
            Card carInHand = cards.get(index);
            // if the card is greater than your card , index +1
            if (card.isGreaterthan(carInHand)){
                index ++ ;
            }

            else {done = true;}
        }
        cards.add(index ,card);

    }
//This methos is to print the cards
    public String toString (){
        String string ="";

        //This loop will separate the card by spaces is they have diferent suits
        for (int i=0 ; i<cards.size() ; i++){
            Card card = cards .get(i);
            // if this is not the first card , compare it with the previous card.
            // if the suits are different add a couple extra spaces to differentiated in your hand
            if (i >0){
                Card priorCard= cards.get(i-1);
                char priorSuit = priorCard.getSuit();
                char currentSuit = card.getSuit();

                if (priorSuit != currentSuit){
                    string+="  ";
                }
            }
            // here is to add a space between cards that have the same suit
            string+=card.toString() + " ";
        }
        return string ;
    }


    // this method is similar to ADD , but this one remove the card from the hans
    //So it return nothing
    public void remove (Card card){
        //so , it finds 'card' in the hand
        int index =0 ;
        boolean found = false ;
        //loop from the size of all the cards
        while (!found && index < cards.size()){
            Card compareCard = cards.get(index);
            // if it finds it , remove it ..
            //else ...increment the index
            if (compareCard.equals(card)){
                cards.remove(index);
                found = true ;
            }
            else {
                index++;
            }
        }
    }
    // this method is to c if a hand contains a given card
    // this method gets a card and return a boolean
    public  boolean contains (Card card){
        int index =0 ;
        // it should be initialize to false , when it finds the card = 'True'
        boolean contains = false ;
        // we loop trough the size of the cards
        while (!contains && index < cards.size()){
            Card compareCard = cards.get(index);
            //
            if (compareCard.equals(card)){
                // Bug Removed ...
                //cards.remove(index);
                contains = true ;
            }
            else {
                index++;
            }
        }
        return contains ;
    }
    // return a car at index or position in the hand
    // return an Integer call index and return a card
    public Card cardAt (int i){

        return cards.get(i);
    }

    // to find out how many cards  are in Hand
    public  int size(){
        return cards.size();
    }
}

