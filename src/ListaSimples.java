public class ListaSimples implements IEstruturaSimples {
    private Object[] listaSimples;
    private int limite; //máximo que cabe preenchido
    private int tamanho; //tamanho da lista, preenchida (sem nulos)
    private int posicao; //vai indicar onde está no momento, para inserir um novo


    public ListaSimples(int limite) {
        this.limite = limite;
        this.tamanho = 0;
        this.posicao = 0;
        this.listaSimples = new Object[limite];
    }

    @Override
    public void inserirElemento(Object elemento) {
        if (tamanho < limite) {
            listaSimples[posicao] = elemento; //vai adicionar na posição que está no momento, um elemento novo
            posicao++; //varredura para ir adicionando no próximo que tiver nulo
            tamanho++; //o tamanho da lista cresce, pois tá adicionando na não nula que achar
        } else {
            System.out.println("Limite da lista atingido, não há mais espaço.");
        }
    }

    @Override
    public void inserirElementoIndice(Object elemento, int indice) {
        if (indice < 0 && indice >= limite) { //verificando tanto se for negativo, quanto se passar o limite
            System.out.println("Não é possível.");
            return;
        }
        listaSimples[indice] = elemento; //adiciono no índice,igual no método de cima, porém por índice ao invés de posição

        if (indice >= posicao) {
            posicao = indice + 1; // garante que a próxima adição será na posição certa
        }

        if (listaSimples[indice] != null) {
            tamanho++; //o tamanho só cresce se ele era nulo antes
        }

    }

    @Override
    public void inserirSequencia(Object elementos) {
        Object[] sequencia = (Object[]) elementos;
        for (int i = 0; i < sequencia.length; i++) {
            Object item = sequencia[i];
            if (tamanho < limite) {
                listaSimples[posicao++] = item;
                tamanho++;
            } else {
                System.out.println("Lista cheia!");
            }
        }
    }

    @Override
    public boolean removerElemento() {
        if (estaVazia()) {
            System.out.println("Não há nada para remover.");
            return false;
        }
        listaSimples[0] = null; //deixando o primeiro elemento nulo
//temos que fazer o primeiro nulo, e os outros passam para lá,
// pois na fila sempre é substituído pelo próximo, sem ficar com a posição vazia.
        for (int i = 0; i < tamanho - 1; i++) { //o tamanho diminui 1
            Object proximoElemento = listaSimples[i + 1]; //pega o próximo
            listaSimples[i] = proximoElemento; //coloca na posição atual
        }
        listaSimples[posicao - 1] = null; //última posição limpa

        posicao--; //diminui a posição atual
        tamanho--; // diminui o total de elementos que não são nulos
        return true;
    }

    @Override
    public Object removerIndice(int indice) {
        if (estaVazia()) {
            System.out.println("Não há nada para remover.");
            return null;
        }
        if (indice < 0 && indice >= limite) { //verificando tanto se for negativo, quanto se passar o limite
            System.out.println("Não é possível.");
            return null;
        }
        Object removido = listaSimples[indice]; //salvar o que foi removido, pois quero retornar lá embaixo qual foi removido

        //também temos que fazer o primeiro nulo, e os outros passam para lá,
// pois na fila sempre é substituído pelo próximo, sem ficar com a posição vazia.
        for (int i = indice; i < posicao - 1; i++) {
            listaSimples[i] = listaSimples[i + 1];
        }

        listaSimples[posicao - 1] = null; // limpa a última posição que ficou duplicada
        posicao--; //diminui a posição atual
        tamanho--; //diminui o total de elementos que não são nulos

        System.out.println("Elemento removido: " + removido);
        return removido;
    }

    @Override
    public void removerSequencia(Object elementos) {
        Object[] sequencia = (Object[]) elementos;

        for (int i = 0; i < sequencia.length; i++) {
            Object elementoPraRemover = sequencia[i];

            for (int j = 0; j < posicao; j++) {
                if (listaSimples[j] != null && listaSimples[j].equals(elementoPraRemover)) { //verificacao

                    // agora a parte que puxa os elementos pra cobrir o vazio
                    for (int k = j; k < posicao - 1; k++) {
                        listaSimples[k] = listaSimples[k + 1];
                    }

                    //diminuir os contadores, assim como em todos
                    listaSimples[posicao - 1] = null;
                    posicao--;
                    tamanho--;
                    break; // para de procurar esse item, vai pro próximo da sequência
                }
            }
    }
    }

    @Override
    public void removerTodasOcorrencias(Object elemento) {
        for (int i = 0; i < tamanho; i++) {
            if (listaSimples[i] != null && listaSimples[i].equals(elemento)) {
                removerIndice(i);
                i = -1;
            }
        }
    }
    @Override
    public boolean estaCheia() {
        if (tamanho >= limite) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean estaVazia() {
        if (tamanho > 0) {
            return false; //se o tamanho for mais que 0
        } else {
            return true;
        }
    }

    @Override
    public boolean buscarElemento(Object elemento) {
        if (estaVazia()) {
            return false;
        }
        for (int i = 0; i < tamanho; i++) {
            if (listaSimples[i].equals(elemento)) {
                System.out.println("Elemento " + elemento + " encontrado.");
                return true;
            }
        }
        System.out.println("Elemento não encontrado na lista.");
        return false;
    }

    @Override
    public Object buscarElementoIndice(int indice) {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return null;
        }
        Object elemento = listaSimples[indice]; //criando para usar no return
        if (indice < 0 && indice >= limite) { //verificando tanto se for negativo, quanto se passar o limite
            System.out.println("Não é possível.");
            return null;
        }
        System.out.println("Elemento " + elemento + " encontrado no índice " + indice);
        return elemento;
    }

    @Override
    public void ordenarCrescente() {

    }

    @Override
    public void ordenarDecrescente() {

    }

    @Override
    public int quantidadeElementos() {
        return tamanho;
    }

    @Override
    public void dobrarCapacidade() {

    }

    @Override
    public void editarElemento(Object elementoAntigo, Object elementoNovo) {

    }

    @Override
    public void limpar() {
        for (int i = 0; i < tamanho; i++) {
            listaSimples[i] = null;
        }
//zerar também os contadores utilizados
        tamanho = 0;
        posicao = 0;

        System.out.println("Lista vazia.");
    }

    @Override
    public void exibir() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println("Lista dos elementos: " + i);
        }
    }

    @Override
    public Object obterPrimeiroElemento() {
        if (estaVazia()) {
            System.out.println("Lista vazia!");
            return null;
        }
        return listaSimples[0]; //se não está vazia, retorna o primeiro (0)
    }

    @Override
    public Object obterUltimoElemento() {
        if (estaVazia()) {
            System.out.println("Lista vazia!");
            return null;
        }
        return listaSimples[tamanho - 1]; //se não está vazia, retorna o tamanho total -1,
        // pois assim mostrará o último preenchido
    }
}

