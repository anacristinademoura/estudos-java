import java.util.ArrayList;

public class CarrinhoDeCompras {

	ArrayList<Livro> livros = new ArrayList<>();
	
	public void addLivro(Livro livro) {
		livros.add(livro);
	}
	
	public double calcularTotal() {
		
		double total = 0;
		
		for(Livro livro : livros) {
			
			total += livro.calcularPrecoTotal();
		}
		
		return total;
	}
}
