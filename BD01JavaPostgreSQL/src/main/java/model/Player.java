package model;

import java.util.Scanner;

public class Player extends Usuario{
    private String time;


    public Player(){
        super();
        this.time = "";
    }
    
    public Player(int codigo, String login, String senha, char sexo, String time){
        super(codigo, login, senha, sexo);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPlayer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o codigo: ");
        this.setCodigo(sc.nextInt());
        System.out.println("Digte o login: ");
        this.setLogin(sc.next());
        System.out.println("Digte a senha: ");
        this.setSenha(sc.next());
        System.out.println("Digte o sexo: ");
        this.setSexo(sc.next().charAt(0));
        System.out.println("Digte o time: ");
        this.setTime(sc.next());
        sc.close();
    }

    @Override
	public String toString() {
		return "Usuario [codigo=" + this.getCodigo() + ", login=" + this.getLogin() + ", senha=" + this.getSenha() + ", sexo=" + this.getSexo() + ", time=" + this.getTime() + "]";
	}	
}
