package com.emergentes.dao;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeminarioDAOimpl extends ConexionBD implements SeminarioDAO {

    @Override
    public void insert(Seminario seminario) throws Exception {
        try {
            this.conectar();
            String sql = "insert into seminarios(titulo, expositor, fecha, hora, cupo) values(?, ?, ?, ?, ?)";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, seminario.getTitulo());
            ps.setString(2, seminario.getExpositor());
            ps.setString(3, seminario.getFecha());
            ps.setString(4, seminario.getHora());
            ps.setInt(5, seminario.getCupo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error al actualizar el aviso insert(): " + e.getMessage());
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void update(Seminario seminario) throws Exception {
        try {
            this.conectar();
            String sql = "update seminarios set titulo=?, expositor=?, fecha=?, hora=?, cupo=? WHERE id=?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, seminario.getTitulo());
            ps.setString(2, seminario.getExpositor());
            ps.setString(3, seminario.getFecha());
            ps.setString(4, seminario.getHora());
            ps.setInt(5, seminario.getCupo());
            
            ps.setInt(6, seminario.getId());
            
            ps.executeUpdate();

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("La actualización se realizó correctamente.");
            } else {
                System.out.println("No se realizó ninguna actualización.");
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al actualizar el aviso: " + e.getMessage());
        } finally {
            this.desconectar();
        }
        
    }
   

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "delete from seminarios where id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ps.executeUpdate();
            
            
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    
    @Override
    public Seminario getById(int id) throws Exception {
        Seminario seminario = new Seminario();
        try {
            this.conectar();
            String sql = "select * from seminarios where id = ?";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                seminario.setId(rs.getInt("id"));
                seminario.setTitulo(rs.getString("titulo"));
                seminario.setExpositor(rs.getString("expositor"));
                seminario.setFecha(rs.getString("fecha"));
                seminario.setHora(rs.getString("hora"));
                seminario.setCupo(rs.getInt("cupo"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR EN SQL getById(): " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            this.desconectar();
            
        }

        return seminario;
    }

    @Override
    public List<Seminario> getAll() throws Exception {
        ArrayList<Seminario> lista = new ArrayList<>();
        try {
            this.conectar();
            String sql = "select * from seminarios";
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Seminario seminario = new Seminario();
                seminario.setId(rs.getInt("id"));
                seminario.setTitulo(rs.getString("titulo"));
                seminario.setExpositor(rs.getString("expositor"));
                seminario.setFecha(rs.getString("fecha"));
                seminario.setHora(rs.getString("hora"));
                seminario.setCupo(rs.getInt("cupo"));
                
                lista.add(seminario);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.out.println("ERROR EN SQL getAll(): " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            this.desconectar();
        }

        return lista;

    }

}
