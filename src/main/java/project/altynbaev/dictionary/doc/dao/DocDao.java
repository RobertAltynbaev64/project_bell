package project.altynbaev.dictionary.doc.dao;

import project.altynbaev.dictionary.doc.model.DocumentType;
import project.altynbaev.dictionary.doc.view.DocView;

import java.util.List;

public interface DocDao {
    public DocumentType getDocByCode(Integer docCode);
    public DocumentType getDocByName(String docName);
    public List<DocView> getList();
}
