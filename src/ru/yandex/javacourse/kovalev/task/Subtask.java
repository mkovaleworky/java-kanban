package ru.yandex.javacourse.kovalev.task;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String title, String description) {
        super(title, description);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "ru.yandex.javacourse.kovalev.task.Subtask{id=" + id + ", title='" + title + "', description='" + description + "', status=" + status + ", epicId=" + epicId + "}";
    }
}
