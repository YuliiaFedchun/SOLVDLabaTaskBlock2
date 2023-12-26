package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Passport;
import com.laba.solvd.persistence.repository.PassportRepository;
import com.laba.solvd.persistence.impl.PassportRepositoryImpl;
import com.laba.solvd.service.PassportService;

public class PassportServiceImpl implements PassportService {
    private final PassportRepository passportRepository;

    public PassportServiceImpl() {
        this.passportRepository = new PassportRepositoryImpl();
    }

    @Override
    public Passport create(Passport passport) {
        passport.setId(0);
        passportRepository.create(passport);
        return passport;
    }

    @Override
    public Passport findById(Long passportId) {
        return passportRepository.findById(passportId);
    }

    @Override
    public void deleteById(Long passportId) {
        passportRepository.deleteById(passportId);

    }
}
