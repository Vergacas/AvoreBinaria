package Animal;

import java.util.*;

public class Animal {
    /* Atributos */
    char sexo; //'F' ou 'M'
    Integer id;
    String apelido;
    String especie;
    String dataNascimento; //Se for conhecida
    List<Monitoramento> historico;
    String dataInicioMonitoramento;

    /* Construtores */
    public Animal() { /* Empty */}

    public Animal(Integer id, char sexo, String apelido, String especie,
     String dataNascimento, String dataMonitoramento){
        this.id = id;
        this.sexo = sexo;
        this.apelido = apelido;
        this.especie = especie;
        this.dataNascimento = dataNascimento;
        this.dataInicioMonitoramento = dataMonitoramento;
    }

    /* Métodos Set */
    public void setId(Integer id) {
        this.id = id;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }
   
    public void setMonitoramentos(List<Monitoramento> historico) {
        this.historico = historico;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setDataInicioMonitoramento(String dataInicioMonitoramento) {
        this.dataInicioMonitoramento = dataInicioMonitoramento;
    }


    /* Métodos Get */
    public Integer getId() {
        return id;
    }

    public String getApelido() {
        return apelido;
    }

    public String getEspecie() {
        return especie;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getDataInicioMonitoramento() {
        return dataInicioMonitoramento;
    }

    public List<Monitoramento> getMonitoramentos() {
        return historico;
    }

    /* Outros métodos */
    public void addMonitoramento(Monitoramento monitoramento) {
        if(historico == null){
            historico = new ArrayList<Monitoramento>();
        }
        
        historico.add(monitoramento);
    }

    public void imprimirHistorico(){
        historico.forEach((historico) -> System.out.println(historico));
    }
    
}
