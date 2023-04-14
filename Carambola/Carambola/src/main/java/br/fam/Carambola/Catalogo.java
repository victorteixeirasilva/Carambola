package br.fam.Carambola;

import java.sql.SQLException;
//import java.util.Scanner;

import javax.swing.JOptionPane;

import br.fam.Carambola.Db.ConnectionDb;
import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;



public class Catalogo {
	private UsuarioEstabelecimento estabelecimento; //Atributo fora do diagrama
	private String nome; //Atributo fora do diagrama
	//private Scanner entrada = new Scanner(System.in);
	private ConnectionDb conn = new ConnectionDb();//Atibuto fora do diagrama
	private int id; //Atributo fora do diagrama
	//private static int count = 1; //Atributo fora do diagrama
	
	public Catalogo() {
		
	}
	
	public Catalogo(UsuarioEstabelecimento estabelecimento) throws SQLException {
		super();
		this.estabelecimento = estabelecimento;
		this.nome = estabelecimento.getNome();
		this.id = estabelecimento.getId();
		conn.insert("INSERT INTO TB_CATALOGO(CAT_IDCAT,CAT_NOMECAT) VALUES ("+this.id+",'"+this.nome+"');");
		//Catalogo.count += 1;
	}

	public void cadastrarCategorias() throws SQLException {
		this.nome = JOptionPane.showInputDialog("Informe o nome da categoria:");
		Categoria categoria = new Categoria(nome, id);
		
		JOptionPane.showMessageDialog(null, "A categoria "+categoria.getNome()+" foi cadastrada corretamente!");
	
	}
	
	public void verCategorias(int idEstabelecimento) throws SQLException {
		JOptionPane.showMessageDialog(null, "As categorias desse estabelecimento são:  ");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(idEstabelecimento);
		
	}
	
	public void editarCategorias() throws SQLException {
		String input = JOptionPane.showInputDialog("Informe o Id da categoria que deseja editar:");
		int idCategoria = Integer.parseInt(input);
		
		if(this.id == conn.queryIdCatalogoComIdCategoria(idCategoria)) {//Verifica se o id do catalogo é igual o id do catalogo do id de categoria informado
			String nomeAtual = conn.queryGetCategoriaBd(idCategoria);	
			JOptionPane.showMessageDialog(null, "Nome atual da categoria: "+nomeAtual);
			
			String novoNome = JOptionPane.showInputDialog("Digite o novo nome:");
			
			JOptionPane.showMessageDialog(null, "O nome da categoria será alterado para: "+novoNome);
			
			
			if(nomeAtual != novoNome) {
				
				int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar essa categoria?");
				
				if(escolha == JOptionPane.YES_OPTION) {
					//Edita categoria
					conn.update("UPDATE TB_CATEGORIAS SET CATE_DESCCATE  = '"+novoNome+"' WHERE CATE_IDCATE  = '"+idCategoria+"';");
					
					
				} else if (escolha == JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(null, "Informação original foi mantida!");
					//System.out.println("Informação original foi mantida!");
				} else {
					//System.out.println("Opção Inválida");
				}
			} else {			
				JOptionPane.showMessageDialog(null, "Informações editadas são iguais as informações originais!");
				//System.out.println("Informações editadas são iguais as informações originais!");
			}
			
			//entrada.close();
		} else {
			
		JOptionPane.showMessageDialog(null, "Você não possui categoria com esse id");
		}
		
	}
	
	public void excluirCategorias() throws SQLException {
		String input = JOptionPane.showInputDialog("Informe o Id da categoria que deseja Excluir:");
		int idCategoria = Integer.parseInt(input);
		
		if(this.id == conn.queryIdCatalogoComIdCategoria(idCategoria)) {//Verifica se o id do catalogo é igual o id do catalogo do id de categoria informado
			String nomeAtual = conn.queryGetCategoriaBd(idCategoria);
			JOptionPane.showMessageDialog(null, "Nome atual da categoria: "+nomeAtual);
			int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir essa categoria?");
			
			if(escolha == JOptionPane.YES_OPTION) {
				//Excluir categoria
				conn.update("DELETE FROM TB_CATEGORIAS WHERE CATE_IDCATE = '"+idCategoria+"';");
			} else if (escolha == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(null, "Categoria não foi excluida!");		
			} else {
				JOptionPane.showMessageDialog(null, "Opção Inválida!");			
			}			
		} else {
			JOptionPane.showMessageDialog(null, "Você não possui categoria com esse id");
		}
		
		
		
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
