package com.laba.solvd.service.impl;

import com.laba.solvd.domain.LuggageTariff;
import com.laba.solvd.persistence.repository.LuggageTariffRepository;
import com.laba.solvd.persistence.impl.LuggageTariffRepositoryImpl;
import com.laba.solvd.service.LuggageTariffService;

public class LuggageTariffServiceImpl implements LuggageTariffService {
    private final LuggageTariffRepository luggageTariffRepository;

    public LuggageTariffServiceImpl() {
        this.luggageTariffRepository = new LuggageTariffRepositoryImpl();
    }

    @Override
    public LuggageTariff create(LuggageTariff luggageTariff) {
        luggageTariff.setId(0);
        luggageTariffRepository.create(luggageTariff);
        return luggageTariff;
    }
}
