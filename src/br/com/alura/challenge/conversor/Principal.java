import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaApi consulta = new ConsultaApi();
        int opcao = 0;

        String menu = """
                ***************************************************
                Seja bem-vindo(a) ao Conversor de Moeda =)
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileiro
                4) Real brasileiro =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Sair
                
                Escolha uma opção válida:
                ***************************************************
                """;

        while (opcao != 7) {
            System.out.println(menu);
            try {
                opcao = leitura.nextInt();

                if (opcao == 7) {
                    System.out.println("Obrigado por utilizar o conversor. Finalizando...");
                    break;
                }

                if (opcao < 1 || opcao > 6) {
                    System.out.println("Opção inválida! Por favor, tente novamente.");
                    continue;
                }

                System.out.print("Digite o valor que deseja converter: ");
                double valorParaConverter = leitura.nextDouble();

                String moedaOrigem = "";
                String moedaDestino = "";

                switch (opcao) {
                    case 1:
                        moedaOrigem = "USD";
                        moedaDestino = "ARS";
                        break;
                    case 2:
                        moedaOrigem = "ARS";
                        moedaDestino = "USD";
                        break;
                    case 3:
                        moedaOrigem = "USD";
                        moedaDestino = "BRL";
                        break;
                    case 4:
                        moedaOrigem = "BRL";
                        moedaDestino = "USD";
                        break;
                    case 5:
                        moedaOrigem = "USD";
                        moedaDestino = "COP";
                        break;
                    case 6:
                        moedaOrigem = "COP";
                        moedaDestino = "USD";
                        break;
                }

                Moeda moeda = consulta.buscaTaxasDeCambio(moedaOrigem);
                Double taxa = moeda.taxasDeConversao().get(moedaDestino);

                if (taxa == null) {
                    System.out.println("Não foi possível encontrar a taxa de conversão para " + moedaDestino);
                    continue;
                }

                double valorConvertido = valorParaConverter * taxa;

                System.out.println("-------------------------------------------------");
                System.out.printf("Valor %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]\n",
                        valorParaConverter, moedaOrigem, valorConvertido, moedaDestino);
                System.out.println("-------------------------------------------------");


            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                leitura.next();
            } catch (RuntimeException e) {
                System.out.println("Erro ao consultar a API: " + e.getMessage());
            }
        }
        leitura.close();
    }
}