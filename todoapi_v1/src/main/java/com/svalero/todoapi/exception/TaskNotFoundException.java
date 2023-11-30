package com.svalero.todoapi.exception;

public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(long taskId) {
        super("Task " + taskId + " not found");
    }

    public TaskNotFoundException() {
        super("Task not found");
    }
}
