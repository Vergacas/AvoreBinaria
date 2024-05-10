package dao;
import model.Monitoramento;

import java.io.File;
import java.io.FileWriter;
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
		a.setId_monitoramento(monitoramentos.size()+1);
		monitoramentos.inserir(a);
	}
	
	public void remover(int id) {
		Iterator<No<Monitoramento>> itr = monitoramentos.iterator();
		while(itr.hasNext()){
			Monitoramento monitoramento = itr.next().getDado();
			if (monitoramento.getId_animal() == id) {
				monitoramentos.remover(monitoramento);
			}
		}
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
					+ ";" + monitoramento.getExameFisico() + ";" + monitoramento.getObservacao() 
					+ ";" + monitoramento.getDataAvaliacao() + "\n";
				
				escritor.write(output);
				System.out.println("Monitoramento salvo com sucesso!");
			}
			escritor.close();
		} catch (Exception e) {
			System.out.println("ERRO!");
		}

		
	}
	
	public void recuperarMonitoramentos() {
		try {

			File file = new File("salvos/monitoramentos.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				Monitoramento a = new Monitoramento();
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
				a.setDataAvaliacao(dadosMonitoramento[i++]);

				monitoramentos.inserir(a);
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultar(int id){
		Iterator<No<Monitoramento>> itr = monitoramentos.iterator();
		if(monitoramentos.isEmpty()) {
			System.out.println("Não há monitoramentos cadastrados");
			return;
		}

		while(itr.hasNext()){
			Monitoramento monitoramento = itr.next().getDado();
			if(monitoramento.getId_animal() == id){
				System.out.println(" - - - - - - - - - - - - - - - - - - - - ");
				System.out.println("Data da avaliação: " + monitoramento.getDataAvaliacao());
				System.out.println("Peso(em Kg): " + monitoramento.getPeso());
				System.out.println("Altura(em Cm): " + monitoramento.getAltura());
				System.out.println("Temperatura(em °C): " + monitoramento.getTemperatura());
				if(monitoramento.getColetaSangue())	System.out.println("Coleta de sangue: Sim");
				else System.out.println("Coleta de sangue: Não");
				if(monitoramento.getExameFisico()) System.out.println("Exame físico: Sim");
				else System.out.println("Exame físico: Não");
				System.out.println("Observações: " + monitoramento.getObservacao());
				System.out.println(" - - - - - - - - - - - - - - - - - - - - ");
	
			}
		}
	}

	public int size(){
		return monitoramentos.size();
	}
}
