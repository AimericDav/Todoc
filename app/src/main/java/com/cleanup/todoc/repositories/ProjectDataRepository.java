package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao mProjectDao;

    public ProjectDataRepository(ProjectDao projectDao) { this.mProjectDao = projectDao; }

    public LiveData<List<Project>> getAllProject() {
        return this.mProjectDao.getAllProjects();
    }

    public void insertProject(Project project) {
        this.mProjectDao.insertProject(project);
    }

}
