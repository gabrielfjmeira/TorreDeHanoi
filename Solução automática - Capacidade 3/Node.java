public class Node {

    //Atributo da informação do nó
    private Integer Informacao;

    //Atributo do próximo nó a ser referenciado
    private Node Proximo;

    //Método Construtor
    public Node()
    {
        this.Informacao = null;
        this.Proximo = null;
    }

    //Função que retorna a informação do nó em questão
    public Integer getInformacao() {
        return Informacao;
    }

    //Função que define a informação do nó em questão
    public void setInformacao(Integer informacao) {
        this.Informacao = informacao;
    }

    //Função que retorna o próximo nó
    public Node getProximo() {
        return Proximo;
    }

    //Função que define o próximo nó
    public void setProximo(Node proximo) {
        this.Proximo = proximo;
    }

}