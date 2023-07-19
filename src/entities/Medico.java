package entities;

import java.time.LocalDate;

public class Medico {
    private String crm;
    private String nome;
    private LocalDate dataNasciemnto;
    private LocalDate dataCadastro;

    public Medico(String crm, String nome, LocalDate dataNasciemnto, LocalDate dataCadastro) {
        this.crm = crm;
        this.nome = nome;
        this.dataNasciemnto = dataNasciemnto;
        this.dataCadastro = dataCadastro;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNasciemnto() {
        return dataNasciemnto;
    }

    public void setDataNasciemnto(LocalDate dataNasciemnto) {
        this.dataNasciemnto = dataNasciemnto;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
