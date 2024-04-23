package Avore.model;

//import Avore.model.No;

public class Avl<E extends Comparable<E>> {
	/* Atributos */
	private No<E> raiz;
	private int altura;
	private int N_nos;

	/* Métodos */
	public void insere_AVL(E dado, No<E> no, boolean h) {
		if(no == null) {
			no = new No<E>(dado);
			h = true;
		} else {
			if(dado == no.getDado()) {
				return; //Nó duplicado
			} else if(dado.compareTo(no.getDado()) < 0) {
				insere_AVL(dado, no.getFesq(), h);

				if(h) {
					switch (no.getBal()){
						case 1:
							no.setBal(0);
							h = false;
							break;
						case 0: 
							no.setBal(-1);
							break;
						case -1:
							caso1(no, h); 
							break;
					}
				} else {
					insere_AVL(dado, no.getFdir(), h);

					switch (no.getBal()) {
						case -1:
							no.setBal(0);
							h = false;
							break;
						case 0: 
							no.setBal(1);
							break;
						case 1:
							caso2(no, h); //A IMPLEMENTAR
							break;
					}
				}
			}
		}
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
