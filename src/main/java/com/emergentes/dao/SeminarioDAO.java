
package com.emergentes.dao;

import com.emergentes.modelo.Seminario;
import java.util.List;

public interface SeminarioDAO {
    
    public void insert(Seminario aviso) throws Exception;
    public void update(Seminario aviso) throws Exception;
    public void delete(int id) throws Exception;
    public Seminario getById(int id) throws Exception;
    public List<Seminario> getAll() throws Exception;

}
