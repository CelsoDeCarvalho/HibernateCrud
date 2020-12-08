/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.sidratech.services;

import java.util.List;
import mz.com.sidratech.model.bean.Estudante;

/**
 *
 * @author celso
 */
public interface CRUD {
    /**
     *
     * @param estudante
     * @return
     */
    public String create_update(Estudante estudante);
    
    public Estudante search(int id);
    
    public List<Estudante> list();
    
    public Estudante delete(int id);
    
}
