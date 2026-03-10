package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exception.UserInvalidDataException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getAge() < 18
                || user.getLogin() == null
                || user.getLogin().length() < 5
                || user.getPassword() == null
                || user.getPassword().length() < 5) {
            throw new UserInvalidDataException("User is not valid");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new UserInvalidDataException("User already exists");
        }

        return storageDao.add(user);
    }
}
