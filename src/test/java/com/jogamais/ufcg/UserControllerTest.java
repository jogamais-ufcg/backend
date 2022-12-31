package com.jogamais.ufcg;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    private JSONObject createStudent(String name, String cpf, String email, String enrollment) throws Exception {
        MockMultipartFile fileFront = new MockMultipartFile("fileFront", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());

        MockPart namePart = new MockPart("name", name.getBytes());
        MockPart cpfPart = new MockPart("cpf", cpf.getBytes());
        MockPart emailPart = new MockPart("email", email.getBytes());
        MockPart enrollmentPart = new MockPart("enrollment", enrollment.getBytes());
        MockPart phoneNumber = new MockPart("phoneNumber", "83940028922".getBytes());
        MockPart password = new MockPart("password", "minhasenha".getBytes());
        MockPart isStudent = new MockPart("isStudent", "true".getBytes());
        MockPart isUFCGMember = new MockPart("isUFCGMember", "true".getBytes());

        MvcResult result = mvc.perform(multipart("/users")
                        .file(fileFront)
                        .part(namePart, cpfPart, emailPart, enrollmentPart, phoneNumber, password, isStudent, isUFCGMember)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        return new JSONObject(result.getResponse().getContentAsString());
    }

    private JSONObject createInternalUFCGMember(String name, String cpf, String email) throws Exception {
        MockMultipartFile fileFront = new MockMultipartFile("fileFront", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());
        MockMultipartFile fileBack = new MockMultipartFile("fileBack", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());

        MockPart namePart = new MockPart("name", name.getBytes());
        MockPart cpfPart = new MockPart("cpf", cpf.getBytes());
        MockPart emailPart = new MockPart("email", email.getBytes());
        MockPart phoneNumber = new MockPart("phoneNumber", "83940028922".getBytes());
        MockPart password = new MockPart("password", "minhasenha".getBytes());
        MockPart isStudent = new MockPart("isStudent", "false".getBytes());
        MockPart isUFCGMember = new MockPart("isUFCGMember", "true".getBytes());

        MvcResult result = mvc.perform(multipart("/users")
                        .file(fileFront)
                        .file(fileBack)
                        .part(namePart, cpfPart, emailPart, phoneNumber, password, isStudent, isUFCGMember)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        return new JSONObject(result.getResponse().getContentAsString());
    }

    @Test
    void emptyFindAll() throws Exception {
        mvc.perform(get("/users?page=0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void createSuccessfulStudent() throws Exception {
        String name = "Davi Sousa";
        String cpf = "22406543846";
        String email = "davi@email.com";
        String enrollment = "123456789";

        JSONObject response = createStudent(name, cpf, email, enrollment);

        assert response.getString("name").equals(name);
        assert response.getString("cpf").equals(cpf);
        assert response.getString("email").equals(email);
        assert response.getString("enrollment").equals(enrollment);
    }

    @Test
    void containsTwoStudents() throws Exception {
        createStudent("Davi Sousa", "22406543846", "davi@email.com", "123456789");
        createStudent("Henrique Silva", "65626099840", "henrique@email.com", "987654321");

        mvc.perform(get("/users?page=0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].cpf").value("22406543846"))
                .andExpect(jsonPath("$[1].cpf").value("65626099840"));
    }

    @Test
    void shouldNotCreateStudentWithInvalidNumberDigits() throws Exception {
        MockMultipartFile fileFront = new MockMultipartFile("fileFront", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());

        MockPart namePart = new MockPart("name", "Davi Sousa".getBytes());
        MockPart cpfPart = new MockPart("cpf", "22406543846".getBytes());
        MockPart emailPart = new MockPart("email", "davi@email.com".getBytes());
        MockPart enrollmentPart = new MockPart("enrollment", "123".getBytes());
        MockPart phoneNumber = new MockPart("phoneNumber", "numero".getBytes());
        MockPart password = new MockPart("password", "minhasenha".getBytes());
        MockPart isStudent = new MockPart("isStudent", "true".getBytes());
        MockPart isUFCGMember = new MockPart("isUFCGMember", "true".getBytes());

        mvc.perform(multipart("/users")
                        .file(fileFront)
                        .part(namePart, cpfPart, emailPart, enrollmentPart, phoneNumber, password, isStudent, isUFCGMember)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").value("Número inválido, tente novamente!"));
    }

    @Test
    void shouldNotCreateStudentWithInvalidEnrollmentDigits() throws Exception {
        MockMultipartFile fileFront = new MockMultipartFile("fileFront", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());

        MockPart namePart = new MockPart("name", "Davi Sousa".getBytes());
        MockPart cpfPart = new MockPart("cpf", "22406543846".getBytes());
        MockPart emailPart = new MockPart("email", "davi@email.com".getBytes());
        MockPart enrollmentPart = new MockPart("enrollment", "matricula".getBytes());
        MockPart phoneNumber = new MockPart("phoneNumber", "83940028922".getBytes());
        MockPart password = new MockPart("password", "minhasenha".getBytes());
        MockPart isStudent = new MockPart("isStudent", "true".getBytes());
        MockPart isUFCGMember = new MockPart("isUFCGMember", "true".getBytes());

        mvc.perform(multipart("/users")
                        .file(fileFront)
                        .part(namePart, cpfPart, emailPart, enrollmentPart, phoneNumber, password, isStudent, isUFCGMember)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").value("O número de matrícula informado é inválido!"));
    }

    @Test
    void shouldNotCreateStudentWithoutEnrollment() throws Exception {
        MockMultipartFile fileFront = new MockMultipartFile("fileFront", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());

        MockPart namePart = new MockPart("name", "Davi Sousa".getBytes());
        MockPart cpfPart = new MockPart("cpf", "22406543846".getBytes());
        MockPart emailPart = new MockPart("email", "davi@email.com".getBytes());
        MockPart phoneNumber = new MockPart("phoneNumber", "83940028922".getBytes());
        MockPart password = new MockPart("password", "minhasenha".getBytes());
        MockPart isStudent = new MockPart("isStudent", "true".getBytes());
        MockPart isUFCGMember = new MockPart("isUFCGMember", "true".getBytes());

        mvc.perform(multipart("/users")
                        .file(fileFront)
                        .part(namePart, cpfPart, emailPart, phoneNumber, password, isStudent, isUFCGMember)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").value("Um aluno da UFCG precisa possuir uma matrícula!"));
    }

    @Test
    void shouldNotCreateNonStudentWithoutFileBack() throws Exception {
        MockMultipartFile fileFront = new MockMultipartFile("fileFront", "file.pdf", MediaType.APPLICATION_PDF_VALUE, "SomeBytesToFile".getBytes());

        MockPart namePart = new MockPart("name", "Davi Sousa".getBytes());
        MockPart cpfPart = new MockPart("cpf", "22406543846".getBytes());
        MockPart emailPart = new MockPart("email", "davi@email.com".getBytes());
        MockPart phoneNumber = new MockPart("phoneNumber", "83940028922".getBytes());
        MockPart password = new MockPart("password", "minhasenha".getBytes());
        MockPart isStudent = new MockPart("isStudent", "false".getBytes());
        MockPart isUFCGMember = new MockPart("isUFCGMember", "false".getBytes());

        mvc.perform(multipart("/users")
                        .file(fileFront)
                        .part(namePart, cpfPart, emailPart, phoneNumber, password, isStudent, isUFCGMember)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage").value("O verso do documento é obrigatório!"));
    }

    @Test
    void createSuccessfulInternalUFCGMember() throws Exception {
        String name = "Davi Sousa";
        String cpf = "22406543846";
        String email = "davi@email.com";

        JSONObject response = createInternalUFCGMember(name, cpf, email);

        assert response.getString("name").equals(name);
        assert response.getString("cpf").equals(cpf);
        assert response.getString("email").equals(email);
    }

    @Test
    void updateUserPhoneNumber() throws Exception {
        JSONObject userJSON = createStudent("Davi Sousa", "22406543846", "davi@email.com", "123456789");

        String updatedPhoneNumber = "11911223344";
        JSONObject body = new JSONObject();
        body.put("phoneNumber", updatedPhoneNumber);

        mvc.perform(patch("/users/{id}", userJSON.getLong("id"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(body.toString()))
                .andExpect(jsonPath("$.id").value(userJSON.getLong("id")))
                .andExpect(jsonPath("$.phoneNumber").value(updatedPhoneNumber));
    }
}
