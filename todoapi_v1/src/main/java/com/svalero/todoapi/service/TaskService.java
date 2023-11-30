package com.svalero.todoapi.service;

import com.svalero.todoapi.domain.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAllTasks();
    Task addTask(Task task);
    void deleteTask(long taskId);
}
