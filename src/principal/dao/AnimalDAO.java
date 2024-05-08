package principal.dao;

import principal.model.Animal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AnimalDAO {
	private ArrayList<Animal> animais;
	private static AnimalDAO bdAnimais;

	public AnimalDAO() {
		animais = new ArrayList<>();
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
		
	}
}
