package main;
//Carambola
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Produto;
import utils.Utils;

public class Carambola {
	private static ArrayList<Produto> produtos;
	private static Map<Produto, Integer> carrinho;
	
	public static void main(String[] args) {
		produtos = new ArrayList<>();
		carrinho = new HashMap<>();
		menu();
	}
	
	public static void menu() {
		JOptionPane.showMessageDialog(null, "Bem Vindo ao Carambola!");
		int option = Integer.parseInt(JOptionPane.showInputDialog(
					 "*** Selecione uma operação que deseja realizar ***"
					+"\n| Opção 1 - Cadastrar |"
					+"\n| Opção 2 - Listar    |"
					+"\n| Opção 3 - Comprar   |"
					+"\n| Opção 4 - Carrinho  |"
					+"\n| Opção 5 - Sair      |"
					));
		
		switch (option) {
		case 1:
			cadastrarProdutos();
			break;
		case 2:
			listarProdutos();
			break;
		case 3:
			comprarProdutos();
			break;
		case 4:
			verCarrinho();
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "Volte sempre!");
			System.exit(0);
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida!");
			menu();
			break;
		}
		
	}
	
	private static void cadastrarProdutos() {
		String nome = JOptionPane.showInputDialog("Nome do produto: ");
		Double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço  do produto: "));
		
		Produto produto = new Produto(nome, preco);
		produtos.add(produto);
		
		JOptionPane.showMessageDialog(null, produto.getNome() + " cadastrado com sucesso!");
		menu();
		
	}
	
	private static void listarProdutos() {
		if (produtos.size() > 0) {
			JOptionPane.showMessageDialog(null, "Lista de produtos!");
			
			for (Produto p : produtos) {
				JOptionPane.showMessageDialog(null, p);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado!");
		}
		menu();
	}
	
	private static void comprarProdutos(){
		if (produtos.size() > 0) {
			JOptionPane.showMessageDialog(null, "Código de produto: \n"
					+ "---------- Produtos Disponíveis ----------");
			for (Produto p : produtos) {
				JOptionPane.showMessageDialog(null, p + "\n");
			}
			int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto: "));
			boolean isPresent = false;
			
			for(Produto p : produtos) {
				if (p.getId() == id) {
					int qtd = 0;
					try {
						qtd = carrinho.get(p);
						// Checa se o produto já está no carrinho, incrementa quantidade
						carrinho.put(p, qtd + 1);
					} catch (NullPointerException e) {
						// se o produto for o primeiro no carrinho
						carrinho.put(p, 1);
					}
					JOptionPane.showMessageDialog(null, p.getNome() + " adicionado ao carrinho.");
					isPresent = true;
					
					if (isPresent) {
						int option = Integer.parseInt(JOptionPane.showInputDialog(
									 "Deseha adicionar outro produto ao carrinho? \n"
									+"Digite 1 para sim, ou 0 para finalizar a compra."));
						if (option == 1) {
							comprarProdutos();
						} else {
							finalizarCompra();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado.");
					menu();
				}
			}
			
		} else { 
			JOptionPane.showMessageDialog(null, "Não existem produtos cadastrados!");
			menu();
		}
	}
	
	private static void verCarrinho() {
		JOptionPane.showMessageDialog(null, "---- Produtos no seu carrinho! ----");
		if (carrinho.size() > 0) {
			for (Produto p : carrinho.keySet()) {
				JOptionPane.showMessageDialog(null, "Produto: " + p + "\nQuantidade: " + carrinho.get(p));
			}
		} else {
			JOptionPane.showMessageDialog(null, "Carrinho vazio!");
		}
		menu();
	}
	
	private static void finalizarCompra() {
		Double valorDaCompra = 0.0;
		JOptionPane.showMessageDialog(null, "Seus produtos!");
		
		for (Produto p : carrinho.keySet()) {
			int qtd = carrinho.get(p);
			valorDaCompra += p.getPreco() * qtd;
			JOptionPane.showMessageDialog(null,
					 p 
					+"\nQuantidade: " + qtd
					+"\n------------------------");
		}
		JOptionPane.showMessageDialog(null, "O valor da sua compra é: " + Utils.doubleToString(valorDaCompra));
		carrinho.clear();
		JOptionPane.showMessageDialog(null, "Obrigado pela preferência!");
	}
	
	
	
	
	
	
}
