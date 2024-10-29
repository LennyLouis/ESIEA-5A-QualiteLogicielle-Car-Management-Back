package esiea.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import esiea.metier.Voiture;
import esiea.metier.Voiture.Carburant;

public class VoitureDAOTest {

    private VoitureDAO dao;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    private class TestVoitureDAO extends VoitureDAO {
        @Override
        protected Connection getConnexion() {
            return mockConnection;
        }
    }

    @Before
    public void setUp() throws Exception {
        // Création des mocks
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        // Configuration des mocks basiques
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        // Configuration du ResultSet pour le comptage et le positionnement
        when(mockResultSet.last()).thenReturn(true);
        when(mockResultSet.getRow()).thenReturn(1);
        // Ne pas mocker beforeFirst() car c'est une méthode void
        doNothing().when(mockResultSet).beforeFirst();
        when(mockResultSet.next()).thenReturn(true, false); // Une ligne puis plus rien

        // Configuration du ResultSet pour les données
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("marque")).thenReturn("Test");
        when(mockResultSet.getString("modele")).thenReturn("TestModel");
        when(mockResultSet.getString("finition")).thenReturn("TestFinition");
        when(mockResultSet.getString("carburant")).thenReturn("E");
        when(mockResultSet.getInt("km")).thenReturn(10000);
        when(mockResultSet.getInt("annee")).thenReturn(2020);
        when(mockResultSet.getInt("prix")).thenReturn(15000);

        dao = new TestVoitureDAO();
    }

    @Test
    public void testAjouterVoiture() throws Exception {
        Voiture voiture = creerVoitureTest();
        dao.ajouterVoiture(voiture);
        verify(mockPreparedStatement).setString(1, voiture.getMarque());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testSupprimerVoiture() throws Exception {
        dao.supprimerVoiture("1");
        verify(mockPreparedStatement).setString(1, "1");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testRechercheVoiture() throws Exception {
        ReponseVoiture reponse = dao.rechercherVoitures("1", 0, 10);
        assertNotNull(reponse);
        assertNotNull(reponse.getData());
        assertEquals(1, reponse.getData().length);

        Voiture voiture = reponse.getData()[0];
        assertEquals("Test", voiture.getMarque());
        assertEquals("TestModel", voiture.getModele());
        assertEquals(2020, voiture.getAnnee());
    }

    @Test
    public void testGetToutesVoitures() throws Exception {
        ReponseVoiture reponse = dao.getVoitures(null, 0, 10);
        assertNotNull(reponse);
        assertNotNull(reponse.getData());
        assertEquals(1, reponse.getData().length);
    }

    @Test
    public void testConstruireRequeteMasque() {
        String resultat = dao.construireRequeteMasque("renault clio");
        assertTrue(resultat.contains("marque like ?"));
        assertTrue(resultat.contains("modele like ?"));
        assertTrue(resultat.contains("OR"));
    }

    private Voiture creerVoitureTest() {
        Voiture voiture = new Voiture();
        voiture.setMarque("Test");
        voiture.setModele("TestModel");
        voiture.setFinition("TestFinition");
        voiture.setCarburant(Carburant.ESSENCE);
        voiture.setKm(10000);
        voiture.setAnnee(2020);
        voiture.setPrix(15000);
        return voiture;
    }
}