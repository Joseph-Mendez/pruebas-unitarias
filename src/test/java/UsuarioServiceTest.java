import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.controller.Usuario;
import org.example.controller.UserRepository;
import org.example.controller.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UsuarioServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByIdUserExists() {
        Usuario usuario = new Usuario(1L, "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Usuario result = userService.getUserById(1L);
        assertEquals(usuario, result);
    }

    @Test
    public void testGetUserByIdUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        Usuario result = userService.getUserById(1L);
        assertNull(result);
    }

    @Test
    public void testCreateUser() {
        Usuario usuario = new Usuario(null, "Jane");
        Usuario savedUsuario = new Usuario(1L, "Jane");
        when(userRepository.save(usuario)).thenReturn(savedUsuario);
        Usuario result = userService.createUser(usuario);
        assertEquals(savedUsuario, result);
    }

    @Test
    public void testUpdateUserUserExists() {
        Usuario usuario = new Usuario(null, "Jane");
        Usuario updatedUsuario = new Usuario(1L, "Jane");
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.save(usuario)).thenReturn(updatedUsuario);
        Usuario result = userService.updateUser(1L, usuario);
        assertEquals(updatedUsuario, result);
    }

    @Test
    public void testUpdateUserUserDoesNotExist() {
        Usuario usuario = new Usuario(null, "Jane");
        when(userRepository.existsById(1L)).thenReturn(false);
        Usuario result = userService.updateUser(1L, usuario);
        assertNull(result);
    }

    @Test
    public void testDeleteUserUserExists() {
        when(userRepository.existsById(1L)).thenReturn(true);
        doNothing().when(userRepository).deleteById(1L);
        boolean result = userService.deleteUser(1L);
        assertTrue(result);
    }

    @Test
    public void testDeleteUserUserDoesNotExist() {
        when(userRepository.existsById(1L)).thenReturn(false);
        boolean result = userService.deleteUser(1L);
        assertFalse(result);
    }

    @Test
    public void testCreateUserNullUser() {
        assertThrows(NullPointerException.class, () -> {
            userService.createUser(null);
        });
    }

    @Test
    public void testUpdateUser_InvalidId() {
        Usuario usuario = new Usuario(null, "Jane");
        when(userRepository.existsById(null)).thenReturn(false);
        Usuario result = userService.updateUser(null, usuario);
        assertNull(result);
    }

    @Test
    public void testDeleteUserNullId() {
        assertThrows(NullPointerException.class, () -> {
            userService.deleteUser(null);
        });
    }

    @Test
    public void testGetUserByIdException() {
        when(userRepository.findById(anyLong())).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });
    }
}