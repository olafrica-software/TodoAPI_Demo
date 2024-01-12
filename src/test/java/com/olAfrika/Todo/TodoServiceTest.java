package com.olAfrika.Todo;

import com.olAfrika.Todo.Service.TodoService;
import com.olAfrika.Todo.module.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodoService todoService;


    @Test
    void testGetTodoById() {
        Todo todo = new Todo(null, "Read a book", false);
        Todo savedTodo = todoService.addTodo(todo);

        Optional<Todo> retrievedTodos = todoService.getTodoById(savedTodo.getId());
        assertTrue(retrievedTodos.isPresent());
        assertEquals("Read a book", retrievedTodos.get().getTask());
    }

    @Test
    void testAddTodo() {
        Todo todo = new Todo(null, "Study for exam", false);
        Todo savedTodo = todoService.addTodo(todo);

        assertEquals("Study for exam", savedTodo.getTask());
        assertFalse(savedTodo.isCompleted());
    }

    @Test
    void testUpdateTodo() {
        Todo todo = new Todo(null, "Write code", false);
        Todo savedTodo = todoService.addTodo(todo);

        Todo updatedTodos = new Todo(null, "Write more code", true);
        Todo result = todoService.updateTodo(savedTodo.getId(), updatedTodos);

        assertEquals("Write more code", result.getTask());
        assertTrue(result.isCompleted());
    }

    @Test
    void testDeleteTodo() {
        Todo todo = new Todo(null, "Write more code", true);
        Todo savedTodo = todoService.addTodo(todo);

        boolean deletionResult = todoService.deleteTodo(savedTodo.getId());

        assertTrue(deletionResult);
    }


}
