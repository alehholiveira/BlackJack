import java.util.Arrays;
import java.util.Scanner;

public class DeckOfCardsTest {
   // execute application
   public static void main(String[] args) {
      clearConsole();
      Scanner input = new Scanner(System.in);
      int count = 2;

      System.out.println("Seja bem vindo ao nosso BLACKJACK!!");
      System.out.print("Quantas pessoas irão jogar? ");
      int numPlayers = input.nextInt();
      Card[][] playerHands = new Card[numPlayers][];

      DeckOfCards baralho = new DeckOfCards();
      baralho.shuffle();
      for (int jogador = 0; jogador < numPlayers; jogador++) {

          System.out.printf("Jogador %d: ", jogador + 1);
          playerHands[jogador] = jogo(count, baralho, jogador);

      }
        // Determinar o jogador vencedor
        int maiorPontuacao = -1; // Inicialize com um valor negativo
        int jogadorVencedor = -1;

        for (int jogador = 0; jogador < numPlayers; jogador++) {
            int soma = baralho.ValueCard(Arrays.copyOf(playerHands[jogador], count), count);
            if (soma <= 21 && soma > maiorPontuacao) {
                maiorPontuacao = soma;
                jogadorVencedor = jogador;
            }
        }

        if (jogadorVencedor != -1) {
            System.out.printf("Jogador %d venceu com %d pontos!%n", jogadorVencedor + 1, maiorPontuacao);
        } else {
            System.out.println("Nenhum jogador venceu. Todos estouraram o limite.");
        }
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

   public static Card[] jogo(int count, DeckOfCards baralho, int jogador) {
      Scanner input = new Scanner(System.in);
      int verificadorsim;
      int novacarta = 0;
      int soma = 0;

      // Crie um array de cartas para representar a mão do jogador
      Card[] playerHand = new Card[51];

      System.out.printf("Carta inicialmente distribuídas -> ");
      for (int i = 0; i < count; i++) {
         playerHand[i] = baralho.dealCard(); // Distribua cartas para a mão do jogador
         System.out.printf(" | %s |", playerHand[i]);
      }
      
      do {
         Card[] playerCards = Arrays.copyOf(playerHand, count); // Copie o array da mão do jogador
         soma = baralho.ValueCard(playerCards, count); // Corrige a chamada do método
         System.out.printf(" -  Pontuação: %d%n", soma); // Corrige a formatação da saída
         if (soma > 21) {
            System.out.printf("Você estourou o limite!!%n%n");
            break; // Saia do loop se a soma for maior que 21
         }
         if (soma == 21) {
            System.out.println("Você atingiu 21!!%n%n");
            break; // Saia do loop se a soma for maior que 21
         }
         verificadorsim = 0;
         System.out.printf("Jogador %d: Deseja receber mais uma carta? ", jogador + 1);
         String option = input.nextLine();
         String optionUP = option.toLowerCase();
         System.out.println();
         if (optionUP.equals("sim")) {
            novacarta++;
            playerHand[count] = baralho.dealCard(); // Distribua uma carta adicional
            System.out.printf("Jogador %d: Nova carta distribuída -> | %s |", jogador + 1, playerHand[count]);
            System.out.printf("%nJogador %d: Cartas atualmente distribuídas -> ", jogador + 1);
            for (int i = 0; i < count + 1; i++) {
               System.out.printf(" | %s |", playerHand[i]);
            }
            verificadorsim++;
            count++;
         }
      } while (verificadorsim != 0);

      return playerHand; // Retorne o array de cartas do jogador
   }
}
