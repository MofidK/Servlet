/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author Mofid Krim
 */
public class DAO2 extends DAO {
    
    public DAO2(DataSource dataSource) {
        super(dataSource);
    }
    
    /**
     *
     * @return tous les etats dans la table CUSTOMER
     * @throws DAOException
     */
    public List<String> allStates() throws DAOException {
            List<String> result = new LinkedList<>(); // Liste vide

            String sql = "SELECT DISTINCT STATE AS ST FROM CUSTOMER";
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {

                    try (ResultSet rs = stmt.executeQuery()) {
                            while (rs.next()) { // Tant qu'il y a des enregistrements
                                // On récupère les champs nécessaires de l'enregistrement courant
                                String stateName = rs.getString("ST");
                                result.add(stateName);  // On l'ajoute à la liste des résultats
                            }
                    }
            }  catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }

            return result;
    }
}