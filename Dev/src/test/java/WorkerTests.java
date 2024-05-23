import com.Superlee.HR.Backend.Business.WorkerFacade;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WorkerTests {
    private final WorkerFacade workerFacade = WorkerFacade.getInstance();

    @Before
    public void setUp() {
        workerFacade.reset(0xC0FFEE);
    }

    @Test
    public void testGetAllWorkersWithNoWorkers() {
        int result = workerFacade.getAllWorkers().size();
        assertEquals(0, result);
    }

    @Test
    public void testAddNewWorkerSuccess() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("Idan");
        assertTrue(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadName() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullName() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker(null);
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testGetWorkersByNameSuccess() {
        workerFacade.addNewWorker("Idan");
        int result = workerFacade.getWorkersByName("Idan").size();
        assertEquals(1, result);
    }

    @Test
    public void testGetWorkersByNameFailureNonExisting() {
        int result = workerFacade.getWorkersByName("Idan").size();
        assertEquals(0, result);
    }

    @Test
    public void testGetWorkersByIdSuccess() {
        workerFacade.addNewWorker("Idan");
        int result = workerFacade.getWorkersById(0).size();
        assertEquals(1, result);
    }

    @Test
    public void testGetWorkersByIdFailureNonExisting() {
        int result = workerFacade.getWorkersById(0).size();
        assertEquals(0, result);
    }

    @Test
    public void testAddRoleSuccess() {
        workerFacade.addNewWorker("Idan");
        boolean result = workerFacade.addRole(0, "Manager");
        assertTrue(result);
    }

    @Test
    public void testAddRoleFailureDuplicateRole() {
        workerFacade.addNewWorker("Idan");
        workerFacade.addRole(0, "Manager");
        boolean result = workerFacade.addRole(0, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAddRoleFailureNonExistingWorker() {
        boolean result = workerFacade.addRole(0, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAddRoleFailureBadRole() {
        workerFacade.addNewWorker("David");
        boolean result = workerFacade.addRole(0, "King");
        assertFalse(result);
    }

    @Test
    public void testGetWorkersByRoleSuccess() {
        workerFacade.addNewWorker("Idan");
        boolean result = workerFacade.addRole(0, "Manager");
        assertTrue(result);
    }

    @Test
    public void testAssignWorkerSuccess() {
        workerFacade.addNewWorker("Idan");
        workerFacade.addRole(0, "Manager");
        boolean result = workerFacade.assignWorker(0, 0, "Manager");
        assertTrue(result);
    }

    @Test
    public void testAssignWorkerFailureNonExisting() {
        boolean result = workerFacade.assignWorker(0, 0, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureNonExistingRole() {
        workerFacade.addNewWorker("David");
        boolean result = workerFacade.assignWorker(0, 0, "King");
        assertFalse(result);
    }

    @Test
    public void testUnassignWorkerSuccess() {
        workerFacade.addNewWorker("Idan");
        workerFacade.assignWorker(0, 0, "Manager");
        boolean result = workerFacade.unassignWorker(0, 0);
        assertTrue(result);
    }

    @Test
    public void testUnassignWorkerFailureNonExisting() {
        boolean result = workerFacade.unassignWorker(0, 0);
        assertFalse(result);
    }

    @Test
    public void testUnassignWorkerFailureNonAssigned() {
        workerFacade.addNewWorker("Idan");
        boolean result = workerFacade.unassignWorker(0, 0);
        assertFalse(result);
    }
}
