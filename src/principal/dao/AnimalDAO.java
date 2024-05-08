package principal.dao;

import principal.model.Animal;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalDAO {
	private ArrayList<Animal> animais;
	private static AnimalDAO bdAnimais;

	public AnimalDAO() {
		animais = new ArrayList<>();
		recuperarAnimais();
	}

	public static AnimalDAO getIstance(){
		if(bdAnimais == null) {
			bdAnimais = new AnimalDAO();
		}
		return bdAnimais;
	}
	
	public void addAnimal(Animal a) {
		animais.add(a);
	}
	
	public void remover(Integer a) {
		for(Animal animal : animais) {
			if(animal.getId().equals(a)) {
				animais.remove(animal);
			}
		}
	}

	public Animal getAnimal(int id){
		for (Animal animal : animais) {
			if (animal.getId() == id) {
				return animal;
			}
		}
		return null;
	}
	
	public void salvarAnimais(){

		try {
			String caminho = "salvos/animais.txt";
		
			FileWriter escritor = new FileWriter(caminho);
			String output = "";
			for(Animal animal : animais) {
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
				String[] dadosAnimal;
				String recuperar = scan.nextLine();
				dadosAnimal = recuperar.split(";");
				a.setId(Integer.valueOf(dadosAnimal[0]));
				a.setApelido(dadosAnimal[1]);
				a.setEspecie(dadosAnimal[2]);
				a.setDataNascimento(dadosAnimal[3]);
				char sexo = dadosAnimal[4].charAt(0);
				a.setSexo(sexo);
				a.setDataInicioMonitoramento(dadosAnimal[5]);
				animais.add(a);
			}
			scan.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
