package com.laba.solvd.domain;

import java.util.Objects;

public class Passenger {
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private Passport passport;

    public Passenger(String first_name, String last_name, int age, String phoneNumber,
                     String email) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(firstName, passenger.firstName)
                && Objects.equals(lastName, passenger.lastName)
                && Objects.equals(passport, passenger.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, passport);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passport=" + passport.toString() +
                '}';
    }

    public Passenger() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }
}
