package ru.yandex.javacourse.kovalev.schedule;

import ru.yandex.javacourse.kovalev.manager.TaskManager;
import ru.yandex.javacourse.kovalev.task.*;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создание задач
        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        manager.createTask(task1);
        manager.createTask(task2);

        // Создание эпика и подзадач
        Epic epic1 = new Epic("Epic 1", "Description 1");
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Subtask 1", "Description 1");
        subtask1.setEpicId(epic1.getId());
        manager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Subtask 2", "Description 2");
        subtask2.setEpicId(epic1.getId());
        manager.createSubtask(subtask2);

        // Создание второго эпика и подзадачи
        Epic epic2 = new Epic("Epic 2", "Description 2");
        manager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Subtask 3", "Description 3");
        subtask3.setEpicId(epic2.getId());
        manager.createSubtask(subtask3);

        // Вывод всех задач, эпиков и подзадач
        System.out.println("All Tasks: " + manager.getAllTasks());
        System.out.println("All Epics: " + manager.getAllEpics());
        System.out.println("All Subtasks: " + manager.getAllSubtasks());

        // Обновление статуса задач и подзадач
        task1.setStatus(Status.IN_PROGRESS);
        manager.updateTaskById(task1.getId(), task1);

        subtask1.setStatus(Status.DONE);
        manager.updateSubtaskById(subtask1.getId(), subtask1);

        subtask2.setStatus(Status.DONE);
        manager.updateSubtaskById(subtask2.getId(), subtask2);

        // Вывод обновленных данных
        System.out.println("Updated Task 1: " + manager.getTaskById(task1.getId()));
        System.out.println("Updated Epic 1: " + manager.getEpicById(epic1.getId()));

        // Удаление задач и эпиков
        manager.deleteTaskById(task2.getId());
        manager.deleteEpicById(epic2.getId());

        // Вывод всех задач, эпиков и подзадач после удаления
        System.out.println("All Tasks after deletion: " + manager.getAllTasks());
        System.out.println("All Epics after deletion: " + manager.getAllEpics());
        System.out.println("All Subtasks after deletion: " + manager.getAllSubtasks());
    }
}
