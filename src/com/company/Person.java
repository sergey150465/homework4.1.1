package com.company;

import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected int age;
    protected String address;

    public Person(PersonBuilder personBuilder) {
        this.name = personBuilder.name;
        this.surname = personBuilder.surname;
        this.age = personBuilder.age;
        this.address = personBuilder.address;
        if (getName() == null || getSurname() == null) {
            throw new IllegalStateException("name и surname не могут быть нулевыми!");
        }
        if (age < 0) {
            throw new IllegalArgumentException("age не может быть отрицательным!");
        }
    }

    public boolean hasAge() {
        return getAge().isPresent();
    }

    public boolean hasAddress() {
        boolean result = true;
        if (getAddress() == null) {
            result = false;
        }
        return result;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return OptionalInt.of(age);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void happyBirthday() {
        if (hasAge() == true) {
            age++;
        }
    }

    @Override
    public String toString() {
        return name + " "
                + surname
                + ", возраст " + age + " год"
                + ", адрес " + address;
    }

    @Override
    public int hashCode() {
        final int thirtyOne = 31;
        int result = 1;
        return thirtyOne * result + ((getName() == null) ? 0 : getName().hashCode()) +
                ((getSurname() == null) ? 0 : getSurname().hashCode()) +
                getAge().hashCode() +
                getAddress().hashCode();
    }

    public PersonBuilder newChildBuilder() {
        PersonBuilder child = new PersonBuilder();
        child.setSurname(getSurname());
        child.setAge(0);
        child.setAddress(getAddress());
        return child;
    }
}