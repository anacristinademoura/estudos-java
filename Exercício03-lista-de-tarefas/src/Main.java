import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Tarefa> listaDeTarefas = new ArrayList<Tarefa>();
		
		int proximoID = 1;
		int opcao = 0;
		
		while(opcao != 4) {
			System.out.println("-----Menu-----");
			System.out.println("1- Adicionar Tarefa" +
	                           "\n2- Remover Tarefa" + 
	                           "\n3- Listar Tarefas" +
	                           "\n4- Sair");
			
			opcao = input.nextInt();
			input.nextLine(); //Limpa o buffer após o ENTER.
			
			switch(opcao) {
			case 1:
				Tarefa.adicionar(listaDeTarefas, input, proximoID);
				proximoID++;
				break;
			case 2:
				Tarefa.remover(listaDeTarefas, input);
				break;
			case 3:
				Tarefa.listarTarefas(listaDeTarefas);
				break;
			case 4:
				System.out.println("Programa encerrado!");
				break;
			default:
				System.out.println("Opção inválida, tente novamente.");
			}
		}
		input.close();
	}

}
