package ie.kf.db;

import android.content.Context;
import android.database.SQLException;

import ie.kf.models.Bird;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DBManager {

    public Realm realmDatabase;

    public DBManager(Context context) {
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("birds.realm")
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(config);
    }

    public void open() throws SQLException {
        realmDatabase = Realm.getDefaultInstance();
    }

    public void close() {
        realmDatabase.close();
    }

    public void add(Bird b) {
        realmDatabase.beginTransaction();
        realmDatabase.copyToRealm(b);
        realmDatabase.commitTransaction();
    }

    public void update(Bird b, String species ,String sex, String age)
    {
        realmDatabase.beginTransaction();
        b.species = species;
        b.sex = sex;
        b.age = age;
        realmDatabase.commitTransaction();
    }

    public void delete(String birdId) {
        realmDatabase.beginTransaction();
        realmDatabase.where(Bird.class)
                .equalTo("birdId", birdId)
                .findAll()
                .deleteAllFromRealm();
        realmDatabase.commitTransaction();
    }

    public RealmResults<Bird> getAll() {
        RealmResults<Bird> result = realmDatabase.where(Bird.class)
                .findAll();
        return result;
    }

    public Bird get(String birdId) {
        return realmDatabase.where(Bird.class)
                .equalTo("birdId", birdId)
                .findAll()
                .first();
    }

    public void reset() {
        realmDatabase.beginTransaction();
        realmDatabase.where(Bird.class)
                .findAll()
                .deleteAllFromRealm();
        realmDatabase.commitTransaction();
    }
}