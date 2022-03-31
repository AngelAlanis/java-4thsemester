package com.misael.Mathematics;

import org.junit.Assert;
import org.junit.Test;


public class MathematicsTest {

    @Test
    public void Potencia_NumeroALa0_DebeRegresar1() {
        Assert.assertEquals(1, Mathematics.potencia(5, 0), 0.00001);
    }

    @Test
    public void Potencia_3ALa3_DebeRegresar27() {
        Assert.assertEquals(27, Mathematics.potencia(3, 3), 0.00001);
    }

    @Test
    public void Potencia_2ALa15_DebeRegresar32768() {
        Assert.assertEquals(32768, Mathematics.potencia(2, 15), 0.00001);
    }

    @Test
    public void Potencia_10ALaMenos2_DebeRegresar0p01() {
        Assert.assertEquals(0.01, Mathematics.potencia(10, -2), 0.00001);
    }

    @Test
    public void Potencia_9ALaMenos3_DebeRegresar1p37Por10ALaMenos3() {
        Assert.assertEquals(0.001371742112, Mathematics.potencia(9, -3), 0.00001);
    }

    @Test
    public void Raiz_DeNueve_DebeRegresarTres() {
        Assert.assertEquals(3, Mathematics.raiz(9), 0.00001);
    }

    @Test
    public void Raiz_De25_DebeRegresar5() {
        Assert.assertEquals(5, Mathematics.raiz(25), 0.00001);
    }

    @Test
    public void Raiz_De9855_DebeRegresar100() {
        Assert.assertEquals(99.27235265, Mathematics.raiz(9855), 0.00001);
    }

    @Test
    public void Raiz_De85_DebeRegresar9() {
        Assert.assertEquals(9.219544457, Mathematics.raiz(85), 0.00001);
    }

    @Test
    public void Absoluto_DeMenos90_DebeRegresar90() {
        Assert.assertEquals(90, Mathematics.absoluto(-90), 0.00001);
    }

    @Test
    public void Absoluto_De50_DebeRegresar50() {
        Assert.assertEquals(50, Mathematics.absoluto(50), 0.00001);
    }

    @Test
    public void Absoluto_De0_DebeRegresar0() {
        Assert.assertEquals(0, Mathematics.absoluto(0), 0.00001);
    }

    @Test
    public void Error_De1p4843y1p4687_DebeRegresar0p0105() {
        Assert.assertEquals(0.010510004, Mathematics.error(1.4843, 1.4687), 0.000001);
    }

    @Test
    public void NextSolucionReglaFalsaTest() {
        Assert.assertEquals(1.6057, Mathematics.nextSolucionReglaFalsa(1, 2, 2.5403, -1.6536), 0.0001);
    }

    @Test
    public void NextSolucionReglaFalsaTest2() {
        Assert.assertEquals(1.4762, Mathematics.nextSolucionReglaFalsa(1.4761, 1.4804, 0.0004, -0.0229), 0.0001);
    }

    @Test
    public void BusquedaIncremental_Test1() throws TeoremaDeBolzanoException {
        Assert.assertArrayEquals(new double[]{5,6}, Mathematics.busquedaIncremental("x^4-5x^3-4"), 0.00001);
    }

    @Test
    public void ReglaFalsa_Test1() {
        Assert.assertEquals(5.0312595, Mathematics.metodoReglaFalsa("x^4-5x^3-4", 0.0001), 0.01);
    }

    @Test
    public void ReglaFalsa_Test2() {
        Assert.assertEquals(1.4405, Mathematics.metodoReglaFalsa("x^5-5x+1", 0.0001), 0.01);
    }

    @Test
    public void ReglaFalsa_Test3(){
        Assert.assertEquals(2.0476, Mathematics.metodoReglaFalsa("3x^2-x^4+5", 0.0001), 0.01);
    }

    @Test
    public void Biseccion_Test1(){
        Assert.assertEquals(5.0312595, Mathematics.metodoBiseccion("x^4-5x^3-4", 0.0001), 0.01);
    }

    @Test
    public void Biseccion_Test2() {
        Assert.assertEquals(1.4405, Mathematics.metodoBiseccion("x^5-5x+1", 0.0001), 0.01);
    }

    @Test
    public void Biseccion_Test3(){
        Assert.assertEquals(2.0476, Mathematics.metodoBiseccion("3x^2-x^4+5", 0.0001), 0.01);
    }

    @Test
    public void Secante_Test1(){
        Assert.assertEquals(5.0312595, Mathematics.metodoSecante("x^4-5x^3-4", 0.0001), 0.01);
    }

    @Test
    public void Secante_Test2() {
        Assert.assertEquals(1.4405, Mathematics.metodoSecante("x^5-5x+1", 0.0001), 0.01);
    }

    @Test
    public void Secante_Test3(){
        Assert.assertEquals(2.0476, Mathematics.metodoSecante("3x^2-x^4+5", 0.0001), 0.01);
    }
}