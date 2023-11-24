import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] variaveisInsercao = {20000, 100000, 500000, 1000000, 5000000};
        int[] variaveisTamTabela = {14000, 70000, 350000, 700000, 3500000};
        int[] valoresBuscados = new int[5];

        for (int insercoes : variaveisInsercao) {
            for (int tamanhos : variaveisTamTabela) {
                TabelaHash tabelaHash = new TabelaHash(tamanhos);
                Random random = new Random();

                long startTime = System.nanoTime(); // Tempo inicial
                int aux = 0;

                //Geração de números aleatórios de 9 digitos
                for (int i = 0; i < insercoes; i++) {
                    Registro r;
                    r = new Registro(random.nextInt(900000000) + 100000000);
                    tabelaHash.inserirHashDobramento(r);
                    if (aux < 5) {
                        valoresBuscados[aux] = r.getCodigo();
                        aux++;
                    }

                }

                long endTime = System.nanoTime(); // Tempo final em nanossegundos
                double tempoInsercao = endTime - startTime; // ns
                tempoInsercao /= 1e9; // Converter para segundos
                double[] temposBusca = new double[5];
                int aux2 = 0;

                for (int buscas: valoresBuscados) {
                    long tempoInicio = System.nanoTime(); // Tempo inicial buscas
                    tabelaHash.buscarTabelaHash(buscas);
                    long tempoFim = System.nanoTime();
                    double tempoBusca = (tempoFim - tempoInicio)/ 1e9;
                    temposBusca[aux2] = tempoBusca;
                    aux2++;

                }

                System.out.println("Tamanho da tabela: " + tabelaHash.getTamanho());
                System.out.println("Numero de dados inseridos: " + insercoes
                        + " \nTempo de insercao: " + tempoInsercao + " segundos.");
                System.out.printf("Numero de colisoes: " + tabelaHash.getColisoes() + "\n");
                for (double tempo: temposBusca) {
                    System.out.printf("Busca: " + tempo + "\n");
                }
                System.out.println("\n\n");
            }
        }
    }
}