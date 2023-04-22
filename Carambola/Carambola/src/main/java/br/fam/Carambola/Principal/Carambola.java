package br.fam.Carambola.Principal;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Catalogo;
import br.fam.Carambola.Endereco;
import br.fam.Carambola.FormaDePagamento;
import br.fam.Carambola.Db.ConnectionDb;
import br.fam.Carambola.Usuarios.Usuario;
import br.fam.Carambola.Usuarios.UsuarioCliente;
import br.fam.Carambola.Usuarios.UsuarioEstabelecimento;


public class Carambola {
	
	public static void main(String[] args) throws SQLException {
		
		menu();
	}
	
	private static void menu() throws SQLException {
		JOptionPane.showMessageDialog(null, "SEJA BEM VINDO AO CARAMBOLA!");
		int escolha = JOptionPane.showConfirmDialog(null, 
				"Já possui cadastro em nosso sistema?\n"
				+ "\nSe sim escolha a opção (YES) para Login\n"
				+ "\nSe não escolha a opção (NO) para se Cadastrar\n"
				+ "\nCaso não queira fazer login nem cadastro escolha a opção (CANCEL)\n"
				);
		if(escolha == JOptionPane.YES_OPTION) {
			fazerLogin();
		} else if (escolha == JOptionPane.NO_OPTION) {
			cadastrarNoSistema();
			fazerLogin();
		} else if (escolha == JOptionPane.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, 
						"Obrigado por usar o CARAMBOLA!"
					+ "\nESPERAMOS SEU RETORNO EM BREVE!");
		} 
	}
	
	private static void cadastrarNoSistema() throws SQLException {
		int escolha = JOptionPane.showConfirmDialog(null, "Você deseja se cadastrar como Estabelecimento?");
		if (escolha == JOptionPane.YES_OPTION) {
			cadastrarEstabelecimento();
		} else if (escolha == JOptionPane.NO_OPTION) {
			cadastrarUsuarioCliente();
		}
	}
	
	private static void cadastrarUsuarioCliente() throws SQLException {
		//Cadastro do usuario
		String email = JOptionPane.showInputDialog("Informe o email para cadastro:");
		String senha = JOptionPane.showInputDialog("Informe a senha para cadastro:");
		String telefoneString = JOptionPane.showInputDialog("Informe o telefone para cadastro:");
		long telefone = Long.parseLong(telefoneString);
		
		//Cadastro UsuarioCliente
		String nome = JOptionPane.showInputDialog("Informe o nome completo:");
		String cpfString = JOptionPane.showInputDialog("Informe o cpf:");
		long cpf = Long.parseLong(cpfString);
		String dataNascimento = JOptionPane.showInputDialog("Informe a data de nascimento: \nLembrando de seguir esse formato (ANO-MÊS-DIA) \nExemplo 23 de agosto de 2003, (2003-08-23");
		
		//Cadastro Endereço Cliente
		String rua = JOptionPane.showInputDialog("Agora vamos cadastrar seu endereço!" + "\nDigite o nome da rua:");
		String bairro = JOptionPane.showInputDialog("Digite o nome do bairro:");
		String referencia = JOptionPane.showInputDialog("Digite uma referencia: \nCaso não tenha deixe em branco e clique em (ok)");
		String cep = JOptionPane.showInputDialog("Digite o CEP:");
		String numeroString = JOptionPane.showInputDialog("Digite o número da residencia:");
		int numero = Integer.parseInt(numeroString);
		
		//Criando Objeto do tipo usuario e fazendo o insert do usuário no banco de dados
		Usuario usuario = new Usuario(telefone,email,senha);
		
		//Criando Objeto do tipo usuario cliente e fazendo insert no banco de dados
		UsuarioCliente usuarioCliente = new UsuarioCliente(usuario, nome, cpf, dataNascimento);
		
		//Criando Objeto do tipo endereço e fazendo o insert no banco de dados
		Endereco endereco = new Endereco(rua, bairro, referencia, cep, numero, usuarioCliente);
		usuarioCliente.setEndereco(endereco);
		
		JOptionPane.showInputDialog("Cadastro finalizado com sucesso!");
	}
	
	private static void cadastrarEstabelecimento() throws SQLException {
		//Cadastro do usuario
		String email = JOptionPane.showInputDialog("Informe o email para cadastro:");
		String senha = JOptionPane.showInputDialog("Informe a senha para cadastro:");
		String telefoneString = JOptionPane.showInputDialog("Informe o telefone para cadastro");
		long telefone = Long.parseLong(telefoneString);
		
		//Cadastro UsuarioEstabelecimento
		String nomeEstabelecimento = JOptionPane.showInputDialog("Informe o nome do Estabelecimento:");
		String cnpjString = JOptionPane.showInputDialog("Informe o CNPJ do seu Estabelecimento");
		long cnpj = Long.parseLong(cnpjString);
		
		//Cadastro Endereço Estabelecimento
		String rua = JOptionPane.showInputDialog("Agora vamos cadastrar seu endereço!" + "\nDigite o nome da rua:");
		String bairro = JOptionPane.showInputDialog("Digite o nome do bairro:");
		String referencia = JOptionPane.showInputDialog("Digite uma referencia: \nCaso não tenha deixe em branco e clique em (ok)");
		String cep = JOptionPane.showInputDialog("Digite o CEP:");
		String numeroString = JOptionPane.showInputDialog("Digite o número da residencia:");
		int numero = Integer.parseInt(numeroString);
		
		//Criando Objeto do tipo usuario e fazendo o insert do usuário no banco de dados
		Usuario usuario = new Usuario(telefone,email,senha);
		
		//Criando Objeto do tipo usuario estabelecimento e fazendo insert no banco de dados
		UsuarioEstabelecimento usuarioEstabelecimento = new UsuarioEstabelecimento(usuario, nomeEstabelecimento, cnpj);

		//Criando Objeto do tipo endereço e fazendo o insert no banco de dados
		Endereco endereco = new Endereco(rua, bairro, referencia, cep, numero, usuarioEstabelecimento);
		usuarioEstabelecimento.setEndereco(endereco);
		
		ConnectionDb conn = new ConnectionDb();
		int idEstabelecimento = conn.buscarIdUsuario(email, senha);
		
		Catalogo catalogo = new Catalogo(usuarioEstabelecimento, idEstabelecimento);
		JOptionPane.showInputDialog("Cadastro finalizado com sucesso!");
		catalogo.getEstabelecimento();
	}
	
	private static void fazerLogin() throws SQLException {
		ConnectionDb conn = new ConnectionDb();
		String email = JOptionPane.showInputDialog("Informe o e-mail do usuário para login");
		String senha = JOptionPane.showInputDialog("Informe a senha do usuário para login");
		if(conn.verificarLoginBd(email, senha)) {
			int idUsuario = conn.buscarIdUsuario(email, senha);
			if(conn.verificarSeEstabelecimento(idUsuario)) {//Se usuario for cadastrado e for do tipo estabelecimento
				menuEstabelecimento(idUsuario);
			} else if (conn.verificarSeCliente(idUsuario)){ //Se usuario for cadatrado e for do tipo cliente
				menuUsuarioCliente(idUsuario);
			} else if (conn.verificarSeFuncionario(idUsuario)){ //Se usuario for cadatrado e for do tipo funcionario
				menuFuncionario(idUsuario);
			}
		} else {
			JOptionPane.showInputDialog("Email ou senha invalidos\n \nClique ok para tentar novamente!");
			fazerLogin();
		}
	}
	
	private static void menuFuncionario(int idUsuario) {
		
	}
	
	private static void menuEstabelecimento(int idEstabelecimento) throws SQLException {
		ConnectionDb conn = new ConnectionDb();
		int opcao;
		do {
		String nomeEstabelecimentoBd = conn.getNomeBdEstabelecimento(idEstabelecimento); 
		String opcaoString = JOptionPane.showInputDialog(
				"Olá "+nomeEstabelecimentoBd+" \n"
				+ "\nBem vindo ao menu do CARAMBOLA!\n"
				
				+ "\nEscolha uma das opões abaixo:\n\n"
				
				+ "1-Verificar Mesas\n"//ok
				+ "2-Atualizar Estoque\n"//ok
				+ "3-Cadastrar Categorias\n"//ok
				+ "4-Ver Categorias\n"//ok
				+ "5-Editar Categorias\n"//ok
				+ "6-Excluir Categorias\n"//ok
				+ "7-Cadastrar Produto\n"//ok
				+ "8-Excluir Produto\n"//ok
				+ "9-Editar Produto\n"//ok
				+ "10-Ver Produtos\n"//ok
				+ "11-Verificar Faturamento\n"//Implementacão Futura
				+ "12-Impulsionar Estabelecimento\n"//Implementacão Futura
				+ "13-Ver Funcionários Cadastrados\n"//Implementacão Futura
				+ "14-Editar Funcionários Cadastrados\n"//Implementacão Futura
				+ "15-Excluir Funcionários Cadastrados\n"//Implementacão Futura
				+ "16-Cadastrar Funcinários\n"//Implementacão Futura
				+ "17-Simular Compra\n"
				+ "18-Desconectar-se\n"//ok
				+ "19-Sair\n"//ok
				);
		
		UsuarioEstabelecimento estabelecimento = new UsuarioEstabelecimento();
		Catalogo catalogo = new Catalogo();
		opcao = Integer.parseInt(opcaoString);
		switch (opcao){
			case 1:
				//Verificar Mesas
				estabelecimento.verificarMesas(idEstabelecimento);
				break;
			case 2:
				//Atualizar Estoque
				estabelecimento.atualizarEstoque(idEstabelecimento);
				break;
			case 3:
				//Cadastrar Categorias
				catalogo.cadastrarCategorias(idEstabelecimento);
				break;
			case 4:
				//Ver Categorias
				catalogo.verCategorias(idEstabelecimento);
				break;
			case 5:
				//Editar Categorias
				catalogo.editarCategorias(idEstabelecimento);
				break;
			case 6:
				//Excluir Categorias
				catalogo.excluirCategorias(idEstabelecimento);
				break;
			case 7:
				//Cadastrar Produto
				estabelecimento.cadastrarProduto(idEstabelecimento);
				break;
			case 8:
				//Excluir Produto
				estabelecimento.excluirProdutos(idEstabelecimento);
				break;
			case 9:
				//Editar Produtos
				estabelecimento.editarProdutos(idEstabelecimento);
				break;
			case 10:
				//Ver Produto
				estabelecimento.verProdutos(idEstabelecimento);
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
			case 14:
				break;
			case 15:
				break;
			case 16:
				break;
			case 17:
				break;
			case 18:
				menu();
				break;
			case 19:
				JOptionPane.showMessageDialog(null, 
						"Obrigado por usar o CARAMBOLA!"
					+ "\nESPERAMOS SEU RETORNO EM BREVE!");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção invalida, por favor tente novamente!");
				}
		} while (opcao != 19);
	}
	
	private static void menuUsuarioCliente(int idUsuario) throws SQLException {
		ConnectionDb conn = new ConnectionDb();
		int opcao;
		do {
		String nomeClienteBd = conn.getNomeBdCliente(idUsuario); 
		String opcaoString = JOptionPane.showInputDialog(
				"Olá "+nomeClienteBd+" \n"
				+ "\nBem vindo ao menu do CARAMBOLA!\n"
				+ "\nEscolha uma das opões abaixo:\n\n"
				+ "1-Buscar Estabelecimento\n"//ok
				+ "2-Ver Informações da Conta\n"//ok
				+ "3-Cadastrar Forma de Pagamento\n"//ok
				+ "4-Ver Formas de Pagamento Cadastradas\n"//ok
				+ "5-Excluir Forma de Pagamento\n"//ok
				+ "6-Desconectar-se\n"//ok
				+ "7-Sair\n"//ok
				);
		
		UsuarioCliente usuario = new UsuarioCliente();
		FormaDePagamento formaDePagamento = new FormaDePagamento();
		opcao = Integer.parseInt(opcaoString);
		switch (opcao){
		case 1:
			usuario.buscarEstabelecimento(idUsuario); //Falta apenas a parte de comprar produtos
			break;
		case 2:
			usuario.verInformacoesDaConta(idUsuario);
			break;
		case 3:
			formaDePagamento.cadastrarFormaDePagamento(idUsuario);
			break;
		case 4:
			formaDePagamento.verFormaDePagamento(idUsuario);
			break;
		case 5:
			formaDePagamento.excluirFormaDePagamento(idUsuario);
			break;
		case 6:
			menu();
			break;
		case 7:
			JOptionPane.showMessageDialog(null, 
					"Obrigado por usar o CARAMBOLA!"
				+ "\nESPERAMOS SEU RETORNO EM BREVE!");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção invalida, por favor tente novamente!");
			
			}
		} while (opcao != 7);
	}
	
}
