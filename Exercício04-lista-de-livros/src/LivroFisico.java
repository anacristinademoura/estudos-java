public class LivroFisico extends Livro{

	private double peso;
	private double frete;
	
	
	public LivroFisico(String titulo, String autor, String isbn, double preco, double peso, double frete) {
		super(titulo, autor, isbn, preco);
		this.peso = peso;
		this.frete = frete;
	}
	
	public LivroFisico() {}
	
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getFrete() {
		return frete;
	}
	public void setFrete(double frete) {
		this.frete = frete;
	}
	
	@Override
	public double calcularPrecoTotal() {
		return super.getPreco() + frete;
	}
	
}
