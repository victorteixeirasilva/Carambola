package br.carambola;

public class Produto {
	private static int count = 500; //Foi Adicionado o Atributo count para sempre gerar um id com um número depois.
	private int id; 
	private String nome, descricao;
	private Double preco;
	private Categoria categoria;
	private boolean temEstoque;
	
	public Produto(String nome, String descricao, Double preco, Categoria categoria, boolean temEstoque) {
		super();
		this.id = count;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.categoria = categoria;
		this.temEstoque = temEstoque;
		Produto.count += 1;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public boolean isTemEstoque() {
		return temEstoque;
	}
	public void setTemEstoque(boolean temEstoque) {
		this.temEstoque = temEstoque;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Produto [id=" + id + "\nnome=" + nome + "\ndescricao=" + descricao + "\npreco=" + preco + "\ncategoria="
				+ categoria + "\ntemEstoque=" + temEstoque + "]";
	}
	
	
}
