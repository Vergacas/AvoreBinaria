package Avore.model;

public class No<E extends Comparable<E>> {
	/* Atributos */
	private E dado;		//Valor do nó
	private int bal;	//Balanceamento do nó
	private No<E> fesq; //Nó filho esquerdo
	private No<E> fdir;	//Nó filho direito
	
	/* Construtores */
	public No() {
		this.bal = 0;
		this.dado = null;
		this.fesq = null;
		this.fdir = null;
	}

	public No(E dado) {
		this();
		setDado(dado);
	}
	
	/* Métodos Set */
	public void setDado(E dado) {
		this.dado = dado;
	}

	public void setBal(int bal) {
		this.bal = bal;
	}

	public void setFesq(No<E> fesq) {
		this.fesq = fesq;
	}
	
	public void setFdir(No<E> fdir) {
		this.fdir = fdir;
	}
	

	/* Métodos Get */
	public E getDado() {
		return dado;
	}

	public int getBal() {
		return  bal;
	}
	
	public No<E> getFesq() {
		return fesq;
	}
	
	public No<E> getFdir() {
		return fdir;
	}	
	
}
