package project.altynbaev.dao;

import project.altynbaev.model.Document;

public interface DocumentDao {

    Document findById(int id);

    void save(Document document);

    void update(Document document);

}
