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
		Catalogo.count += 1;
	}

	public void cadastrarCategorias() throws SQLException {
		System.out.print("Informe o nome da categoria: ");
		this.nome = entrada.nextLine();
		Categoria categoria = new Categoria(nome, id);
		System.out.println("A categoria "+categoria+" foi cadastrada corretamente!");
		//entrada.close();
	}
	
	public void verCategorias() throws SQLException {
		System.out.println("As categorias do Catalogo "+this.estabelecimento.getNome()+" são:  ");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(id);
		
	}
	
	public void editarCategorias() throws SQLException {
		System.out.print("Informe o Id da categoria que deseja editar: ");
		int idCategoria = entrada.nextInt();
		String nomeAtual = conn.queryGetCategoriaBd(idCategoria);
		
		System.out.println("Nome atual da categoria: "+nomeAtual);
		System.out.print("Digite o novo nome: ");
		String entradaAux = entrada.nextLine();
		String novoNome = entrada.nextLine();
		
		System.out.println("\nO nome da categoria será alterado para: "+novoNome);
		
		if(nomeAtual != novoNome) {
			System.out.print("Tem certeza que deseja editar essa categoria((1)->Sim / (2)->Não): ");
			int escolha = entrada.nextInt();
		
			if(escolha == 1) {
				//Edita categoria
				conn.update("UPDATE TB_CATEGORIAS SET CATE_DESCCATE  = '"+novoNome+"' WHERE CATE_IDCATE  = '"+idCategoria+"';");
			
			
			} else if (escolha == 2) {
				System.out.println("Informação original foi mantida!");
			} else {
			System.out.println("Opção Inválida");
			}
		} else {			
		System.out.println("Informações editadas são iguais as informações originais!");
		}
		
		//entrada.close();
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
