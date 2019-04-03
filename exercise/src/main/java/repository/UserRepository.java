package repository;

import Model.User;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;

public interface UserRepository {
     Collection<User> findAll();
}
