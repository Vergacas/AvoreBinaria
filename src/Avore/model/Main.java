package Avore.model;

public class Main {
    public static void main(String[] args) {
        Avl<Integer> arvore = new Avl<Integer>();
        Integer ints[] =  new Integer[]{1, 2, 3, 4, 5};

        for(Integer i : ints) arvore.inserir(i);

        arvore.imprimir();
    }
}
