public class Lampada {
	//PROPRIEDADES
	private int potencia;
	private boolean ligado;
	
	//MÉTODO CONSTRUTOR
	public Lampada(int potencia) {
		this.potencia = potencia;
		this.ligado = false;
	}
	
	//MÉTODOS GETTERS AND SETTERS
	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public boolean isLigado() {
		return ligado;
	}

	public void setLigado(boolean ligado) {
		this.ligado = ligado;
	}

	//MÉTODOS
	void Ligar() {
		ligado = true;
	}
	void Desligar() {
		ligado = false;
	}
}
