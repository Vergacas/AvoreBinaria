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
	private void insere_AVL(No<E> novoNo, No<E> no) {

		switch (novoNo.getDado().compareTo(no.getDado())){
			case -1:
				if(no.getFesq() == null){
					no.setFesq(novoNo);
					novoNo.setPai(no);
					balancear(no); // A IMPLEMENTAR
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
					balancear(no); // A IMPLEMENTAR
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
			else return Math.max(calcAltura(no.getFesq()), calcAltura(no.getFdir()));
		}
	}

	//Método para balancear a AVL
	private void balancear(No<E> no){
		int balanceamento = calcAltura(no.getFdir()) - calcAltura(no.getFesq()); 
		
		no.setBal(balanceamento);
		
		if(balanceamento == -2) {
			//Filho esquerdo do nó
			No<E> noEsq = no.getFesq();

			//Calcula a altura dos filhos do nó esquerdo
			int altFesq = calcAltura(noEsq.getFesq());
			int altFdir = calcAltura(noEsq.getFdir());
			
			if(altFesq >= altFdir) no = rotacaoDireta(no);
			else no = rotacaoDuplaEsquerdaDireita(no);
		} else if(balanceamento == 2) {
			//Filho direito do nó
			No<E> noDir = no.getFdir();
			
			//Calcular a altura dos filhos do nó direito
			int altFesq = calcAltura(noDir.getFesq());
			int altFdir = calcAltura(noDir.getFdir());
			
			if(altFdir >= altFesq) no = rotacaoEsquerda(no);
			else no = rotacaoDuplaDireitaEsquerda(no);
		}

		if(no.getPai() != null) balancear(no.getPai());
		else raiz = no;
	}

	private No<E> rotacaoDireta(No<E> no){
		No<E> noEsq = no.getFesq();

		noEsq.setPai(no.getPai());

		no.setFesq(noEsq.getFdir());
		if(no.getFesq() != null) no.getFesq().setPai(no);

		noEsq.setFdir(no);

		no.setPai(noEsq);

		No<E> noPai = noEsq.getPai();

		if(noPai != null){
			if(noPai.getFdir() == no) noPai.setFdir(noEsq);
			else noPai.setFesq(noEsq);
		}

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));
		noEsq.setBal(calcAltura(noEsq.getFdir()) - calcAltura(noEsq.getFesq()));

		return noEsq;
	}

	private No<E> rotacaoEsquerda(No<E> no){
		No<E> noDir = no.getFdir();

		noDir.setPai(no.getPai());

		no.setFdir(noDir.getFesq());
		if(no.getFdir() != null) no.getFdir().setPai(no);

		noDir.setFesq(no);

		no.setPai(noDir);

		No<E> noPai = noDir.getPai();

		if(noPai != null){
			if(noPai.getFdir() == no) noPai.setFdir(noDir);
			else noPai.setFesq(noDir);
		}

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));
		noDir.setBal(calcAltura(noDir.getFdir()) - calcAltura(noDir.getFesq()));

		return noDir;
	}

	private No<E> rotacaoDuplaEsquerdaDireita(No<E> no){
		No<E> noEsq = rotacaoEsquerda(no.getFesq());
		no.setFdir(noEsq);

		return rotacaoDireta(no);
	}

	private No<E> rotacaoDuplaDireitaEsquerda(No<E> no){
		No<E> noDir = rotacaoDireta(no.getFdir());
		no.setFdir(noDir);

		return rotacaoEsquerda(no);
	}

	public void imprimir(){
		No<E> no = raiz;

		imprimirNos(no);
	}

	public void imprimirNos(No<E> no){
		if(no.getPai() != null) System.out.println("Nó: " + no.getDado() + " - Pai: " + no.getPai().getDado());
		else System.out.println("Nó: " + no.getDado());

		if(no.getFesq() != null) imprimirNos(no.getFesq());
		if(no.getFdir() != null) imprimirNos(no.getFdir());
	}

}
