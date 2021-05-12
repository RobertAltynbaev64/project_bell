package project.altynbaev.service.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.altynbaev.dao.DocRepository;
import project.altynbaev.model.Doc;

import java.util.List;

@Service
public class DocServiceImpl implements DocService {

    private final DocRepository docRepository;

    @Autowired
    public DocServiceImpl(DocRepository docRepository) {
        this.docRepository = docRepository;
    }

    @Override
    public List<Doc> getAll() {
        return docRepository.findAll();
    }

}
