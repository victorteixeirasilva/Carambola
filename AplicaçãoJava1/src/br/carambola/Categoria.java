package br.carambola;
/*
 * CLASSE CATEGORIA
 * 
 * By Victor Teixeira
 * 
 * Anotação:	Não sei se está correto, provavelmente não. Mas deselvolvi a logica dessa classe sem Banco de dados
 * 				mas imagino que nesse metodo verProdutosDaCategoria, não vai manter essa logica e sim uma de consulta
 * 				no banco de dados. OBS: tirar essa dúvida com a professora.
 */
import java.util.List;

public class Categoria {
	private int id; //Id da categoria criado após o diagrama
	private int count = 0; //Count do id criado após a categoria
	private String nome;
	
	
	
	public Categoria(String nome) {
		super();
		this.setId(count);
		this.nome = nome;
		count = count + 1;
	}

	public void verProdutosDaCategoria(List<Produto> produtos, Categoria categoria) {
		for (Produto produto : produtos) {
			if(produto.getCategoria().equals(categoria)) {
				System.out.println(produto);
			}
		}
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
