package principal.dao;

import principal.model.Animal;
import principal.model.Monitoramento;

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
	
	public void remover(int id) {
		animais.remove(getAnimal(id));
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
				a.setHistorico(new ArrayList<Monitoramento>());
				
				animais.add(a);
			}
			scan.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public boolean listar(){
		System.out.println("Animais cadastrados: ");
		for(int i=0; i<animais.size(); ++i){
			Animal animal = animais.get(i);

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
		Animal animal = getAnimal(id);

		System.out.println("ID: " + animal.getId());
		System.out.println("Apelido: " + animal.getApelido());
		System.out.println("Especie: " + animal.getEspecie());
		System.out.println("Data de Nascimento: " + animal.getDataNascimento());
		System.out.println("Data de início de Monitoramento: " + animal.getDataInicioMonitoramento());
		System.out.println("Histórico de monitoramento: ");

		if(animal.getHistorico().isEmpty()) System.out.println("Vazio.");
		else for(Monitoramento monitoramento : animal.getHistorico()) {
			System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
			System.out.println(monitoramento);
			System.out.println("- - - - - - - - - - - - - - - - - - - - - -");
		}
			
	}

	public void cadastrar(Monitoramento monitoramento){
		Animal animal = getAnimal(monitoramento.getId_animal());

		animal.addMonitoramento(monitoramento);
	}
}
