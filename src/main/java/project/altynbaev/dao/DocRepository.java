package project.altynbaev.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.altynbaev.model.Doc;

import java.util.Optional;

@Repository
public interface DocRepository extends JpaRepository<Doc, Integer> {

    Optional<Doc> findById(Integer id);

    Optional<Doc> findByDocCode(int docCode);

    Optional<Doc> findByDocName(String name);

}
