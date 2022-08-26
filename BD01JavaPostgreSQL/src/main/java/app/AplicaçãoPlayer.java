package app;

import java.util.List;

import dao.DAO;
import dao.PlayerDAO;
import model.Player;

public class AplicaçãoPlayer {
    public static void main(String[] args) throws Exception {
		
		PlayerDAO playerDAO = new PlayerDAO();
		
		System.out.println("\n\n==== Inserir usuário === ");
		Player player = new Player(11, "pablo", "pablo",'M', "galo");
		if(playerDAO.insert(player) == true) {
			System.out.println("Inserção com sucesso -> " + player.toString());
		}
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + player.getLogin() + "): " + playerDAO.autenticar("pablo", "pablo"));
			
		System.out.println("\n\n==== Mostrar usuários do sexo masculino === ");
		List<Player> players = playerDAO.getSexoMasculino();
		for (Player u: players) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar senha (código (" + player.getCodigo() + ") === ");
		player.setSenha(DAO.toMD5("pablo"));
		playerDAO.update(player);
		
		System.out.println("\n\n==== Testando autenticação ===");
		System.out.println("Usuário (" + player.getLogin() + "): " + playerDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + player.getLogin() + "): " + playerDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		players = playerDAO.getOrderByCodigo();
		for (Player u: players) {
			System.out.println(u.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + player.getCodigo() + ") === ");
		playerDAO.delete(player.getCodigo());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		players = playerDAO.getOrderByLogin();
		for (Player u: players) {
			System.out.println(u.toString());
		}
	}
}
