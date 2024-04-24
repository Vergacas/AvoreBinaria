package principal.dao;

import principal.model.Animal;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AnimalDAO {
	private ArrayList<Animal> animais;
	
	public AnimalDAO() {
		animais = new ArrayList<>();
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
	
	public void salvarAnimais() throws IOException {
		String caminho = "salvos/animais.txt";
		
		FileWriter escritor = new FileWriter(caminho);
		String output = "";
		for(Animal animal : animais) {
			output = String.valueOf(animal.getId()) + ";" + animal.getApelido()
			+ ";" + animal.getEspecie() + ";" + animal.getDataNascimento() + ";"
			+ animal.getSexo() + ";" + animal.getDataInicioMonitoramento() + "\n";
			
			escritor.write(output);
		}
		escritor.close();
	}
	
	public void recuperarAnimais() {
		
	}
}
