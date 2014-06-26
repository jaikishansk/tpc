package com.edu.tpc.service;

/**
 *
 * @author Jaikishan
 * Date: 25/10/11
 */

import com.edu.tpc.dao.DocumentDAO;
import com.edu.tpc.domain.Document;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("documentService")
public class DocumentServiceImpl implements DocumentService
{

    private DocumentDAO documentDAO;

    public DocumentDAO getDocumentDAO() {
        return documentDAO;
    }

    @Autowired
    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    @Override
    public void saveDocument(Document doc)
    {
        documentDAO.save(doc);
    }
}
