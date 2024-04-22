package Avore.model;

public class No<E> {
	private E dado;
	private No<?> pai;
	private No<?> fesq;
	private No<?> fdire;
	
	public No() {
		this.pai = null;
		this.fesq = null;
		this.fdire = null;
		this.dado = null;
	}
	
	public E getDado() {
		return dado;
	}

	public void setDado(E dado) {
		this.dado = dado;
	}

	public No<?> getPai() {
		return pai;
	}
	
	public void setPai(No<?> pai) {
		this.pai = pai;
	}
	
	public No<?> getFesq() {
		return fesq;
	}
	
	public void setFesq(No<?> fesq) {
		this.fesq = fesq;
	}
	
	public No<?> getFdire() {
		return fdire;
	}
	
	public void setFdire(No<?> fdire) {
		this.fdire = fdire;
	}
	
	
}
