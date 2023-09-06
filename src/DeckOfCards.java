import java.security.SecureRandom;

import javax.print.event.PrintEvent;

public class DeckOfCards {
    private Card[] deck; // array of Card objects
    private int currentCard; // index of next Card to be dealt (0-51)
    private static final int NUMBER_OF_CARDS = 52; // constant # of Cards
    // random number generator
    private static final SecureRandom randomNumbers = new SecureRandom();

    // constructor fills deck of Cards
    public DeckOfCards() {
        String[] faces = { "Ás", "Dois", "Três", "Quatro", "Cinco", "Seis",
                "Sete", "Oito", "Nove", "Dez", "Valete", "Dama", "Rei" };
        String[] suits = { "Copas", "Ouros", "Paus", "Espada" };

        deck = new Card[NUMBER_OF_CARDS]; // create array of Card objects
        currentCard = 0; // first Card dealt will be deck[0]

        // populate deck with Card objects
        for (int count = 0; count < deck.length; count++)
            deck[count] = new Card(faces[count % 13], suits[count / 13]);
    }

    public Card[] getCurrentCard() {
        return deck;
    }

    // shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        // next call to method dealCard should start at deck[0] again
        currentCard = 0;

        // for each Card, pick another random Card (0-51) and swap them
        for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        }
    }

    // deal one Card
    public Card dealCard() {
        // determine whether Cards remain to be dealt
        if (currentCard < deck.length)
            return deck[currentCard++]; // return current Card in array
        else
            return null; // return null to indicate that all Cards were dealt
    }

    public int ValueCard(Card cartas[], int count) {
        int soma = 0;

        for (int currentCard = 0; currentCard < count; currentCard++) {
            switch (cartas[currentCard].getFace()) {
                case "Ás":
                    soma += 1;
                    break;
                case "Dois":
                    soma += 2;
                    break;
                case "Três":
                    soma += 3;
                    break;
                case "Quatro":
                    soma += 4;
                    break;
                case "Cinco":
                    soma += 5;
                    break;
                case "Seis":
                    soma += 6;
                    break;
                case "Sete":
                    soma += 7;
                    break;
                case "Oito":
                    soma += 8;
                    break;
                case "Nove":
                    soma += 9;
                    break;
                case "Dez":
                case "Valete":
                case "Dama":
                case "Rei":
                    soma += 10;
                    break;
                default:
                    soma += 0;
                    break;
            }
        }
        return soma;
    }

}