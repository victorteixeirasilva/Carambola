package br.fam.Carambola;

import java.sql.SQLException;
//import java.util.Scanner;

import javax.swing.JOptionPane;

import br.fam.Carambola.Db.ConnectionDb;



public class Categoria {
	private int id; //Id da categoria criado após o diagrama
	private int idEstabelecimento;
	private String nome;
	private ConnectionDb conn = new ConnectionDb();//Atibuto fora do diagrama
	//private Scanner entrada = new Scanner(System.in);//Atibuto fora do diagrama
	
	public Categoria() {
		
	}
	
	public Categoria(String nome, int idEstabelecimentos) throws SQLException {
		this.nome = nome;
		this.idEstabelecimento = idEstabelecimentos;
		conn.insert("INSERT INTO TB_CATEGORIAS (CATE_IDCATE, CATE_IDCAT,CATE_DESCCATE) VALUES (NEXT VALUE FOR SQ_CATE_IDCATE,'"+this.idEstabelecimento+"',"+"'"+this.nome+"'"+");");
	    this.id = conn.getIdCategoria(this.nome, this.idEstabelecimento);
	}

	public void verProdutosDaCategoria() throws SQLException {
		//System.out.print("Qual id da categoria deseja ver os produtos: ");
		String input = JOptionPane.showInputDialog("Qual id da categoria deseja ver os produtos:");
		int idCategoria = Integer.parseInt(input);
		conn.queryVerProdutosDeUmaCategoria(idCategoria);
	}
	
	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return  JOptionPane.showInputDialog("A categoria "+nome+" foi cadastrada corretamente");
	}

	public int getIdEstabelecimento() {
		return idEstabelecimento;
	}

	public void setIdEstabelecimento(int idEstabelecimento) {
		this.idEstabelecimento = idEstabelecimento;
	}
	
	

}
