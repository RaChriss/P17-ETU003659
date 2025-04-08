package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import Utils.BaseObject;

public class Credits extends BaseObject {

    private String libelle;
    private float montant;
    private Date dateDebut;
    private Date dateFin;
    private float totalDepenses;



    public Credits() {
        super();
    }

    public Credits(int id, String libelle, float montant) {
        super(id);
        this.libelle = libelle;
        this.montant = montant;
    }

    public Credits(int id, String libelle, float montant, Date dateDebut, Date dateFin) {
        super(id);
        this.libelle = libelle;
        this.montant = montant;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public float getReste(Connection conn) throws Exception {
        try {
            float reste = 0;
            Depenses depense = new Depenses();
            Depenses[] liste = (Depenses[]) depense.getAllByCredit(this.id, conn);
            float sommeDepense = 0;
            for(int i=0;i<liste.length;i++) {
                sommeDepense += liste[i].getMontant();
            }
            reste = this.montant - sommeDepense;
            return reste;
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recuperation du reste: " + e.getMessage());
        }
    }

    @Override
    public void save(Connection conn) throws Exception {
        PreparedStatement statement = null;
        try {
            String sql = "INSERT INTO web_credits (libelle, montant) VALUES (?,?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, this.libelle);
            statement.setFloat(2, this.montant);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new Exception("Erreur de sauvegarde du Credit: " + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public static Credits getById(int id, Connection conn) throws Exception {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM web_credits WHERE id_credit = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int ID = resultSet.getInt("id_credit");
                String libelle = resultSet.getString("libelle");
                float montant = resultSet.getFloat("montant");
                return new Credits(ID, libelle, montant);
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
        ArrayList<Credits> listeCredits = new ArrayList<Credits>();
        String sql = "SELECT * FROM web_credits";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                Credits credit = new Credits();
                credit.setId(rs.getInt("id_credit"));
                credit.setLibelle(rs.getString("libelle"));
                credit.setMontant(rs.getFloat("montant"));
                listeCredits.add(credit);
            }
            return listeCredits.toArray(new Credits[listeCredits.size()]);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recuperation des Credits:" + e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public float getTotalDepenses() {
        return totalDepenses;
    }

    public void setTotalDepenses(float totalDepenses) {
        this.totalDepenses = totalDepenses;
    }
}
