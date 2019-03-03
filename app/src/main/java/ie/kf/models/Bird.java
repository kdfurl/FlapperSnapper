package ie.kf.models;

import java.io.Serializable;

public class Bird implements Serializable {

    String filePath, species, sex, age;

    public Bird() {}

    public Bird(String filePath, String species, String sex, String age) {

        this.filePath = filePath;
        this.species = species;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return filePath + ", " + species + ", " + sex + ", " + age;
    }

}
