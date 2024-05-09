package principal.dao;

import principal.model.Animal;
import principal.model.Monitoramento;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import Avore.model.Avl;
import Avore.model.No;

public class MonitoramentoDAO {
	private Avl<Monitoramento> monitoramentos;
	private static MonitoramentoDAO bdMonitoramentos;

	public MonitoramentoDAO() {
		monitoramentos = new Avl<>();
		recuperarMonitoramentos();
	}

	public static MonitoramentoDAO getIstance(){
		if(bdMonitoramentos == null) {
			bdMonitoramentos = new MonitoramentoDAO();
		}
		return bdMonitoramentos;
	}
	
	public void addMonitoramento(Monitoramento a) {
		monitoramentos.inserir(a, monitoramentos.getRaiz());
	}
	
	public void remover(int id) {
		monitoramentos.remove(getMonitoramento(id));
	}

	public Monitoramento getMonitoramento(int id){
		Iterator<No<Monitoramento>> itr = monitoramentos.iterator();
		while(itr.hasNext()){
			Monitoramento monitoramento = itr.next().getDado();
			if (monitoramento.getId_monitoramento() == id) {
				return monitoramento;
			}
		}
		return null;
	}
	
	public void salvarMonitoramentos(){

		try {
			String caminho = "salvos/monitoramentos.txt";
		
			FileWriter escritor = new FileWriter(caminho);
			String output = "";
			Iterator<No<Monitoramento>> itr = monitoramentos.iterator();
			while(itr.hasNext()){
				Monitoramento monitoramento = itr.next().getDado();
				output = monitoramento.getId_monitoramento() + ";" + monitoramento.getId_animal()
					+ ";" + monitoramento.getPeso() + ";" + monitoramento.getAltura()
					+ ";" + monitoramento.getTemperatura() + ";" + monitoramento.getColetaSangue()
					+ ";" + monitoramento.getExameFisico() + ";" + monitoramento.getObservacao() + "\n";
				
				escritor.write(output);
				System.out.println("Monitoramento salvo com sucesso!");
			}
			escritor.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERRO!");
		}

		
	}
	
	public void recuperarMonitoramentos() {
		try {

			File file = new File("salvos/monitoramentos.txt");
			Scanner scan = new Scanner(file);
			Monitoramento a = new Monitoramento();
			while (scan.hasNextLine()) {
				int i = 0;
				String[] dadosMonitoramento;
				String recuperar = scan.nextLine();
				dadosMonitoramento = recuperar.split(";");
				a.setId_monitoramento(Integer.valueOf(dadosMonitoramento[i++]));
				a.setId_animal((Integer.valueOf(dadosMonitoramento[i++])));
				a.setPeso(Double.valueOf(dadosMonitoramento[i++]));
				a.setAltura(Double.valueOf(dadosMonitoramento[i++]));
				a.setTemperatura(Double.valueOf(dadosMonitoramento[i++]));
				a.setColetaSangue(Boolean.valueOf(dadosMonitoramento[i++]));
				a.setExameFisico(Boolean.valueOf(dadosMonitoramento[i++]));
				a.setObservacao(dadosMonitoramento[i++]);

				monitoramentos.inserir(a, monitoramentos.getRaiz());
			}
			scan.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public int size(){
		return 1;
	}

	public void consultar(int id){
		Iterator<No<Monitoramento>> itr = monitoramentos.iterator();
		while(itr.hasNext()){
			Monitoramento monitoramento = itr.next().getDado();
			if(monitoramento.getId_animal() == id){
				System.out.println("Data: " + monitoramento.getDataAvaliacao());
				System.out.println("Peso: " + monitoramento.getPeso());
				System.out.println("Altura: " + monitoramento.getAltura());
				System.out.println("Temperatura: " + monitoramento.getTemperatura());
				if(monitoramento.getColetaSangue())	System.out.println("Coleta de sangue: Sim");
				else System.out.println("Coleta de sangue: Não");
				if(monitoramento.getExameFisico()) System.out.println("Exame físico: Sim");
				else System.out.println("Exame físico: Não");
				System.out.println("Observações: " + monitoramento.getObservacao());
	
			}
		}
	}
}
