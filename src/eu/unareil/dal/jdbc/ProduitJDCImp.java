package eu.unareil.dal.jdbc;

import eu.unareil.bo.*;
import eu.unareil.dal.DAO;
import eu.unareil.dal.DalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitJDCImp implements DAO<Produit> {

    private static final String SQL_INSERT="insert into produit (marque,libelle,prixUnitaire,qteStock,typeProduit,dateLimiteConso,poids,couleur,typeMine,parfum,temperatureConservation,typeCartePostale) values(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="update produit set marque=?,libelle=?,prixUnitaire=?,qteStock=?,typeProduit=?,dateLimiteConso=?,poids=?,couleur=?,typeMine=?,parfum=?,temperatureConservation=?,typeCartePostale=? where refProd=?";
    private static final String SQL_DELETE="delete from produit where refProd=?";
    private static final String SQL_SELECT_ALL="select * from produit";
    private static final String SQL_INSERT_CARTE_AUTEUR="insert into carte_auteur ( idAuteur , refProd ) values(?,?)";
    private static final String SQL_SELECT_ALL_CARTE_AUTEUR_BY_ID="select * from carte_auteur where refProd = ?";

    private static final String SQL_SELECT_BY_ID="select * from produit where refProf=?";

    @Override
    public void insert(Produit data) throws DalException {
        PreparedStatement pstmt=null;
        //Connection cnx=null;
        Connection cnx=JdbcTools.getConenction();
        try {
            pstmt=cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, data.getMarque());
            pstmt.setString(2, data.getLibelle());
            pstmt.setFloat(3, data.getPrixUnitaire());
            pstmt.setLong(4, data.getQteStock());
            if (data instanceof Pain){
                pstmt.setString(5, "Pain");
                pstmt.setDate(6, (Date.valueOf( ((ProduitPerissable)  data).getDatelimitConso())));
                pstmt.setInt(7, ((Pain) data).getPoids());
                pstmt.setNull(8, java.sql.Types.VARCHAR );
                pstmt.setNull(9, java.sql.Types.VARCHAR );
                pstmt.setNull(10, java.sql.Types.VARCHAR );
                pstmt.setNull(11, java.sql.Types.INTEGER );
                pstmt.setNull(12, java.sql.Types.VARCHAR );
            } else if (data instanceof Stylo) {
                pstmt.setString(5, "Stylo");
                pstmt.setNull(7, java.sql.Types.INTEGER);
                pstmt.setString(8,  ((Stylo) data).getCouleur());
                pstmt.setNull(6, java.sql.Types.DATE);
                pstmt.setString(9, ((Stylo) data).getTypeMine());
                pstmt.setNull(10,java.sql.Types.VARCHAR );
                pstmt.setNull(11, java.sql.Types.INTEGER);
                pstmt.setNull(12, java.sql.Types.VARCHAR);
            }else if (data instanceof Glace) {
                pstmt.setString(5, "Glace");
                pstmt.setDate(6, (Date.valueOf( ((ProduitPerissable)  data).getDatelimitConso())));
                pstmt.setNull(7, java.sql.Types.INTEGER);
                pstmt.setNull(8,  java.sql.Types.VARCHAR);
                pstmt.setString(9, ((Glace) data).getParfum());
                pstmt.setInt(10, ((Glace) data).getTemperatureConservation());
                pstmt.setNull(11, java.sql.Types.INTEGER);
                pstmt.setNull(12,  java.sql.Types.VARCHAR);
            }else if (data instanceof CartePostale) {
                pstmt.setString(5, "CartePostale");
                pstmt.setNull(6, java.sql.Types.DATE);
                pstmt.setNull(7, java.sql.Types.INTEGER);
                pstmt.setNull(8, java.sql.Types.VARCHAR);
                pstmt.setNull(9, java.sql.Types.VARCHAR);
                pstmt.setNull(10, java.sql.Types.VARCHAR);
                pstmt.setNull(11, java.sql.Types.INTEGER);
                pstmt.setString(12, ((CartePostale) data).getType().toString());

                PreparedStatement CarteAuteurPstmt=null;


                for (Auteur auteur : ((CartePostale) data).getLesAuteurs()) {
                    System.out.println("hello");
                    try {
                        CarteAuteurPstmt=cnx.prepareStatement(SQL_INSERT_CARTE_AUTEUR);
                        CarteAuteurPstmt.setLong(1, auteur.getId());
                        CarteAuteurPstmt.setLong(2, data.getRefProd());
                    }catch (SQLException e){
                        throw new DalException("erreur du insert - CarteAuteur="+data,e.getCause());
                    }finally
                    {
                        try {
                            if(CarteAuteurPstmt!=null)
                            {
                                CarteAuteurPstmt.close();
                            }
                        } catch (SQLException e) {
                            // TODO Auto-generated catch block
                            throw new DalException("erreur du insert au niveau du close- data="+data,e.getCause());
                        }
                    }
                }
            }
            int nbRow=pstmt.executeUpdate();
            if (nbRow==1)
            {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next())
                {
                    data.setRefProd(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DalException("erreur du insert - data="+data,e.getCause());
        }
        finally
        {
            try {
                if(pstmt!=null)
                {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DalException("erreur du insert au niveau du close- data="+data,e.getCause());
            }
        }

    }

    @Override
    public void delete(Produit data) throws DalException {
        PreparedStatement pstmt=null;
        long refProd=data.getRefProd();
        Connection cnx=JdbcTools.getConenction();
        try {
            pstmt=cnx.prepareStatement(SQL_DELETE);
            pstmt.setLong(1, refProd);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DalException("erreur du delete - data="+data,e.getCause());
        }
        finally
        {
            try {
                if(pstmt!=null)
                {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DalException("erreur du delete au niveau du close- data="+data,e.getCause());
            }
        }
    }

    @Override
    public void update(Produit data) throws DalException {
        PreparedStatement pstmt=null;
        //Connection cnx=null;
        long refProd=data.getRefProd();
        Connection cnx=JdbcTools.getConenction();
        try {
            pstmt=cnx.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, data.getMarque());
            pstmt.setString(2, data.getLibelle());
            pstmt.setFloat(3, data.getPrixUnitaire());
            pstmt.setLong(4, data.getQteStock());
            if (data instanceof Pain){
                pstmt.setString(5, "Pain");
                pstmt.setDate(6, (Date.valueOf( ((ProduitPerissable)  data).getDatelimitConso())));
                pstmt.setInt(7, ((Pain) data).getPoids());
                pstmt.setNull(8, java.sql.Types.VARCHAR );
                pstmt.setNull(9, java.sql.Types.VARCHAR );
                pstmt.setNull(10, java.sql.Types.VARCHAR );
                pstmt.setNull(11, java.sql.Types.INTEGER );
                pstmt.setNull(12, java.sql.Types.VARCHAR );
            } else if (data instanceof Stylo) {
                pstmt.setString(5, "Stylo");
                pstmt.setNull(7, java.sql.Types.INTEGER);
                pstmt.setString(8,  ((Stylo) data).getCouleur());
                pstmt.setNull(6, java.sql.Types.DATE);
                pstmt.setString(9, ((Stylo) data).getTypeMine());
                pstmt.setNull(10,java.sql.Types.VARCHAR );
                pstmt.setNull(11, java.sql.Types.INTEGER);
                pstmt.setNull(12, java.sql.Types.VARCHAR);
            }else if (data instanceof Glace) {
                pstmt.setString(5, "Glace");
                pstmt.setDate(6, (Date.valueOf( ((ProduitPerissable)  data).getDatelimitConso())));
                pstmt.setNull(7, java.sql.Types.INTEGER);
                pstmt.setNull(8,  java.sql.Types.VARCHAR);
                pstmt.setString(9, ((Glace) data).getParfum());
                pstmt.setInt(10, ((Glace) data).getTemperatureConservation());
                pstmt.setNull(11, java.sql.Types.INTEGER);
                pstmt.setNull(12,  java.sql.Types.VARCHAR);
            }else if (data instanceof CartePostale) {
                pstmt.setString(5, "CartePostale");
                pstmt.setNull(6, java.sql.Types.DATE);
                pstmt.setNull(7, java.sql.Types.INTEGER);
                pstmt.setNull(8, java.sql.Types.VARCHAR);
                pstmt.setNull(9, java.sql.Types.VARCHAR);
                pstmt.setNull(10, java.sql.Types.VARCHAR);
                pstmt.setNull(11, java.sql.Types.INTEGER);
                pstmt.setString(12, ((CartePostale) data).getType().toString());
            }
            pstmt.setLong(13, refProd);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DalException("erreur du update - data="+data,e.getCause());
        }
        finally
        {
            try {
                if(pstmt!=null)
                {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DalException("erreur du update au niveau du close- data="+data,e.getCause());
            }
        }

    }

    @Override
    public Produit selectById(long id) throws DalException {
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Produit produit=null;

        Connection cnx=JdbcTools.getConenction();
        //Statement pour récupérer les Carte liée avec l'auteur
        PreparedStatement CartePstmt=null;
        ResultSet CarteRs=null;

        try {
            pstmt=cnx.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setLong(1, id);
            rs=pstmt.executeQuery();
            if(rs.next())
            {
                if (rs instanceof Pain){
                    produit = new Pain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5),rs.getFloat(6));

                }else if (rs instanceof Stylo){
                    produit  = new Stylo(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getFloat(5),rs.getString(6),rs.getString(7));
                }

                else if (rs instanceof Glace){
                    produit  = new Glace(rs.getLong(1),Date.valueOf(rs.getString(7)).toLocalDate() ,rs.getString(2),rs.getString(3),rs.getInt(11),rs.getString(6),rs.getLong(7),rs.getFloat(8));
                }
                else if (rs instanceof CartePostale){
                    CartePstmt=cnx.prepareStatement(SQL_SELECT_ALL_CARTE_AUTEUR_BY_ID);
                    CartePstmt.setLong(1,rs.getLong(1));
                    CarteRs=CartePstmt.executeQuery();
                    AuteurJDCImp auteurJdbc = new AuteurJDCImp();
                    List<Auteur> listAuteur = new ArrayList<>();
                    if (CarteRs.next()){
                        listAuteur.add( auteurJdbc.selectById( rs.getLong(1)));
                    }
                    produit = new CartePostale(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5),listAuteur,TypeCartePostale.valueOf(rs.getString(7)));
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DalException("erreur du select by id - id="+id,e.getCause());
        }
        finally
        {
            try {
                if(pstmt!=null)
                {
                    pstmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DalException("erreur du select by id au niveau du close- id="+id,e.getCause());
            }
        }
        return produit;




    }




    @Override
    public List<Produit> selectAll() throws DalException {
        Statement stmt=null;
        ResultSet rs=null;
        PreparedStatement CartePstmt=null;
        ResultSet CarteRs=null;
        List<Produit> lesProduits= new ArrayList<>();
        Produit produit=null;
        //Connection cnx=null;
        Connection cnx=JdbcTools.getConenction();
        try {
            stmt=cnx.createStatement();
            rs=stmt.executeQuery(SQL_SELECT_ALL);
            while(rs.next())
            {
                if (rs instanceof Pain){
                    Pain pain = new Pain(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5),rs.getFloat(6));
                    lesProduits.add(pain);

                }else if (rs instanceof Stylo){
                    Stylo stylo = new Stylo(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getFloat(5),rs.getString(6),rs.getString(7));
                    lesProduits.add(stylo);
                }

                else if (rs instanceof Glace){
                    Glace glace = new Glace(rs.getLong(1),Date.valueOf(rs.getString(7)).toLocalDate() ,rs.getString(2),rs.getString(3),rs.getInt(11),rs.getString(6),rs.getLong(7),rs.getFloat(8));
                    lesProduits.add(glace);
                }
                else if (rs instanceof CartePostale){
                    CartePstmt=cnx.prepareStatement(SQL_SELECT_ALL_CARTE_AUTEUR_BY_ID);
                    CartePstmt.setLong(1,rs.getLong(1));
                    CarteRs=CartePstmt.executeQuery();
                    AuteurJDCImp auteurJdbc = new AuteurJDCImp();
                    List<Auteur> listAuteur = new ArrayList<>();
                    if (CarteRs.next()){
                        listAuteur.add( auteurJdbc.selectById( rs.getLong(1)));
                    }
                    CartePostale cartePostale = new CartePostale(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getLong(5),listAuteur,TypeCartePostale.valueOf(rs.getString(7)));
                }

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new DalException("erreur du select all",e.getCause());
        }
        finally
        {
            try {
                if(stmt!=null)
                {
                    stmt.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                throw new DalException("erreur du select all au niveau du close- ",e.getCause());
            }
        }
        return lesProduits;
    }
}