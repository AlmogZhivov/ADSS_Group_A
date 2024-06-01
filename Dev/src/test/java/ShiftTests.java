import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.Superlee.HR.Backend.Business.ShiftToSend;
import com.Superlee.HR.Backend.Business.WorkerFacade;
import org.junit.Before;
import org.junit.Test;

import java.time.DateTimeException;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ShiftTests {
    private final WorkerFacade workerFacade = WorkerFacade.getInstance();
    private final ShiftFacade shiftFacade = ShiftFacade.getInstance();
    // TODO: delete this
    private final String branch = "Hakol BeHinam";

    @Before
    public void setUp() {
        workerFacade.reset(0xC0FFEE);
        shiftFacade.reset(0xC0FFEE);
    }

    private void addWorker() {
        workerFacade.addNewWorker("0", "Super", "Lee");
    }

    private void addRole() {
        workerFacade.addRole("0", "Manager");
    }

    private int addShift() {
        return shiftFacade.addNewShift(branch, "2025-01-01T08:00", "2025-01-01T16:00");
    }

    @Test
    public void testAddNewShiftSuccess() {
        int startSize = shiftFacade.getAllShifts().size();
        int result = addShift();
        assertNotEquals(-1, result);
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void testAddNewShiftWithNullStart() {
        int startSize = shiftFacade.getAllShifts().size();
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.addNewShift(branch, null, "2025-01-01T16:00"));
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewShiftWithNullEnd() {
        int startSize = shiftFacade.getAllShifts().size();
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.addNewShift(branch, "2025-01-01T08:00", null));
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewShiftWithStartEqualToEnd() {
        int startSize = shiftFacade.getAllShifts().size();
        assertThrows(DateTimeException.class, () -> shiftFacade.addNewShift(branch, "2025-01-01T08:00", "2025-01-01T08:00"));
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewShiftWithEndBeforeStart() {
        int startSize = shiftFacade.getAllShifts().size();
        assertThrows(DateTimeException.class, () -> shiftFacade.addNewShift(branch, "2025-01-01T16:00", "2025-01-01T08:00"));
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testGetShiftSuccess() {
        int sid = addShift();
        ShiftToSend result = shiftFacade.getShift(sid);
        assertNotNull(result);
    }

    @Test
    public void testGetShiftWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.getShift(-1));
    }

    @Test
    public void testGetShiftWithNonExistingId() {
        assertThrows(NoSuchElementException.class, () -> shiftFacade.getShift(0));
    }

    @Test
    public void testAssignWorkerSuccess() {
        workerFacade.reset(0xC0FFEE);
        shiftFacade.reset(0xC0FFEE);
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        boolean result = shiftFacade.assignWorker("0", sid, "Manager");
        assertTrue(result);
    }

    @Test
    public void testAssignWorkerFailureNonExistingWorker() {
        addRole();
        int sid = addShift();
        assertThrows(NoSuchElementException.class, () -> shiftFacade.addAvailability("0", sid));
        assertThrows(NoSuchElementException.class, () -> shiftFacade.assignWorker("0", sid, "Manager"));
        assertThrows(NoSuchElementException.class, () -> shiftFacade.assignWorker("1", sid, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureNotAvailable() {
        addWorker();
        addRole();
        int sid = addShift();
        assertThrows(IllegalStateException.class, () -> shiftFacade.assignWorker("0", sid, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureNonExistingShift() {
        addWorker();
        addRole();
        assertThrows(NoSuchElementException.class, () -> shiftFacade.addAvailability("0", 0));
        assertThrows(NoSuchElementException.class, () -> shiftFacade.assignWorker("0", 0, "Manager"));
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.assignWorker("0", -1, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureNonExistingRole() {
        addWorker();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        assertThrows(IllegalStateException.class, () -> shiftFacade.assignWorker("0", sid, "Manager"));
    }

    @Test
    public void testAssignWorkerFailureAlreadyAssigned() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        shiftFacade.assignWorker("0", sid, "Manager");
        assertThrows(IllegalStateException.class, () -> shiftFacade.assignWorker("0", sid, "Manager"));
    }

    @Test
    public void testUnassignWorkerSuccess() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        shiftFacade.assignWorker("0", sid, "Manager");
        boolean result = shiftFacade.unassignWorker("0", sid);
        assertTrue(result);
    }

    @Test
    public void testUnassignWorkerFailureNonExistingShift() {
        addWorker();
        assertThrows(NoSuchElementException.class, () -> shiftFacade.unassignWorker("0", 0));
    }

    @Test
    public void testUnassignWorkerFailureNonAssigned() {
        addWorker();
        int sid = addShift();
        assertThrows(IllegalStateException.class, () -> shiftFacade.unassignWorker("0", sid));
    }

    @Test
    public void testGetWorkersByShiftSuccessEmptyShift() {
        int sid = addShift();
        int result = shiftFacade.getWorkersByShift(sid).size();
        assertEquals(0, result);
    }

    @Test
    public void testGetWorkersByShiftSuccess() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        shiftFacade.assignWorker("0", sid, "Manager");
        int result = shiftFacade.getWorkersByShift(sid).size();
        assertEquals(1, result);
    }

    @Test
    public void testGetWorkersByShiftWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.getWorkersByShift(-1));
    }

    @Test
    public void testGetWorkersByShiftWithNonExistingId() {
        assertThrows(NoSuchElementException.class, () -> shiftFacade.getWorkersByShift(0));

    }

    @Test
    public void testGetAssignableWorkersForShiftSuccessEmptyShift() {
        int sid = addShift();
        int result = shiftFacade.getAssignableWorkersForShift(sid).size();
        assertEquals(0, result);
    }

    @Test
    public void testGetAssignableWorkersForShiftSuccess() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        shiftFacade.assignWorker("0", sid, "Manager");
        int result = shiftFacade.getAssignableWorkersForShift(sid).size();
        assertEquals(1, result);
    }

    @Test
    public void testGetAssignableWorkersForShiftWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.getAssignableWorkersForShift(-1));
    }

    @Test
    public void testGetAssignableWorkersForShiftWithNonExistingId() {
        assertThrows(NoSuchElementException.class, () -> shiftFacade.getAssignableWorkersForShift(0));
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleSuccess() {
        int sid = addShift();
        boolean result = shiftFacade.setShiftRequiredWorkersOfRole(sid, "Manager", 1);
        assertTrue(result);
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleFailureNonExistingShift() {
        assertThrows(NoSuchElementException.class, () -> shiftFacade.setShiftRequiredWorkersOfRole(0, "Manager", 1));
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleFailureNonExistingRole() {
        int sid = addShift();
        assertThrows(NoSuchElementException.class, () -> shiftFacade.setShiftRequiredWorkersOfRole(sid, "Emperor", 1));
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleFailureNegativeAmount() {
        int sid = addShift();
        assertThrows(IllegalArgumentException.class, () -> shiftFacade.setShiftRequiredWorkersOfRole(sid, "Manager", -1));
    }
}