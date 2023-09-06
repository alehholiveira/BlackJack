import java.util.Scanner;

public class DeckOfCardsTest
{
   // execute application
   public static void main(String[] args)
   {
      Scanner input = new Scanner(System.in);
      int count = 2;
      DeckOfCards Player1 = new DeckOfCards();
      Player1.shuffle(); // place Cards in random order
      
      // print all 52 Cards in the order in which they are dealt
      System.out.printf("Carta inicialmente distribuidas -> ");
      for (int i = 1; i <= 2; i++)
      {
         System.out.printf(" | %s |", Player1.dealCard());
         
      } 
      System.out.printf("%nDeseja receber mais uma carta? ");
      String option = input.nextLine();
      if(option.equals("SIM"))
      {
         count++;
      }
      else
      {
         Card[] playerCards = Player1.getCurrentCard(); // Obtém as cartas do jogador
         int soma = Player1.ValueCard(playerCards, count); // Corrige a chamada do método

         System.out.printf("Soma das cartas: %d%n", soma); // Corrige a formatação da saída
      }



      input.close();
   } 
} // end class DeckOfCardsTest
