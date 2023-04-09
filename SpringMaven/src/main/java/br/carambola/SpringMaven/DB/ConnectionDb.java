package br.carambola.SpringMaven.DB;
/*
 * CLASSE ConnectionDb -> Está fora do Diagrama
 * 
 * By Victor Teixeira
 * 
 * Anotação:	Classe de conexão com o banco de dados H2, que será utilizada
 * 				para criação dos metodos de acesso ao Banco de dados
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
	String url = "jdbc:h2:~/teste"; //Link local apra conexão do banco de dados
	String usuario = "sa"; //Usuario padrão
	String senha = "";// Senha padrao
	
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
        System.out.println(row + "Registo foi cadastrado corretamente!");
        
        //Fecha Statemente e Conexão
        conexao.close();
        statement.close();
	}
	        
}
