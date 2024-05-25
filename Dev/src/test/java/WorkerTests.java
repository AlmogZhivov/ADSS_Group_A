import com.Superlee.HR.Backend.Business.WorkerFacade;
import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.Superlee.HR.Backend.Business.WorkerToSend;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTests {
    private final WorkerFacade workerFacade = WorkerFacade.getInstance();
    private final ShiftFacade shiftFacade = ShiftFacade.getInstance();

    @Before
    public void setUp() {
        workerFacade.reset(0xC0FFEE);
        shiftFacade.reset(0xC0FFEE);
    }

    private int addShift() {
        return shiftFacade.addNewShift("2025-01-01T08:00", "2025-01-01T16:00");
    }

    private boolean addWorker() {
        return workerFacade.addNewWorker("0", "Super", "Lee");
    }

    private boolean addRole() {
        return workerFacade.addRole("0", "Manager");
    }

    @Test
    public void testGetAllWorkersWithNoWorkers() {
        int result = workerFacade.getAllWorkers().size();
        assertEquals(0, result);
    }

    @Test
    public void testAddNewWorkerSuccess() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = addWorker();
        assertTrue(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void testAddNewWorkerWithEmptyId() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("", "Super", "Lee");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadId() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("id", "Super", "Lee");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullId() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker(null, "Super", "Lee");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithDuplicateId() {
        int startSize = workerFacade.getAllWorkers().size();
        addWorker();
        boolean result = addWorker();
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadFirstname() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("0", "", "Lee");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadSurname() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("0", "Super", "");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullFirstname() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("0", null, "Lee");
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullSurname() {
        int startSize = workerFacade.getAllWorkers().size();
        boolean result = workerFacade.addNewWorker("0", "Super", null);
        assertFalse(result);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testGetWorkersByNameSuccess() {
        addWorker();
        int result = workerFacade.getWorkersByName("Super", "Lee").size();
        assertEquals(1, result);
    }

    @Test
    public void testGetWorkersByNameFailureNonExisting() {
        int result = workerFacade.getWorkersByName("Super", "Lee").size();
        assertEquals(0, result);
    }

    @Test
    public void testGetWorkersByIdSuccess() {
        addWorker();
        WorkerToSend result = workerFacade.getWorkerById("0");
        assertNotNull(result);
    }

    @Test
    public void testGetWorkersByIdFailureNonExisting() {
        WorkerToSend result = workerFacade.getWorkerById("0");
        assertNull(result);
    }

    @Test
    public void testAddRoleSuccess() {
        addWorker();
        boolean result = addRole();
        assertTrue(result);
    }

    @Test
    public void testAddRoleFailureDuplicateRole() {
        addWorker();
        addRole();
        boolean result = addRole();
        assertFalse(result);
    }

    @Test
    public void testAddRoleFailureNonExistingWorker() {
        boolean result = addRole();
        assertFalse(result);
    }

    @Test
    public void testAddRoleFailureBadRole() {
        addWorker();
        boolean result = workerFacade.addRole("0", "Emperor");
        assertFalse(result);
    }

    @Test
    public void testGetWorkersByRoleSuccess() {
        addWorker();
        addRole();
        int result = workerFacade.getWorkersByRole("Manager").size();
        assertEquals(1, result);
    }

    @Test
    public void testAssignWorkerSuccess() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        boolean result = workerFacade.assignWorker("0", sid, "Manager");
        assertTrue(result);
    }

    @Test
    public void testAssignWorkerFailureNonExisting() {
        addWorker();
        shiftFacade.addAvailability("0", 0);
        boolean result = workerFacade.assignWorker("0", 0, "Manager");
        assertFalse(result);
        result = workerFacade.assignWorker("0", -1, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureNonExistingRole() {
        addWorker();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        boolean result = workerFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureNotAvailable() {
        addWorker();
        addRole();
        int sid = addShift();
        boolean result = workerFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureAlreadyAssigned() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        workerFacade.assignWorker("0", sid, "Manager");
        boolean result = workerFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
    }

    @Test
    public void testUnassignWorkerSuccess() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        workerFacade.assignWorker("0", sid, "Manager");
        boolean result = workerFacade.unassignWorker("0", sid);
        assertTrue(result);
    }

    @Test
    public void testUnassignWorkerFailureNonExisting() {
        int sid = addShift();
        boolean result = workerFacade.unassignWorker("0", sid);
        assertFalse(result);
    }

    @Test
    public void testUnassignWorkerFailureNonAssigned() {
        addWorker();
        int sid = addShift();
        boolean result = workerFacade.unassignWorker("0", sid);
        assertFalse(result);
        shiftFacade.addAvailability("0", sid);
        result = workerFacade.unassignWorker("0", sid);
        assertFalse(result);
    }
}
