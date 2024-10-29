package esiea.metier;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Calendar;
import esiea.metier.Voiture.Carburant;

public class VoitureTest {
    
    @Test
    public void testCheckValid() {
        Voiture voiture = new Voiture();
        voiture.setId(1);
        voiture.setMarque("Renault");
        voiture.setModele("Clio");
        voiture.setFinition("Business");
        voiture.setCarburant(Carburant.DIESEL);
        voiture.setKm(50000);
        voiture.setAnnee(2020);
        voiture.setPrix(15000);
        
        assertTrue(voiture.check());
    }

    @Test
    public void testCheckInvalid() {
        Voiture voiture = new Voiture();
        // ID négatif
        voiture.setId(-1);
        assertFalse(voiture.check());
        
        // Test avec marque vide
        voiture.setId(1);
        voiture.setMarque("");
        assertFalse(voiture.check());
        
        // Test année future
        voiture = createValidVoiture();
        voiture.setAnnee(Calendar.getInstance().get(Calendar.YEAR) + 1);
        assertFalse(voiture.check());
    }

    @Test
    public void testGetTypeDonnee() {
        assertEquals("string", Voiture.getTypeDonnee("marque"));
        assertEquals("entier", Voiture.getTypeDonnee("prix"));
        assertEquals("", Voiture.getTypeDonnee("inconnu"));
    }

    @Test
    public void testCarburantEnum() {
        assertEquals(Carburant.DIESEL, Carburant.get("D"));
        assertEquals(Carburant.ESSENCE, Carburant.get("E"));
        assertNull(Carburant.get("X"));
    }

    private Voiture createValidVoiture() {
        Voiture voiture = new Voiture();
        voiture.setId(1);
        voiture.setMarque("Renault");
        voiture.setModele("Clio");
        voiture.setFinition("Business");
        voiture.setCarburant(Carburant.DIESEL);
        voiture.setKm(50000);
        voiture.setAnnee(2020);
        voiture.setPrix(15000);
        return voiture;
    }
}
