public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        Task task1 = new Task("Task 1", "Description 1");
        Task task2 = new Task("Task 2", "Description 2");
        manager.createTask(task1);
        manager.createTask(task2);

        Epic epic1 = new Epic("Epic 1", "Description 1");
        Subtask subtask1 = new Subtask("Subtask 1", "Description 1");
        Subtask subtask2 = new Subtask("Subtask 2", "Description 2");
        manager.createEpic(epic1);
        subtask1.setEpic(epic1);
        subtask2.setEpic(epic1);
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);

        Epic epic2 = new Epic("Epic 2", "Description 2");
        Subtask subtask3 = new Subtask("Subtask 3", "Description 3");
        manager.createEpic(epic2);
        subtask3.setEpic(epic2);
        manager.createSubtask(subtask3);

        System.out.println("All Tasks: " + manager.getAllTasks());
        System.out.println("All Epics: " + manager.getAllEpics());
        System.out.println("All Subtasks: " + manager.getAllSubtasks());

        task1.setStatus(Status.IN_PROGRESS);
        manager.updateTask(task1);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        manager.updateTask(subtask1);
        manager.updateTask(subtask2);

        System.out.println("Updated Task 1: " + manager.getTaskById(task1.getId()));
        System.out.println("Updated Epic 1: " + manager.getEpicById(epic1.getId()));

        manager.deleteTaskById(task2.getId());
        manager.deleteTaskById(epic2.getId());

        System.out.println("All Tasks after deletion: " + manager.getAllTasks());
        System.out.println("All Epics after deletion: " + manager.getAllEpics());
        System.out.println("All Subtasks after deletion: " + manager.getAllSubtasks());
    }
}
