package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class VarastoTest {
 
    Varasto varasto;
    double vertailuTarkkuus = 0.0001;
 
    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }
 
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriEiSalliNegatiivistaTilavuutta() {
        varasto = new Varasto(-4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoVarastollaOikeaTilavuus() {
        varasto = new Varasto(10, 0);
        assertEquals(4, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoVarastollaOikeaSaldo() {
        varasto = new Varasto(10, 4);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriEiSalliNegatiivistaTilavuutta() {
        varasto = new Varasto(-4, 0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriEiSalliNegatiivistaAlkuSaldoa() {
        varasto = new Varasto(4, -4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldoKonstruktoriEiYlitaTilavuutta() {
        varasto = new Varasto(4, 8);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);
 
        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
 
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
 
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
 
        double saatuMaara = varasto.otaVarastosta(2);
 
        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
 
    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);
 
        varasto.otaVarastosta(2);
 
        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tilavuuttaEiVoiYlittaa() {
        varasto.lisaaVarastoon(14);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoaEiVoiAlittaa() {
        varasto.lisaaVarastoon(4);
        double saatuMaara = varasto.otaVarastosta(8);
        assertEquals(4, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaMaaraaEiVoiLisata() {
        varasto.lisaaVarastoon(-4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivistaMaaraaEiVoiOttaa() {
        varasto.otaVarastosta(-4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(4);
        assertEquals(varasto.toString(), "saldo = 4.0, vielä tilaa 6.0");
    }
}