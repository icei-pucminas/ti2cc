package app;

import java.util.Scanner;
import dao.PlayerDAO;
import model.Player;

public class Principal {

    public static void main(String[] args) {
        cruid();
    }

    public static void cruid(){
        PlayerDAO PDAO = new PlayerDAO();
		Scanner sc = new Scanner(System.in);
		int opc;
		boolean end = true;
		do{
			menu();
			try{
				opc = sc.nextInt();
				switch(opc) {
					case 1:
                        Player p = new Player();
                        p.setPlayer();
                        PDAO.insert(p);
						break;
					case 2:
                        PDAO.printBD();
						break;
					case 3:
                        System.out.println("Digite o codigo do player que deseja atualizar: ");
                        int id = sc.nextInt();
                        PDAO.update(PDAO.get(id));
						break;
					case 4:
                        System.out.println("Digite o codigo do player que deseja deletar: ");
                        id = sc.nextInt();
                        PDAO.delete(id);
						break;
					case 5:
						end = false;
						break;
				}
            }
			catch (NumberFormatException e) {
				System.err.println(e.getMessage());
			}
		}while(end);
		sc.close();
	}

	public static void menu(){
		System.out.println("\n\t1) Inserir\n\t2) Listar\n\t3) Atualizar\n\t4) Excluir\n\t5) Sair");
	}


}
