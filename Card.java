package com.HebertCarbonel;

/**
 * Created by hbrtxito on 11/1/2015.
 */
public class Card {
    // Rank A,2,3,4,5,6,7,8,9,T,J,Q,K
    // I'm going to use A=Ace , T=10
    // Suits are H = Hearts
    //           D= Diamonds
    //           C = Clubs
    //           S = Spades

    private char rank = ' ';
    private char suit = ' ';

    // This method is to return a String  (join  rank and suit)
    // Adding a empty String  to convert the characters to a string
    public String toString() {
        String rs = "" + rank + suit;
        return rs;
    }

    //Im doing "static and final"  because  this should never CHANGE
    //Ranks it is for the values of the cards
    //Suits for the suits :  hearts , diamonds Clubs and Spades
    private static final String RANKS = "A23456789TJQK";
    private static final String SUITS = "HDCS";

    // I left my Class  "chars" empty ....
    // Because I want my constructor build my card
    //String "rs" is actually going to be r= rank  and s=suits in one string
    // I'm using "charAT" to put the position of my rand and suit
    public Card(String rs) {
        // this is to make sure that is only 2 characters
        if (rs.length() == 2) {
            // I get rs.chartAT(0)(first position) and  rs.chartAT(1)(second position)
            char r = rs.charAt(0);
            char s = rs.charAt(1);
            //To find out is a character is in a string Im using "indexOf"
            //to return the number of the first occurrence
            // this is going to return -1 if it doesn't find it
            int ri = RANKS.indexOf(r);
            int si = SUITS.indexOf(s);
            //Validation
            if (ri > -1 && si > -1) {
                rank = r;
                suit = s;
            }
        }

    }

    // This Constructor is for creating a integer number card
    //  Im using '%52' .. so I can call 0 to 51  (52 total cards)
    public Card(int id) {
        id = id % 52;
        // this is in case the id is negative ...
        //  this would make it positive
        if (id < 0) {
            id = id * -1;
        }
        //Using Module '%" to get 0-12 (first position of the cards )
        //Using '/' to get 0-3 ( to get the suit of the card)

        rank = RANKS.charAt(id % 13);
        suit = SUITS.charAt(id / 13);

    }

    // getRank() and getSuit() is to get their values from a private instance variables
    public char getRank() {
        return rank;
    }

    public char getSuit() {
        return suit;
    }

    //this BOOLEAN method is To return if a Card is valid
    public boolean isValid() {
        boolean valid = false;
        // a Suit never should be blank
        if (suit != ' ') {
            valid = true;
        }
        return valid;
    }

    //Boolean Method To Compare is this card is equal to another Card
    // get 1 parameter (a card from the class Card)
    public boolean equals (Card card){
        boolean equals = false ;

        if ((card.getSuit()==suit) && (card.getRank() == rank)){
            equals =true ;
        }
        return equals ;
    }
    //To compare is one card is Greater than another card
    // get 1 parameter (a card from the class Card)
    public boolean isGreaterthan (Card card){
        boolean greaterthan = false ;
        //getting the suit and the ranks of the card
        char cardSuit = card.getSuit();
        char cardRank =  card.getRank() ;
        //check if the index of suit in SUITS is greater than indexOf of this card
        //this is based on the order of my suit  and Ranks= "A23456789TJQK"
        if (SUITS.indexOf(suit)> SUITS.indexOf(cardSuit)){
            greaterthan = true ;

        }
        //if both cards has equals suits .. then It will check from their RANKS
        else if (suit == cardSuit) {
            if (RANKS.indexOf(rank)> RANKS.indexOf(cardRank)){
                greaterthan = true ;

            }

        }
        return greaterthan ;
    }
    //MEthos to check if the character is a valid suit
    //Static because it uses from this class the STATIC STRING SUITS
    public static boolean isValidSuit( char c){
        boolean valid = false ;
        if (SUITS.indexOf(c)>-1){
            valid =true ;
        }
        return valid;
    }
}
