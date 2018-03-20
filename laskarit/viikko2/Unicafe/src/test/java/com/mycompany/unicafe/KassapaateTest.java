/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ellikarv
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void luodunKassanRahaMaaraOnOikea() {
        assertTrue(kassa.kassassaRahaa() == 100000);
    }
    
    @Test
    public void luodunKassanEdullistenLounaidenMaaraOikea() {
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void luodunKassanMaukkaittenLounaidenMaaraOikea() {
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kassassaOikeaMaaraRahaaMaukkaanLounanOstaessa(){
        kassa.syoMaukkaasti(1000);
        assertTrue(kassa.kassassaRahaa() == 100400);
    }
    
    @Test
    public void kassassaOikeaMaaraRahaaEdullisenLounanOstaessa(){
        kassa.syoEdullisesti(1000);
        assertTrue(kassa.kassassaRahaa() == 100240);
    }
    
    @Test
    public void vaihtoRahaOikeaEdullisenLounaanOstaessa(){
        assertTrue( kassa.syoEdullisesti(300) == 60 );
    }
    
    @Test
    public void vaihtoRahaOikeaMaukkaanLounaanOstaessa(){
        assertTrue( kassa.syoMaukkaasti(460) == 60 );
    }
    
    @Test
    public void myytyjenMaukkaittenLounaidenMaaraKasvaa(){
        kassa.syoMaukkaasti(460);
        kassa.syoMaukkaasti(460);
        assertTrue( kassa.maukkaitaLounaitaMyyty() == 2);

    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaa(){
        kassa.syoEdullisesti(400);
        kassa.syoEdullisesti(400);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 2);
    }
    
    @Test
    public void kassanRahaMaaraEiMuutuEdullisenLounaanMyytaessaKunRahaaEiTarpeeksi(){
       kassa.syoEdullisesti(100);
       assertTrue(kassa.kassassaRahaa() == 100000);
        
    }
    @Test
    public void kassanRahaMaaraEiMuutuMaukkaanLounaanMyytaessaKunRahaaEiTarpeeksi(){
       kassa.syoMaukkaasti(100);
       assertTrue(kassa.kassassaRahaa() == 100000);
        
    }
    
    @Test
    public void kaikkiRahatPalautetaanEdullisenLounaanOstaessa(){
        assertTrue( kassa.syoEdullisesti(30) == 30 );
    }
    
    @Test
    public void kaikkiRahatPalautetaanMaukkaanLounaanOstaessa(){
        assertTrue(kassa.syoMaukkaasti(20)== 20);
    }
    
    @Test
    public void myytejeneEdullistenLounaidenSummaEiMuutuEdullistaOsatessa(){
        kassa.syoEdullisesti(20);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void myytejenMaukkaitenLounaidenSummaEiMuutuMaukastaOstaessa(){
        kassa.syoMaukkaasti(20);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kortiltaVeloitetaanOikeinEdullistaOstaessa(){
        assertTrue(kassa.syoEdullisesti(kortti) == true);
        assertTrue(kortti.saldo() == 760);
        
    }
    
    @Test
    public void kortiltaVeloitetaanOikeinMaukastaOstaessa(){
        assertTrue(kassa.syoMaukkaasti(kortti) == true);
        assertTrue(kortti.saldo() == 600);
    }
    
    @Test
    public void edullistenLounaidenMaaraKasvaaKortillaOstaessa(){
        kassa.syoEdullisesti(kortti);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 1);
    }
    @Test
    public void maukkaidenLounaidenMaaraKasvaaKortillaOstaessa(){
        kassa.syoMaukkaasti(kortti);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 1);
    }
    
    @Test
    public void kortiltaVeloitetaanOikeinEdullistaOstaessaKunRahaEiRiita(){
        Maksukortti kortti2 = new Maksukortti(20);
        assertTrue(kassa.syoEdullisesti(kortti2) == false);
        assertTrue(kortti2.saldo() == 20);
        
    }
    
    @Test
    public void kortiltaVeloitetaanOikeinMaukastaOstaessaKunRahaEiRiita(){
        Maksukortti kortti2 = new Maksukortti(20);
        assertTrue(kassa.syoMaukkaasti(kortti2) == false);
        assertTrue(kortti2.saldo() == 20);
    }
    
    @Test
    public void kortillaOstaessaEdullistenLounaidenMaaraEiMuutuKunRahaEiRiita(){
        Maksukortti kortti2 = new Maksukortti(20);
        kassa.syoEdullisesti(kortti2);
        assertTrue(kassa.edullisiaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kortillaOstaessaMaukkaidenLounaidenMaaraEiMuutuKunRahaEiRiita(){
        Maksukortti kortti2 = new Maksukortti(20);
        kassa.syoMaukkaasti(kortti2);
        assertTrue(kassa.maukkaitaLounaitaMyyty() == 0);
    }
    
    @Test
    public void kortilleLadattaessaKortinSaldoMuuttuu(){
        kassa.lataaRahaaKortille(kortti, 100);
        assertTrue(kortti.saldo() == 1100);
    }
    
    @Test
    public void kortilleLadattaessaKassanSaldoMuuttuu(){
        kassa.lataaRahaaKortille(kortti, 100);
        assertTrue(kassa.kassassaRahaa() == 100100);
    }
    
    @Test
    public void kortilleLadattaessaKortinSaldoEiMuutu(){
        kassa.lataaRahaaKortille(kortti, -10);
        assertTrue(kortti.saldo() == 1000);
    }
    
    
    
    
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
