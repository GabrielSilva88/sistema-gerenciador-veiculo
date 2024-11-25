package br.com.sistemagerenciadorveiculo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathVariableResolver;

public class Aplication {

	public static void main(String[] args) {
		
		List<String> lintas = new ArrayList<String>();
		
		for (String string : lintas) {
			System.out.println(lintas);
		}
		
		lintas.add("gabriel");
		
		String url = "jdbc:postgresql://localhost:5432/gerenciamentoveiculos";
		String user = "postgres";
		String password = "gabriel";

		// DriverManager

		try {
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado");

			String nome = "Sivla";
			String cpf = "3692584575";
			int idade = 32;

			String comandoSQL = "insert into cliente (nome, cpf, idade) values (?,?,?)";

			// String insertCliente = "insert into cliente (nome, cpf, idade) values
			// (?,?,?)";

			PreparedStatement pstm = con.prepareStatement(comandoSQL, PreparedStatement.RETURN_GENERATED_KEYS);

			pstm.setString(1, nome);
			pstm.setString(2, cpf);
			pstm.setInt(3, idade);
			// Scanner scanner = new Scanner(System.in);

			// pstm.execute();

			var affectedRows = pstm.getUpdateCount();

			System.out.println(" Quantas linhas foram auteradas: " + affectedRows);

			if (affectedRows > 0) {
				try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						long idGerado = generatedKeys.getLong(1);
						System.out.println("ID generado: " + idGerado);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			var logradouro = "logradouro";
			var distrito = "distrito";
			var complemento = "complemento";
			var uf = "uf";
			var cep = 0;

			var comandoInsertEnd = "insert into endereco (logradouro, distrito, complemento, uf, cep) "
					+ "values (?,?,?,?,?)";

			PreparedStatement pstmInsertEnd = con.prepareStatement(comandoInsertEnd,
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmInsertEnd.setString(1, logradouro);
			pstmInsertEnd.setString(2, distrito);
			pstmInsertEnd.setString(3, complemento);
			pstmInsertEnd.setString(4, uf);
			pstmInsertEnd.setInt(5, cep);

			pstmInsertEnd.execute();

			String consultaSqlEndereco = "select * from endereco";

			PreparedStatement pstmConsultaEnd = con.prepareStatement(consultaSqlEndereco);

			ResultSet respostaEnd = pstmConsultaEnd.executeQuery();

			while (respostaEnd.next()) {

				String EndLogradouro = respostaEnd.getString("logradouro");
				String EndDistrito = respostaEnd.getString("distrito");
				String EndComplemento = respostaEnd.getString("complemento");
				String EndUf = respostaEnd.getString("uf");
				int EndCep = respostaEnd.getInt("cep");

				System.out.println("logradouro: " + EndLogradouro + "Distrito: " + EndDistrito + "Complemento: "
						+ EndComplemento + "UF: " + EndUf + "CEP: " + EndCep);
			}

			// resultSet classe utilizado um conjunto de resultado. SELECT

			String comandoSqlConsulta = "select * from cliente ";

			PreparedStatement pstmConsultar = con.prepareStatement(comandoSqlConsulta,
					PreparedStatement.RETURN_GENERATED_KEYS);

			ResultSet resposta = pstmConsultar.executeQuery();

			while (resposta.next()) {

				String codigoUser = resposta.getString("id_cliente");
				String nomeUser = resposta.getString("nome");
				String cpfUser = resposta.getString("cpf");
				int idadeUser = resposta.getInt("idade");
				// String endUser = resposta.getString("endereco");

				System.out.println("Nome: " + nomeUser);
				System.out.println("CPF: " + cpfUser);
				System.out.println("Idade: " + idadeUser);
			}

			// SELECT WHERE

			String nomeCoringa = "F";

			String comandoSqlConsultaComWhere = "select * from cliente where nome like ?;";

			PreparedStatement pstmConsultaComWhere = con.prepareStatement(comandoSqlConsultaComWhere,
					PreparedStatement.RETURN_GENERATED_KEYS);

			pstmConsultaComWhere.setString(1, nomeCoringa + "%");

			ResultSet respostasWhere = pstmConsultaComWhere.executeQuery();

			while (respostasWhere.next()) {

				int idadeUser = respostasWhere.getInt("idade");
				String cpfUser = respostasWhere.getString("cpf");
				int codigoUser = respostasWhere.getInt("id_cliente");
				String nomeUser = resposta.getString("nome");

				System.out.println("Nome: " + nomeUser);
				System.out.println("Idade: " + idadeUser);
				System.out.println("CPF: " + cpfUser);
				System.out.println("Codigo: " + codigoUser);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
