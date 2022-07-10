package clinicacanina.repositorios;

import clinicacanina.modelo.Navegador;

public interface RepositorioNavegacion {
	
	void guardarNavegacion(Navegador navegador);
	Navegador buscarNavegacion(String patente);

}
