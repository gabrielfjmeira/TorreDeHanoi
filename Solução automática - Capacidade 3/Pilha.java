public class Pilha {
    //Atributo de capacidade da pilha
    private int Capacidade;

    //Atributo do topo da pilha
    private Node Topo;

    //Método construtor
    public Pilha(int capacidade){
        this.Capacidade = capacidade;
        this.Topo = null;
    }

    //Método para retornar o topo da pilha
    public Node getTopo() {
        return this.Topo;
    }

    //Método para retornar a capacidade da pilha
    public int getCapacidade(){
        return this.Capacidade;
    }

    //Método para inserir nó na lista
    public void push(int informacao){
        //Cria novo nó para ser adicionado na pilha
        Node no = new Node();
        no.setInformacao(informacao);

        //Se a pilha não estiver vazia seta o próximo do nó a ser inserido como o topo antigo
        if (this.Topo != null){
           no.setProximo(this.Topo);
        }

        //Atualiza o Topo com o novo nó inserido
        this.Topo = no;
    }

    //Método para remover nó do topo da lista
    public void pop(){
        this.Topo = this.Topo.getProximo();
    }

    //Método para verificar se a pilha está cheia
    public boolean cheia(){
        //Variável que vai percorrer a pilha
        Node atual = this.Topo;

        //Variável auxiliar que é o contador de elementos da pilha
        int contador = 0;

        //Percorre a pilha somando a quantidade de elementos
        while (atual != null){
            contador ++;
            atual = atual.getProximo();
        }

        //Se a pilha estiver o mesmo número de elementos da capacidade das pilhas, então ela estará cheia
        if (contador == this.Capacidade){
            return true;
        }

        return false;
    }

    //Método para imprimir a pilha
    public void imprime(){
        Node atual = this.Topo;
        System.out.print("[");
        while (atual != null) {
            if (atual.getProximo() == null){
                System.out.print(atual.getInformacao());
            }else{
                System.out.print(atual.getInformacao()+ ", ");
            }

            atual= atual.getProximo();
        }
        System.out.println("]");

        System.out.println();
    }
}

