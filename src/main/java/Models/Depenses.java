package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import Utils.BaseObject;

public class Depenses extends BaseObject {
    private int idCredit;
    private float montant;
    private Date dateDepense;

    public Depenses() {
        super();
    }

    public Depenses(int id, int idCredit, float montant) {
        super(id);
        this.idCredit = idCredit;
        this.montant = montant;
    }

    public Depenses(int id, int idCredit, float montant, Date dateDepense) {
        super(id);
        this.idCredit = idCredit;
        this.montant = montant;
        this.dateDepense = dateDepense;
    }

    public boolean isValideInsert(Connection conn) throws Exception {
        try {
            Credits credit = Credits.getById(this.idCredit, conn);
            if (credit == null) {
                throw new Exception("Credit non Existant dans la base");
            } else {
                Depenses[] liste = (Depenses[]) getAllByCredit(idCredit, conn);
                float totalDepense = 0;
                for (int i = 0; i < liste.length; i++) {
                    totalDepense += liste[i].getMontant();
                }
                float reste = credit.getMontant() - totalDepense;
                if (reste < this.montant) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            throw new Exception("Erreur lors du check: " + e.getMessage());
        }
    }

    public BaseObject[] getAllByCredit(int idCredit, Connection conn) throws Exception {
        ArrayList<Depenses> listeDepenses = new ArrayList<Depenses>();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM web_depenses where id_credit= ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idCredit);
            rs = statement.executeQuery();
            while (rs.next()) {
                Depenses depense = new Depenses();
                depense.setId(rs.getInt("id_depense"));
                depense.setIdCredit(rs.getInt("id_credit"));
                depense.setMontant(rs.getFloat("montant"));
                listeDepenses.add(depense);
            }
            return listeDepenses.toArray(new Depenses[listeDepenses.size()]);
        } catch (Exception e) {
            throw new Exception(
                    "Erreur lors de la recuperation des Depenses du credit" + idCredit + ":" + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static Depenses getById(int id, Connection conn) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM web_depenses WHERE id_depense = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("id_depense");
                int id_credit = resultSet.getInt("id_credit");
                float montant = resultSet.getFloat("montant");
                return new Depenses(ID, id_credit, montant);
            } else {
                throw new Exception("Credit not found with ID: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error retrieving credit: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void save(Connection conn) throws Exception {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO web_depenses (id_credit, montant) VALUES (?,?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, this.idCredit);
            statement.setFloat(2, this.montant);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur de sauvegarde du Depense: " + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    @Override
    public void delete(Connection conn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Connection conn) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public BaseObject[] getAll(Connection conn) throws Exception {
        ArrayList<Depenses> listeDepenses = new ArrayList<Depenses>();
        String sql = "SELECT * FROM web_depenses";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Depenses depense = new Depenses();
                depense.setId(rs.getInt("id_depense"));
                depense.setIdCredit(rs.getInt("id_credit"));
                depense.setMontant(rs.getFloat("montant"));
                listeDepenses.add(depense);
            }
            return listeDepenses.toArray(new Depenses[listeDepenses.size()]);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recuperation des Depenses:" + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

}
