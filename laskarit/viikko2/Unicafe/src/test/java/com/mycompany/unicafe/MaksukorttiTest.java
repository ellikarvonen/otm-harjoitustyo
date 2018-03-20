package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoOnAlussaOikein(){
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void saldoVäheneeJosRahaaTarpeeksi(){
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(15);
        assertEquals("saldo: 0.10", kortti.toString());

    }
    @Test
    public void metodiPalauttaaTrueJosRahatRiittavat(){
        assertEquals("true", String.valueOf(kortti.otaRahaa(5)));
        
    }
    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiita(){
        
        assertEquals("false", String.valueOf(kortti.otaRahaa(15)));

    }
    @Test
    public void kortinSaldoOnOikein(){
        assertEquals("10", String.valueOf(kortti.saldo()));
    }
}
