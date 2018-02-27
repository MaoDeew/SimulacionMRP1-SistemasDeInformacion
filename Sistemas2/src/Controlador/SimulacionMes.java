/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Vector;

/**
 *
 * @author mabet
 */
public class SimulacionMes {

    
       private Vector<Float> cantidadSimuladaTotal;
       
       private float publicidad, clima, competencia;

    public SimulacionMes(int publicidad, int clima, int competencia) {
        this.publicidad = (float) publicidad/100;
        this.clima = (float) clima/100;
        this.competencia = (float) competencia/100;
        cantidadSimuladaTotal = new Vector<>();
    }

    public Vector<Float> getCantidadSimuladaPorMes(Vector<Float> cantidadSimulada) {
        
        float promedioNegativos = (float) (clima+competencia)/2;
        
        for (int i = 0; i < cantidadSimulada.size(); i++) {
            float valorMes = cantidadSimulada.get(i);
            float nuevoValor = Math.round(valorMes*(publicidad-promedioNegativos)+valorMes);
            cantidadSimuladaTotal.add(nuevoValor);
        }
        
        return cantidadSimuladaTotal;
    }
    
    @Override
    public String toString() {
        return "SimulacionMes{" + "publicidad=" + publicidad*100 + "% , clima=" + clima*100 + "% , competencia=" + competencia*100 +"%"+ '}';
    }
     
    
    
       


}
