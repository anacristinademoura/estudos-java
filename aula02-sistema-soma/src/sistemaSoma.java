import java.util.Scanner;

public class sistemaSoma {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int valor1, valor2;
		
		System.out.println("Insira o primeiro valor: ");
		valor1 = input.nextInt();
		System.out.println("Insira o segundo valor: ");
		valor2 = input.nextInt();
		
		int resultado = valor1 + valor2;
		
		System.out.println("O resultado da soma de " + valor1 + " + " + valor2 + " é igual a " + resultado);
		
		input.close();
	}

}
