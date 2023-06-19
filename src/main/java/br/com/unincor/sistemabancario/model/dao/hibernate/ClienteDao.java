/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unincor.sistemabancario.model.dao.hibernate;

import br.com.unincor.sistemabancario.model.domain.Cliente;

/**
 *
 * @author dioge
 */
public class ClienteDao extends GenericDao<Integer, Cliente>{
    
    public static void main(String[] args) {
        new ClienteDao().buscarTudo().forEach(System.out::println);
    }
    
}
