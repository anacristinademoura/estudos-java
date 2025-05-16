import java.util.Scanner;
import java.util.ArrayList;

public class Tarefa {
	
	private int id;
	private String titulo;
	private String descricao;
	
	public Tarefa (int id, String titulo, String descricao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	public int getId() {return id;}
	public String getTitulo() {return titulo;}
	public String getDescricao() {return descricao;}

	public static void adicionar(ArrayList<Tarefa> listaDeTarefas, Scanner input, int id  ) {
		System.out.println("Escreva um título: ");
		String titulo = input.nextLine();
		
		System.out.println("Escreva uma descrição: ");
		String descricao = input.nextLine();
		
		Tarefa novaTarefa = new Tarefa(id, titulo, descricao);
		listaDeTarefas.add(novaTarefa);
		System.out.println("Tarefa adicionada!\n");
	}
	
	public static void remover(ArrayList<Tarefa> listaDeTarefas, Scanner input) {
		System.out.println("Digite o ID da tarefa que deseja remover: ");
		int ID = input.nextInt();
		input.nextLine(); //Limpa o buffer
		
		Tarefa tarefaParaRemover = null;
		for (Tarefa t : listaDeTarefas) {
			if(t.getId() == ID) {
				tarefaParaRemover = t;
				break;
			}
		}
		if(tarefaParaRemover != null) {
			System.out.println("Tem certeza que deseja remover a tarefa com ID " + ID + " ?");
			System.out.println("1 - Sim\n2 - Não");
			int resposta = input.nextInt();
			input.nextLine(); //Limpa o buffer
			
			if(resposta == 1) {
				listaDeTarefas.remove(tarefaParaRemover);
				System.out.println("Tarefa removida com sucesso!\n");
			}else {
				System.out.println("Operação não concluída.\n");
			}
		}else {
			System.out.println("Tarefa com ID " + ID + " não encontrada.\n");
		}
	}
	public static void listarTarefas(ArrayList<Tarefa> listaDeTarefas) {
		if(listaDeTarefas.size() == 0) {
			System.out.println("Tarefas não cadastradas.\n");
			return;
		}
		System.out.println("\n-----Lista de Tarefas-----");
		for(Tarefa t : listaDeTarefas) {
			System.out.println(t);
		}
		
		System.out.println(); //Linha em branco no final
		
	}
	public String toString() {
	    return "ID: " + id +
	           " | Título: " + titulo +
	           " | Descrição: " + descricao + "\n";
	}
}
