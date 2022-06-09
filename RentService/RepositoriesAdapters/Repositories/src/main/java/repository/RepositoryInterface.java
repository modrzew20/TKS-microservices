package repository;


import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryInterface<T> {


    List<T> readAll();

    T readById(UUID uuid) throws ItemNotFound;

    T create(T object) throws LoginInUseException;

    T delete(UUID uuid) throws ItemNotFound, CannotDeleteItem;

    T update(T object) throws ItemNotFound, LoginInUseException;

    T deleteLocalObject(String login);

}
