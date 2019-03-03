package ie.kf.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Bird implements Serializable {

    String species, sex, age;
    Bitmap bitmap;

    public Bird() {}

    public Bird(Bitmap bitmap, String species, String sex, String age) {

        this.bitmap = bitmap;
        this.species = species;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return bitmap + ", " + species + ", " + sex + ", " + age;
    }

}
