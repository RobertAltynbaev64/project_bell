package project.altynbaev.model;

import javax.persistence.*;

@Entity
@Table(name = "doc")
public class Doc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "doc_name", length = 100)
    private String docName;

    @Column(name = "doc_code", length = 10)
    private int docCode;

    public Doc(String docName, int docCode) {
        this.docName = docName;
        this.docCode = docCode;
    }

    public Doc() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getDocCode() {
        return docCode;
    }

    public void setDocCode(int docCode) {
        this.docCode = docCode;
    }

}
