package com.example.todoapi;

import com.example.todoapi.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")  // Limpa o banco de dados antes de cada teste
class TaskControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateTaskSuccess() {
        var task = new Task();
        task.setTitle("Tarefa de Teste");
        task.setDescription("Descrição de Teste");
        task.setCompleted(false);
        task.setPriority(3);

        webTestClient
                .post()
                .uri("/tasks")
                .bodyValue(task)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.title").isEqualTo(task.getTitle())
                .jsonPath("$.description").isEqualTo(task.getDescription())
                .jsonPath("$.completed").isEqualTo(task.isCompleted())
                .jsonPath("$.priority").isEqualTo(task.getPriority());
    }

    @Test
    void testCreateTaskFailure() {
        var task = new Task();  // Falta o título e a descrição, falha esperada
        task.setCompleted(false);
        task.setPriority(0);

        webTestClient
                .post()
                .uri("/tasks")
                .bodyValue(task)
                .exchange()
                .expectStatus().isBadRequest();  // Espera erro 400 devido à validação
    }

    @Sql("/import.sql")  // Carrega dados de exemplo antes deste teste
    @Test
    void testUpdateTaskSuccess() {
        var updatedTask = new Task();
        updatedTask.setTitle("Tarefa Atualizada");
        updatedTask.setDescription("Descrição Atualizada");
        updatedTask.setCompleted(false);
        updatedTask.setPriority(2);

        webTestClient
                .put()
                .uri("/tasks/1")  // Atualizando a tarefa com ID 1
                .bodyValue(updatedTask)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.title").isEqualTo(updatedTask.getTitle())
                .jsonPath("$.description").isEqualTo(updatedTask.getDescription())
                .jsonPath("$.completed").isEqualTo(updatedTask.isCompleted())
                .jsonPath("$.priority").isEqualTo(updatedTask.getPriority());
    }

    @Test
    void testUpdateTaskFailure() {
        var invalidTask = new Task();
        invalidTask.setTitle("");  // Título inválido
        invalidTask.setDescription("");
        invalidTask.setCompleted(false);
        invalidTask.setPriority(6);  // Prioridade fora do intervalo

        webTestClient
                .put()
                .uri("/tasks/1")  // Atualizando a tarefa com ID 1
                .bodyValue(invalidTask)
                .exchange()
                .expectStatus().isBadRequest();  // Erro 400 devido à validação
    }

    @Sql("/import.sql")
    @Test
    void testDeleteTaskSuccess() {
        webTestClient
                .delete()
                .uri("/tasks/1")  // Deletando a tarefa com ID 1
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteTaskFailure() {
        var invalidId = 999L;  // ID inválido, tarefa não existe

        webTestClient
                .delete()
                .uri("/tasks/" + invalidId)
                .exchange()
                .expectStatus().isNotFound();  // Erro 404, tarefa não encontrada
    }

    @Sql("/import.sql")
    @Test
    void testListTasks() {
        webTestClient
                .get()
                .uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(5);  // Assumindo que temos 5 tarefas
    }
}
