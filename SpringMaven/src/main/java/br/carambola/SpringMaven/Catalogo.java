package br.carambola.SpringMaven;

import java.sql.SQLException;
import java.util.Scanner;

import br.carambola.SpringMaven.DB.ConnectionDb;
import br.carambola.SpringMaven.Usuarios.UsuarioEstabelecimento;

public class Catalogo {
	private UsuarioEstabelecimento estabelecimento; //Atributo fora do diagrama
	private String nome; //Atributo fora do diagrama
	private Scanner entrada = new Scanner(System.in);
	private ConnectionDb conn = new ConnectionDb();//Atibuto fora do diagrama
	private int id; //Atributo fora do diagrama
	private static int  count = 1; //Atributo fora do diagrama
	
	public Catalogo(UsuarioEstabelecimento estabelecimento) throws SQLException {
		super();
		this.setId(count);
		this.estabelecimento = estabelecimento;
		this.nome = estabelecimento.getNome();
		conn.insert("INSERT INTO TB_CATALOGO(CAT_IDCAT,CAT_NOMECAT) VALUES (NEXT VALUE FOR SQ_CAT_IDCAT,'"+this.nome+"');");
		count = count + 1;
	}

	public void cadastrarCategorias() throws SQLException {
		System.out.print("Informe o nome da categoria: ");
		this.nome = entrada.nextLine();
		Categoria categoria = new Categoria(nome, id);
		System.out.println("A categoria "+categoria+" foi cadastrada corretamente!");
		entrada.close();
	}
	
	public void verCategorias() throws SQLException {
		System.out.println("As categorias do Catalogo "+this.estabelecimento.getNome()+" são:  ");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(id);
		
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
