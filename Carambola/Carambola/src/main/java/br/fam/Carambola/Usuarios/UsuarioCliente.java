package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Catalogo;
import br.fam.Carambola.Categoria;
import br.fam.Carambola.Endereco;
import br.fam.Carambola.FormaDePagamento;
import br.fam.Carambola.Db.ConnectionDb;

public class UsuarioCliente{
	private Usuario usuario;
	private String nome, email, senha, dataNascimento;
	private long cpf;
	private int telefone;
	private int id;
	private Endereco endereco;
	private FormaDePagamento formaDePagamento;
	private ConnectionDb conn = new ConnectionDb();
	
	
	public UsuarioCliente() {
		
	}
	
	public UsuarioCliente(Usuario usuario,String nome, long cpf, String dataNascimento) throws SQLException {
		this.nome = nome;
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.id = usuario.getId();
		
		conn.insert("INSERT INTO TB_USUARIOS_CLIENTE(USUCLI_IDUSUCLI, USUCLI_IDUSU, USUCLI_NOME, USU_DATANASC, USU_CPF) VALUES ("+this.id+", "+this.id+", '"+this.nome+"', '"+this.dataNascimento+"', "+this.cpf+");");
		
	}
	
	public void verInformacoesDaConta(int idUsuario) throws SQLException {
	String opcaoString = JOptionPane.showInputDialog(null, 
				  "Bem Vindo aos Detalhes da sua conta:\n" 
			    + "\n1 - Nome: "+conn.getNomeBdCliente(idUsuario)
				+ "\n2 - E-MAIL: "+conn.getEmailUsuarioCliente(idUsuario)
				+ "\n3 - Senha: "+conn.getSenhaUsuarioCliente(idUsuario)
				+ "\n4 - CPF:"+conn.getCPFUsuarioCliente(idUsuario)
				+ "\n5 - Caso não deseje editar nenhuma informação\n"
				+ "\nInforme o número cujo a informação deseja editar");
	int opcao = Integer.parseInt(opcaoString);
	int opcao2;
	switch(opcao) {
		case 1:
			String novoNome = JOptionPane.showInputDialog("Nome: "+conn.getNomeBdCliente(idUsuario)+"\n\nInforme o Novo Nome:");
			opcao2 = JOptionPane.showConfirmDialog(null, "O Nome será alterado para "+novoNome+"\n\nTem certeza que deseja fazer esssa edição?"
					+ "\n\nPara confirmar edição selecione (YES)."
					+ "\n\nCaso deseje editar novamente selecione (NO)."
					+ "\n\nCaso queira cancelar edição selecione (CANCEL).");
			if(opcao2 == JOptionPane.YES_OPTION) {			
				conn.update("UPDATE TB_USUARIOS_CLIENTE SET USUCLI_NOME = '"+novoNome+"' WHERE USUCLI_IDUSUCLI = "+idUsuario+";");
			} else if(opcao2 == JOptionPane.NO_OPTION) {
				verInformacoesDaConta(idUsuario);
			} else if (opcao2 == JOptionPane.CANCEL_OPTION) {
				return;
			}
			break;
		case 2:
			String novoEmail = JOptionPane.showInputDialog("E-MAIL: "+conn.getEmailUsuarioCliente(idUsuario)+"\n\nInforme o Novo E-MAIL:");
			opcao2 = JOptionPane.showConfirmDialog(null, "O e-mail será alterado para "+novoEmail+"\n\nTem certeza que deseja fazer esssa edição?"
					+ "\n\nPara confirmar edição selecione (YES)."
					+ "\n\nCaso deseje editar novamente selecione (NO)."
					+ "\n\nCaso queira cancelar edição selecione (CANCEL).");
			if(opcao2 == JOptionPane.YES_OPTION) {			
				conn.update("UPDATE TB_USUARIOS SET USU_EMAIL = '"+novoEmail+"' WHERE USU_IDUSU = "+idUsuario+";");
			} else if(opcao2 == JOptionPane.NO_OPTION) {
				verInformacoesDaConta(idUsuario);
			} else if (opcao2 == JOptionPane.CANCEL_OPTION) {
				return;
			}
			break;
		case 3:
			String novaSenha = JOptionPane.showInputDialog("Senha: "+conn.getSenhaUsuarioCliente(idUsuario)+"\n\nInforme o Nova Senha:");
			opcao2 = JOptionPane.showConfirmDialog(null, "A senha será alterado para "+novaSenha+"\n\nTem certeza que deseja fazer esssa edição?"
					+ "\n\nPara confirmar edição selecione (YES)."
					+ "\n\nCaso deseje editar novamente selecione (NO)."
					+ "\n\nCaso queira cancelar edição selecione (CANCEL).");
			if(opcao2 == JOptionPane.YES_OPTION) {			
				conn.update("UPDATE TB_USUARIOS SET USU_SENHA = '"+novaSenha+"' WHERE USU_IDUSU = "+idUsuario+";");
			} else if(opcao2 == JOptionPane.NO_OPTION) {
				verInformacoesDaConta(idUsuario);
			} else if (opcao2 == JOptionPane.CANCEL_OPTION) {
				return;
			}		
			break;
		case 4:
			String novoCPF = JOptionPane.showInputDialog("CPF: "+conn.getCPFUsuarioCliente(idUsuario)+"\n\nInforme o Novo CPF:");
			opcao2 = JOptionPane.showConfirmDialog(null, "O CPF será alterado para "+novoCPF+"\n\nTem certeza que deseja fazer esssa edição?"
					+ "\n\nPara confirmar edição selecione (YES)."
					+ "\n\nCaso deseje editar novamente selecione (NO)."
					+ "\n\nCaso queira cancelar edição selecione (CANCEL).");
			if(opcao2 == JOptionPane.YES_OPTION) {			
				conn.update("UPDATE TB_USUARIOS_CLIENTE SET USU_CPF = '"+novoCPF+"' WHERE USUCLI_IDUSUCLI = "+idUsuario+";");
			} else if(opcao2 == JOptionPane.NO_OPTION) {
				verInformacoesDaConta(idUsuario);
			} else if (opcao2 == JOptionPane.CANCEL_OPTION) {
				return;
			}
			break;
		case 5:
			return;
		default:
			JOptionPane.showMessageDialog(null, "Você informou uma opção inválida");
			verInformacoesDaConta(idUsuario);
	}
	
	
	
	}

	private void reservarMesa() {
		String mesaReservadaString = JOptionPane.showInputDialog("Informe o número da mesa que deseja reservar:");
		int mesaReservada = Integer.parseInt(mesaReservadaString);
		//Codigo para mudar o status de disponivel da mesa para false
		
		//Codigo para verificar se a mesa foi reservada corretamente, se sim informar ao usuario
		
	}
	
	public void buscarEstabelecimento() throws SQLException {
		conn.queryVerTodosOsEstabelecimentos();
		String idEstabelecimentoString = JOptionPane.showInputDialog("Digite o Id do estabelecimento que deseja: ");
		int idEstabelecimento = Integer.parseInt(idEstabelecimentoString);
		if(idEstabelecimento == JOptionPane.CANCEL_OPTION) {
			buscarEstabelecimento();
		}
		escolherEstabelecimento(idEstabelecimento);
	}
	
	private void escolherEstabelecimento(int idEstabelecimento) throws SQLException {
		String nomeEstabelecimento = conn.getNomeBdEstabelecimento(idEstabelecimento);
		JOptionPane.showMessageDialog(null, "Bem Vindo ao estabelecimento "+nomeEstabelecimento);
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja reservar uma mesa no estabelecimento?"
				+ "\nSe sim selecione a opção (YES)"
				+ "\nSe não selecione a opção (NO) para ver o catalogo"
				+ "\nSelecione (CANCEL) para retornar ao menu");
		
		switch (opcao) {
			case JOptionPane.YES_OPTION:
				JOptionPane.showMessageDialog(null, "Mesas disponíveis no estabelecimento:");
				conn.mostrarMesasDisponiveis();
				reservarMesa();
				break;
			case JOptionPane.NO_OPTION:				
				Catalogo catalogo = new Catalogo();
				catalogo.verCategorias(idEstabelecimento);
				Categoria categoria = new Categoria();
				categoria.verProdutosDaCategoria();
				comprarProdutos(idEstabelecimento);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
		}
	}
	
	public void comprarProdutos(int idEstabelecimento) throws SQLException {
		String idProdutoString = JOptionPane.showInputDialog("Digite o id do produto que deseja comprar:");
		int idProduto = Integer.parseInt(idProdutoString);
		String quantidadeString = JOptionPane.showInputDialog("Digite a quantidade que deseja comprar:");
		int quantidade = Integer.parseInt(quantidadeString);
		int opcao = JOptionPane.showConfirmDialog(null, "Itens adicionado ao seu carrinho:"
				+ "\nDeseja continuar comprado precione (YES)"
				+ "\nDeseja finalizar seu pedido precione (NO)"
				+ "\nPara remover esses produtos do seu carrinho (CANCEL)");
		switch (opcao) {
		case JOptionPane.YES_OPTION:
			conn.insert("");
			Catalogo catalogo = new Catalogo();
			catalogo.verCategorias(idEstabelecimento);
			Categoria categoria = new Categoria();
			categoria.verProdutosDaCategoria();
			comprarProdutos(idEstabelecimento);
			//Falta perguntar ao usuário o número da mesa em que ele está
			break;
		case JOptionPane.NO_OPTION:
			//Precisa inserir itens de pedido, pedido, em comanda ou mesa
			conn.insert("");
			break;
		case JOptionPane.CANCEL_OPTION:
			JOptionPane.showMessageDialog(null, "Produtos retirados do seu carrinho!");
			break;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
