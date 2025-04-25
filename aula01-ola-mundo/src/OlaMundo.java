import java.util.Scanner;

public class OlaMundo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		System.out.println("Digite seu nome: ");
		String name = input.nextLine();
		
		System.out.println("Olá "+name+", bem vinda ao Mundo Java!");
		
		input.close();
	}

}
