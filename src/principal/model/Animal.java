package model;
public class Animal implements Comparable<Animal> {
    /* Atributos */
	private Integer id;
    private char sexo; //'F' ou 'M'
    private String apelido;
    private String especie;
    private String dataNascimento; //Se for conhecida
    private String dataInicioMonitoramento;

    /* Construtores */
    public Animal() {
    	this.id = null;
    	this.sexo = ' ';
    	this.apelido = null;
    	this.especie = null;
    	this.dataNascimento = null;
    }

    public Animal(int id){
        this.id = id;
    }

    public Animal(char sexo, String apelido, String especie,
     String dataNascimento, String dataMonitoramento){
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
    
    public char getSexo() {
    	return this.sexo;
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

    /* Outros métodos */
    @Override
    public int compareTo(Animal a2) {
        return this.id.compareTo(a2.getId());
    }

}
