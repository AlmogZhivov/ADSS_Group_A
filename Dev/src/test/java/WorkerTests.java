import com.Superlee.HR.Backend.Business.WorkerFacade;
import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.Superlee.HR.Backend.Business.WorkerToSend;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

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
        return shiftFacade.addNewShift("Hakol BeHinam", "2025-01-01T08:00", "2025-01-01T16:00");
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
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker("", "Super", "Lee"));
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadId() {
        int startSize = workerFacade.getAllWorkers().size();
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker("id", "Super", "Lee"));

        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullId() {

        int startSize = workerFacade.getAllWorkers().size();
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker(null, "Super", "Lee"));
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithDuplicateId() {
        int startSize = workerFacade.getAllWorkers().size();
        addWorker();
        assertThrows(IllegalArgumentException.class, this::addWorker);
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadFirstname() {
        int startSize = workerFacade.getAllWorkers().size();
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker("0", "", "Lee"));
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithBadSurname() {
        int startSize = workerFacade.getAllWorkers().size();
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker("0", "Super", ""));
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullFirstname() {
        int startSize = workerFacade.getAllWorkers().size();
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker("0", null, "Lee"));
        int endSize = workerFacade.getAllWorkers().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewWorkerWithNullSurname() {
        int startSize = workerFacade.getAllWorkers().size();
        assertThrows(IllegalArgumentException.class, () -> workerFacade.addNewWorker("0", "Super", null));
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
        assertThrows(NoSuchElementException.class, () -> workerFacade.getWorkerById("0"));
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
        assertThrows(NoSuchElementException.class, () -> shiftFacade.addAvailability("0", 0));
        assertThrows(IllegalStateException.class, () -> workerFacade.assignWorker("0", 0, "Manager"));
        assertThrows(IllegalArgumentException.class, () -> workerFacade.assignWorker("0", -1, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureNonExistingRole() {
        addWorker();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        assertThrows(IllegalStateException.class, () -> workerFacade.assignWorker("0", sid, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureNotAvailable() {
        addWorker();
        addRole();
        int sid = addShift();
        assertThrows(IllegalStateException.class, () -> workerFacade.assignWorker("0", sid, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureAlreadyAssigned() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        workerFacade.assignWorker("0", sid, "Manager");
        assertThrows(IllegalStateException.class, () -> workerFacade.assignWorker("0", sid, "Manager"));
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
        assertThrows(NoSuchElementException.class, () -> workerFacade.unassignWorker("0", sid));
    }

    @Test
    public void testUnassignWorkerFailureNonAssigned() {
        addWorker();
        int sid = addShift();
        assertThrows(IllegalStateException.class, () -> workerFacade.unassignWorker("0", sid));
        shiftFacade.addAvailability("0", sid);
        assertThrows(IllegalStateException.class, () -> workerFacade.unassignWorker("0", sid));
    }
}
