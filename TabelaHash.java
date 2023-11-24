public class TabelaHash {
    private int tamanho; 
    private ListaEncadeada[] tabela; 
    private int colisoes; 


    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        tabela = new ListaEncadeada[tamanho];
        preencherLista(); 
        colisoes = 0; 
    }

    public void preencherLista() {
        for (int i = 0; i < tamanho; i++) tabela[i] = new ListaEncadeada();
    }


    public int hashMultiplicacao(int dado) {
        double A = 0.6180339887;
        return (int) (tamanho * ((dado * A) % 1));
    }


    public int hashRestoDeDivisao(int dado) {
        return dado % tamanho;
    }

    public int hashDobramento(int dado) {
        return dado / 1000000 + (dado / 1000) % 1000 + dado % 1000;
    }


    public void inserirHashDivisao(Registro r) {
        int i = hashRestoDeDivisao(r.getCodigo());
        if (tabela[i].getPrimeiro() != null) colisoes++; 
        tabela[i].inserirListEnc(r);
    }


        int i = hashMultiplicacao(r.getCodigo());
        if (tabela[i].getPrimeiro() != null) colisoes++;
        tabela[i].inserirListEnc(r);
    }

    
    public void inserirHashDobramento(Registro r) {
        int i = hashDobramento(r.getCodigo());
        if (tabela[i].getPrimeiro() != null) colisoes++; 
        tabela[i].inserirListEnc(r);
    }



    public int buscarTabelaHash(int dado) {
        int i = hashDobramento(dado); 
        ListaEncadeada lista = tabela[i]; 
        Node atual = lista.primeiro;


        while (atual != null) {
            if (atual.valor.getCodigo() == dado) return i;
            atual = atual.proximo;
        }

        System.out.println("Número não encontrado");
        return -1; // 
    }

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


    public int getColisoes() {
        return colisoes;
    }

    public int getTamanho() {
        return tamanho;
    }
}
