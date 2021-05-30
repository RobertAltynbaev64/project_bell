package project.altynbaev.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @Column(name = "id")
    private Integer id;

    @Version
    private Integer version = 0;

    @Column(name = "document_number", length = 30)
    private long documentNumber;

    @Column(name = "document_date")
    private Date documentDate;

    @ManyToOne
    @JoinColumn(name = "doc_id")
    private Doc doc;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public Document(long documentNumber, Date documentDate, Doc doc, User user) {
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.doc = doc;
        this.user = user;
    }

    public Document() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
