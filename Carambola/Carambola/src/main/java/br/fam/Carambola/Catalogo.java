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
		//System.out.print("Informe o nome da categoria: ");
		this.nome = JOptionPane.showInputDialog("Informe o nome da categoria:");
		Categoria categoria = new Categoria(nome, id);
		JOptionPane.showMessageDialog(null, "A categoria "+categoria+" foi cadastrada corretamente!");
		//System.out.println("A categoria "+categoria+" foi cadastrada corretamente!");
		//entrada.close();
	}
	
	public void verCategorias() throws SQLException {
		JOptionPane.showMessageDialog(null, "As categorias do Catalogo "+this.estabelecimento.getNome()+" são:  ");
		//System.out.println("As categorias do Catalogo "+this.estabelecimento.getNome()+" são:  ");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(id);
		
	}
	
	public void editarCategorias() throws SQLException {
		//System.out.print("Informe o Id da categoria que deseja editar: ");
		String input = JOptionPane.showInputDialog("Informe o Id da categoria que deseja editar:");
		int idCategoria = Integer.parseInt(input);
		String nomeAtual = conn.queryGetCategoriaBd(idCategoria);
		
		JOptionPane.showMessageDialog(null, "Nome atual da categoria: "+nomeAtual);
		//System.out.println("Nome atual da categoria: "+nomeAtual);
		System.out.print("Digite o novo nome: ");
		//String entradaAux = entrada.nextLine();
		String novoNome = JOptionPane.showInputDialog("Digite o novo nome:");
		
		JOptionPane.showMessageDialog(null, "O nome da categoria será alterado para: "+novoNome);
		//System.out.println("\nO nome da categoria será alterado para: "+novoNome);
		
		if(nomeAtual != novoNome) {
			//;System.out.print("Tem certeza que deseja editar essa categoria((1)->Sim / (2)->Não): ");
			int escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja editar essa categoria?");
		
			if(escolha == JOptionPane.YES_OPTION) {
				//Edita categoria
				conn.update("UPDATE TB_CATEGORIAS SET CATE_DESCCATE  = '"+novoNome+"' WHERE CATE_IDCATE  = '"+idCategoria+"';");
			
			
			} else if (escolha == JOptionPane.NO_OPTION) {
				//System.out.println("Informação original foi mantida!");
			} else {
			JOptionPane.showMessageDialog(null, "Opção Inválida!");
			//System.out.println("Opção Inválida");
			}
		} else {			
		JOptionPane.showMessageDialog(null, "Informações editadas são iguais as informações originais!");
		//System.out.println("Informações editadas são iguais as informações originais!");
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