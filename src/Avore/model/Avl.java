package Avore.model;

//import Avore.model.No;

public class Avl<E extends Comparable<E>> {
	/* Atributos */
	private No<E> raiz;

	/* Construtor */
	public Avl(){
		raiz = null;
	}

	/* Métodos */

	public void setRaiz(No<E> raiz) {
		this.raiz = raiz;
	}

	public No<E> getRaiz() {
		return raiz;
	}

	//Método para inserção de um nó na AVL
	public No<E> inserir(E dado, No<E> no, Boolean h) {
		if (no == null) {
			no = new No<E>(dado);
			h = true;
		} else {
			switch (dado.compareTo(no.getDado())){
				case -1:
					no.setFesq(inserir(dado, no.getFesq(), h));
					imprimir();
					System.out.println(h);
					if(h){
						switch(no.getBal()){
							case 0:
								no.setBal(-1);
								break;
							case 1:
								no.setBal(0);
								h = false;
								break;
							case -1:
								No<E> noEsq = no.getFesq();

								h = false;

								if(noEsq.getBal() == -1) return rotacaoDireta(no, noEsq);
								else return rotacaoDuplaDireita(no, noEsq);
						}
					}
					break;
				case 0 : 
					//Nó duplicado
					break;
				case 1:
					no.setFdir(inserir(dado, no.getFdir(), h));
					imprimir();
					if(h){
						switch(no.getBal()){
							case 0:
								no.setBal(1);
								break;
							case -1:
								no.setBal(0);
								h = false;
								break;
							case 1:
								No<E> noDir = no.getFdir();

								h = false;

								if(noDir.getBal() == 1) return rotacaoEsquerda(no, noDir);
								else return rotacaoDuplaEsquerda(no, noDir);
						}
					}
					break;
			}
		}

		return no;
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
			else { 
				int hDir = calcAltura(no.getFdir());
				int hEsq = calcAltura(no.getFesq());

				if(hEsq >= hDir) return hEsq;
				else return hDir;
			}
		}
	}

	private No<E> rotacaoDireta(No<E> no, No<E> noEsq){
		System.out.println("Rotação direita aplicada nos " + no.getDado()+ " " + noEsq.getDado());

		no.setFesq(noEsq.getFdir());
		noEsq.setFdir(no);
		

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));
		noEsq.setBal(calcAltura(noEsq.getFdir()) - calcAltura(noEsq.getFesq()));

		return noEsq;
	}

	private No<E> rotacaoEsquerda(No<E> no, No<E> noDir){
		System.out.println("Rotação direita aplicada nos " + no.getDado()+ " " + noDir.getDado());

		no.setFdir(noDir.getFesq());
		noDir.setFesq(no);

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));
		noDir.setBal(calcAltura(noDir.getFdir()) - calcAltura(noDir.getFesq()));

		return noDir;
	}

	private No<E> rotacaoDuplaDireita(No<E> no, No<E> noEsq){
		no.setFesq(rotacaoEsquerda(noEsq, noEsq.getFdir()));

		return rotacaoDireta(no, noEsq);
	}

	private No<E> rotacaoDuplaEsquerda(No<E> no, No<E> noDir){
		noDir = rotacaoDireta(no, noDir);
		no.setFdir(noDir);

		return rotacaoEsquerda(no, noDir);
	}

	public void imprimir(){
		if(raiz != null) imprimirNos(raiz);
	}

	public void imprimirNos(No<E> no){
		System.out.println("Nó: " + no.getDado());
		System.out.println("Balanceamento: " + no.getBal());
		System.out.println("Filho Esquerdo do nó " + no.getDado() + ": ");

		if(no.getFesq() != null) imprimirNos(no.getFesq());

		System.out.println("Filho Direito do nó " + no.getDado() + ": ");
		if(no.getFdir() != null) imprimirNos(no.getFdir());
	}

}
