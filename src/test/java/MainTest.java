package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class MainTest {

    @Test
    void testAddTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Test task"));

        assertEquals(1, tasks.size());
        assertEquals("Test task", tasks.get(0).toString());
    }

    @Test
    void testDeleteTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Task 1"));
        tasks.add(new Task("Task 2"));

        tasks.remove(0);

        assertEquals(1, tasks.size());
        assertEquals("Task 2", tasks.get(0).toString());
    }

    @Test
    void testEditTask() {
        Task task = new Task("Old");

        task.setTitle("New");

        assertEquals("New", task.getTitle());
    }
}