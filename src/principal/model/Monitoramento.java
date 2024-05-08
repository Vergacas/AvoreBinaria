package principal.model;

public class Monitoramento {
    /* Atributos */
	private Integer id_monitoramento;
	private Integer id_animal;
	
    private Double peso;
    private Double altura;
    private String observacao; //Caso o exame físico não esteja ok
    private Double temperatura;
    private Boolean exameFisico; //Se foi realizado a coleta de sangue
    private Boolean coletaSangue; //Resultado do exame físico - Ok(true) ou Não(false)
    private String dataAvaliacao;

    /* Construtores */
    public Monitoramento(){ /* Empty */}
    
    public Monitoramento(int IDAnimal, Double peso, Double altura, Double temp, 
    Boolean coletaSangue, Boolean exameFisico, String obs, String data){
        this.id_animal = IDAnimal;
        this.peso = peso;
        this.altura = altura;
        this.temperatura = temp;
        this.coletaSangue = coletaSangue;
        this.exameFisico = exameFisico;
        this.observacao = obs;
        this.dataAvaliacao = data;
    }

    
    
   /* Métodos Get*/
    public Integer getId_monitoramento() {
		return id_monitoramento;
	}

	public Integer getId_animal() {
		return id_animal;
	}

	public Double getPeso() {
		return peso;
	}

	public Double getAltura() {
		return altura;
	}

	public String getObservacao() {
		return observacao;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public Boolean getExameFisico() {
		return exameFisico;
	}

	public Boolean getColetaSangue() {
		return coletaSangue;
	}

	public String getDataAvaliacao() {
		return dataAvaliacao;
	}

	/* Métodos Set */
	public void setId_animal(Integer id_animal) {
		this.id_animal = id_animal;
	}
	
	public void setId_monitoramento(Integer id_monitoramento) {
		this.id_monitoramento = id_monitoramento;
	}
	
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
        return " Peso: " + this.peso + "\n Altura: " + this.altura + "\n Temperatura: " + 
            this.temperatura + "\n Exame Físico: " + this.exameFisico + "\n Coleta de Sangue: " + 
            this.coletaSangue + "\n Observação: " + this.observacao + "\n Data: " + this.dataAvaliacao;
    }

    
}
