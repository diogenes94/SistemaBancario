package br.com.unincor.sistemabancario.model.domain;

public class Conta {
    
    private Integer id;
    private Cliente cliente;
    private Agencia agencia;
    private Double saldo;

    public Conta() {
    }

    public Conta(Integer id, Cliente cliente, Agencia agencia, Double saldo) {
        this.id = id;
        this.cliente = cliente;
        this.agencia = agencia;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Conta{" + "id=" + id + ", cliente=" + cliente + ", agencia=" + agencia + ", saldo=" + saldo + '}';
    }
    
    
}
