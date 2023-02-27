package com.seda.payer.core.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Menu implements Serializable{
	private String menuLivelloUno = null;
	private String menuLivelloDue = null;
	private String menuLivelloTre = null;
	
	public Menu(String menuLivelloUno, String menuLivelloDue,
			String menuLivelloTre) {
		super();
		this.menuLivelloUno = menuLivelloUno;
		this.menuLivelloDue = menuLivelloDue;
		this.menuLivelloTre = menuLivelloTre;
	}

	public Menu() {}

	public String getMenuLivelloUno() {
		return menuLivelloUno;
	}
	public void setMenuLivelloUno(String menuLivelloUno) {
		this.menuLivelloUno = menuLivelloUno;
	}
	public String getMenuLivelloDue() {
		return menuLivelloDue;
	}
	public void setMenuLivelloDue(String menuLivelloDue) {
		this.menuLivelloDue = menuLivelloDue;
	}
	public String getMenuLivelloTre() {
		return menuLivelloTre;
	}
	public void setMenuLivelloTre(String menuLivelloTre) {
		this.menuLivelloTre = menuLivelloTre;
	}

}
