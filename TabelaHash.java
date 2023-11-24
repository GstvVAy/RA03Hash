public class TabelaHash {
    private int tamanho; // Tamanho da tabela hash
    private ListaEncadeada[] tabela; // Array de listas encadeadas para armazenar os elementos
    private int colisoes; // Contador de colisões na tabela

    // Construtor da classe TabelaHash
    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new ListaEncadeada[tamanho];
        preencherLista(); // Inicializa cada posição da tabela com uma lista encadeada vazia
        colisoes = 0; // Inicializa o contador de colisões
    }

    // Método para inicializar cada posição da tabela com uma lista encadeada vazia
    public void preencherLista() {
        for (int i = 0; i < tamanho; i++) tabela[i] = new ListaEncadeada();
    }

    // Função de hash utilizando o método da multiplicação
    public int hashMultiplicacao(int dado) {
        double A = 0.6180339887;
        return (int) (tamanho * ((dado * A) % 1));
    }

    // Função de hash utilizando o método do resto da divisão
    public int hashRestoDeDivisao(int dado) {
        return dado % tamanho;
    }

    // Função de hash utilizando o método do dobramento
    public int hashDobramento(int dado) {
        return dado / 1000000 + (dado / 1000) % 1000 + dado % 1000;
    }

    // Método para inserir um elemento na tabela usando o método do resto da divisão
    public void inserirHashDivisao(Registro r) {
        int i = hashRestoDeDivisao(r.getCodigo());
        if (tabela[i].getPrimeiro() != null) colisoes++; // Incrementa o contador de colisões se a posição já estiver ocupada
        tabela[i].inserirListEnc(r);
    }

    // Método para inserir um elemento na tabela usando o método da multiplicação
    public void inserirHashMultiplicacao(Registro r) {
        int i = hashMultiplicacao(r.getCodigo());
        if (tabela[i].getPrimeiro() != null) colisoes++; // Incrementa o contador de colisões se a posição já estiver ocupada
        tabela[i].inserirListEnc(r);
    }

    // Método para inserir um elemento na tabela usando o método do dobramento
    public void inserirHashDobramento(Registro r) {
        int i = hashDobramento(r.getCodigo());
        if (tabela[i].getPrimeiro() != null) colisoes++; // Incrementa o contador de colisões se a posição já estiver ocupada
        tabela[i].inserirListEnc(r);
    }

    //------------------------------------------------------------------------------------------------------------------------

    // Método de busca na tabela hash
    public int buscarTabelaHash(int dado) {
        int i = hashDobramento(dado); // Encontra o índice usando a função de hash
        ListaEncadeada lista = tabela[i]; // Obtém a lista encadeada no índice
        Node atual = lista.primeiro; // Obtenha a cabeça da lista encadeada

        // Percorre a lista encadeada para procurar o dado
        while (atual != null) {
            if (atual.valor.getCodigo() == dado) return i;
            atual = atual.proximo;
        }

        System.out.println("Número não encontrado");
        return -1; // Retorna -1 se o dado não for encontrado
    }

    // Método para mostrar a tabela hash
    public void mostrarTabelaHash() {
        for (int i = 0; i < tamanho; i++) {
            System.out.print("Linha[" + i + "]: ");
            Node atual = tabela[i].primeiro;
            while (atual != null) {
                System.out.print(atual.valor + " -> ");
                atual = atual.proximo;
            }
            System.out.println("NULL");
        }
    }

    // Getters para obter o número de colisões e o tamanho da tabela
    public int getColisoes() {
        return colisoes;
    }

    public int getTamanho() {
        return tamanho;
    }
}
