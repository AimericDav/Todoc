package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.dao.TodocMasterDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // For data
    private TodocMasterDatabase mDatabase;

    // DATA for TEST
    private Project PROJECT_TARTAMPION = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
    private Project PROJECT_LUCIDIA = new Project(2L, "Projet Lucidia", 0xFFB4CDBA);
    private Task TASK_DEMO = new Task(1,1L,"TEST",0);

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                TodocMasterDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb( ) throws Exception {
        this.mDatabase.close();
    }


    @Test
    public void insertAndGetAllProject() throws InterruptedException {
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mProjectDao().getAllProjects()).size());
        this.mDatabase.mProjectDao().insertProject(PROJECT_TARTAMPION);
        this.mDatabase.mProjectDao().insertProject(PROJECT_LUCIDIA);
        assertEquals(2, LiveDataTestUtil.getValue(this.mDatabase.mProjectDao().getAllProjects()).size());
    }

    @Test
    public void insertAndGetTask() throws InterruptedException {
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks()).size());
        this.mDatabase.mTaskDao().insertTask(TASK_DEMO);
        assertEquals(1, LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks()).size());
    }

    @Test
    public void insertAndDelete() throws InterruptedException {
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks()).size());
        this.mDatabase.mTaskDao().insertTask(TASK_DEMO);
        assertEquals(1, LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks()).size());
        this.mDatabase.mTaskDao().deleteTask(TASK_DEMO.getId());
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mTaskDao().getTasks()).size());
    }

}
