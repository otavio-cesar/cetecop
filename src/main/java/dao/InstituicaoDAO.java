package dao;

import model.Instituicao;

public class InstituicaoDAO extends EMFactory<Instituicao> {

    private static InstituicaoDAO instance;

    private InstituicaoDAO() {
        entityManager = getEntityManager();
    }

    public static InstituicaoDAO getInstance() {
        if (instance == null) {
            instance = new InstituicaoDAO();
        }
        return instance;
    }

    @Override
    public Instituicao findById(Instituicao id) {
        return entityManager.find(Instituicao.class, id.getId());
    }

    public Instituicao removeById(final Integer id) {
        Instituicao i = new Instituicao();
        i.setId(id);
        i = findById(i);
        remove(i);
        return i;

    }

}
