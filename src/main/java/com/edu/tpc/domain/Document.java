package com.edu.tpc.domain;

/**
 *
 * @author Jaikishan
 * Date: 25/10/11
 */

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;
import java.sql.Blob;
import javax.persistence.Lob;

@Entity
@Table(name="Documents")
public class Document extends AuditInfo
                      implements java.io.Serializable
{
    @Id
    private int documentId;
    @Column private int documentType;
    @Column private String documentName;
    @Lob
    @Column private Blob documentContent;
    @Column private String contentType;

    @ManyToOne
    @JoinColumn(name="candidateId",updatable = false, insertable = false)
    private Candidate candidate ;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Blob getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(Blob documentContent) {
        this.documentContent = documentContent;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getDocumentType() {
        return documentType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    } 

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }
}
