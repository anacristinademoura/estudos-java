package br.com.cdb.heranca;
import br.com.cdb.heranca.model.*;

public class Main {

	public static void main(String[] args) {
		
		//Exemplo didático, NUNCA use propriedades diretamente;
		
		Pessoa fulano = new Pessoa("Fulano");
		fulano.cpf = 12345678912l;
		fulano.apresentacao();
		
		Aluno aluno1 = new Aluno("Ana", 123456);
		aluno1.cpf = 12345678911l;
		
		
		Funcionario funcionario = new Funcionario("Funcionário");
		
		Professor prof = new Professor("Denilson");
		prof.cpf = 12345678910l;
		prof.salario = 1;
		prof.numeroDeAulas = 20;
		
		Faxineiro faxineiro = new Faxineiro("Seu Zé");
		faxineiro.cpf = 12345678900l; //Herdado de Pessoa
		faxineiro.salario = 1; //Herdado de Funcionario
		faxineiro.turno = "Tarde"; //Propriedade própria
		
	}

}
