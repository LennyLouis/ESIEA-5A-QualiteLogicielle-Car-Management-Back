package esiea.dao;

import static org.junit.Assert.*;
import org.junit.Test;
import esiea.metier.Voiture;

public class ReponseVoitureTest {
    
    @Test
    public void testSetData() {
        ReponseVoiture reponse = new ReponseVoiture();
        Voiture voiture = new Voiture();
        voiture.setId(1);
        
        reponse.setData(voiture, 0);
        assertNotNull(reponse.getData());
        assertEquals(1, reponse.getData().length);
        assertEquals(voiture, reponse.getData()[0]);
    }

    @Test
    public void testVolume() {
        ReponseVoiture reponse = new ReponseVoiture();
        reponse.setVolume(10);
        assertEquals(10, reponse.getVolume());
    }
}
