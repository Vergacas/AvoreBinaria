package principal.dao;

import principal.model.Monitoramento;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MonitoramentoDAO {
	private ArrayList<Monitoramento> monitoramentos;
	private static MonitoramentoDAO bdMonitoramentos;

	public MonitoramentoDAO() {
		monitoramentos = new ArrayList<>();
		recuperarMonitoramentos();
	}

	public static MonitoramentoDAO getIstance(){
		if(bdMonitoramentos == null) {
			bdMonitoramentos = new MonitoramentoDAO();
		}
		return bdMonitoramentos;
	}
	
	public void addMonitoramento(Monitoramento a) {
		monitoramentos.add(a);
	}
	
	public void remover(int id) {
		monitoramentos.remove(getMonitoramento(id));
	}

	public Monitoramento getMonitoramento(int id){
		for (Monitoramento monitoramento : monitoramentos) {
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
			for(Monitoramento monitoramento : monitoramentos) {
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

				monitoramentos.add(a);
			}
			scan.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void consultar(int id){
		Monitoramento monitoramento = getMonitoramento(id);

		System.out.println(monitoramento);
			
	}

	public int size(){
		return 1;
	}
}
