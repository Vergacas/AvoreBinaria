package Avore.model;

//import Avore.model.No;

public class Avl<E extends Comparable<E>> {
	/* Atributos */
	private No<E> raiz;
	private int altura;
	private int N_nos;

	/* Métodos */

	//Método para inserir um nó na AVL
	public void inserir(E dado){
		No<E> no = new No<E>(dado);
		if(raiz == null) {
			raiz = no;
		} else {
			insere_AVL(no, raiz);
		}

	}

	//Método auxiliar para inserção de um nó na AVL
	public void insere_AVL(No<E> novoNo, No<E> no) {

		switch (novoNo.getDado().compareTo(no.getDado())){
			case -1:
				if(no.getFesq() == null){
					no.setFesq(novoNo);
					novoNo.setPai(no);
					//balancear(no); // A IMPLEMENTAR
				}
				else insere_AVL(novoNo, no.getFesq());
				break;
			case 0 : 
				//Nó duplicado
				break;
			case 1:
				if (no.getFdir() == null) {
					no.setFdir(novoNo);
					novoNo.setPai(no);
					//balancear(no); // A IMPLEMENTAR
				}
				insere_AVL(novoNo, no.getFdir());
				break;
		}
	}


	//Método para calcular a altura de um nó na AVL
	private int calcAltura(No<E> no){
		//Caso esteja a baixo de uma folha
		if(no == null) return -1;
		//Caso não tenha filho a esquerda
		else if(no.getFesq() == null){
			//Caso não tenha filho a direita
			if(no.getFdir() == null) return 0;
			//Caso tenha filho a direita
			else return 1 + calcAltura(no.getFdir());
		} 
		//Caso tenha filho a esquerda
		else {
			//Caso não tenha filho a direita
			if(no.getFdir() == null) return 1 + calcAltura(no.getFesq());
			//Caso tenha filho a direita
			else return Math.max(calcAltura(no.getFesq()), calcAltura(no.getFdir()))
		}
	}

	//Método para balancear a AVL
	public void balancear(No<E> no){
		int balanceamento = calcAltura(no.getFdir()) - calcAltura(no.getFesq()); 
		
		no.setBal(balanceamento);
		
		if(balanceamento == -2) {
			//Filho esquerdo do nó
			No<E> noEsq = no.getFesq();

			//Calcula a altura dos filhos do nó esquerdo
			int altFesq = calcAltura(noEsq.getFesq());
			int altFdir = calcAltura(noEsq.getFdir());
			
			if(altFesq >= altFdir) rotacaoDireta(no);
			else rotacaoDuplaEsquerdaDireita(no);
		} else if(balanceamento == 2) {
			//Filho direito do nó
			No<E> noDir = no.getFdir();
			
			//Calcular a altura dos filhos do nó direito
			int altFesq = calcAltura(noDir.getFesq());
			int altFdir = calcAltura(noDir.getFdir());
			
			if(altFdir >= altFesq) rotacaoEsquerda(no);
			else rotacaoDuplaDireitaEsquerda(no);
		}

		if(no.getPai() != null) balancear(no.getPai());
		else raiz = no;
	}

	public void caso1(No<E> no, boolean h) {
		No<E> noEsq = no.getFesq();
		
		if(noEsq.getBal() == -1) {
			no.setFesq(noEsq.getFdir());
			noEsq.setFdir(no);
			no.setBal(0);
			no = noEsq;
		} else {
			No<E> noDir = noEsq.getFdir();
			
			noEsq.setFdir(noDir.getFesq());
			noDir.setFesq(noEsq);
			no.setFesq(noDir.getFdir());
			noDir.setFdir(no);
			
			if(noDir.getBal() == -1) no.setBal(1);
			else no.setBal(0);

			if(noDir.getBal() == 1) noEsq.setBal(-1);
			else noEsq.setBal(0);

			no = noDir;
		}	
		
		no.setBal(0);
		h = false;
	}

	public void caso2(No<E> no, boolean h) {
		No<E> noDir = no.getFdir();

		if(noDir.getBal() == 1 ) {
			no.setFdir(noDir.getFesq());
			noDir.setFesq(no);
			no.setBal(0);
			no = noDir;
		} else {
			No<E> noEsq = noDir.getFesq();
			
			noDir.setFesq(noEsq.getFdir());
			noEsq.setFdir(noDir);
			no.setFdir(noEsq.getFesq());
			noEsq.setFesq(no);

			if(noEsq.getBal() == 1) no.setBal(-1);
			else no.setBal(0);

			if(noEsq.getBal() == -1) noDir.setBal(1);
			else noDir.setBal(0);

			no = noEsq;
		}

		no.setBal(0);
		h = false;
	}

}
