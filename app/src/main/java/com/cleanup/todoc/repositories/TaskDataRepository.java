package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao mTaskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.mTaskDao = taskDao;
    }

    public LiveData<List<Task>> getAllTask() {
        return this.mTaskDao.getTasks();
    }

    public void insertTask(Task task) {
        mTaskDao.insertTask(task);
    }

    public void deleteTask(Task task) {
        mTaskDao.deleteTask(task);
    }

}
