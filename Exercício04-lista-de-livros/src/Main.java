public class Main {

	public static void main(String[] args) {
		
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		
		LivroFisico livroFisico = new LivroFisico("Titulo", "Autor", "asd123", 20, 1, 7);
		Ebook ebook = new Ebook("Titulo", "Autor", "asd123", 20, 12);
		
		carrinho.addLivro(livroFisico);
		carrinho.addLivro(ebook);
		
		
		System.out.println(carrinho.calcularTotal());

	}

}
