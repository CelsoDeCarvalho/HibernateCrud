/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.sidratech.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import mz.com.sidratech.connection.ConnectionFactory;
import mz.com.sidratech.model.bean.Estudante;
import mz.com.sidratech.services.CRUD;

/**
 *
 * @author celso
 */
public class EstudanteDAO implements CRUD{

    @Override
    public  String create_update(Estudante estudante) {
        
        EntityManager entityManager= ConnectionFactory.getConnection();
        
        try{
           entityManager.getTransaction().begin();
           
           if(estudante.getId()==null){
               entityManager.persist(estudante);
           }else
               entityManager.merge(estudante);
           
           entityManager.getTransaction().commit();
           return estudante.getNome()+" adicionado/actualizado com sucesso";
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
           throw new RuntimeException("Erro ao Salvar/Actualizar Estudante",ex);
        }finally{
            entityManager.close();
        }
    }

    @Override
    public Estudante search(int id) {
       
        EntityManager entityManager= ConnectionFactory.getConnection();
        Estudante estudante=null;
        
       try{
           estudante=entityManager.find(Estudante.class, id);
       }catch(Exception ex){
           throw new RuntimeException(ex);
       }finally{
           entityManager.close();
       }
       
       return estudante;
    }

    @Override
    public List<Estudante> list(){
        EntityManager entityManager= ConnectionFactory.getConnection();
        List<Estudante> estudantes=null;
        
        try{
            estudantes=entityManager.createQuery("from Estudante").getResultList();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }finally{
            entityManager.close();
        }
        return estudantes;
    }

    @Override
    public Estudante delete(int id) {
        EntityManager entityManager= ConnectionFactory.getConnection();
        Estudante estudante=null;
        
        try{
            estudante=entityManager.find(Estudante.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(estudante);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw new RuntimeException(ex);
        }finally{
            entityManager.close();
        }
        return estudante;
    }
    
}
