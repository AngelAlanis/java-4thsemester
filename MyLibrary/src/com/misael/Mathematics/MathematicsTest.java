package com.misael.Mathematics;

import org.junit.Assert;
import org.junit.Test;


public class MathematicsTest {

    @Test
    public void NumeroALaCeroDebeRegresarUno() {
        Assert.assertEquals(1, Mathematics.potencia(5, 0), 0.00001);
    }

    @Test
    public void TresALaTresDebeRegresarVeintisiete() {
        Assert.assertEquals(27, Mathematics.potencia(3, 3), 0.00001);
    }

    @Test
    public void DosALaQuinceDebeRegresar32768() {
        Assert.assertEquals(32768, Mathematics.potencia(2, 15), 0.00001);
    }

    @Test
    public void DiezALaMenosDosDebeRegresar001 () {
        Assert.assertEquals(0.01, Mathematics.potencia(10, -2), 0.00001);
    }

    @Test
    public void NueveALaMenos3DebeRegresar1Punto37PorDiezALaMenos3 () {
        Assert.assertEquals(0.001371742112, Mathematics.potencia(9, -3), 0.00001);
    }

    @Test
    public void RaizDeNueveDebeRegresarTres(){
        Assert.assertEquals(3, Mathematics.raiz(9), 0.00001);
    }

    @Test
    public void RaizDe25DebeRegresar5(){
        Assert.assertEquals(5, Mathematics.raiz(25), 0.00001);
    }

    @Test
    public void RaizDe9855DebeRegresar100(){
        Assert.assertEquals(100, Mathematics.raiz(9855), 0.00001);
    }

    @Test
    public void RaizDe85DebeDar9(){
        Assert.assertEquals(9, Mathematics.raiz(85), 0.00001);
    }
}