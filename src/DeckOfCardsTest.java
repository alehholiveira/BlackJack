import java.util.Scanner;

public class DeckOfCardsTest {
   // execute application
   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);

      int verificadorsim;
      int count = 2;

      DeckOfCards baralho = new DeckOfCards();
      baralho.shuffle(); // place Cards in random order
      // print all 52 Cards in the order in which they are dealt
      clearConsole();
      System.out.printf("Carta inicialmente distribuidas -> ");
      for (int i = 1; i <= 2; i++) {
         System.out.printf(" | %s |", baralho.dealCard());

      }
      do {
         verificadorsim = 0;
         System.out.printf("%nDeseja receber mais uma carta? ");
         String option = input.nextLine();
         String optionUP = option.toLowerCase();
         System.out.println();
         if (optionUP.equals("sim")) {
            System.out.printf("NOVA CARTA DISTRIBUIDA -> | %s |", baralho.dealCard());
            verificadorsim++;
            count++;
         }
      } while (verificadorsim != 0);

      Card[] playerCards = baralho.getCurrentCard(); // Obtém as cartas do jogador

      System.out.print("TODAS AS CARTAS -> | ");
      for (int i = 0; i < count; i++) {
         System.out.printf("%s | ", playerCards[i]);
      }
      int soma = baralho.ValueCard(playerCards, count); // Corrige a chamada do método
      System.out.printf("%nSoma das cartas: %d%n", soma); // Corrige a formatação da saída
      System.out.println(); // Para criar uma nova linha após a impressão das cartas

      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      String option = input.nextLine();
      int count2 = count;


      // print all 52 Cards in the order in which they are dealt
      clearConsole();
      System.out.printf("Carta inicialmente distribuidas -> ");
      for (int i = 1; i <= 2; i++) {
         System.out.printf(" | %s |", baralho.dealCard());

      }
      do {
         verificadorsim = 0;
         System.out.printf("%nDeseja receber mais uma carta? ");
         option = input.nextLine();
         String optionUP = option.toLowerCase();
         System.out.println();
         if (optionUP.equals("sim")) {
            System.out.printf("NOVA CARTA DISTRIBUIDA -> | %s |", baralho.dealCard());
            verificadorsim++;
            count2++;
         }
      } while (verificadorsim != 0);

      Card[] playerCards2 = baralho.getCurrentCard(); // Obtém as cartas do jogador

      System.out.print("TODAS AS CARTAS -> | ");
      for (int i = count; i < count2 + 2; i++) {
         System.out.printf("%s | ", playerCards2[i]);
      }
      int soma2 = baralho.ValueCard(playerCards2, count2); // Corrige a chamada do método
      System.out.printf("%nSoma das cartas: %d%n", soma2); // Corrige a formatação da saída
      System.out.println(); // Para criar uma nova linha após a impressão das cartas

      input.close();
   }

   public static void clearConsole() { // FUNCAO PARA LIMPAR O CONSOLE DO USUARIO
      try {
          final String os = System.getProperty("os.name");

          if (os.contains("Windows")) {
              // Para sistemas Windows
              new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          } else {
              // Para sistemas Unix/Linux/Mac
              System.out.print("\033[H\033[2J");
              System.out.flush();
          }
      } catch (Exception e) {
      }
  }
} // end class DeckOfCardsTest
