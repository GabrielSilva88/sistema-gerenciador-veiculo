package br.com.sistemagerenciadorveiculo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aplication {

	public static void main(String[] args) {
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password ="gabriel";
		
		//DriverManager
		
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado");
			
			String nome = "Sivla";
			String cpf = "62488074079";
			int idade = 32;
			
			String comandoSQL = "insert into usuario (nome, cpf, idade) values (?,?,?);";
			
			PreparedStatement pstm = con.prepareStatement(comandoSQL);
			
			pstm.setString(1, nome);
			pstm.setString(2, cpf);
			pstm.setInt(3, idade);
			
			pstm.execute();
			
			// resultSet classe utilizado um conjunto de resultado. SELECT
			
			String comandoSqlConsulta = "select * from usuario; ";
			
			PreparedStatement pstmConsultar = con.prepareStatement(comandoSqlConsulta);
			
			ResultSet resposta = pstmConsultar.executeQuery();
			
			while (resposta.next()) {
	
				String nomeUser = resposta.getString("nome");
				String cpfUser = resposta.getString("cpf");
				int idadeUser = resposta.getInt("idade");
				//String endUser = resposta.getString("endereco");
				
				System.out.println("Nome: " + nomeUser);
				System.out.println("CPF: " + cpfUser);
				System.out.println("Idade: " + idadeUser);
			}
			
			// SELECT WHERE
			
			String nomeCoringa = "F";
			
			String comandoSqlConsultaComWhere = "select * from usuario where nome like ?;";
			
			PreparedStatement pstmConsultaComWhere = con.prepareStatement(comandoSqlConsultaComWhere);
			
			pstmConsultaComWhere.setString(1, nomeCoringa + "%");
			
			ResultSet respostasWhere = pstmConsultaComWhere.executeQuery();
			
			while (respostasWhere.next()) {
				
				int idadeUser = respostasWhere.getInt("idade");
				String cpfUser = respostasWhere.getString("cpf");
				int codigoUser = respostasWhere.getInt("codigo");
				String nomeUser = resposta.getString("nome");
				
				System.out.println("Nome: " + nomeUser);
				System.out.println("Idade: " + idadeUser);
				System.out.println("CPF: " + cpfUser);
				System.out.println("Nome: " + codigoUser);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
