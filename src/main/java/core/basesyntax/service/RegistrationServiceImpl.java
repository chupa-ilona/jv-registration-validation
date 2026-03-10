package core.basesyntax.service;

import core.basesyntax.Exception.UserInvalidDataException;
import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if ((user.getAge() < 18) && (user.getLogin().length() < 5) && (user.getPassword().length() < 5)) {
            throw new UserInvalidDataException("User is not valid");
        } else if (storageDao.get(user.getLogin()) != null){
            throw new UserInvalidDataException("User already exists");
        } else {
            return storageDao.add(user);
        }
    }
}
