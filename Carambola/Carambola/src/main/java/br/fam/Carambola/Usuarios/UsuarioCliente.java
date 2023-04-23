package br.fam.Carambola.Usuarios;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import br.fam.Carambola.Catalogo;
import br.fam.Carambola.Categoria;
import br.fam.Carambola.Endereco;
import br.fam.Carambola.FormaDePagamento;
import br.fam.Carambola.Formatador;
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

	private void reservarMesa(int idEstabelecimento, int idUsuario) throws SQLException {
		if(conn.verificarSeExisteMesas(idEstabelecimento)) {
			conn.mostrarMesasDisponiveis(idEstabelecimento);
			String mesaReservadaString = JOptionPane.showInputDialog("Informe o número da mesa que deseja reservar:");
			int mesaReservada = Integer.parseInt(mesaReservadaString);
			//Codigo para mudar o status de disponivel da mesa para false
			conn.insert("UPDATE TB_MESA_COMANDA SET MES_DISPONIVEL = FALSE WHERE MES_NUMERO = "+mesaReservada+" AND MES_IDUSUEST = "+idEstabelecimento+";");
			//Codigo para verificar se a mesa foi reservada corretamente, se sim informar ao usuario
			JOptionPane.showMessageDialog(null, "Mesa número "+mesaReservada+" foi reservada corretamente!");
			//Mostrar o catalogo
			Catalogo catalogo = new Catalogo();
			catalogo.verCategorias(idEstabelecimento);
			Categoria categoria = new Categoria();
			categoria.verProdutosDaCategoria();
			comprarProdutos(idEstabelecimento, idUsuario);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe mesas disponíveis nesse estabelecimento!");
		}
		
	}
	
	public void buscarEstabelecimento(int idUsuario) throws SQLException {
		JOptionPane.showMessageDialog(null, conn.queryVerTodosOsEstabelecimentos());
		String idEstabelecimentoString = JOptionPane.showInputDialog("Digite o Id do estabelecimento que deseja: ");
		int idEstabelecimento = Integer.parseInt(idEstabelecimentoString);
		if(idEstabelecimento == JOptionPane.CANCEL_OPTION) {
			buscarEstabelecimento(idUsuario);
		}
		escolherEstabelecimento(idEstabelecimento, idUsuario);
	}
	
	private void escolherEstabelecimento(int idEstabelecimento, int idUsuario) throws SQLException {
		String nomeEstabelecimento = conn.getNomeBdEstabelecimento(idEstabelecimento);
		JOptionPane.showMessageDialog(null, "Bem Vindo ao estabelecimento "+nomeEstabelecimento);
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja reservar uma mesa no estabelecimento?"
				+ "\nSe sim selecione a opção (YES)"
				+ "\nSe não selecione a opção (NO) para ver o catalogo"
				+ "\nSelecione (CANCEL) para retornar ao menu");
		
		switch (opcao) {
			case JOptionPane.YES_OPTION:
				JOptionPane.showMessageDialog(null, "Mesas disponíveis no estabelecimento:");
				JOptionPane.showMessageDialog(null, conn.mostrarMesasDisponiveis(idEstabelecimento));
				reservarMesa(idEstabelecimento, idUsuario);
				break;
			case JOptionPane.NO_OPTION:				
				Catalogo catalogo = new Catalogo();
				catalogo.verCategorias(idEstabelecimento);
				Categoria categoria = new Categoria();
				categoria.verProdutosDaCategoria();
				comprarProdutos(idEstabelecimento, idUsuario);
				break;
			case JOptionPane.CANCEL_OPTION:
				break;
		}
	}
	
	public void continuarComprando(int idEstabelecimento, int idUsuario, int idPedido) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.verCategorias(idEstabelecimento);
		Categoria categoria = new Categoria();
		categoria.verProdutosDaCategoria();
		
		String idProdutoString = JOptionPane.showInputDialog("Digite o id do produto que deseja comprar:");
		int idProduto = Integer.parseInt(idProdutoString);
		String quantidadeString = JOptionPane.showInputDialog("Digite a quantidade que deseja comprar:");
		int quantidade = Integer.parseInt(quantidadeString);
		int opcao = JOptionPane.showConfirmDialog(null, "Itens adicionado ao seu carrinho:"
				+ "\nDeseja continuar comprado precione (YES)"
				+ "\nDeseja finalizar seu pedido precione (NO)"
				+ "\nPara remover esses produtos do seu carrinho e cancelar o pedido (CANCEL)");
		Double valorTotalQtd = conn.getValorProdutoBd(idProduto)*quantidade;
		conn.insert("INSERT INTO TB_ITENSPEDIDO(ITE_IDCOM,ITE_CODPROD,ITE_QTDITENS,ITE_VALOR) VALUES("+idPedido+","+idProduto+","+quantidade+","+valorTotalQtd+");");
		switch(opcao) {
			case JOptionPane.YES_OPTION:
				continuarComprando(idEstabelecimento, idUsuario, idPedido);
				break;
			case JOptionPane.NO_OPTION:
				return;
			case JOptionPane.CANCEL_OPTION:
				break;
		}
	}
	
	public void comprarProdutos(int idEstabelecimento, int idUsuario) throws SQLException {
		String idProdutoString = JOptionPane.showInputDialog("Digite o id do produto que deseja comprar:");
		int idProduto = Integer.parseInt(idProdutoString);
		String quantidadeString = JOptionPane.showInputDialog("Digite a quantidade que deseja comprar:");
		int quantidade = Integer.parseInt(quantidadeString);
		int opcao = JOptionPane.showConfirmDialog(null, "Itens adicionado ao seu carrinho:"
				+ "\nDeseja continuar comprado precione (YES)"
				+ "\nDeseja finalizar seu pedido precione (NO)"
				+ "\nPara remover esses produtos do seu carrinho e cancelar o pedido(CANCEL)");
		LocalDate dataAtual = LocalDate.now();
		int idPedido = conn.insertPedido("INSERT INTO TB_PEDIDOS(COM_IDCOM,COM_IDUSU,COM_DATA,COM_STATUS,COM_IDEST) VALUES (NEXT VALUE FOR SQ_COM_IDCOM,"+idUsuario+",'"+dataAtual+"','EM ANDAMENTO',"+idEstabelecimento+");", idUsuario, dataAtual);
		Double valorTotalQtd = conn.getValorProdutoBd(idProduto)*quantidade;
		switch (opcao) {
		case JOptionPane.YES_OPTION:
			conn.insert("INSERT INTO TB_ITENSPEDIDO(ITE_IDCOM,ITE_CODPROD,ITE_QTDITENS,ITE_VALOR) VALUES("+idPedido+","+idProduto+","+quantidade+","+valorTotalQtd+");");
			
			continuarComprando(idEstabelecimento, idUsuario, idPedido);
			
			finalizarCompra(idEstabelecimento, idPedido);
			break;
		
		case JOptionPane.NO_OPTION:
			//Precisa inserir itens de pedido, pedido, em comanda ou mesa
			conn.insert("INSERT INTO TB_ITENSPEDIDO(ITE_IDCOM,ITE_CODPROD,ITE_QTDITENS,ITE_VALOR) VALUES("+idPedido+","+idProduto+","+quantidade+","+valorTotalQtd+");");
			
			finalizarCompra(idEstabelecimento, idPedido);
			
			break;
		case JOptionPane.CANCEL_OPTION:
			JOptionPane.showMessageDialog(null, "Produtos retirados do seu carrinho!");
			conn.insert("UPDATE TB_PEDIDOS SET COM_STATUS  = 'CANCELADO' WHERE COM_IDCOM = "+idPedido+";");//Alterar o Status desse pedido para CANCELADO
			break;
		}
	}
	
	public void finalizarCompra(int idEstabelecimento, int idPedido) throws SQLException {
		if(conn.verificarSeExisteMesas(idEstabelecimento)) {
			JOptionPane.showMessageDialog(null, conn.mostrarMesasDisponiveis(idEstabelecimento));
			String mesaReservadaString = JOptionPane.showInputDialog("Informe o número da mesa que deseja receber seu pedido:");
			int mesaReservada = Integer.parseInt(mesaReservadaString);
			//Codigo para mudar o status de disponivel da mesa para false
			conn.insert("UPDATE TB_MESA_COMANDA SET MES_DISPONIVEL = FALSE WHERE MES_NUMERO = "+mesaReservada+" AND MES_IDUSUEST = "+idEstabelecimento+";");
			//Codigo para verificar se a mesa foi reservada corretamente, se sim informar ao usuario
			JOptionPane.showMessageDialog(null, "Seu pedido será entregue na mesa número "+mesaReservada);
			//Mostrar detalhes dos pedido
			Double valorTotalPedido = conn.getValorPedidoBd(idPedido);
			conn.insert("UPDATE TB_PEDIDOS SET COM_VALORPEDIDO = "+valorTotalPedido+" WHERE COM_IDCOM = "+idPedido+";");//SQL para colocar o valor total do pedido no pedido
			String itensPedido = conn.getItensQuantidadePedido(idPedido);
			String statusPedido = conn.getStatusPedido(idPedido);
			JOptionPane.showMessageDialog(null, 
					"Detalhes do pedido!\n\n"
					+ "Número do pedido: "+idPedido
					+ "\nValor total do Pedido: "+Formatador.doubleToString(valorTotalPedido)
					+ "\nIntens do pedido: \n"+itensPedido
					+ "\nStatus do pedido: "+statusPedido
					+ "\n\nDigite ok assim que terminar de consumir seu pedido para liberar a mesa e fazer o pagamento!");
			//Código para escolher a forma de pagamento e fazer o pagamento
			//Informar que o pedido foi pago e liberara  mesa para uso
			conn.insert("UPDATE TB_MESA_COMANDA SET MES_DISPONIVEL = TRUE WHERE MES_NUMERO = "+mesaReservada+" AND MES_IDUSUEST = "+idEstabelecimento+";");
			conn.insert("UPDATE TB_PEDIDOS SET COM_STATUS  = 'FINALIZADO' WHERE COM_IDCOM = "+idPedido+";");
			JOptionPane.showMessageDialog(null, "Pedido pago de acordo com a sua forma de pagamento cadastrada, e mesa liberada para o próximo!");
		} else {
			JOptionPane.showMessageDialog(null, "Não existe mesas disponíveis nesse estabelecimento!");
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
