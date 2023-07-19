package Progam;

import entities.Consulta;
import entities.Medico;
import entities.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Progam {

    private static ArrayList<Medico> medicos = new ArrayList<>();
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Consulta> consultas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opc = -1;
        while (opc != 0) {
            System.out.println("===== MENU =====");
            System.out.println("\nSistema de Consultório\n");
            System.out.println("1 - Cadastro de Médicos");
            System.out.println("2 - Cadastro de Pacientes");
            System.out.println("3 - Cadastro de Consultas");
            System.out.println("4 - Cancelamento de Consultas");
            System.out.println("5 - Relatório de Médicos Cadastrados");
            System.out.println("6 - Relatório de Consultas Agendadas");
            System.out.println("0 - Sair");
            System.out.println("================");

            System.out.print("Digite a opção: ");
            opc = sc.nextInt();
            System.out.println();
            switch (opc) {
                case 0:
                    System.out.println("Programa encerrado! ");
                    break;
                case 1:
                    cadastraMedico();
                    break;
                case 2:
                    cadastroPaciente();
                    break;
                case 3:
                    cadastroConsulta();
                    break;
                case 4:
                    cancelaConsulta();
                    break;
                case 5:
                    relatorioMedicos();
                    break;
                case 6:
                    relatorioConsultas();
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    public static void cadastraMedico() {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("--CADASTRO DE MÉDICO--");

        System.out.print("CRM: ");
        String crm = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        LocalDate meuca = LocalDate.parse(sc.nextLine(), fmt1);

        LocalDate dataCadastro = LocalDate.now();

        Medico medico = new Medico(crm, nome, meuca, dataCadastro);
        medicos.add(medico);
    }

    public static void cadastroPaciente() {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("--CADASTRO DE PACIENTE--");
        System.out.println();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Data de nascimento: (dd/MM/YYYY): ");
        LocalDate dataNascimento = LocalDate.parse(sc.nextLine(), fmt1);

        LocalDate dataCadastro = LocalDate.now();

        String end = sc.nextLine();

        Paciente paciente = new Paciente(cpf, nome, dataNascimento, dataCadastro, end);
        pacientes.add(paciente);
    }

    public static void cadastroConsulta() {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.print("CRM MÉDICO: ");
        String crmMedico = sc.nextLine();
        System.out.print("CPF PACIENTE: ");
        String cpfPaciente = sc.nextLine();

        Medico medico = null;
        for (Medico c : medicos) {
            if (c.getCrm().equals(crmMedico)) {
                medico = c;
                break;
            }
            if (medico == null) {
                System.out.println("\nMÉDICO NÃO ENCONTRADO\n");
                return;
            }
        }
        Paciente paciente = null;
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(cpfPaciente)) {
                paciente = p;
                break;
            }
            if (paciente == null) {
                System.out.println("\n PACIENTE NÃO ENCONTRADO\n");
                return;
            }
        }
        System.out.print("Data da consulta (dd/MM/yyyy): ");
        LocalDate dataConsulta = LocalDate.parse(sc.nextLine(), fmt1);

        System.out.print("Hora da consulta (hh/mm): ");
        LocalTime horaConsulta = LocalTime.parse(sc.nextLine());

        System.out.print("Primeira consulta(s/n): ");
        String primeiraConsultaStr = sc.nextLine();

        boolean primeiraConsulta = primeiraConsultaStr.equalsIgnoreCase("S");
        double valorConsulta = primeiraConsulta ? 350.0 : 300;
        String status = "AGENDADA";
        Consulta consulta = new Consulta(medico, paciente, dataConsulta, horaConsulta, primeiraConsulta, status, valorConsulta);
        consultas.add(consulta);
        System.out.println();
        System.out.println("\nConsulta cadastrada com sucesso\n");
    }

    public static void cancelaConsulta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--CANCELAMENTO DE CONSULTA--");
        System.out.println();

        System.out.print("CPF do paciente: ");
        String cpfPaciente = sc.nextLine();

        System.out.print("Data da consulta (dd/MM/yyyy): ");
        LocalDate dataConsulta = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("CRM do médico: ");
        String crmMedico = sc.nextLine();

        Consulta consulta = null;
        for (Consulta c : consultas) {
            if (c.getPaciente().getCpf().equals(cpfPaciente) && c.getDataConsulta().equals(dataConsulta) && c.getMedico().getCrm().equals(crmMedico)) {
                consulta = c;
                break;
            }
        }

        if (consulta == null) {
            System.out.println("\nConsulta não encontrada!\n");
            return;
        }

        consulta.setStatus("CANCELADA");

        System.out.println("\nConsulta cancelada com sucesso!\n");
    }
    public static void relatorioConsultas(){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nRelatório de consultas\n");
        System.out.print("CRM do médico: ");
        String crmMedico = sc.nextLine();

        System.out.print("Data da consulta(dd/MM/yyyy): ");
        LocalDate data = LocalDate.parse(sc.nextLine(),fmt1);

        ArrayList<Consulta> relatorioCosnultas = new ArrayList<>();
        for(Consulta c : consultas){
            if(c.getMedico().getCrm().equals(crmMedico)&&c.getDataConsulta().equals(data)){
                relatorioCosnultas.add(c);
            }
        if (relatorioCosnultas.isEmpty()){
            System.out.println("Cosnulta não encontrada!");
            return;
        }
    }
        for (Consulta c : relatorioCosnultas){
            System.out.println("Paciente: "+c.getPaciente().getNome());
            System.out.println("Hora: "+c.getHoraConsulta());
            System.out.println("Data: "+c.getDataConsulta());
            System.out.println("-----------------------------------");
    }
}
    public static void relatorioMedicos(){
        if (medicos.isEmpty()) {
            System.out.println("Não há médicos cadastrados.");
        } else {
            System.out.println("Médicos cadastrados:");
            for (Medico medico : medicos) {
                System.out.println("CRM: " + medico.getCrm() + " | Nome: " + medico.getNome() +
                        " | Data de cadastro: " + medico.getDataCadastro());
            }
        }
    }
}



