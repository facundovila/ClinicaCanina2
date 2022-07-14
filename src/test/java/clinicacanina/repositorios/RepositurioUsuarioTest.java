package clinicacanina.repositorios;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

public class RepositurioUsuarioTest extends SpringTest {
        @Autowired
    private RepositorioUsuario repositorioUsuario;
        private final long idUSUARIO=1L;

    @Test
    @Transactional
    @Rollback
    public void cuandoBuscoLaListaDeMascotasREgresoLaListaDeMAscotas(){
        List <Mascota> listaGuardada=dadoQueTengoLaListaDEMAscotasYLasGuardoAlUsuario(idUSUARIO);
        List <Mascota> listaRetorno= cuandoBuscoLaListaDeMascotasDelUsuarioREgresoLaListaDeMAscotas(idUSUARIO);
        entoncesComparoLasListas(listaGuardada,listaRetorno);
    }

    private void entoncesComparoLasListas(List<Mascota> listaGuardada, List<Mascota> listaRetorno) {
        assertThat(listaGuardada.size()).isEqualTo(listaRetorno.size());
    }

    private List<Mascota> cuandoBuscoLaListaDeMascotasDelUsuarioREgresoLaListaDeMAscotas(long idUSUARIO) {
     return repositorioUsuario.listarMascotasPropias(idUSUARIO);
    }
    private List<Mascota> dadoQueTengoLaListaDEMAscotasYLasGuardoAlUsuario(long  idUSUARIO) {
             Usuario usuario=new Usuario();
             usuario.setId(idUSUARIO);
             usuario.setEmail("asd@live.com");
            Mascota mascota1= new Mascota();
            mascota1.setId(1L);
            mascota1.setUsuario(usuario);
            Mascota mascota2= new Mascota();
            mascota2.setId(2L);
            mascota2.setUsuario(usuario);

            session().save(usuario);
            session().save(mascota1);
            session().save(mascota2);

            List<Mascota> lista= new ArrayList<>();
             lista.add(mascota1);
             lista.add(mascota2);
             return lista;

    }
}
