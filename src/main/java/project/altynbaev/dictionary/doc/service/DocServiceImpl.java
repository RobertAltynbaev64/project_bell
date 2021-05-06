package project.altynbaev.dictionary.doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.altynbaev.dictionary.doc.dao.DocDao;
import project.altynbaev.dictionary.doc.model.DocumentType;
import project.altynbaev.dictionary.doc.view.DocView;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {
    private DocDao dao;

    @Autowired
    public DocServiceImpl(DocDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentType getDocByCode(Integer docCode) {
        return dao.getDocByCode(docCode);
    }

    @Override
    @Transactional(readOnly = true)
    public DocumentType getDocByName(String docName) {
        return dao.getDocByName(docName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocView> getList() {
        return dao.getList();
    }
}
