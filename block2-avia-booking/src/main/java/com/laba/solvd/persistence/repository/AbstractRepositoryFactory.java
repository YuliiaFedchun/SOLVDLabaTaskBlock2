package com.laba.solvd.persistence.repository;

import com.laba.solvd.persistence.impl.NoSQLRepositoriesFactory;
import com.laba.solvd.persistence.impl.RelationalRepositoriesFactory;

public class AbstractRepositoryFactory {
    public static RepositoriesFactory createFactory(String dbType) {
        RepositoriesFactory factory;
        switch (dbType) {
            case "RELATIONAL":
                factory = new RelationalRepositoriesFactory();
                break;
            case "NOSQL":
                factory = new NoSQLRepositoriesFactory();
                break;
            default:
                throw new RuntimeException("Can't find a needed factory");
        }
        return factory;
    }
}
