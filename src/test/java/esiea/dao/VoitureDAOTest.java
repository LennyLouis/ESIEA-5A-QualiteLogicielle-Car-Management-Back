package esiea.dao;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import esiea.metier.Voiture;
import esiea.metier.Voiture.Carburant;

public class VoitureDAOTest {
    
    private VoitureDAO dao;
    
    @Before
    public void setUp() {
        dao = new VoitureDAO();
    }

    @Test
    public void testAjouterVoiture() throws Exception {
        Voiture voiture = new Voiture();
        voiture.setMarque("Test");
        voiture.setModele("TestModel");
        voiture.setFinition("TestFinition");
        voiture.setCarburant(Carburant.ESSENCE);
        voiture.setKm(10000);
        voiture.setAnnee(2020);
        voiture.setPrix(15000);
        
        dao.ajouterVoiture(voiture);
        
        ReponseVoiture reponse = dao.rechercherVoitures("Test", 0, 10);
        assertTrue(reponse.getVolume() > 0);
    }

    @Test
    public void testConstruireRequeteMasque() {
        String resultat = dao.construireRequeteMasque("renault clio");
        assertTrue(resultat.contains("marque like ?"));
        assertTrue(resultat.contains("modele like ?"));
        assertTrue(resultat.contains("OR"));
    }

    @Test
    public void testRechercheVoiture() throws Exception {
        ReponseVoiture reponse = dao.rechercherVoitures("1", 0, 10);
        assertNotNull(reponse);
        assertTrue(reponse.getVolume() >= 0);
    }
}
