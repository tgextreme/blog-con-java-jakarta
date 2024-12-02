package main;

import java.util.List;

public interface UsuarioDAO {
    void insertar(Usuario usuario);
    Usuario obtenerPorId(int id);
    List<Usuario> obtenerTodos();
    void actualizar(Usuario usuario);
    void eliminar(int id);
}

