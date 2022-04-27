package EjemploHilos;

public class HiloEsperaNotifica {

    public static void main(String[] args) {
        Aviso aviso = new Aviso();

        HiloRun hiloEspera1  = new HiloRun("HiloEspera1", false, aviso);
        HiloRun hiloEspera2  = new HiloRun("HiloEspera2", false, aviso);
        HiloRun hiloEspera3  = new HiloRun("HiloEspera3", false, aviso);
        HiloRun hiloNotifica = new HiloRun("HiloNotifica", true, aviso);

        hiloEspera1.start();
        hiloEspera2.start();
        hiloEspera3.start();
        hiloNotifica.start();
    }
}
