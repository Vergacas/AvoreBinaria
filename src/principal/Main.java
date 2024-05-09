package principal;

import principal.dao.AnimalDAO;
import principal.dao.MonitoramentoDAO;
import principal.model.Animal;
import principal.model.Monitoramento;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int option = 0;
        AnimalDAO animais = new AnimalDAO();
        MonitoramentoDAO monitoramentos = new MonitoramentoDAO();
        boolean fileSalved = false;
        Scanner scanner = new Scanner(System.in);


        while(option != 6){
            Menu();

            try{
                option = scanner.nextInt();
            } catch (Exception e){
                scanner.next();
                System.out.println("Opção inválida");
                continue;
            }

            switch(option){
                case 1: animais.addAnimal(Cadastro(scanner));
                        fileSalved = false;
                        break;
                case 2: if(animais.listar()) System.out.println("Nenhum animal cadastrado!");
                        else{
                            animais.remover(Remocao(scanner));
                            fileSalved = false;
                        }
                        break;
                case 3: if(animais.listar()) System.out.println("Nenhum animal cadastrado!");
                        else animais.consultar(Consulta(scanner));
                        break;
                case 4: if(animais.listar()) System.out.println("Nenhum animal cadastrado!");
                        else{
                            Monitoramento monitoramento = Registro(scanner, monitoramentos.size());
                            animais.cadastrar(monitoramento);
                            monitoramentos.addMonitoramento(monitoramento);
                            fileSalved = false;
                        }                        
                        break;
                case 5: animais.salvarAnimais();
                        monitoramentos.salvarMonitoramentos();
                        fileSalved = true;
                        break;
                case 6: if(Sair(fileSalved, scanner)){
                            animais.salvarAnimais();
                            monitoramentos.salvarMonitoramentos();
                            System.out.println("Sistema encerrado com sucesso!");
                            System.exit(0);
                        }
                        break;
                default:System.out.println("Opção inválida");
                        break;
            }

        }

        scanner.close();
        
    }

    public static void Menu(){
        System.out.println("---------------------------------------------------------");
        System.out.println("BEM-VINDO AO SISTEMA DE MONITORAMENTO DO PARQUE DAS DUNAS");
        System.out.println("---------------------------------------------------------");
        System.out.println("O que gostaria de fazer a seguir?");
        System.out.println("(Digite o número da opção de sua escolha)");
        System.out.println("1 - Cadastrar novo animal no monitoramento");
        System.out.println("2 - Remover animal em monitoramento");
        System.out.println("3 - Consultar informações sobre um animal");
        System.out.println("4 - Registrar novo monitoramento de um animal");
        System.out.println("5 - Salvar dados da fauna");
        System.out.println("6 - Sair");
        System.out.println("---------------------------------------------------------");
    }

    public static Animal Cadastro(Scanner input){
        int id;
        char sexo;
        String apelido, especie, dataNascimento, dataMonitoramento;

        System.out.println("Informe o id do animal: ");

        while(true){    
            try{
                id = input.nextInt();
                break;
            } catch(Exception e) {
                System.out.println("ID não válida. Tente novamente.");
                input.next();
            }
        }
        input.nextLine();

        System.out.println("Informe o apelido do animal: ");
        apelido = input.nextLine();

        System.out.println("Informe a especie do animal: ");
        especie = input.nextLine();

        System.out.println("Informe o sexo do animal:(F/M) ");
        while(true){
            sexo = input.next().charAt(0);
            
            if(sexo != 'F' && sexo != 'M'){
                System.out.println("Sexo inválido. Digite novamente 'F' para Fêmea ou 'M' para Macho.");
            } else {
                break;
            }
        }
        input.nextLine();
 
        System.out.println("Para as datas a seguir, utilize o formato dd-MM-AAAA");
        System.out.println("Informe a data de nascimento do animal(caso não saiba aperte enter): ");
        dataNascimento = input.nextLine();
        System.out.println("Informe a data de início do monitoramento: ");
        dataMonitoramento = input.nextLine();
            
        Animal animal = new Animal(id, sexo, apelido, especie, dataNascimento, dataMonitoramento);
        return animal;
    }

    public static int Remocao(Scanner input){
        int id = -1;

        System.out.println("Informe o id do animal que deseja remover: ");

        while(true){
            try{
                id = input.nextInt();
                break;
            } catch (Exception e ) {
                System.out.println("O id informado é inválido!");
            }
        }

        return id;
    }

    public static int Consulta(Scanner input){
        int id = -1;

        System.out.println("Informe o id do animal que deseja consultar: ");

        while(true){
            try{
                id = input.nextInt();
                break;
            } catch (Exception e ) {
                System.out.println("O id informado é inválido!");
            }
        }

        return id;
    }

    public static Monitoramento Registro(Scanner input, int id){
        char aux;
        int idAnimal = -1, idMonit = id;
        String observacao, data;
        double peso, altura, temperatura;
        boolean coletaSangue, exameFisico;

        System.out.println("Informe o id do animal que deseja registrar o monitoramento: ");

        while(true){
            try{
                idAnimal = input.nextInt();
                break;
            } catch (Exception e ) {
                System.out.println("O id informado é inválido!");
            }
        }

        System.out.println("Informe o peso do animal: ");

        while(true){
            try{
                peso = input.nextDouble();
                break;
            } catch (Exception e ) {
                System.out.println("O peso informado é inválido!");
                input.next();
            }
        }
        
        System.out.println("Informe a altura do animal: ");
        while(true){
            try{
                altura = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("A altura informada é inválida!");
                input.next();
            }
        }

        System.out.println("Informe a temperatura do animal: ");
        while(true){
            try{
                temperatura = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("A temperatura informada é inválida!");
                input.next();
            }
        }

        System.out.println("Houve coleta de sangue: (S/N)");
        while(true){
            aux = input.next().charAt(0);

            if(aux == 's' || aux == 'S'){
                coletaSangue = true;
                break;
            } else if(aux == 'n' || aux == 'N'){
                coletaSangue = false;
                break;
            } else {
                System.out.println("Informação inválida. Digite novamente 'S' caso tenha ocorrido ou 'N' caso contrário.");
            }
        }

        System.out.println("Foi notado algum problema com a saúde do animal?(S/N)");
        while(true){
            aux = input.next().charAt(0);

            if(aux == 's' || aux == 'S'){
                exameFisico = true;
                break;
            } else if(aux == 'n' || aux == 'N'){
                exameFisico = false;
                break;
            } else {
                System.out.println("Informação inválida. Digite novamente 'S' caso algum problema foi notado ou 'N' caso contrário.");
            }
        }

        if(exameFisico){
            System.out.println("Descreva o problema notado durante o exame: ");
            observacao = input.next();
        } else {
            observacao = "-";
        }

        System.out.println("Informe a data de realização do monitoramento(dd-MM-AAAA): ");
        data = input.next();

        return new Monitoramento(idMonit, idAnimal, peso, altura, temperatura, coletaSangue, exameFisico, observacao, data);
    }

    public static boolean Sair(boolean fileSalved, Scanner input){
        if(!fileSalved) {
            System.out.println("Você ainda não salvou os dados!");
            System.out.println("Deseja salvar os dados? (S/N)");
    
            char answer = input.next().charAt(0);

            if (answer == 'S' || answer == 's') {
                return true;
            } else {
                return false;
            }
        } 

        return false;
    }

}
