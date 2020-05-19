package dmc.repo;

import DMCmodels.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserManager extends CrudRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
}