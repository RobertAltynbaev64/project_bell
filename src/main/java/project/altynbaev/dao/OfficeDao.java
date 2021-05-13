package project.altynbaev.dao;

import project.altynbaev.model.Office;

import java.util.List;

public interface OfficeDao {

    List<Office> filter(Office office);

    Office findById(int id);

    void save(Office office);

}
