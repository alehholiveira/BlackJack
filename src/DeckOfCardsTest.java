import java.util.Arrays;
import java.util.Scanner;

public class DeckOfCardsTest {
   // execute application
   public static void main(String[] args) {

      clearConsole();
      Scanner input = new Scanner(System.in); // criando um input para as entradas
      int count = 2; // numero de cartas distribuidas inicialmente
      int numPlayers = 0;
      int verificaMaisDeUmVencedor = 0;

      System.out.println("          [------------]    Seja bem vindo ao BLACKJACK    [------------]");
      System.out.println(" -> O 'ás' vale 1 ou 11 pontos (dependendo o que é mais conveniente para o jogador);");
      System.out.println(" -> 'J', 'Q', 'K' valem 10 pontos;");
      System.out.println(" -> As demais cartas têm seu próprio valor;");
      System.out.printf(" -> Os naipes das cartas não têm valor nenhum no jogo.%n");
      numPlayers = getIntInput(input, "Quantas pessoas irão jogar? ");
      int[] ResultadoGame = new int[numPlayers]; // Criando uma variavel int(array) para receber o valor da mão de cada jogador

      DeckOfCards baralho = new DeckOfCards(); // criando um baralho
      baralho.shuffle(); // embaralhando ele aletóriamente
      for (int jogador = 0; jogador < numPlayers; jogador++) {  // for que chama a funcao "jogo", onde se distribui as cartas para o jogador. 
                                                                // critério de parada é o numero de jogares

          System.out.printf("Jogador %d: ", jogador + 1);
          ResultadoGame[jogador] = jogo(count, baralho, jogador); // retorna o resultado da mão do jogador

      }
      // Determinar o jogador vencedor
      int maiorPontuacao = -1; // Inicializa com um valor negativo
      int jogadorVencedor = -1;

      for (int jogador = 0; jogador < numPlayers; jogador++) { // for para reconhecer o vencedor do jogo
         if (ResultadoGame[jogador] <= 21 && ResultadoGame[jogador] > maiorPontuacao) { 
            maiorPontuacao = ResultadoGame[jogador];
            jogadorVencedor = jogador;
         }
      }

      for (int jogador = 0; jogador < numPlayers; jogador++) { // for para reconhecer o vencedor do jogo
         if (maiorPontuacao == ResultadoGame[jogador]) { 
            verificaMaisDeUmVencedor++;
         }
      }

      

      if (jogadorVencedor != -1 && verificaMaisDeUmVencedor == 1) {
         System.out.printf("Jogador %d venceu com %d pontos!%n", jogadorVencedor + 1, maiorPontuacao);
      } 
      if(jogadorVencedor != -1 && verificaMaisDeUmVencedor > 1){
         System.out.printf("Ocorreu um empate!!%n");
      }
      System.out.println();
      input.close();
   }
   
   public static int getIntInput(Scanner input, String prompt) {
      int result = 0;
      boolean inputValid = false;

      while (!inputValid) {
          System.out.print(prompt);

          if (input.hasNextInt()) {
              result = input.nextInt();
              inputValid = true;
          } else {
              System.out.println("Por favor, insira um número válido.");
              input.nextLine(); // Limpa o buffer do teclado
          }
      }

      return result;
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

   public static int jogo(int count, DeckOfCards baralho, int jogador) {
      
      Scanner input = new Scanner(System.in); // criando um input para as entradas
      int verificadorsim;
      int novacarta = 2;
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
            System.out.printf("Você atingiu 21!!%n%n");
            break; // Saia do loop se a soma for maior que 21
         }
         verificadorsim = 0;
         System.out.printf("Jogador %d: Deseja receber mais uma carta? ", jogador + 1);
         String option = input.nextLine();
         String optionUP = option.toLowerCase();
         System.out.println();
         if (optionUP.equals("sim")) {
            playerHand[novacarta] = baralho.dealCard(); // Distribua uma carta adicional
            System.out.printf("Jogador %d: Nova carta distribuída -> | %s |", jogador + 1, playerHand[count]);
            System.out.printf("%nJogador %d: Cartas atualmente distribuídas -> ", jogador + 1);
            for (int i = 0; i < count + 1; i++) {
               System.out.printf(" | %s |", playerHand[i]);
            }
            verificadorsim++;
            count++;
            novacarta++;
         }
      } while (verificadorsim != 0);

      return soma; // Retorne o array de cartas do jogador
      
   }
}
