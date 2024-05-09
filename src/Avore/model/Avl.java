package Avore.model;

//import Avore.model.No;

public class Avl<E extends Comparable<E>> {
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

	//Método para inserção de um nó na AVL
	public No<E> inserir(E dado, No<E> no) {
		if (no == null) {
			no = new No<E>(dado);
			h = true;
		} else {
			switch (dado.compareTo(no.getDado())){
				case -1:
					no.setFesq(inserir(dado, no.getFesq()));
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
					no.setFdir(inserir(dado, no.getFdir()));
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

	
	/* Remove o no e retorna a sub-avore desse no sem ele*/
	private No<E> removerRaiz(No<E> NoRemovivel){
		No<E> saida = null;
		/*Se so tem 1 elemento */
		if (NoRemovivel.getFdir() == null && NoRemovivel.getFesq() == null) {	
			return saida;
		}

		/* Se tem 2 ou 3 elementos*/
		if (NoRemovivel.getFdir() == null || NoRemovivel.getFesq() == null) {
			
			if (NoRemovivel.getFdir() == null ) {
				saida = NoRemovivel.getFesq();
			}else if (NoRemovivel.getFesq() == null) {
				saida = NoRemovivel.getFdir();
			}else{
				saida = NoRemovivel.getFdir();
			}
			return saida;

		}

		/* Avore com mais de 3 elemetos */

		return saida;
	}

	public E remover(E dado){
		E saida = null;
		boolean auxiliar = true;
		No<E> noAuxiliar = raiz;
		No<E> noAnterior = null;
		
		/* Loop para procurar o no q deve ser removido */
		while (auxiliar) {
			switch (dado.compareTo(noAuxiliar.getDado())) {
				case -1:
					noAnterior = noAuxiliar;
					noAuxiliar = noAnterior.getFesq();
					break;
				case 0:
					
					saida = noAuxiliar.getDado();
					noAuxiliar = removerRaiz(noAuxiliar);
					if (noAnterior == null) {
						setRaiz(noAuxiliar);
					}else{
						if (noAuxiliar.getDado().compareTo(noAnterior.getDado()) == -1) {
							noAnterior.setFesq(noAuxiliar);
						}else{
							noAnterior.setFdir(noAuxiliar);
						}
					}
					break;
				case 1:
					break;
				default:
					noAnterior = noAuxiliar;
					noAuxiliar = noAnterior.getFesq();
					break;
			}
		}
		return saida;
	}

}
