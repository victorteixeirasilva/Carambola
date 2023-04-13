package br.fam.Carambola.Principal;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Endereco;
import br.fam.Carambola.Db.ConnectionDb;
import br.fam.Carambola.Usuarios.Usuario;
import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;


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
	
	
	public static void menu() throws SQLException {
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
	
	public static void cadastrarNoSistema() throws SQLException {
		ConnectionDb conn = new ConnectionDb();
		int escolha = JOptionPane.showConfirmDialog(null, "Você deseja se cadastrar como Estabelecimento?");
		if (escolha == JOptionPane.YES_OPTION) {
			cadastrarEstabelecimento();
		}
	}
	
	public static void cadastrarEstabelecimento() throws SQLException {
		//Cadastro do usuario
		String email = JOptionPane.showInputDialog("Informe o email para cadastro:");
		String senha = JOptionPane.showInputDialog("Informe a senha para cadastro:");
		
		//Cadastro UsuarioEstabelecimento
		String telefoneString = JOptionPane.showInputDialog("Informe o telefone para cadastro");
		int telefone = Integer.parseInt(telefoneString);
		String nomeEstabelecimento = JOptionPane.showInputDialog("Informe o nome do Estabelecimento:");
		String cnpjString = JOptionPane.showInputDialog("Informe o CNPJ do seu Estabelecimento");
		long cnpj = Long.parseLong(cnpjString);
		
		//Cadastro Endereço
		String rua = JOptionPane.showInputDialog("Agora vamos cadastrar seu endereço!" + "\nDigite o nome da rua:");
		String bairro = JOptionPane.showInputDialog("Digite o nome do bairro:");
		String referencia = JOptionPane.showInputDialog("Digite uma referencia: \nCaso não tenha deixe em branco e clique em (ok)");
		String cep = JOptionPane.showInputDialog("Digite o CEP:");
		String numeroString = JOptionPane.showInputDialog("Digite o número da residencia:");
		int numero = Integer.parseInt(numeroString);
		
		Usuario usuario = new Usuario(telefone,email,senha);
		
		//UsuarioEstabelecimento usuarioEstabelecimento = new UsuarioEstabelecimento(usuario, null, cnpj, telefone, numero);
		
		//Endereco endereco = new Endereco(rua, bairro, referencia, telefone, numero,usuarioEstabelecimento.getId());
	}
	public static void fazerLogin() {
		ConnectionDb conn = new ConnectionDb();
		String email = JOptionPane.showInputDialog("Informe o e-mail do usuário para login");
		String senha = JOptionPane.showInputDialog("Informe a senha do usuário para login");
		conn.verificarLoginBd(email, senha);	
	}
	
}
