package modelo;

import utils.Utils;

public class Produto {
	private static int count = 1;
	private int id;
	private String nome;
	private Double preco;
	
	public Produto(String nome, Double preco) {
		this.id = count;
		this.nome = nome;
		this.preco = preco;
		Produto.count += 1;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public int getId() {
		return id;
	}
	
	public String toString() {
		return 	 "ID: " 	 + this.getId()
				+"\nNome: "  + this.getNome()
				+"\nPreço: " + Utils.doubleToString(this.getPreco());
	}
	
	
	
	
	
}
