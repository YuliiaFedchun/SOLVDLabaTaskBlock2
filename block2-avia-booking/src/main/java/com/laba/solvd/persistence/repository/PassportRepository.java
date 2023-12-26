package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Passport;

public interface PassportRepository {
    void create(Passport passport);

    Passport findById(Long passportId);

    void deleteById(Long passportId);
}
