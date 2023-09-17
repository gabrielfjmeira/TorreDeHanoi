//Clase Operacoes
public class Operacoes {
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

    //Operação que gera o vetor de vitória
    public static void gerarPilhaGabarito(Pilha pilha){
        //Cria e popula vetor para ordenar a fila encadeada
        int contador = 0;
        int vetorOrdenado[];
        vetorOrdenado = new int[pilha.getCapacidade()];

        //Popula o vetorOrdenado com os dados da pilha para serem ordenados posteriormente
        Node atual = pilha.getTopo();
        while (atual != null) {
            vetorOrdenado[contador] = atual.getInformacao();
            atual= atual.getProximo();
            contador ++;
        }

        //Ordena o Vetor vetorOrdenado
        for (int i = 0; i < vetorOrdenado.length; i++) {
            for (int x = 0; x < vetorOrdenado.length; x++) {
                //Ordem crescente
                if (Main.mode == 1){
                    if (vetorOrdenado[x] >= vetorOrdenado[i]) {
                        int menor = vetorOrdenado[i];
                        int maior = vetorOrdenado[x];
                        vetorOrdenado[i] = maior;
                        vetorOrdenado[x] = menor;
                    }
                    //Ordem decrescente
                }else{
                    if (vetorOrdenado[x] <= vetorOrdenado[i]) {
                        int menor = vetorOrdenado[i];
                        int maior = vetorOrdenado[x];
                        vetorOrdenado[i] = maior;
                        vetorOrdenado[x] = menor;
                    }
                }
            }
        }

        //Reposiciona os elementos de forma ordenada no nó da fila
        for (int d : vetorOrdenado){
            Main.pilhaGabarito.push(d);
        }

    }

    //Solução automática - método otimizado para torre de hanoi com capacidade 3
    public static void solucaoAutomatica() {
        while (true){
            //Imprime as pilhas
            imprimePilhas();
            //Variáveis de execução
            int infoA = -1;
            if (Main.pilha1.getTopo()!= null){
                infoA = Main.pilha1.getTopo().getInformacao();
            }
            int infoB = -1;
            if (Main.pilha2.getTopo() != null){
                infoB = Main.pilha2.getTopo().getInformacao();
            }
            int infoGabarito = Main.pilhaGabarito.getTopo().getInformacao();

            //Topo da pilhaA para pilhaC
            if (infoA == infoGabarito){
                moveTopoPilhaToPilha(Main.pilha1, Main.pilha3);
                Main.pilhaGabarito.pop();
            //Topo da pilhaB para pilhaC
            }else if (infoB == infoGabarito){
                moveTopoPilhaToPilha(Main.pilha2, Main.pilha3);
                Main.pilhaGabarito.pop();
            }else{
                if (Main.mode == 1){
                    if (infoA > infoB && infoB != -1) {
                        //a->c, b->a, c->b
                        imprimePilhas();
                        moveTopoPilhaToPilha(Main.pilha1, Main.pilha3);
                        moveTopoPilhaToPilha(Main.pilha2, Main.pilha1);
                        moveTopoPilhaToPilha(Main.pilha3, Main.pilha2);

                    }else{
                        if (Main.pilha1.getTopo() == null){
                            imprimePilhas();
                            moveTopoPilhaToPilha(Main.pilha2, Main.pilha1);
                        }else{
                            imprimePilhas();
                            moveTopoPilhaToPilha(Main.pilha1, Main.pilha2);
                        }
                    }
                }else{
                    if (infoA < infoB && infoB != -1) {
                        //a->c, b->a, c->b
                        imprimePilhas();
                        moveTopoPilhaToPilha(Main.pilha1, Main.pilha3);
                        moveTopoPilhaToPilha(Main.pilha2, Main.pilha1);
                        moveTopoPilhaToPilha(Main.pilha3, Main.pilha2);

                    }else{
                        if (Main.pilha1.getTopo() == null){
                            imprimePilhas();
                            moveTopoPilhaToPilha(Main.pilha2, Main.pilha1);
                        }else{
                            imprimePilhas();
                            moveTopoPilhaToPilha(Main.pilha1, Main.pilha2);
                        }
                    }
                }

            }
        }
    }
}
