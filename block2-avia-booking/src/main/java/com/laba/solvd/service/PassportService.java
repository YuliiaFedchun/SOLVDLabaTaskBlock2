package com.laba.solvd.service;

import com.laba.solvd.domain.Passport;

public interface PassportService {
    Passport create(Passport passport);

    Passport findById(Long passportId);

    void deleteById(Long passportId);

}
