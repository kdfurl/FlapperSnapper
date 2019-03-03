package ie.kf.models;

import java.util.UUID;

import io.realm.RealmObject;

public class Bird extends RealmObject {

    public String birdId, species, sex, age;
    public byte[] byteArray;

    public Bird() {}

    public Bird(byte[] byteArray, String species, String sex, String age) {

        this.birdId = UUID.randomUUID().toString();
        this.byteArray = byteArray;
        this.species = species;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return byteArray + ", " + birdId + ", " + species + ", " + sex + ", " + age;
    }

}
