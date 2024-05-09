package principal.dao;

import principal.model.Animal;
import principal.model.Monitoramento;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Avore.model.Avl;
import Avore.model.No;

public class AnimalDAO {
	private Avl<Animal> animais;
	private static AnimalDAO bdAnimais;

	public AnimalDAO() {
		animais = new Avl<Animal>();
		recuperarAnimais();
	}

	public static AnimalDAO getIstance(){
		if(bdAnimais == null) {
			bdAnimais = new AnimalDAO();
		}
		return bdAnimais;
	}
	
	public void addAnimal(Animal a) {
		animais.inserir(a, animais.getRaiz());
	}
	
	public void remover(int id) {
		animais.remove(getAnimal(id));
	}

	public No<Animal> getAnimal(int id){
		
		return animais.buscar(new No<Animal>( new Animal(id)), animais.getRaiz());
	}
	
	public void salvarAnimais(){

		try {
			String caminho = "salvos/animais.txt";
		
			FileWriter escritor = new FileWriter(caminho);
			String output = "";

			Iterator<No<Animal>> itr = animais.iterator();
			while(itr.hasNext()){
				Animal animal = itr.next().getDado();

				output = String.valueOf(animal.getId()) + ";" + animal.getApelido()
				+ ";" + animal.getEspecie() + ";" + animal.getDataNascimento() + ";"
				+ animal.getSexo() + ";" + animal.getDataInicioMonitoramento() + "\n";
				
				escritor.write(output);
				System.out.println("Animal salvo com sucesso!");
			}
			escritor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO!");
		}

		
	}
	
	public void recuperarAnimais() {
		try {

			File file = new File("salvos/animais.txt");
			Scanner scan = new Scanner(file);
			Animal a = new Animal();
			while (scan.hasNextLine()) {
				int i = 0;
				String[] dadosAnimal;
				String recuperar = scan.nextLine();
				dadosAnimal = recuperar.split(";");
				a.setId(Integer.valueOf(dadosAnimal[i++]));
				a.setApelido(dadosAnimal[i++]);
				a.setEspecie(dadosAnimal[i++]);
				a.setDataNascimento(dadosAnimal[i++]);
				char sexo = dadosAnimal[i++].charAt(0);
				a.setSexo(sexo);
				a.setDataInicioMonitoramento(dadosAnimal[i++]);
				
				animais.inserir(a, animais.getRaiz());
			}
			scan.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean listar(){
		System.out.println("Animais cadastrados: ");
		Iterator<No<Animal>> itr = animais.iterator();
		while(itr.hasNext()){
			Animal animal = itr.next().getDado();

			System.out.println(" - - - - - - - - - - - - - - - - - - ");
			
			System.out.println("ID: " + animal.getId());
			System.out.println("Apelido: " + animal.getApelido());
			System.out.println("Especie: " + animal.getEspecie());
			System.out.println("Data Nascimento: " + animal.getDataNascimento());
			System.out.println("Data de início de monitoramento: " + animal.getDataInicioMonitoramento());
			System.out.println(" - - - - - - - - - - - - - - - - - - ");
		}
		return animais.isEmpty();
	}

	public void consultar(int id){
		Animal animal = getAnimal(id).getDado();

		System.out.println("ID: " + animal.getId());
		System.out.println("Apelido: " + animal.getApelido());
		System.out.println("Especie: " + animal.getEspecie());
		System.out.println("Data de Nascimento: " + animal.getDataNascimento());
		System.out.println("Data de início de Monitoramento: " + animal.getDataInicioMonitoramento());	
	}
}
