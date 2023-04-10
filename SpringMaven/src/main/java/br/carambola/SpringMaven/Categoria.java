package br.carambola.SpringMaven;

import java.sql.SQLException;
import java.util.Scanner;
import br.carambola.SpringMaven.DB.ConnectionDb;

public class Categoria {
	private int id; //Id da categoria criado após o diagrama
	private int count = 10; //Count do id criado após a categoria
	private String nome;
	private ConnectionDb conn = new ConnectionDb();//Atibuto fora do diagrama
	private Scanner entrada = new Scanner(System.in);//Atibuto fora do diagrama
	
	public Categoria(String nome, int id) throws SQLException {
		super();
		this.setId(count);
		this.nome = nome;
		count = count + 1;
		conn.insert("INSERT INTO TB_CATEGORIAS (CATE_IDCATE, CATE_IDCAT,CATE_DESCCATE) VALUES (NEXT VALUE FOR SQ_CATE_IDCATE,'"+id+"',"+"'"+this.nome+"'"+");");
	}

	public void verProdutosDaCategoria(Categoria categoria) throws SQLException {
		System.out.print("Qual id da categoria deseja ver os produtos: ");
		int idCategoria = entrada.nextInt();
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
		return    "\n{"
				+ "\nCategoria"
				+ "\n["
				+ "\nid=" + id 
				+ "\nnome=" + nome 
				+ "\n]"
				+ "\n}";
	}
	
	

}
