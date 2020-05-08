package com.cleanup.todoc.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TodocViewModel extends ViewModel {

    // Declare les repository
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor mExecutor;

    public TodocViewModel(ProjectDataRepository projectDataRepository, TaskDataRepository taskDataRepository, Executor executor) {
        this.projectDataSource = projectDataRepository;
        this.taskDataSource = taskDataRepository;
        this.mExecutor = executor;
    }

    // TASK
    public LiveData<List<Task>> getAllTasks() {
        return taskDataSource.getAllTask();
    }

    public void insertTask(Task task) {
        mExecutor.execute(() -> {
            taskDataSource.insertTask(task);
        });
    }

    public void deleteTask(long id) {
        mExecutor.execute(() -> {
            taskDataSource.deleteTask(id);
        });
    }

    // Project
    public List<Project> getAllProject() {
        return projectDataSource.getAllProject();
    }

    public void insertProject(Project project) {
        mExecutor.execute(() -> {
            projectDataSource.insertProject(project);
        });
    }
}

