import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private ListaSimples listaSimples;
    private Scanner scanner; //escolha do usuário

    public Menu() {
        listaSimples = new ListaSimples(6);
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("Menu:");
            System.out.println("1. Inserir elemento");
            System.out.println("2. Inserir sequência de elementos");
            System.out.println("3. Remover elemento");
            System.out.println("4. Remover todas as ocorrências de um elemento");
            System.out.println("5. Verificar se a lista está cheia");
            System.out.println("6. Verificar se a lista está vazia");
            System.out.println("7. Ordenar em ordem crescente");
            System.out.println("8. Ordenar em ordem decrescente");
            System.out.println("9. Editar elemento");
            System.out.println("10. Exibir lista");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt(); //scanner vai ler a opção do usuário

            switch (opcao) {
                case 1:
                    System.out.print("Digite o elemento a ser inserido: ");
                    Object elemento = scanner.next(); //elemento
                    listaSimples.inserirElemento(elemento);
                    break;
                case 2:
                    List<Object> elementos = new ArrayList<>(); //armazenar os elementos
                    System.out.println("Digite a sequência de elementos (pressione Enter para finalizar):");
                    scanner.nextLine(); // Limpa o buffer
                    while (true) {
                        String input = scanner.nextLine(); //entrada do usuário
                        if (input.isEmpty()) { // entrada vazia= sai do loop
                            break;
                        }
                        elementos.add(input); // adc na lista
                    }
                    listaSimples.inserirSequencia(elementos.toArray()); //transoforma em array
                    break;
                case 3:
                    listaSimples.removerElemento();
                    break;
                case 4:
                    System.out.print("Digite o elemento a ser removido: ");
                    Object elementoRemover = scanner.next();
                    listaSimples.removerTodasOcorrencias(elementoRemover);
                    break;
                case 5:
                    if (listaSimples.estaCheia()) {
                        System.out.println("A lista está cheia.");
                    } else {
                        System.out.println("A lista ainda tem espaço livre.");
                    }
                    break;
                case 6:
                    if (listaSimples.estaVazia()) {
                        System.out.println("A lista está vazia. Nenhum elemento foi inserido ainda.");
                    } else {
                        System.out.println("A lista contém elementos.");
                    }
                    break;
                case 7:
                    listaSimples.ordenarCrescente();
                    System.out.println("Lista ordenada com sucesso.");
                    break;
                case 8:
                    listaSimples.ordenarDecrescente();
                    System.out.println("Lista ordenada com sucesso.");
                    break;
                case 9:
                    System.out.print("Digite o elemento antigo: ");
                    Object elementoAntigo = scanner.next();
                    System.out.print("Digite o novo elemento: ");
                    Object elementoNovo = scanner.next();
                    listaSimples.editarElemento(elementoAntigo, elementoNovo);
                    break;
                case 10:
                    listaSimples.exibir();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0); //até que o usuário escolha sair

        scanner.close(); //
    }
}