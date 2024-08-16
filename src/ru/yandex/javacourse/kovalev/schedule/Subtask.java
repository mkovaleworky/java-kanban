package ru.yandex.javacourse.kovalev.schedule;

public class Subtask extends Task {
    private Epic epic;

    public Subtask(String title, String description) {
        super(title, description);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

    @Override
    public String toString() {
        return "ru.yandex.javacourse.kovalev.schedule.Subtask{id=" + id + ", title='" + title + "', description='" + description + "', status=" + status + ", epicId=" + (epic != null ? epic.getId() : null) + "}";
    }
}
