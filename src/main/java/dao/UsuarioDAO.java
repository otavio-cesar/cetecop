package dao;

import model.Usuario;

public class UsuarioDAO extends EMFactory<Usuario>{

    private static UsuarioDAO instance;

    private UsuarioDAO() {
        entityManager = getEntityManager();
    }

    public static UsuarioDAO getInstance() {
        if (instance == null) {
            instance = new UsuarioDAO();
        }
        return instance;
    }

    @Override
    public Usuario findById(Usuario user) {
        return entityManager.find(Usuario.class, user.getId());
    }
    
    public Usuario removeById(final Integer id){
        Usuario user = new Usuario();
        user.setId(id);
        user = findById(user);
        return remove(user);
    }


}
