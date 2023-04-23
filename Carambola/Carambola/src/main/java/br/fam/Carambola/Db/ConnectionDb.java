package br.fam.Carambola.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import br.fam.Carambola.Formatador;
/**
 * @author Victor Teixeira Silva
 * 
 * @version 1.0
 * 
 * - Classe ConnectionDb está fora do Diagrama, Essa classe é responsavel por fazer a conexão com o banco de dados em H2 e fazer tantos os comandos DDL quanto os DML
 * 
 * 
 */
public class ConnectionDb {
/**
 * @param url - Atributo responsavel por receber a String de conexão do banco de dados.
 * @param usuario - Atributo responsavel por receber o usuário de conexão no banco de dados.
 * @param senha - Atributo responsavel por receber a senha do usuário passado no parametro usuario.
 * @param formatador - Objeto da classe formatador responsavel por formatar os inteiros em formato de moeda.
 * 
 */
	String url = "jdbc:h2:~/teste";
	String usuario = "sa"; //Usuario padrão
	String senha = "";// Senha padrao
	Formatador formatador = new Formatador();
	
	public void verificarFaturamento(int idEstabelecimento, String dataInicial, String dataFinal) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT SUM(COM_VALORPEDIDO) AS COM_VALORPEDIDO FROM TB_PEDIDOS WHERE COM_STATUS = 'FINALIZADO' AND COM_IDEST = "+idEstabelecimento+" AND COM_DATA BETWEEN '"+dataInicial+"' AND '"+dataFinal+"';";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	Double valor = row.getDouble("COM_VALORPEDIDO");
        	JOptionPane.showMessageDialog(null,   "\nFATURAMENTO!\n\n"
        			+ "Data inicial: "+dataInicial
        			+ "\nData final: "+dataFinal
        			+"\n\nDurante esse periodo mensionado anteriormente o seu estabelecimento teve um faturamento de:"
        			+ "\nFaturamento: "+Formatador.doubleToString(valor));
        	//System.out.println("ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria + ", Valor: " + valor);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	
	public String getDataPedido(int idPedido) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT * FROM TB_PEDIDOS WHERE COM_IDCOM = "+idPedido+";");
		
		String data = "";
		//Mostra o resultado na tela]
		while(row.next()) {
		data = row.getString("COM_DATA");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return data;
		
	}
	
	public String getStatusPedido(int idPedido) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT * FROM TB_PEDIDOS WHERE COM_IDCOM = "+idPedido+";");
		
		String status = "";
		//Mostra o resultado na tela]
		while(row.next()) {
		status = row.getString("COM_STATUS");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return status;
		
	}
	
	public String getItensQuantidadePedido(int idPedido) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT ITE.ITE_CODPROD,PRO.PRO_DESC,ITE.ITE_QTDITENS FROM TB_ITENSPEDIDO ITE JOIN TB_PRODUTOS PRO ON PRO.PRO_IDPROD = ITE.ITE_CODPROD WHERE ITE.ITE_IDCOM = "+idPedido+";");
		
		String lista = "Lista de produtos e quantidade dentro do pedido\n";
		//Mostra o resultado na tela]
		while(row.next()) {
		String	nomeProduto = row.getString("PRO_DESC");
		int idProduto = row.getInt("ITE_CODPROD");
		int qtdProduto = row.getInt("ITE_QTDITENS");
		
		lista +=  "\nCódigo do produto: "+idProduto
				+ "\nNome do Produto: "+nomeProduto
				+ "\nQuantidade comprada: "+qtdProduto
				+ "\n";
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return lista;
		
	}
	
	public Double getValorPedidoBd(int idPedido) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT SUM(ITE_VALOR) as valorTotal FROM TB_ITENSPEDIDO WHERE ITE_IDCOM = "+idPedido+" GROUP BY ITE_IDCOM;");
				
		Double valorPedidoBd = 0.0;
		//Mostra o resultado na tela]
		while(row.next()) {
			valorPedidoBd = row.getDouble("valorTotal");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return valorPedidoBd;
	}
	
	public int insertPedido(String comandoSQL, int idUsuario, LocalDate dataAtual) throws SQLException{ // metodo de inserir novo registo
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = comandoSQL;
		int row = statement.executeUpdate(sql);
        System.out.println(row + " Registo foi cadastrado corretamente!");

		ResultSet row2 = statement.executeQuery("SELECT * FROM TB_PEDIDOS WHERE COM_IDUSU =  "+idUsuario+" AND COM_DATA = '"+dataAtual+"' AND COM_STATUS = 'EM ANDAMENTO';");
        
		int idPedido = 0;
		while(row2.next()) {
			idPedido = row2.getInt("COM_IDCOM");
		}
		
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        return idPedido;
	}
	
	public Double getValorProdutoBd(int idProduto) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT * FROM TB_PRODUTOS WHERE PRO_IDPROD = "+idProduto+";");
				
		Double valorProdutoBd = 0.0;
		//Mostra o resultado na tela]
		while(row.next()) {
			valorProdutoBd = row.getDouble("PRO_VALOR");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return valorProdutoBd;
	}
	
	public String getNomeProdutoBd(int idProduto) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT * FROM TB_PRODUTOS WHERE PRO_IDPROD = "+idProduto+";");
				
		String nomeProdutoBd = "";
		//Mostra o resultado na tela]
		while(row.next()) {
			nomeProdutoBd = row.getString("PRO_DESC");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return nomeProdutoBd;
	}
	
	public int getIdProdutoBd(int idProduto) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		ResultSet row = statement.executeQuery("SELECT * FROM TB_PRODUTOS WHERE PRO_IDPROD = "+idProduto+";");
				
		int idProdutoBd = 0;
		//Mostra o resultado na tela]
		while(row.next()) {
			idProdutoBd = row.getInt("PRO_IDPROD");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return idProdutoBd;
	}
	
	public String verProdutoDetalhadoTrueFalse(int idProduto) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_PRODUTOS PRO JOIN TB_CATEGORIAS CATE ON PRO.PRO_IDCATE = CATE.CATE_IDCATE WHERE  PRO.PRO_IDPROD = "+idProduto+";";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        String retorno = "PRODUTOS!\n";
        while(row.next()) {
        	int id = row.getInt("PRO_IDPROD");
        	String nome = row.getString("PRO_DESC");
        	String categoria = row.getString("CATE_DESCCATE");
        	Double valor = row.getDouble("PRO_VALOR");
        	boolean disponivel = row.getBoolean("PRO_TEMESTOQUE");
        	retorno += "\nID: " + id + "\nNome: " + nome + "\nCategoria: " + categoria + "\nValor: " + Formatador.doubleToString(valor) + "\nDisponível: "+disponivel+"\n";
        	//JOptionPane.showMessageDialog(null,   "\nID: " + id + "\nNome: " + nome + "\nCategoria: " + categoria + "\nValor: " + Formatador.doubleToString(valor) + "\nDisponível: "+disponivel);
        	//System.out.println("ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria + ", Valor: " + valor);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        return retorno;
	}
	
	public String verProdutosDeUmaCategoriaTrueFalse(int idCategoria) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_PRODUTOS PRO JOIN TB_CATEGORIAS CATE ON PRO.PRO_IDCATE = CATE.CATE_IDCATE WHERE CATE.CATE_IDCATE = '"+idCategoria+"';";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        String retorno = "PRODUTOS!\n";
        while(row.next()) {
        	int id = row.getInt("PRO_IDPROD");
        	String nome = row.getString("PRO_DESC");
        	String categoria = row.getString("CATE_DESCCATE");
        	Double valor = row.getDouble("PRO_VALOR");
        	boolean disponivel = row.getBoolean("PRO_TEMESTOQUE");
        	retorno += "\nID: " + id + "\nNome: " + nome + "\nCategoria: " + categoria + "\nValor: " + Formatador.doubleToString(valor) + "\nDisponível: "+disponivel+"\n";
        	//JOptionPane.showMessageDialog(null,   "\nID: " + id + "\nNome: " + nome + "\nCategoria: " + categoria + "\nValor: " + Formatador.doubleToString(valor) + "\nDisponível: "+disponivel);
        	//System.out.println("ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria + ", Valor: " + valor);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        return retorno;
	}
	
	public String mostrarMesasDisponiveis(int idEstabelecimento) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT MES_IDUSUEST, MES_NUMERO, MES_DISPONIVEL FROM TB_MESA_COMANDA WHERE MES_DISPONIVEL = TRUE AND MES_IDUSUEST = "+idEstabelecimento+";";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        String retorno = "MESAS DISPONÍVEIS!\n";
        //Mostra o resultado na tela]
        while(row.next()) {
        	int numeroMesa = row.getInt("MES_NUMERO");
        	retorno += "\nNúmero da mesa ou da comanda: "+numeroMesa+"\nMESA OU COMANDA DISPONIVEL!\n";
        	//JOptionPane.showMessageDialog(null,   "\nNúmero da mesa ou da comanda: "+numeroMesa+"\nMESA OU COMANDA DISPONIVEL!");
        }
        
        return retorno;
	}
	
	public boolean verificarSeExisteMesas(int idEstabelecimento) throws SQLException {
		boolean resultado = false;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (MES_IDUSUEST) FROM TB_MESA_COMANDA WHERE MES_IDUSUEST = "+idEstabelecimento+";";
        ResultSet row = statement.executeQuery(sql);
		
        int idEstabelecimentoBd = 0;
        while(row.next()) {
        	idEstabelecimentoBd = row.getInt("MES_IDUSUEST");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        if(idEstabelecimentoBd == idEstabelecimento) {
        	resultado = true;
        } else {
        	resultado = false;
        }
        		
		return resultado;
	}
	
	public void verDetalhesFormaPagamentoPorId(int idFormaPagamento) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT PAG_IDPAG,PAG_NUMERO,PAG_BANDEIRA,PAG_NOME,PAG_CODSEG,PAG_DATAVENCI  FROM TB_FORMAS_PAGAMENTO WHERE PAG_IDPAG = "+idFormaPagamento+";";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int idFormaDePagamento = row.getInt("PAG_IDPAG");
        	String numeroCartao = row.getString("PAG_NUMERO");
        	String bandeiraCartao = row.getString("PAG_BANDEIRA");
        	String nomeTitular = row.getString("PAG_NOME");
        	String codigoSeguranca = row.getString("PAG_CODSEG");
        	String dataVencimento = row.getString("PAG_DATAVENCI");
        	JOptionPane.showMessageDialog(null, 
  				"DETALHES DAS FORMAS DE PAGAMENTO CADASTRADAS!\n\n"
  				+ "\nId Forma de Pagamento: "+idFormaDePagamento
  				+ "\nNúmero do Cartão: "+numeroCartao
  				+ "\nBandeira do Cartão: "+bandeiraCartao
  				+ "\nNome do Titular do Cartão: "+nomeTitular
  				+ "\nCódigo de Segurança: "+codigoSeguranca
  				+ "\nData de Vencimento: "+dataVencimento.substring(0,7)
  				+ "\n");
        }
        	
        }
	
	public void verDetalhesFormaPagamento(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT PAG_IDPAG,PAG_NUMERO,PAG_BANDEIRA,PAG_NOME,PAG_CODSEG,PAG_DATAVENCI  FROM TB_FORMAS_PAGAMENTO WHERE PAG_IDUSU = "+idUsuario+";";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int idFormaDePagamento = row.getInt("PAG_IDPAG");
        	String numeroCartao = row.getString("PAG_NUMERO");
        	String bandeiraCartao = row.getString("PAG_BANDEIRA");
        	String nomeTitular = row.getString("PAG_NOME");
        	String codigoSeguranca = row.getString("PAG_CODSEG");
        	String dataVencimento = row.getString("PAG_DATAVENCI");
        	JOptionPane.showMessageDialog(null, 
  				"DETALHES DAS FORMAS DE PAGAMENTO CADASTRADAS!\n\n"
  				+ "\nId Forma de Pagamento: "+idFormaDePagamento
  				+ "\nNúmero do Cartão: "+numeroCartao
  				+ "\nBandeira do Cartão: "+bandeiraCartao
  				+ "\nNome do Titular do Cartão: "+nomeTitular
  				+ "\nCódigo de Segurança: "+codigoSeguranca
  				+ "\nData de Vencimento: "+dataVencimento.substring(0,7)
  				+ "\n");
        	
        }
	}
	
	public String getSenhaUsuarioCliente(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USU_SENHA) FROM TB_USUARIOS WHERE USU_IDUSU = "+idUsuario+";";
		ResultSet row = statement.executeQuery(sql);
		
		
		String senha = "";
		//Mostra o resultado na tela]
		while(row.next()) {
			senha = row.getString("USU_SENHA");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return senha;
	}
	
	public int getDataUsuarioCliente(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USU_DATANASC) FROM TB_USUARIOS_CLIENTE WHERE USUCLI_IDUSUCLI = "+idUsuario+";";
		ResultSet row = statement.executeQuery(sql);
		
		
		int dataNascimento = 0;
		//Mostra o resultado na tela]
		while(row.next()) {
			dataNascimento = row.getInt("USU_DATANASC");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return dataNascimento;
	}
	
	public long getCPFUsuarioCliente(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USU_CPF) FROM TB_USUARIOS_CLIENTE WHERE USUCLI_IDUSUCLI = "+idUsuario+";";
		ResultSet row = statement.executeQuery(sql);
		
		
		long cpf = 0;
		//Mostra o resultado na tela]
		while(row.next()) {
			cpf = row.getLong("USU_CPF");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return cpf;
	}
	
	public String getEmailUsuarioCliente(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USU_EMAIL) FROM TB_USUARIOS WHERE USU_IDUSU = "+idUsuario+";";
		ResultSet row = statement.executeQuery(sql);
		
		
		String email = "";
		//Mostra o resultado na tela]
		while(row.next()) {
			email = row.getString("USU_EMAIL");
		}
		
		//Fecha Statemente e Conexão
		conexao.close();
		statement.close();
		
		return email;
	}
	
	public String getNomeUsuarioCliente(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
				
		//executa a query
		String sql = "SELECT (USUEST_NOME) FROM TB_USUARIOS_ESTABELECIMENTO WHERE USUEST_IDUSU  = "+idUsuario+";";
        ResultSet row = statement.executeQuery(sql);
        
        
        String nome = "";
        //Mostra o resultado na tela]
        while(row.next()) {
        	nome = row.getString("USUEST_NOME");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
		
        return nome;
	}
	
	public int getIdCategoria(String nomeCategoria, int idEstabelecimentos) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
				
		//executa a query
		String sql = "SELECT (CATE_IDCATE) FROM TB_CATEGORIAS WHERE CATE_DESCCATE = '"+nomeCategoria+"' AND CATE_IDCAT = "+idEstabelecimentos+";";
        ResultSet row = statement.executeQuery(sql);
        
        
        int idCategoria = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idCategoria = row.getInt("CATE_IDCATE");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
		
        return idCategoria;
	}
	
	public void queryVerTodosOsEstabelecimentos() throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT USUEST_IDUSUEST,USUEST_NOME FROM TB_USUARIOS_ESTABELECIMENTO;";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int idEstabelecimento = row.getInt("USUEST_IDUSUEST");
        	String nomeEstabelecimento = row.getString("USUEST_NOME");
        	JOptionPane.showMessageDialog(null,   "\nId do Estabelecimento: " + idEstabelecimento
        										+ "\nNome do Estabelecimento: " + nomeEstabelecimento);
        }
	}
	
	public String getNomeBdEstabelecimento(int idEstabelecimento) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USUEST_NOME) FROM TB_USUARIOS_ESTABELECIMENTO WHERE USUEST_IDUSUEST = "+idEstabelecimento+";";
        ResultSet row = statement.executeQuery(sql);
        
        
        String nomeBd = "";
        //Mostra o resultado na tela]
        while(row.next()) {
        	nomeBd = row.getString("USUEST_NOME");
        }
        
        conexao.close();
        statement.close();

        return nomeBd;
	}
	
	public String getNomeBdCliente(int idUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USUCLI_NOME) FROM TB_USUARIOS_CLIENTE WHERE USUCLI_IDUSU = "+idUsuario+";";
        ResultSet row = statement.executeQuery(sql);
        
        
        String nomeBd = "";
        //Mostra o resultado na tela]
        while(row.next()) {
        	nomeBd = row.getString("USUCLI_NOME");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();

        return nomeBd;
	}
	
	public int buscarIdUsuario(String emailUsuario, String senhaUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
				
		//executa a query
		String sql = "SELECT (USU_IDUSU) FROM TB_USUARIOS WHERE USU_EMAIL = '"+emailUsuario+"' AND USU_SENHA = '"+senhaUsuario+"';";
        ResultSet row = statement.executeQuery(sql);
        
        
        int idUsuario = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idUsuario = row.getInt("USU_IDUSU");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
		
        return idUsuario;
	}
	
	public boolean verificarSeFuncionario(int idUsuario) throws SQLException {
		boolean resultado = false;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USUFUN_IDUSU) FROM TB_USUARIOS_FUNCIONARIO WHERE USUFUN_IDUSU = "+idUsuario+"; ";
        ResultSet row = statement.executeQuery(sql);
		
        int idUsuarioBd = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idUsuarioBd = row.getInt("USUFUN_IDUSU");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        if(idUsuario == idUsuarioBd) {
        	resultado = true;
        } else {
        	resultado = false;
        }
        		
		return resultado;
	}
	
	public boolean verificarSeCliente(int idUsuario) throws SQLException {
		boolean resultado = false;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USUCLI_IDUSU) FROM TB_USUARIOS_CLIENTE WHERE USUCLI_IDUSU = "+idUsuario+"; ";
        ResultSet row = statement.executeQuery(sql);
		
        int idUsuarioBd = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idUsuarioBd = row.getInt("USUCLI_IDUSU");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        if(idUsuario == idUsuarioBd) {
        	resultado = true;
        } else {
        	resultado = false;
        }
        
		return resultado;
	}
	
	public boolean verificarSeEstabelecimento(int idUsuario) throws SQLException {
		boolean resultado = false;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
				
		//executa a query
		String sql = "SELECT (USUEST_IDUSU) FROM TB_USUARIOS_ESTABELECIMENTO WHERE USUEST_IDUSU = "+idUsuario+"; ";
        ResultSet row = statement.executeQuery(sql);
        
        
        int idUsuarioBd = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idUsuarioBd = row.getInt("USUEST_IDUSU");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        if(idUsuario == idUsuarioBd) {
        	resultado = true;
        } else {
        	resultado = false;
        }
        return resultado;
	}
	
	private String queryGetSenha(String email, String senha) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USU_SENHA) FROM TB_USUARIOS WHERE USU_EMAIL = '"+email+"' AND USU_SENHA = '"+senha+"';";
        ResultSet row = statement.executeQuery(sql);
        
        
        String senhaBd = "";
        //Mostra o resultado na tela]
        while(row.next()) {
        	senhaBd = row.getString("USU_SENHA");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
		
        return senhaBd;
	}
	
	private String queryGetEmail(String emailUsuario, String senhaUsuario) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa a query
		String sql = "SELECT (USU_EMAIL) FROM TB_USUARIOS WHERE USU_EMAIL = '"+emailUsuario+"' AND USU_SENHA = '"+senhaUsuario+"';";
        ResultSet row = statement.executeQuery(sql);
        
        
        String emailBd = "";
        //Mostra o resultado na tela]
        while(row.next()) {
        	emailBd = row.getString("USU_EMAIL");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
		
        return emailBd;
		
	}
	
	public boolean verificarLoginBd(String emailUsuario, String senhaUsuario) throws SQLException{
		boolean resultado = false;
        String emailBd = queryGetEmail(emailUsuario, senhaUsuario);
        String senhaBd = queryGetSenha(emailUsuario, senhaUsuario);
        if((emailUsuario.equalsIgnoreCase(emailBd))&&(senhaUsuario.equals(senhaBd))) {
        	resultado = true;
        } else {
        	resultado = false;
        }
        
        return resultado;
	}
	/**
	 * 
	 * @param comandoSQL - String que vai receber o comando sql sendo eles, de inserção
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por receber uma String como parametro que é um código em sql to tipo de inserção de dados, o metodo exucuta essa string no banco de dados
	 * 
	 * @return 
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Registo foi cadastrado corretamente!", caso o comando sql execute sem problema e faça a inserção no banco de dados
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */
	public void insert(String comandoSQL) throws SQLException{ // metodo de inserir novo registo
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = comandoSQL;
        int row = statement.executeUpdate(sql);
        System.out.println(row + " Registo foi cadastrado corretamente!");
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	/**
	 * 
	 * @param comandoSQL - String que vai receber o comando sql sendo eles, de inserção
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por receber uma String como parametro que é um código em sql to tipo que faz atualização em dados, o metodo exucuta essa string no banco de dados
	 * 
	 * @return 
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Registo foi cadastrado corretamente!", caso o comando sql execute sem problema e faça a inserção no banco de dados
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */ 
		public void update(String comandoSQL) throws SQLException{ // metodo de atualizar um registo
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = comandoSQL;
        int row = statement.executeUpdate(sql);
        System.out.println(row + " Registo foi alterado corretamente!");
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	/**
	 * 
	 * @param idCatalogo - Parametro responsavel po receber o ID do catalogo para que seja feito a consulta em base nele
	 * @throws SQLException
	 * 
	 * - Esse metodo é responsavel por fazer uma consulta dentro do banco de dados e mostrar todas as categorias cadastradas em um catalogo especifico, cujo id é passado como parametro
	 * 
	 * @return
	 *  Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */
	public String queryVerTodasAsCategoriasDeUmCatalogo(int idCatalogo) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_CATEGORIAS CATE JOIN TB_CATALOGO CAT ON CATE.CATE_IDCAT = CAT.CAT_IDCAT WHERE CAT.CAT_IDCAT = '"+idCatalogo+"';";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        String retorno = "CATEGORIAS!\n\n";
        while(row.next()) {
        	int id = row.getInt("CATE_IDCATE");
        	String nomeCategoria = row.getString("CATE_DESCCATE");
        	retorno += "\nId da Categoria: " + id + "\nNome da Categoria: " + nomeCategoria + "\n";
        	//JOptionPane.showMessageDialog(null,   "\nId da Categoria: " + id
        	//									+ "\nNome da Categoria: " + nomeCategoria);
        	//System.out.println("Id da Categoria: " + id + ", Id do Catalogo: " + idCatalogo + ", Nome da Categoria: " + nomeCategoria);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        
        return retorno;
	}
	/**
	 * 
	 * @param idCategoria - parametro responsavel por receber o ID da categoria para que seja usado na consulta.
	 * @throws SQLException
	 * 
	 * - Esse metodo é responsavel por fazer uma consulta dentro do banco de dados e mostrar todos os produtos presentes em uma categoria, de acordo com o ID da mesma passada como parametro.
	 * 
	 * @return
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 */
	public void queryVerProdutosDeUmaCategoria(int idCategoria) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_PRODUTOS PRO JOIN TB_CATEGORIAS CATE ON PRO.PRO_IDCATE = CATE.CATE_IDCATE WHERE CATE.CATE_IDCATE = '"+idCategoria+"' AND PRO_TEMESTOQUE = TRUE;";
        ResultSet row = statement.executeQuery(sql);
        System.out.println(row + " Consulta feita corretamente no Banco de Dados!");
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int id = row.getInt("PRO_IDPROD");
        	String nome = row.getString("PRO_DESC");
        	String categoria = row.getString("CATE_DESCCATE");
        	Double valor = row.getDouble("PRO_VALOR");
        	JOptionPane.showMessageDialog(null,   "\nID: " + id 
        										+ "\nNome: " + nome
        										+ "\nCategoria: " + categoria
        										+ "\nValor: " + Formatador.doubleToString(valor));
        	//System.out.println("ID: " + id + ", Nome: " + nome + ", Categoria: " + categoria + ", Valor: " + valor);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	/**
	 * 
	 * @param id1
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por fazer uma consulta no banco de dados e retornar uma categoria de acordo com o id
	 * 
	 * @return
	 * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco

	 */
	public String queryGetCategoriaBd(int id1) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT CATE_DESCCATE FROM TB_CATEGORIAS WHERE CATE_IDCATE = '"+id1+"';";
        ResultSet row = statement.executeQuery(sql);
        //System.out.println(row + "Consulta feita corretamente no Banco de Dados!");
        
        String categoria = null;
        //Mostra o resultado na tela]
        while(row.next()) {
        	categoria = row.getString("CATE_DESCCATE");
        	//System.out.println("Categoria: " + categoria);
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return categoria;
	}
	/**
	 * 
	 * @param idEstabelecimento - Parametro que devera receber o id do estabelecimento
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por fazer uma consulta no banco de dados e retornar o id do catalogo de acordo com o id do estabelecimento informado como parametro
	 * 
	 * @return
	 * * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 * 
	 */
	public int queryGetIdCatalagoPorEstabelecimento(int idEstabelecimento) throws SQLException {
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT CAT_IDCAT FROM TB_CATALOGO WHERE CAT_IDUSUEST = '"+idEstabelecimento+"';";
        ResultSet row = statement.executeQuery(sql);
        
        int idCatalogo = 0;
        //Mostra o resultado na tela]
        while(row.next()) {
        	idCatalogo = row.getInt("CAT_IDCAT");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return idCatalogo;
	}
	
	/**
	 * 
	 * @param idCategoria - Parametro responsavel por receber o id da categoria
	 * @throws SQLException
	 * 
	 * - Metodo responsavel por pegar o id do catalogo de acordo com o id de uma categoria informada no parametro do metodo.
	 * 
	 * @return
	 * * Esse metodo possui 3 retornos possiveis. 
	 * 1.Esse metodo tem como retorno a mensagem "Conexão bem sucessida!" caso a cenexão com o banco esteja funcionando corretamente.
	 * 2.Esse metodo também tem como retorno a mensagem "Consulta feita corretamente no Banco de dados", caso o comando sql execute sem problema e faça consulta no banco de dados.
	 * 3.O terceiro retorno possivel é a mensagem "Ocorreu um erro ao conectar", que retorna sempre que o projeto tem dificuldade em se conectar com o banco
	 * 
	 */
	public int queryIdCatalogoComIdCategoria(int idCategoria) throws SQLException {
		int idCatalogo = 0;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT CATE_IDCAT FROM TB_CATEGORIAS WHERE CATE_IDCATE = '"+idCategoria+"';";
        ResultSet row = statement.executeQuery(sql);
        
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	int idCatalogo1 = row.getInt("CATE_IDCAT");
        	idCatalogo = idCatalogo1;
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return idCatalogo;
	}
	
	public int queryIdEstabelecimentoComIdProduto(int idProduto) throws SQLException {
		int idEstabelecimento = 0;
		//Testa a conexão no Banco de dados
		try (Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha)) {
			//System.out.println("Conexão bem-sucedida!");
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao conectar: " + e.getMessage());
		}
		
		//Após testar se conecta de fato
		Connection conexao = DriverManager.getConnection(this.url, this.usuario, this.senha);
		
		//Cria um statement para receber comandos
		java.sql.Statement statement = conexao.createStatement();
		
		//executa o comando de insert
		String sql = "SELECT * FROM TB_PRODUTOS PRO JOIN TB_CATEGORIAS CATE ON CATE.CATE_IDCATE = PRO.PRO_IDCATE WHERE CATE.CATE_IDCAT = "+idEstabelecimento+";";
        ResultSet row = statement.executeQuery(sql);
        
        
        //Mostra o resultado na tela]
        while(row.next()) {
        	idEstabelecimento = row.getInt("CATE_IDCAT");
        }
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
        return idEstabelecimento;
	}
	
}
