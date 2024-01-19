package com.laba.solvd.persistence.impl.mybatis;

import com.laba.solvd.domain.Passenger;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.PassengerRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PassengerRepositoryMybatisImpl implements PassengerRepository {
    SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
    private final PassengerRepository repository = sqlSession.getMapper(PassengerRepository.class);

    @Override
    public void create(Passenger passenger, Long passportId) {
        repository.create(passenger, passportId);
    }

    @Override
    public void deleteById(Long passengerId) {
        repository.deleteById(passengerId);

    }

    @Override
    public Passenger findById(Long passengerId) {
        return repository.findById(passengerId);
    }

    @Override
    public Long getPassportId(Long passengerId) {
        return repository.getPassportId(passengerId);
    }

    @Override
    public List<Passenger> findAll() {
        return repository.findAll();
    }

    @Override
    public void updateContactInfoById(String phoneNumber, String email, Long passengerId) {
        repository.updateContactInfoById(phoneNumber, email, passengerId);
    }
}
