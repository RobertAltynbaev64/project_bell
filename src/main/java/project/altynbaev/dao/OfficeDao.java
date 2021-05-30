package project.altynbaev.dao;

import project.altynbaev.dto.office.OfficeFilterInDto;
import project.altynbaev.model.Office;

import java.util.List;

public interface OfficeDao {

    List<Office> filter(OfficeFilterInDto office);

    Office findById(int id);

    void save(Office office);

    void update(Office office, int id);
}
