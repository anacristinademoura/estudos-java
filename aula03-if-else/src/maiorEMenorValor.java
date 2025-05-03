import java.util.Scanner;

public class MaiorEMenorValor {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int valor1, valor2;
		
		System.out.println("Insira o primeiro valor: ");
		valor1 = input.nextInt();
		System.out.println("Insira o segundo valor: ");
		valor2 = input.nextInt();
		
		if(valor1 > valor2) {
			System.out.println(valor1 + " é maior do que " + valor2);
		}else if(valor2 > valor1) {
			System.out.println(valor2 + " é maior do que " + valor1);
		}else {
			System.out.println(valor1 + " é igual a " + valor2);
		}
		
		input.close();
	}

}
