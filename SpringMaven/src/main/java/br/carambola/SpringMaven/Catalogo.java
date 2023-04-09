package br.carambola.SpringMaven;

import java.sql.SQLException;
import java.util.Scanner;

import br.carambola.SpringMaven.Usuarios.UsuarioEstabelecimento;

public class Catalogo {
	private UsuarioEstabelecimento estabelecimento; //Atributo fora do diagrama
	private String nome; //Atributo fora do diagrama
	private Scanner entrada = new Scanner(System.in);
	
	
	public Catalogo(UsuarioEstabelecimento estabelecimento) {
		super();
		this.estabelecimento = estabelecimento;
		this.setNome(this.estabelecimento.getNome());
	}

	public void cadastrarCategorias() throws SQLException {
		System.out.print("Informe o nome da categoria: ");
		this.nome = entrada.nextLine();
		Categoria categoria = new Categoria(nome);
		System.out.println("A categoria "+categoria+" foi cadastrada corretamente!");
		entrada.close();
	}
	
	public void verCategorias() {
		
	}
	
	public void editarCategorias() {
		
	}
	
	public void excluirCategorias() {
		
	}

	public UsuarioEstabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(UsuarioEstabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
