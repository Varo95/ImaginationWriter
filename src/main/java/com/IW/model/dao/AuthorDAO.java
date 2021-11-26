package com.IW.model.dao;

import com.IW.interfaces.SQL.IAuthorDAO;
import com.IW.model.objects.Author;
import com.IW.utils.PersistenceUnit;

import javax.persistence.EntityManager;

public class AuthorDAO extends Author implements IAuthorDAO {
    //TODO cambiar el EM
    @Override
    public void persist() {
        EntityManager em = PersistenceUnit.createEM("");
        em.getTransaction().begin();
        em.persist(this);
        em.getTransaction().commit();
    }

    @Override
    public void remove() {
        EntityManager em = PersistenceUnit.createEM("");
        em.getTransaction().begin();
        em.remove(this);
        em.getTransaction().commit();
    }

    public boolean checkUser() {
        /*String encryptedUPwd = Tools.encryptSHA256(getPassword());
        List<Object> params = new ArrayList<>();
        String encryptedDBPwd="";
        if(getId()!=-1){
            params.add(getId());
            try {
                ResultSet rs = SQL.execQuery(querys.GETPASSWORD_BY_ID.getQ(), params);
                if (rs != null) {
                    while (rs.next()) {
                        encryptedDBPwd = rs.getString("passwd");
                    }
                }
            }catch (SQLException e){
                logger.error("Hubo un error al intentar recuperar la contraseña del usuario por el id. Con el mensaje:\n"+e.getMessage());
            }
        }else{
            params.add(userToCheck.getName());
            try {
                ResultSet rs = SQL.execQuery(querys.GETPASSWORD_BY_NAME.getQ(), params);
                if (rs != null) {
                    while (rs.next()) {
                        encryptedDBPwd = rs.getString("passwd");
                        userToCheck.setId(rs.getLong("id"));
                    }
                }
            }catch (SQLException e){
                logger.error("Hubo un error al intentar recuperar la contraseña del usuario con el nombre. Con el mensaje:\n"+e.getMessage());
            }
        }
        return encryptedUPwd.equals(encryptedDBPwd);*/
        return false;
    }
}
