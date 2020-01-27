package com.mft.securwa;

import com.mft.securwa.controllers.DocumentController;
import com.mft.securwa.crypto.CustoCrypto;
import com.mft.securwa.repositories.DocumentRepository;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class DocumentControllerUnitTest {

    private static DocumentController documentController;
    private static Model mockedModel;
    private static MockMultipartFile mockedFile;
    private static CustoCrypto mockedCrypto;

    @BeforeClass
    public static void setUp() throws SizeLimitExceededException {
        DocumentRepository mockedDocumentRepository = mock(DocumentRepository.class);
        mockedModel = mock(Model.class);
        documentController = new DocumentController(mockedDocumentRepository);

         mockedFile = new MockMultipartFile("file", "filename.txt", "text/plain", "toto".getBytes());
         documentController.addFile(mockedFile, mockedModel);
    }

    @Test
    public void whenCalledAddFile_thenCorrect() throws SizeLimitExceededException {
        assertThat(documentController.addFile(mockedFile, mockedModel)).isEqualTo("documents");
    }

}
