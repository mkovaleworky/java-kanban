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
    public void setStatus(Status status) {
        super.setStatus(status);
        if (epic != null) {
            epic.updateStatus();
        }
    }

    @Override
    public String toString() {
        return "Subtask{id=" + id + ", title='" + title + "', description='" + description + "', status=" + status + ", epicId=" + (epic != null ? epic.getId() : null) + "}";
    }
}
