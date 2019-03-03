package ie.kf.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.UUID;

public class Bird implements Serializable {

    public String birdId, species, sex, age;
    Bitmap bitmap;

    public Bird() {}

    public Bird(Bitmap bitmap, String species, String sex, String age) {

        this.birdId = UUID.randomUUID().toString();
        this.bitmap = bitmap;
        this.species = species;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return bitmap + ", " + birdId + ", " + species + ", " + sex + ", " + age;
    }

}
