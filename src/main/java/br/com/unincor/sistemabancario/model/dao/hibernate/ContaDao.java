/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.model.dao.hibernate;

import br.com.unincor.sistemabancario.model.domain.Conta;

/**
 *
 * @author dioge
 */
public class ContaDao extends GenericDao<Integer, Conta>{
    
    
    public static void main(String[] args) {
        ContaDao contaDao = new ContaDao();
        contaDao.buscarTudo().forEach(System.out::println);
    }
}
