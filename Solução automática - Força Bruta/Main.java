//Importa biliotecas
import java.util.Scanner;
import java.util.Random;

//Classe Main
public class Main {

    //Criação da variável de movimentos
    static int movimentos = 0;

    //Variável de modo de jogo: c -> Crescente, d -> Decrescente
    static int mode;

    //Declaração estáticas das pilhas
    static Pilha pilha1 = null;
    static Pilha pilha2 = null;
    static Pilha pilha3 = null;

    public static void main(String[] args) {

        //Cria gerador automático para popular a pilha
        Random gerador = new Random();
        Scanner sc = new Scanner(System.in);

        //Entrada da capacidade das Pilhas
        System.out.println("Insira a capacidade das pilhas: ");
        int capacidade = sc.nextInt();

        //Criação das pilhas de acordo com a capacidade
        pilha1 = new Pilha(capacidade);
        pilha2 = new Pilha(capacidade);
        pilha3 = new Pilha(capacidade);

        //Entrada do modo de jogo
        System.out.println("Insira o modo de jogo(1 -> crescente, 2 -> decrescente): ");
        mode = sc.nextInt();

        //Insere dados aleatórios na pilha
        for (int i = 1; i <= capacidade; i++){
            pilha1.push(gerador.nextInt(101));
        }

        //Verifica a vitória na pilha 1
        Operacoes.verificaVitoria(pilha1);

        //Menu do jogo
         int option = -1;
        while (option != 0){
            Operacoes.imprimePilhas();
            System.out.println("0 -> Sair do jogo");
            System.out.println("1 -> movimentar");
            System.out.println("2 -> solução automática");
            System.out.println("Escolha uma opção:");
            option = sc.nextInt();

            //Trata a opção escolhida
            switch (option){
                //Sair de jogo
                case 0:
                    //Encerra o jogo
                    System.exit(0);
                    break;
                //Solução manual
                case 1:
                    //Imprime operações
                    Operacoes.imprimePilhas();
                    System.out.println("1 -> Topo da pilha 1 para pilha 2");
                    System.out.println("2 -> Topo da pilha 1 para pilha 3");
                    System.out.println("3 -> Topo da pilha 2 para pilha 1");
                    System.out.println("4 -> Topo da pilha 2 para pilha 3");
                    System.out.println("5 -> Topo da pilha 3 para pilha 1");
                    System.out.println("6 -> Topo da pilha 3 para pilha 2");
                    System.out.println("Escolha uma opção: ");
                    option = sc.nextInt();

                    //Trata a opção selecionada
                    switch (option){
                        case 1:
                            Operacoes.moveTopoPilhaToPilha(pilha1, pilha2);
                            break;
                        case 2:
                            Operacoes.moveTopoPilhaToPilha(pilha1, pilha3);
                            break;
                        case 3:
                            Operacoes.moveTopoPilhaToPilha(pilha2, pilha1);
                            break;
                        case 4:
                            Operacoes.moveTopoPilhaToPilha(pilha2, pilha3);
                            break;
                        case 5:
                            Operacoes.moveTopoPilhaToPilha(pilha3, pilha1);
                            break;
                        case 6:
                            Operacoes.moveTopoPilhaToPilha(pilha3, pilha2);
                            break;
                        default:
                            System.out.println("Selecione uma opção válida!");
                            break;
                    }
                    break;
                //Solução automática - método de força bruta
                case 2:
                    Operacoes.manipulaPilhaInicio(pilha1, pilha2, pilha3);
                    Operacoes.solucaoAutomatica(pilha1, pilha2, pilha3);
                    break;
            }
        }


    }

}