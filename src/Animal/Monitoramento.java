package Animal;

public class Monitoramento {
    /* Atributos */
    Double peso;
    Double altura;
    String observacao; //Caso o exame físico não esteja ok
    Double temperatura;
    Boolean exameFisico; //Se foi realizado a coleta de sangue
    Boolean coletaSangue; //Resultado do exame físico - Ok(true) ou Não(false)
    String dataAvaliacao;

    /* Construtores */
    public Monitoramento(){ /* Empty */}
    
    public Monitoramento(Double peso, Double altura, Double temp, 
    Boolean coletaSangue, Boolean exameFisico, String obs, String data){
        this.peso = peso;
        this.altura = altura;
        this.temperatura = temp;
        this.coletaSangue = coletaSangue;
        this.exameFisico = exameFisico;
        this.observacao = obs;
        this.dataAvaliacao = data;
    }

    /* Métodos Set */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public void setExameFisico(Boolean exameFisico) {
        this.exameFisico = exameFisico;
    }

    public void setColetaSangue(Boolean coletaSangue) {
        this.coletaSangue = coletaSangue;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    /* Outros métodos */
    @Override
    public String toString(){
        return "Peso: " + this.peso + " Altura: " + this.altura + " Temperatura: " + 
            this.temperatura + " Exame Físico: " + this.exameFisico + " Coleta de Sangue: " + 
            this.coletaSangue + " Observação: " + this.observacao + " Data: " + this.dataAvaliacao;
    }

    
}
