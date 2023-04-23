package br.fam.Carambola.Usuarios;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.fam.Carambola.Catalogo;
import br.fam.Carambola.Categoria;
import br.fam.Carambola.Endereco;
import br.fam.Carambola.Formatador;
import br.fam.Carambola.Db.ConnectionDb;

public class UsuarioEstabelecimento  {
	private String nome, email, senha;
	private Endereco endereco;
	private float classificacao;
	private int id;
	private long telefone;
	private long cnpj;
	private Usuario usuario;
	private ConnectionDb conn = new ConnectionDb();
	
	public UsuarioEstabelecimento() {
		
	}
	
	public UsuarioEstabelecimento(Usuario usuario, String nomeEstabelecimento , long cnpj) throws SQLException {
		this.usuario = usuario;
		this.nome = nomeEstabelecimento;
		this.email = this.usuario.getEmail();
		this.senha = this.usuario.getSenha();
		this.cnpj = cnpj;
		this.telefone = this.usuario.getTelefone();
		this.id = usuario.getId();
		conn.insert("INSERT INTO TB_USUARIOS_ESTABELECIMENTO(USUEST_IDUSUEST, USUEST_IDUSU, USUEST_NOME, USU_CNPJ) VALUES ("+this.usuario.getId()+", "+this.usuario.getId()+", '"+this.nome+"', "+this.cnpj+");");
		
	}

	private void cadastrarNovaMesa(int idEstabelecimento) throws SQLException {
		int opcao = JOptionPane.showConfirmDialog(null, "Deseja criar uma nova mesa ou comanda?\n\n");
		if(opcao == JOptionPane.YES_OPTION) {
			String numeroMesaComandaString = JOptionPane.showInputDialog("Informe o número da mesa ou comanda que deseja cadastrar!\n\n" + "OBS: Lembrando que esse número é apenas para" + "\n melhorar a logistica em seu estabelecimento");
			int numeroMesaComanda = Integer.parseInt(numeroMesaComandaString);
			conn.insert("INSERT INTO TB_MESA_COMANDA(MES_IDMES,MES_IDUSUEST,MES_NUMERO,MES_DISPONIVEL) VALUES (NEXT VALUE FOR SQ_MES_IDMES, "+idEstabelecimento+", "+numeroMesaComanda+", TRUE);");
			JOptionPane.showMessageDialog(null, "Nova Mesa ou Comanda Cadastrada corretamente!");
		} else if (opcao == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Nenhuma mesa ou comanda cadastrada no seu estabelecimento!");
			return;
		}
	}
	
	public void verificarMesas(int idEstabelecimento) throws SQLException {
		if(conn.verificarSeExisteMesas(idEstabelecimento)) {
			String nomeEstabelecimento = conn.getNomeBdEstabelecimento(idEstabelecimento);
			JOptionPane.showMessageDialog(null, "As mesas ou comandas presentes no seu estabelecimento são!" + "\nNome Estabelecimento: "+nomeEstabelecimento);
			conn.mostrarMesasDisponiveis(idEstabelecimento);
			int opcao1 = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar uma nova mesa ou comanda?");
			if(opcao1 == JOptionPane.YES_OPTION) {
				cadastrarNovaMesa(idEstabelecimento);
			} else {
				return;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Não existem mesas nem comandas em seu estabelecimento!\n\n");
			cadastrarNovaMesa(idEstabelecimento);
			return;
		}
	}
	
	
	
	public void cadastrarProduto(int idEstabelecimento) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.verCategorias(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o Id da categoria cujo qual deseja cadastrar seu produto:\n\n");
		int idCategoria = Integer.parseInt(idCategoriaString);
		if(idEstabelecimento == conn.queryIdCatalogoComIdCategoria(idCategoria)) {
			String nomeProduto = JOptionPane.showInputDialog("Informe o Nome do produto:");
			String precoProdutoString = JOptionPane.showInputDialog("Informe o valor unitario do produto:");
			Double precoProduto = Double.parseDouble(precoProdutoString);
			int opcao = JOptionPane.showConfirmDialog(null, "Esse produto tem estoque? e está disponivel para venda?");
			boolean disponivel;
			if(opcao == JOptionPane.YES_OPTION) {
				disponivel = true;
			} else if (opcao == JOptionPane.CANCEL_OPTION) {
				JOptionPane.showMessageDialog(null, "Produto não foi cadastrado!");
				return;
			} else {
				disponivel = false;				
			}
			conn.insert("INSERT INTO TB_PRODUTOS(PRO_IDPROD, PRO_IDCATE, PRO_DESC, PRO_VALOR, PRO_TEMESTOQUE) VALUES (NEXT VALUE FOR SQ_PRO_IDPROD, "+idCategoria+", '"+nomeProduto+"', "+precoProduto+", "+disponivel+");");
		} else {
			JOptionPane.showMessageDialog(null, "Você não possui categoria com esse id");
		}
	}
	
	public void editarProdutos(int idEstabelecimento) throws SQLException {
		//Mostrar todos os Produtos cadastrados
		JOptionPane.showMessageDialog(null, "Selecione um produto dentro de uma categoria pelo id");
		verProdutos(idEstabelecimento);
		//Escolher Produto para edição
		String input = JOptionPane.showInputDialog("Informe o Id do produto que deseja editar:");
		int idProduto = Integer.parseInt(input);
		//Mostrar detalhes do Produto escolhido e numerar as opções
		//if(idEstabelecimento == conn.queryIdEstabelecimentoComIdProduto(idProduto)) {//Verifica se o id do catalogo é igual o id do estabelecimento informado
			int idProdutoBd = conn.getIdProdutoBd(idProduto);
			String nomeProdutoBd = conn.getNomeProdutoBd(idProdutoBd);
			Double valorBd = conn.getValorProdutoBd(idProdutoBd);
			//escolher a informação para ser editada
			String escolhaString = JOptionPane.showInputDialog(
					  "Detalhes do produto informado para edição:\n"
					+ "\nId do Produto: "+idProdutoBd
					+ "\n1-Nome do Produto: "+nomeProdutoBd
					+ "\n2-Valor do Produto: "+Formatador.doubleToString(valorBd)
					+ "\n\nEscolha a informação cuja qual você deseja editar: ");
			int escolha = Integer.parseInt(escolhaString);
			int opcao;
			switch(escolha){
				case 1:
					String novoNome = JOptionPane.showInputDialog(
							  "Nome atual do produto: "+nomeProdutoBd
							+ "\n\nInforme o novo nome do produto: ");
					if(novoNome != nomeProdutoBd) {
						//Altera nome
						opcao = JOptionPane.showConfirmDialog(null, "O nome do produto será alterado para: "+novoNome
								+ "\n\nTem certeza que deseja fazer essa alteração?");
						if(opcao == JOptionPane.YES_OPTION) {
							conn.insert("UPDATE TB_PRODUTOS SET PRO_DESC = '"+novoNome+"' WHERE PRO_IDPROD = "+idProduto+";");
							JOptionPane.showMessageDialog(null, "Nome do produto foi alterado corretamente para: "+novoNome);
						} else {
							JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Nome informado é igual ao nome original, nenhuma alteração foi feita!");
					}
					break;
				case 2:
					String novoValorString = JOptionPane.showInputDialog(
							  "Valor atual do produto: "+Formatador.doubleToString(valorBd)
							+ "\n\nInforme o novo valor do produto: ");
					Double novoValor = Double.parseDouble(novoValorString);
					if(novoValor != valorBd) {
						//Altera valor
						opcao = JOptionPane.showConfirmDialog(null, "O valor do produto será alterado para: "+Formatador.doubleToString(novoValor)
								+ "\n\nTem certeza que deseja fazer essa alteração?");
						if(opcao == JOptionPane.YES_OPTION) {
							conn.insert("UPDATE TB_PRODUTOS SET PRO_VALOR = "+novoValor+" WHERE PRO_IDPROD = "+idProduto+";");
							JOptionPane.showMessageDialog(null, "Valor do produto foi alterado corretamente para: "+Formatador.doubleToString(novoValor));
						} else {
							JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita!");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Valor informado é igual ao valor original, nenhuma alteração foi feita!");
					}
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Invalida!");
					editarProdutos(idEstabelecimento);
					break;
			}
			
		//} else {	
		//JOptionPane.showMessageDialog(null, "Você não possui Produtos com esse id");
		//}
	}
	
	public void verProdutos(int idEstabelecimento) throws SQLException {
		JOptionPane.showMessageDialog(null, "As categorias desse estabelecimento são:  ");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o Id da Categoria que deseja ver os produtos: ");
		int idCategoria = Integer.parseInt(idCategoriaString);
		conn.verProdutosDeUmaCategoriaTrueFalse(idCategoria);
	}
	
	public void excluirProdutos(int idEstabelecimento) throws SQLException {
		//Mostrar todos os produtos cadastrados
		JOptionPane.showMessageDialog(null, "Selecione um produto dentro de uma categoria pelo id");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o nome da categoria que deseja ver os produtos:");
		int idCategoria = Integer.parseInt(idCategoriaString);
		conn.verProdutosDeUmaCategoriaTrueFalse(idCategoria);
		//Escolher o Produto que deseja excluir
		String idProdutoString = JOptionPane.showInputDialog("Informe o Id do produto que deseja excluir:");
		int idProduto = Integer.parseInt(idProdutoString);
		conn.verProdutoDetalhadoTrueFalse(idProduto);
		//Excluir Produto escolhido]
		int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse produto?\n\n");
		if(opcao == JOptionPane.YES_OPTION) {
			conn.insert("DELETE FROM TB_PRODUTOS WHERE PRO_IDPROD = "+idProduto+";");
			//Informar que o Produto foi excluido corretamente
			JOptionPane.showMessageDialog(null, "O produto foi excluido corretamente!");
		} else {
			JOptionPane.showMessageDialog(null, "Produto não foi excluido!");
			return;
		}
	}
	
	public void cadastrarFuncionario() {
		//Pedir Informações para cadastrar funcionario
		//Cadastrar Funcionário
		//Informar que o funcionário foi cadastrado corretamente
	}
	
	public void excluirFuncionarios() {
		//Mostrar todos os funcionários cadastrados
		//Escolher o funcionário que deseja excluir
		//Excluir Funcionário escolhido
		//Informar que o funcionário foi excluido corretamente
	}
	
	public void verFuncionarios() {
		//Mostrar todos os funcionários cadastrados
	}
	
	public void editarFuncionarios() {
		//Mostrar todos os funcionarios cadastrados
		//Escolher Funcionario para edição
		//Mostrar detalhes do funcionário escolhido e numerar as opções
		//escolher a informação para ser editada
	}
	
	public void simularCompra() {
		//Simulação de compra para o estabelecimento
	}
	
	public void impulsionarEstabelecimento() {
		//Implementação futura
	}
	
	public void verificarFaturamento(int idEstabelecimento) throws SQLException {
		//Pegas todos os valores totais de pedidos feitos em determinado periodo e somar
		String dataInicial = JOptionPane.showInputDialog("Informe a data inicial que deseja verificar o faturamento: " + "\n\nlembre de informar a data dessa forma ANO-MÊS-DIA por exemplo (a data 2003-08-23 seria a data 23 de agosto do ano de 2023)");
		String dataFinal = JOptionPane.showInputDialog("Informe a data final que deseja verificar o faturamento: " + "\n\nlembre de informar a data dessa forma ANO-MÊS-DIA por exemplo (a data 2003-08-23 seria a data 23 de agosto do ano de 2023)");
		conn.verificarFaturamento(idEstabelecimento, dataInicial, dataFinal);
	
	}
	
	public void atualizarEstoque(int idEstabelecimento) throws SQLException {
		JOptionPane.showMessageDialog(null, "Selecione um produto dentro de uma categoria pelo id");
		conn.queryVerTodasAsCategoriasDeUmCatalogo(idEstabelecimento);
		String idCategoriaString = JOptionPane.showInputDialog("Informe o nome da categoria que deseja ver os produtos:");
		int idCategoria = Integer.parseInt(idCategoriaString);
		conn.verProdutosDeUmaCategoriaTrueFalse(idCategoria);
		String idProdutoString = JOptionPane.showInputDialog("Informe o Id do produto que deseja atualizar o estoque:");
		int idProduto = Integer.parseInt(idProdutoString);
		conn.verProdutoDetalhadoTrueFalse(idProduto);
		int opcao = JOptionPane.showConfirmDialog(null, 
				  "Conforme o produto visto anteriormente!\n\n"
				+ "Este produto está com estoque e está disponível para venda?\n\n"
				+ "(YES), altera o status para True"
				+ "(NO), altera o status para False");
		if(opcao == JOptionPane.YES_OPTION) {
			conn.insert("UPDATE TB_PRODUTOS SET PRO_TEMESTOQUE  = 'TRUE' WHERE PRO_IDPROD  = "+idProduto+";");//comando para alterar para True
			JOptionPane.showMessageDialog(null, "Disponibilidade do produto alterada para disponível");
			conn.verProdutoDetalhadoTrueFalse(idProduto);
		} else if(opcao == JOptionPane.NO_OPTION) {
			conn.insert("UPDATE TB_PRODUTOS SET PRO_TEMESTOQUE  = 'FALSE' WHERE PRO_IDPROD  = "+idProduto+";");//comando para alterar false
			JOptionPane.showMessageDialog(null, "Disponibilidade do produto alterada para indisponível");
			conn.verProdutoDetalhadoTrueFalse(idProduto);
		} else {
			JOptionPane.showMessageDialog(null, "Nenhuma alteração foi feita!");
			return;
		}
	}
	
	public void verCatalogo(int idEstabelecimento) throws SQLException {
		Catalogo catalogo = new Catalogo();
		catalogo.verCategorias(idEstabelecimento);
		Categoria categoria = new Categoria();
		categoria.verProdutosDaCategoria();
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public float getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(float classificacao) {
		this.classificacao = classificacao;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public long getTelefone() {
		return telefone;
	}
	
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
}
