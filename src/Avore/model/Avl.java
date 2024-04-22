package Avore.model;

//import Avore.model.No;

public class Avl<E extends Comparable<E>> {
	/* Atributos */
	private No<E> raiz;
	private int altura;
	private int N_nos;

	/* Métodos */
	public void insere_AVL(E dado, No<E> no, boolean[] h) {
		if(no == null){
			no = new No<E>(dado);
			h[0] = true;
		} else {
			if(dado == no.getDado()){
				return; //Nó duplicado
			} else if(dado.compareTo(no.getDado()) < 0){
				insere_AVL(dado, no.getFesq(), h);
				if(h[0]) {
					switch (no.getBal()){
						case 1:
							no.setBal(0);
							h[0] = false;
							break;
						case 0: 
							no.setBal(-1);
							break;
						case -1:
							//caso1(no, h); //A IMPLEMENTAR
							break;
					}
				} else {
					insere_AVL(dado, no.getFdir(), h);
					switch (no.getBal()){
						case -1:
							no.setBal(0);
							h[0] = false;
							break;
						case 0: 
							no.setBal(1);
							break;
						case 1:
							//caso2(no, h); //A IMPLEMENTAR
							break;
					}
				}
			}
		}
	}

}
