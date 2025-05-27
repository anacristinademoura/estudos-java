package br.com.cdb.abstracao;
import br.com.cdb.abstracao.model.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		Aluno aluno1 = new Aluno("Ana", 123456);
		aluno1.cpf = 12345678911l;

		Funcionario funcionario = new Funcionario("Funcionário");

		Professor prof = new Professor("Denilson");
		prof.cpf = 12345678910l;
		prof.salario = 1;
		prof.novoSalario(2, 42);

		Faxineiro faxineiro = new Faxineiro("Seu Zé");
		faxineiro.cpf = 12345678900l;
		faxineiro.salario = 1;
		faxineiro.turno = "Tarde";

		ArrayList<Pessoa> listaDaEscola = new ArrayList<>();

		listaDaEscola.add(aluno1);
		listaDaEscola.add(funcionario);
		listaDaEscola.add(prof);
		listaDaEscola.add(faxineiro);
		
		for (Pessoa p : listaDaEscola) {
			p.apresentacao();
		}

	}

}
