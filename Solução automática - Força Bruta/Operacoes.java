//Importa biblioteca
import java.util.Random;

//Clase Operacoes
public class Operacoes {
    static Random gerador = new Random();

    //Operação para imprimir as três pilhas
    public static void imprimePilhas(){
        System.out.println("Total de Movimentos: " + Main.movimentos);
        System.out.print("Pilha 1: ");
        Main.pilha1.imprime();
        System.out.print("Pilha 2: ");
        Main.pilha2.imprime();
        System.out.print("Pilha 3: ");
        Main.pilha3.imprime();
    }

    //Operação de mover o topo entre as pilhas
    public static void moveTopoPilhaToPilha(Pilha pilhaA, Pilha pilhaB){
        //Verifica se a pilhaA possuí topo antes de mover o mesmo para a pilhaB
        if (pilhaA.getTopo() != null){

            //Salva o topo da pilhaA em uma variável auxiliar para poder realizar manipulações
            int informacaoA = pilhaA.getTopo().getInformacao();

            //Inicializa a variável informaçãoB com o mesmo valor da informação A
            int informacaoB = informacaoA;

            //Salva o topo da pilhaB em uma variável auxiliar para poder realizar manipulações
            if (pilhaB.getTopo() != null){
                informacaoB = pilhaB.getTopo().getInformacao();
            }

            //Modo crescente
            if (Main.mode == 1){
                if (informacaoA <= informacaoB){
                    //Remove o topo da pilha
                    pilhaA.pop();

                    //Adiciona o topo antigo da pilhaA para a pilhaB
                    pilhaB.push(informacaoA);

                    //Adiciona um movimento
                    Main.movimentos++;

                    //Verifica a vitória na pilha que recebeu o push()
                    verificaVitoria(pilhaB);

                }else{
                    System.out.println("Erro! O topo a ser enviado para pilha deve ser menor ou igual a " + informacaoB);
                }
            //Modo decrescente
            }else{
                if (informacaoA >= informacaoB){
                    //Remove o topo da pilha
                    pilhaA.pop();

                    //Adiciona o topo antigo da pilhaA para a pilhaB
                    pilhaB.push(informacaoA);

                    //Adiciona um movimento
                    Main.movimentos++;

                    //Verifica a vitória na pilha que recebeu o push()
                    verificaVitoria(pilhaB);

                }else{
                    System.out.println("Erro! O topo a ser enviado para pilha deve ser maior ou igual a " + informacaoB);
                }
            }
        }else{
            System.out.println("Não há topo para se mover!");
        }
    }

    //Operação para verificar a condição de vitória em uma jogada
    public static void verificaVitoria(Pilha pilha){
        //Só verifica a vitória se a pilha estiver cheia
        if (pilha.cheia() == true){
            //Contador como variável auxiliar
            int contador = 0;

            //Variáveis de auxílio para lógica
            Node topoBefore = pilha.getTopo();
            Node topoNow = pilha.getTopo();

            //Implementação da lógica do loop para pontuar o contador
            while (topoNow != null){
                //Modo crescente
                if (Main.mode == 1){
                    if(topoBefore.getInformacao() <= topoNow.getInformacao()){
                        contador ++;
                    }
                //Modo decrescente
                }else{
                    if(topoBefore.getInformacao() >= topoNow.getInformacao()){
                        contador ++;
                    }
                }
                //Atualiza os topos que auxiliam a lógica
                topoBefore = topoNow;
                topoNow = topoNow.getProximo();
            }

            //Verifica a vitória baseado na comparação do contador de índices que ficaram ordenados de forma crescente/decrescente com a capacidade da pilha
            if (contador == pilha.getCapacidade()){
                imprimePilhas();
                System.out.println("Ordenação concluída em "  + Main.movimentos + " jogadas");
                System.exit(0);
            }
        }
    }

    //Distribuí os elementos entre as três pilhas para realizar a solução automática
    public static void manipulaPilhaInicio(Pilha pilhaA, Pilha pilhaB, Pilha pilhaC){
        //No início do jogo tentamos tirar os elementos da pilhaA
        while (pilhaA.getTopo() != null){
            //Pega a informação do topo da pilhaA
            int infoA = pilhaA.getTopo().getInformacao();

            //Insere a informação do topo da pilhaA na pilhaB caso ela esteja vazia
            if(pilhaB.getTopo() == null){
                moveTopoPilhaToPilha(pilhaA, pilhaB);
                //Insere a informação do topo da pilhaA na pilhaC caso ela esteja vazia
            } else if(pilhaC.getTopo() == null){
                moveTopoPilhaToPilha(pilhaA, pilhaC);
                //Caso nenhuma pilha tenha topo vazio ele faz o tratamento para movimentar os elementos
            }else{
                break;
            }
        }
    }

    //Operação de solução automática - método de força bruta
    public static void solucaoAutomatica(Pilha pilhaA, Pilha pilhaB, Pilha pilhaC) {
        while (true){
            //Imprime as pilhas
            imprimePilhas();

            //Seleciona uma pilha para mover o topo (0 -> pilhaA, 1 -> pilhaB, 2 -> pilhaC)
            int opcao = gerador.nextInt(3);
            switch (opcao){
                case 0:
                    opcao = gerador.nextInt(2);
                    //Seleciona uma opção para mover o topo da pilhaA para outra pilha(0 -> pilhaB, 1 -> pilhaC)
                    if (opcao == 0){
                        moveTopoPilhaToPilha(pilhaA, pilhaB);
                    }else{
                        moveTopoPilhaToPilha(pilhaA, pilhaC);
                    }
                    break;
                case 1:
                    //Seleciona uma opção para mover o topo da pilhaB para outra pilha(0 -> pilhaA, 1 -> pilhaC)
                    opcao = gerador.nextInt(2);
                    if (opcao == 0){
                        moveTopoPilhaToPilha(pilhaB, pilhaA);
                    }else{
                        moveTopoPilhaToPilha(pilhaB, pilhaC);
                    }
                    break;
                case 2:
                    //Seleciona uma opção para mover o topo da pilhaC para outra pilha(0 -> pilhaA, 1 -> pilhaB)
                    opcao = gerador.nextInt(2);
                    if (opcao == 0){
                        moveTopoPilhaToPilha(pilhaC, pilhaA);
                    }else{
                        moveTopoPilhaToPilha(pilhaC, pilhaB);
                    }
                    break;
            }
        }
    }
}
