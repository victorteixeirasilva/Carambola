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
		
		//
		Catalogo catalogo = new Catalogo(usuarioEstabelecimento);
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
	
	private static void menuEstabelecimento(int idEstabelecimento) {
		
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
				+ "1-Buscar Estabelecimento\n"
				+ "2-Ver Informações da Conta\n"
				+ "3-Cadastrar Forma de Pagamento\n"
				+ "4-Ver Forma de Pagamento Cadastrada\n"
				+ "5-Editar Forma de Pagamento\n"
				+ "6-Excluir Forma de Pagamento\n"
				+ "7-Desconectar-se\n"
				+ "8-Sair\n"
				);
		
		UsuarioCliente usuario = new UsuarioCliente();
		FormaDePagamento formaDePagamento = new FormaDePagamento();
		opcao = Integer.parseInt(opcaoString);
		switch (opcao){
		case 1:
			usuario.buscarEstabelecimento();
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
			formaDePagamento.editarFomraDePagamento(idUsuario); //Não implementado ainda
		case 6:
			usuario.getFormaDePagamento().excluirFormaDePagamento(); //Não implementado ainda
		case 7:
			fazerLogin();
			break;
		case 8:
			JOptionPane.showMessageDialog(null, 
					"Obrigado por usar o CARAMBOLA!"
				+ "\nESPERAMOS SEU RETORNO EM BREVE!");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção invalida, por favor tente novamente!");
			
			}
		} while (opcao != 8);
	}
	
}
