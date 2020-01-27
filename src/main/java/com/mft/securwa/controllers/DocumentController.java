package com.mft.securwa.controllers;

import com.mft.securwa.crypto.CustoCrypto;
import com.mft.securwa.entities.Document;
import com.mft.securwa.repositories.DocumentRepository;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import java.io.File;
import java.io.IOException;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class DocumentController {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @PostMapping(path = "/doc/addFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addFile(@RequestParam("file") MultipartFile multipartFile, Model model)  throws MaxUploadSizeExceededException, SizeLimitExceededException {
        Document document = new Document();
        if (multipartFile.getOriginalFilename() != null) {

            try {
                document.setName(multipartFile.getOriginalFilename());
                byte[] bytes = multipartFile.getBytes();
                document.setFile(CustoCrypto.doCypher(Cipher.ENCRYPT_MODE, bytes));
                documentRepository.save(document);
                model.addAttribute("documents", documentRepository.findAll());
            } catch (IOException | MaxUploadSizeExceededException e) {
                e.printStackTrace();
            }
        }

        return "documents";
    }

    @GetMapping("/doc/display/{id}")
    @ResponseBody
    public FileSystemResource displayDoc(@PathVariable("id") long id, Model model) {
        Document doc = documentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid doc Id:" + id));
        File file = new File(doc.getName());

        model.addAttribute("documents", documentRepository.findAll());
        try {
            byte[] Outputbytes = CustoCrypto.doCypher(Cipher.DECRYPT_MODE, doc.getFile());
            FileUtils.writeByteArrayToFile(file, Outputbytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new FileSystemResource(file);
    }

    @GetMapping("/doc/download/{id}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> ddlDoc(@PathVariable("id") long id, Model model) {
        Document doc = documentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid doc Id:" + id));

        model.addAttribute("documents", documentRepository.findAll());
        byte[] Outputbytes = CustoCrypto.doCypher(Cipher.DECRYPT_MODE, doc.getFile());

        ByteArrayResource ressource = new ByteArrayResource(Outputbytes);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + doc.getName())
                .contentLength(doc.getFile().length)
                .body(ressource);
    }

    @GetMapping("/doc/delete/{id}")
    public String deleteDoc(@PathVariable("id") long id, Model model) {
        Document doc = documentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid doc Id:" + id));
        documentRepository.delete(doc);
        model.addAttribute("documents", documentRepository.findAll());

        return "documents";
    }
}

