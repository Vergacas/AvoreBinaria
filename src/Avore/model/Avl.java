package Avore.model;

import java.util.Iterator;
import java.util.Stack;

//import Avore.model.No;

public class Avl<E extends Comparable<E>> implements Iterable<No<E>> {
	/* Atributos */
	private No<E> raiz;
	private boolean h;

	/* Construtor */
	public Avl(){
		raiz = null;
		h = true;
	}

	/* Métodos */

	public void setRaiz(No<E> raiz) {
		this.raiz = raiz;
	}

	public No<E> getRaiz() {
		return raiz;
	}


	public void inserir(E dado){
		raiz = inserirAVL(dado, raiz);
	}
	
	//Método para inserção de um nó na AVL
	private No<E> inserirAVL(E dado, No<E> no) {
		if (no == null) {
			no = new No<E>(dado);
			h = true;
		} else {
			switch (dado.compareTo(no.getDado())){
				case -1:
					no.setFesq(inserirAVL(dado, no.getFesq()));
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
					no.setFdir(inserirAVL(dado, no.getFdir()));
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
		no.setFesq(noEsq.getFdir());
		noEsq.setFdir(no);
		

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));
		noEsq.setBal(0);

		return noEsq;
	}

	private No<E> rotacaoEsquerda(No<E> no, No<E> noDir){
		no.setFdir(noDir.getFesq());
		noDir.setFesq(no);

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));
		noDir.setBal(0);

		return noDir;
	}

	private No<E> rotacaoDuplaDireita(No<E> no, No<E> noEsq){
		noEsq = rotacaoEsquerda(noEsq, noEsq.getFdir());
		no.setFesq(noEsq);

		return rotacaoDireta(no, noEsq);
	}

	private No<E> rotacaoDuplaEsquerda(No<E> no, No<E> noDir){
		noDir = rotacaoDireta(noDir, noDir.getFesq());
		no.setFdir(noDir);

		return rotacaoEsquerda(no, noDir);
	}

	public void imprimir(){
		if(raiz != null) {
			System.out.println("Raiz: " + raiz.getDado());
			imprimirNos(raiz);
		}
		else System.out.println("Árvore vazia");
	}

	public void imprimirNos(No<E> no){
		No<E> noEsq = no.getFesq();
		No<E> noDir = no.getFdir();

		if(no.getFesq() != null) {
			System.out.println("Filho esquerdo do nó " + no.getDado() + ": " + noEsq.getDado());
			imprimirNos(noEsq);
		}
		if(no.getFdir() != null) {
			System.out.println("Filho direito do nó " + no.getDado() + ": " + noDir.getDado());
			imprimirNos(noDir);
		}
	}

	public void remover(E dado){
		raiz = removerAVL(dado, raiz);
	}

	public No<E> removerAVL(E dado, No<E> no){
		if(no == null) return no;

 		switch (dado.compareTo(no.getDado())) {
			case -1:
				//Chave é menor que a chave do nó atual
				no.setFesq(removerAVL(dado, no.getFesq()));
				break;
			case 1:
				//Chave é maior que a chave do nó atual
				no.setFdir(removerAVL(dado, no.getFdir()));					
				break;
			case 0:
				//Chave é igual a chave do nó atual
				
				//Verificar se tem algum filho vazio
				if(no.getFesq() == null || no.getFdir() == null){
					No<E> aux = null;
					//Verificar se tem filho a esquerda
					if(aux == no.getFesq()) aux = no.getFdir();
					else aux = no.getFesq();
					//Verificar caso o nó não tenha filhos
					if(aux == null){
						aux = no;
						no = null;
					} else {
						//Caso o nó tenha um filho
						no = aux;
						
					}
				} else {
					//Caso o nó tenha dois filhos
					No<E> aux = sucessor(no.getFdir());
					no.setDado(aux.getDado());
					no.setFdir(removerAVL(aux.getDado(), no.getFdir()));
				}
				break;

		}

		if(no == null) return no;

		no.setBal(calcAltura(no.getFdir()) - calcAltura(no.getFesq()));

		int bal = no.getBal();

		switch (bal) {
			case 1:
				No<E> fEsq = no.getFesq();

				if(fEsq.getBal() >= 0) return rotacaoDireta(no, fEsq);
				return rotacaoDuplaDireita(no, fEsq);

			case -1: 
				No<E> fDir = no.getFdir();

				if(fDir.getBal() <= 0) return rotacaoEsquerda(no, fDir);
				return rotacaoDuplaEsquerda(no, fDir);
		}

		return no;
	}

	public No<E> sucessor(No<E> no){
		if(no.getFdir() != null) return sucessor(no.getFdir());
		return no;
	}

	public No<E> buscar(No<E> busca , No<E> no){
		if(no != null) {
			switch(no.getDado().compareTo(busca.getDado())){
				case 0: return no;
				case -1: return buscar(busca, no.getFesq());
				case 1: return buscar(busca, no.getFdir());
			}
		}

		return null;
	}

	public boolean isEmpty(){
		if(raiz == null) return true;
		return false;
	}

	/* Iterador */

	 // Implementação do iterador para a travessia em ordem
    private class InOrderIterator implements Iterator<No<E>> {
        Stack<No<E>> fila;

        InOrderIterator(No<E> no) {
            fila = new Stack<>();
            pushLeft(no);
        }

        @Override
        public boolean hasNext() {
            return !fila.isEmpty();
        }

        @Override
        public No<E> next() {
            No<E> atual = fila.pop();
            pushLeft(atual.getFdir());
            return atual;
        }

        private void pushLeft(No<E> no) {
            while (no != null) {
                fila.push(no);
                no = no.getFesq();
            }
        }
    }
	@Override
	public Iterator<No<E>> iterator() {
		return new InOrderIterator(raiz);
	}
}
