package com.olAfrika.Todo.Service;

import com.olAfrika.Todo.TodoRepository;
import com.olAfrika.Todo.module.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long todoId) {
        return todoRepository.findById(todoId);
    }

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long todoId, Todo updatedTodo) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();
            existingTodo.setTask(updatedTodo.getTask());
            existingTodo.setCompleted(updatedTodo.isCompleted());
            return todoRepository.save(existingTodo);
        }
        return null;
    }

    public boolean deleteTodo(Long todoId) {
        Optional<Todo> todoOptional = todoRepository.findById(todoId);

        if (todoOptional.isPresent()) {
            todoRepository.delete(todoOptional.get());
            return true;
        }

        return false;
    }

    public void completeTask(Long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            todo.setCompleted(true);
            todoRepository.save(todo);
        }
    }
}
