package com.laba.solvd.persistence.impl.mybatis;

import com.laba.solvd.domain.Passport;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.PassportRepository;
import org.apache.ibatis.session.SqlSession;

public class PassportRepositoryMybatisImpl implements PassportRepository {
    SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
    private final PassportRepository repository = sqlSession.getMapper(PassportRepository.class);

    @Override
    public void create(Passport passport) {
        repository.create(passport);
    }

    @Override
    public Passport findById(Long passportId) {
        return repository.findById(passportId);
    }

    @Override
    public void deleteById(Long passportId) {
        repository.deleteById(passportId);
    }
}
