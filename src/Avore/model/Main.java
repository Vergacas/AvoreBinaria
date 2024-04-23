package Avore.model;

import Animal.*;
public class Main {
    public static void main(String[] args) {
        Avl<Integer> arvore = new Avl<Integer>();
        Integer ints[] =  new Integer[]{1, 2, 3, 4, 5};

        for(Integer i : ints) arvore.inserir(i);

        arvore.imprimir();

        Animal animal = new Animal(01, 'F', "Laila", "Cachorro", "10-10-10", "20-03-24");
        animal.addMonitoramento(new Monitoramento(5.0, 0.50, 25.0, true, false, "Muito magra", "23-04-24"));

        animal.addMonitoramento(new Monitoramento(5.0, 0.50, 25.0, true, false, "Muito magra", "25-04-24"));

        animal.imprimirHistorico();
    }
}
