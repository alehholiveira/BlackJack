public class Card {
   private final String face; // face of card ("Ace", "Deuce", ...)
   private final String suit; // suit of card ("Hearts", "Diamonds", ...)

   // two-argument constructor initializes card's face and suit
   public Card(String face, String suit) {
      this.face = face;
      this.suit = suit;
   }

   // return the face of the card
   public String getFace() {
      return face;
   }

   // return String representation of Card
   public String toString() {
      return face + " de " + suit;
   }
} // end class Card
