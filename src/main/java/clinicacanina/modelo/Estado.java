package clinicacanina.modelo;

public enum Estado {
	EN_COCHERA, EN_PREPARACION, EN_CAMINO, EN_PUERTA, REGRESANDO;
	
	//Estados posibles de una ambulancia reservada (disponibilidad = false)
	// EN_PREPARACION, EN_CAMINO, EN_PUERTA, REGRESANDO
    
	
	//Estados posibles de una ambulancia disponible (disponibilidad = true)
	//EN_COCHERA
	
	//---------------------------------------------------------------------------
	
	//CUANDO SE CARGA EN LA BD, EL ENUM LO TRADUCE A  INTEGER, POR LO TANTO ---> 
	
	//EN_COCHERA,   EN_PREPARACION,   EN_CAMINO,   EN_PUERTA,   REGRESANDO
	//     0               1              2            3            4
}
