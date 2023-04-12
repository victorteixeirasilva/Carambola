package br.fam.Carambola.Principal;

import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Carambola {

	public static void main(String[] args) throws SQLException {
		
		menu();
		
		
		
		//**TESTE CLASSE PRODUTO ABAIXO**
		//Produto produto = new Produto("COCA", "Latinha de coca 600ml", 5.50, null, true); //Criando um produto
		//System.out.println(produto); //Exibindo detalhes desse produto com o método toString

		//***TESTE CLASSE CATEGORIA ABAIXO***
		//List<Produto> produtos = new ArrayList<Produto>();
		//produtos.add(new Produto("COCA", "Latinha de 600ml", 5.50, bebidas, true));//Criando um produto
		//produtos.add(new Produto("SPRITE", "Latinha de 600ml", 5.50, bebidas, true));//Criando um produto
		//produtos.add(new Produto("AGUA", "Latinha de 600ml", 5.50, bebidas, true));//Criando um produto
		//produtos.add(new Produto("BK", "LANCHE", 5.50, comidas, true));//Criando um produto
		//produtos.add(new Produto("MC", "LANCHE", 5.50, comidas, true));//Criando um produto
		//produtos.add(new Produto("OUTBACK", "LANCHE", 5.50, comidas, true));//Criando um produto
		//bebidas.verProdutosDaCategoria(produtos, bebidas);
		//bebidas.verProdutosDaCategoria(produtos, comidas);
		
		
		//TESTE BANCO DE DADOS H2
		//System.out.println("ola");
		//Categoria bebidas = new Categoria("Bebidas",14);
		//Produto produto1 = new Produto("COCA", "LATA 600ML", 6.5, bebidas, true);
		//Produto produto2 = new Produto("SPRITE", "LATA 600ML", 6.5, bebidas, true);
		//Produto produto3 = new Produto("FANTA", "LATA 600ML", 6.5, bebidas, true);
		//bebidas.verProdutosDaCategoria();
		
		//TESTE CLASE CATALOGO -> METODO CADASTRAR CATEGORIA -> EDITAR CATEGORIA -EXCLUIR CATEGORIA
		//UsuarioEstabelecimento estabelecimento = new UsuarioEstabelecimento(); 
		//Catalogo catalogo = new Catalogo(estabelecimento);
		//catalogo.cadastrarCategorias();
		//catalogo.verCategorias();
		//catalogo.editarCategorias();
		//catalogo.excluirCategorias();
	}
	
	
	public static void menu() {
		JOptionPane.showMessageDialog(null, "SEJA BEM VINDO AO CARAMBOLA!");
		int escolha = JOptionPane.showConfirmDialog(null, 
				"Já possui cadastro em nosso sistema?"
				+ "\nSe sim escolha a opção (SIM)"
				+ "\nSe não escolha a opção (NÃO) para se cadastrar"
				+ "\nCaso não queira fazer login nem cadastro escolha a opção (CANCELAR)"
				);
		if(escolha == JOptionPane.YES_OPTION) {
			fazerLogin();
		} else if (escolha == JOptionPane.NO_OPTION) {
			cadastrarNoSistema();
		} else if (escolha == JOptionPane.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, 
						"Obrigado por usar o CARAMBOLA!"
					+ "\nESPERAMOS SEU RETORNO EM BREVE!");
		}
	}
	
	public static void cadastrarNoSistema() {
		
	}
	public static void fazerLogin() {
		
	}
	
}
