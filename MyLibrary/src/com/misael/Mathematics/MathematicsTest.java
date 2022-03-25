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

}