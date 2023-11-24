public class ListaEncadeada {
    public Node primeiro;

    public ListaEncadeada() {
        primeiro = null;
    }

    public Node getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(Node primeiro) {
        this.primeiro = primeiro;
    }

    public void inserirListEnc(Registro r) {
        Node newNode = new Node(r);

        if (primeiro == null) {
            primeiro = newNode;
        } else {
            Node atual = primeiro;

            while (atual.proximo != null) {
                atual = atual.proximo;
            }

            atual.proximo = newNode;
        }
    }
}