package ru.yandex.javacourse.kovalev.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, Subtask> subtasks;
    private int generatorId; // Переименованный генератор идентификаторов

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.generatorId = 0; // Начнем с 0
    }

    private int generateId() {
        return ++generatorId; // Инкремент до генерации идентификатора
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public int createTask(Task task) {
        int id = generateId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public int createEpic(Epic epic) {
        int id = generateId();
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public int createSubtask(Subtask subtask) {
        int id = generateId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        if (subtask.getEpic() != null) {
            Epic epic = epics.get(subtask.getEpic().getId());
            if (epic != null) {
                epic.addSubtaskId(subtask.getId());
                updateEpicStatus(epic.getId());
            }
        }
        return id;
    }

    public void updateTask(Task task) {
        if (task instanceof Epic) {
            updateEpic((Epic) task);
        } else if (task instanceof Subtask) {
            updateSubtask((Subtask) task);
        } else {
            tasks.put(task.getId(), task);
        }
    }

    private void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    private void updateSubtask(Subtask subtask) {
        int id = subtask.getId();
        Subtask savedSubtask = subtasks.get(id);
        if (savedSubtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpic().getId());
        if (epic == null) {
            return;
        }
        subtasks.put(id, subtask);
        updateEpicStatus(epic.getId());
    }

    public void deleteTaskById(int id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
        } else if (epics.containsKey(id)) {
            deleteEpicById(id);
        } else if (subtasks.containsKey(id)) {
            deleteSubtaskById(id);
        }
    }

    private void deleteEpicById(int id) {
        Epic epic = epics.remove(id); // Удаляем эпик из коллекции

        if (epic != null) {
            // Получаем список идентификаторов подзадач
            List<Integer> subtaskIds = new ArrayList<>(epic.getSubtaskIds());

            for (int subtaskId : subtaskIds) {
                deleteSubtaskById(subtaskId); // Удаляем каждую подзадачу
            }
        }
    }

    private void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask != null) {
            Epic epic = subtask.getEpic();
            if (epic != null) {
                epic.removeSubtaskId(subtask.getId());
                updateEpicStatus(epic.getId());
            }
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllEpics() {
        for (Epic epic : new ArrayList<>(epics.values())) {
            deleteEpicById(epic.getId());
        }
    }

    public void deleteAllSubtasks() {
        for (Subtask subtask : new ArrayList<>(subtasks.values())) {
            deleteSubtaskById(subtask.getId());
        }
    }

    public List<Subtask> getSubtasksOfEpic(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            List<Subtask> subtasksOfEpic = new ArrayList<>();
            for (int subtaskId : epic.getSubtaskIds()) {
                Subtask subtask = subtasks.get(subtaskId);
                if (subtask != null) {
                    subtasksOfEpic.add(subtask);
                }
            }
            return subtasksOfEpic;
        }
        return new ArrayList<>();
    }

    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic != null) {
            List<Subtask> subtasksOfEpic = getSubtasksOfEpic(epicId);

            boolean allDone = true;
            boolean anyInProgress = false;

            for (Subtask subtask : subtasksOfEpic) {
                switch (subtask.getStatus()) {
                    case IN_PROGRESS:
                        anyInProgress = true;
                        break;
                    case NEW:
                        allDone = false;
                        break;
                    case DONE:
                        // Оставляем allDone как есть
                        break;
                }
            }

            if (allDone) {
                epic.setStatus(Status.DONE);
            } else if (anyInProgress) {
                epic.setStatus(Status.IN_PROGRESS);
            } else {
                epic.setStatus(Status.NEW);
            }
        }
    }
}
