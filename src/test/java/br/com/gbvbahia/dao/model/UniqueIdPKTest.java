/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.dao.model;

import br.com.gbvbahia.unique.Unique;
import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
@Unique(fields = {"idOne", "idTwo"})
public class UniqueIdPKTest implements  Serializable{
    
    private Long idOne = 1L;
    
    private long idTwo = 2L;

    public Long getIdOne() {
        return idOne;
    }

    public void setIdOne(Long idOne) {
        this.idOne = idOne;
    }

    public Long getIdTwo() {
        return idTwo;
    }

    public void setIdTwo(Long idTwo) {
        this.idTwo = idTwo;
    }
    
     
}
