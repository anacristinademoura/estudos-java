public class Main {

	public static void main(String[] args) {
		//OBJETOS
		Lampada minhaLampada = new Lampada(20);
		Lampada minhaLampada2 = new Lampada(10);
		Lampada minhaLampada3 = new Lampada(60);

		
		System.out.println("Minha lâmpada de " + 
		minhaLampada.getPotencia() + " está ligada? " +
				minhaLampada.isLigado());
		
	}

}
