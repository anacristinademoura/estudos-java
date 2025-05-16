import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {
		
		//ARRAY - ESTÁTICO
		
		String[] arrayEstatico = new String[5];
		arrayEstatico[0] = "Ana";
		arrayEstatico[1] = "Maria";
		arrayEstatico[2] = "Letícia";
		arrayEstatico[3] = "Beatriz";
		arrayEstatico[4] = "Cristina";
		
		//ARRAYLIST - DINÂMICO
	
		ArrayList<String> arrayDinamico = new ArrayList<String>();
		arrayDinamico.add("Ana");
		arrayDinamico.add("Maria");
		arrayDinamico.add("Letícia");
		arrayDinamico.add("Beatriz");
		arrayDinamico.add("Cristina");
		
		//-----IMPRIMINDO-----
		//ARRAY - ESTÁTICO
		System.out.println("Imprimindo Array Estático: ");
		
		for (int i = 0; i < arrayEstatico.length; i++) {
			System.out.println(arrayEstatico[i]);
		}
		
		//ARRAYLIST - DINÂMICO
		System.out.println("\nImprimindo Array Dinâmico: ");
		System.out.println("Remover Maria"); // remove --> remove um elemento por índice.
		arrayDinamico.remove(1);
		//arrayDinamico.clear(); limpa todos os elementos da lista
		
		for (int i = 0; i < arrayDinamico.size(); i++) {
			System.out.println(arrayDinamico.get(i));
		}

//		//FOREACH
//		for(String nome : arrayDinamico) {
//			System.out.println(nome); //não precisa do índice, ele pega cada elemento da estrutura que foi passada.
//		}
//		
		
		//ArrayList USANDO CLASSE
		//INSTÂNCIANDO OBJETOS:
		 Pessoa joao = new Pessoa("João", 20);
		 Pessoa maria = new Pessoa("Maria", 22);
		 Pessoa pedro = new Pessoa("Pedro", 35);
		 //ARRAYLIST PESSOAS:
		 ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		 //ADICIONANDO
		 pessoas.add(joao);
		 pessoas.add(maria);
		 pessoas.add(pedro);
		 //IMPRIMINDO - FOREACH
		 System.out.println("\nImprimindo Classe Pessoas: ");
		 
		 for (Pessoa pessoa : pessoas) {
			 System.out.println(pessoa.getNome());
		 }
		 
		//ORDENANDO ArrayList DE NÚMEROS
		//ArrayList NÚMEROS INTERIROS:
			ArrayList<Integer> numeros = new ArrayList<Integer>();
			/*
			  Tipo Primitivo --> Wrapper Class
			  int            --> Integer
			  float          --> float
			  double         --> Double
			  char           --> Character
			*/
			numeros.add(50);
			numeros.add(10);
			numeros.add(100);
			numeros.add(78);
			numeros.add(4);
			numeros.add(-32);
			numeros.add(2);
			numeros.add(8);
			
			Collections.sort(numeros); //ordena nosso ArrayList trocando as posições
			
			System.out.println("\nImprimindo números em ordem crescente: ");
			
			for (Integer numero : numeros) {
				System.out.println(numero);
			}
			
			Collections.sort(numeros, Collections.reverseOrder());
			
			System.out.println("\nImprimindo números em ordem decrescente: ");
			
			for (Integer numero : numeros) {
				System.out.println(numero);
			}
			
			//ORDENANDO ArrayList COM STRINGS
			ArrayList<String> nomes = new ArrayList<String>();
			nomes.add("Zoro");
			nomes.add("Vegeta");
			nomes.add("Naruto");
			nomes.add("Goku");
			
			Collections.sort(nomes);
			
			System.out.println("\nImprimindo Strings em ordem crescente (Alfabética)");
			
			for (String nome: nomes) {
				System.out.println(nome);
			}
	}

}
