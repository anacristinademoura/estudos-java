package br.com.cdb.polimorfismo;
import br.com.cdb.polimorfismo.model.*;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		Pessoa fulano = new Pessoa("Fulano");
		fulano.cpf = 12345678912l;
		
		Aluno aluno1 = new Aluno("Ana", 123456);
		aluno1.cpf = 12345678911l;
		
		
		Funcionario funcionario = new Funcionario("Funcionário");
		
		Professor prof = new Professor("Denilson");
		prof.cpf = 12345678910l;
		prof.salario = 1;
		prof.novoSalario(2, 42); //Posso usar os parâmetros dos dois métodos pelo mesmo nome
		
		Faxineiro faxineiro = new Faxineiro("Seu Zé");
		faxineiro.cpf = 12345678900l;
		faxineiro.salario = 1;
		faxineiro.turno = "Tarde";
		
		//PROCESSAMENTO POLIMORFICO
		ArrayList<Pessoa> listaDaEscola = new ArrayList<>();
		
		listaDaEscola.add(fulano);
		listaDaEscola.add(aluno1);
		listaDaEscola.add(funcionario);
		listaDaEscola.add(prof);
		listaDaEscola.add(faxineiro);
		//Só conseguimos adicionar esses objetos porque todos herdam da mesma classe
		for (Pessoa p : listaDaEscola) {
			p.apresentacao();
		}
		
	}

}
