package ru.gb.sobes.hw1.ex1;

/*
 Создать builder для класса Person со следующими полями: String firstName,
 String lastName, String middleName, String country, String address, String phone, int age, String gender.
 */

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person(PersonBuilder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.lastName = personBuilder.lastName;
        this.middleName = personBuilder.middleName;
        this.country = personBuilder.country;
        this.address = personBuilder.address;
        this.phone = personBuilder.phone;
        this.age = personBuilder.age;
        this.gender = personBuilder.gender;
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    static class PersonBuilder {
        private String firstName;
        private String lastName;
        private String middleName;
        private String country;
        private String address;
        private String phone;
        private int age;
        private String gender;

        public Person build() {
            return new Person(this);
        }

        public PersonBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PersonBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PersonBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public PersonBuilder withMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public PersonBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }
    }
}
