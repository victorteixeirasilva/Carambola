package br.carambola.SpringMaven;

import java.sql.SQLException;
import java.util.Scanner;
import br.carambola.SpringMaven.DB.ConnectionDb;

public class Categoria {
	private int id; //Id da categoria criado após o diagrama
	private int count = 10; //Count do id criado após a categoria
	private String nome;
	ConnectionDb conn = new ConnectionDb();//Atibuto fora do diagrama
	Scanner entrada = new Scanner(System.in);
	
	public Categoria(String nome) throws SQLException {
		super();
		this.setId(count);
		this.nome = nome;
		count = count + 1;
		conn.insert("INSERT INTO TB_CATEGORIAS (CATE_IDCATE,CATE_DESCCATE) VALUES (NEXT VALUE FOR SQ_CATE_IDCATE,"+"'"+this.nome+"'"+");");
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

}
