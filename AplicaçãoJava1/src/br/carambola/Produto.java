package br.carambola;

public class Produto {
	private static int count = 500; //Foi Adicionado o Atributo count para sempre gerar um id com um número depois.
	private int id; 
	private String nome, descricao;
	private Double preco;
	private Categoria categoria;
	private boolean temEstoque;
	
	//Construtor da classe produto que recebe nome, descricao, preco categoria, e se tem estoque
	public Produto(String nome, String descricao, Double preco, Categoria categoria, boolean temEstoque) {
		super();
		this.id = count; //id do produto recebe o contador que é incrementado no final do construtor fazendo assim que ele gere o id automatico
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
		return    "***Produto***"
				+ "\nId=" + id
				+ "\nNome=" + nome 
				+ "\nDescricao=" + descricao 
				+ "\nPreço=" + Formatador.doubleToString(this.getPreco()) 
				+ "\nCategoria=" + categoria 
				+ "\nSe tem Estoque=" + temEstoque 
				+ "\n*************";
	}
	
	
}
