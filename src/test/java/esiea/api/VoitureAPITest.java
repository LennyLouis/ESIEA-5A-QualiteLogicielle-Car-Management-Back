package esiea.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.json.JSONObject;
import esiea.dao.VoitureDAO;
import esiea.dao.ReponseVoiture;
import esiea.metier.Voiture;

public class VoitureAPITest {
    
    private VoitureAPI api;
    private VoitureDAO mockDao;
    
    @Before
    public void setUp() {
        mockDao = mock(VoitureDAO.class);
        api = new VoitureAPI();
    }

    @Test
    public void testGetVoituresJsonAll() throws Exception {
        String result = api.getVoituresJson("all");
        assertNotNull(result);
        assertTrue(result.contains("voitures"));
    }

    @Test
    public void testAjouterVoiture() {
        JSONObject voitureJson = new JSONObject();
        voitureJson.put("marque", "Test");
        voitureJson.put("modele", "TestModel");
        voitureJson.put("finition", "TestFinition");
        voitureJson.put("carburant", "E");
        voitureJson.put("km", 10000);
        voitureJson.put("annee", 2020);
        voitureJson.put("prix", 15000);
        
        String result = api.ajouterVoiture(voitureJson.toString());
        JSONObject resultJson = new JSONObject(result);
        assertTrue(resultJson.has("succes"));
    }

    @Test
    public void testSupprimerVoiture() {
        String result = api.supprimerVoiture("1");
        JSONObject resultJson = new JSONObject(result);
        assertTrue(resultJson.has("succes"));
    }
}
