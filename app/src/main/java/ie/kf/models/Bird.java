package ie.kf.models;

import java.util.UUID;

import io.realm.RealmObject;

public class Bird extends RealmObject {

    public String userId, birdId, species, sex, age, address;
    public byte[] byteArray;
    public double latitude, longitude;

    public Bird() {}

    public Bird(String userId, byte[] byteArray, String species, String sex, String age, String address, double latitude, double longitude) {

        this.userId = userId;
        this.birdId = UUID.randomUUID().toString();
        this.byteArray = byteArray;
        this.species = species;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Bird [ user = " + userId + ", birdId = " + birdId + ", species = " + species
                + ", sex = " + sex + ", age = " + age
                + ", latitude = " + latitude
                + ", longitude = " + longitude + " ]";
    }

}
