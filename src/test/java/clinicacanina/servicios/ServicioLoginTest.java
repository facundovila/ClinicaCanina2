package clinicacanina.servicios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
import clinicacanina.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServicioLoginTest {
    private ServicioLogin servicioLogin;
    private RepositorioUsuario repositorioUsuario;


    @Before
    public void init() {
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioLogin= new ServicioLoginImpl(repositorioUsuario);
    }
    @Test
    public void puedoBuscarAlUsuarioPorElId(){
        Usuario usuarioNuevo= cuandoGuardoUnUsuarioNuevo();
        Usuario usuarioRegresado= alConsultarPorElUsuarioNuevo(usuarioNuevo);
        encuentroElMismoUsuario(usuarioNuevo,usuarioRegresado);
    }
    private void encuentroElMismoUsuario(Usuario usuarioNuevo, Usuario usuarioRegresado) {
        assertThat(usuarioNuevo.getId()).isEqualTo(usuarioRegresado.getId());
    }

    private Usuario alConsultarPorElUsuarioNuevo(Usuario usuarioNuevo) {
        when(repositorioUsuario.consultarUsuarioPorID(1L)).thenReturn(usuarioNuevo);
        return servicioLogin.consultarUsuarioPorID(1L);

    }

    private Usuario cuandoGuardoUnUsuarioNuevo() {
        Usuario u =new Usuario();
        u.setId(1L);
        u.setEmail("asd@live.com");
        return u;
    }
    @Test
    public void puedoBuscarLaListaDeMascotasPorIdDelUsuario(){
        List<Mascota> mascotas1=  dadoQueTengoUnaListaDeMascotas();
        List<Mascota> mascotas2= cuandoBuscoListaDeMascotasDelUsuario();
        entoncesEncuentroLaMismasMascotas(mascotas1,mascotas2);
    }

    private void entoncesEncuentroLaMismasMascotas(List<Mascota> mascotas1,List<Mascota> mascotas2) {
        assertThat(mascotas2.size()).isEqualTo(2);
        assertThat(mascotas1.get(0).getId()).isEqualTo(mascotas2.get(0).getId());
        assertThat(mascotas1.get(1).getId()).isEqualTo(mascotas2.get(1).getId());
        assertThat(mascotas1.get(0).getNombre()).isEqualTo(mascotas2.get(0).getNombre());
        assertThat(mascotas1.get(1).getNombre()).isEqualTo(mascotas2.get(1).getNombre());
    }

    private List<Mascota> cuandoBuscoListaDeMascotasDelUsuario() {
        return servicioLogin.listarMascotas(1L);
    }

    private List<Mascota> dadoQueTengoUnaListaDeMascotas() {
        List<Mascota> lista2=new ArrayList<>();
        Mascota m1= new Mascota();
        m1.setId(1L);
        Mascota m2= new Mascota();
        m1.setId(2L);
        lista2.add(m1);
        lista2.add(m2);
        when(repositorioUsuario.listarMascotasPropias(1L)).thenReturn(lista2);
        return lista2;
    }

}
