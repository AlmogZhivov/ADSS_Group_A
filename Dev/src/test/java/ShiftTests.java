import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.Superlee.HR.Backend.Business.ShiftToSend;
import com.Superlee.HR.Backend.Business.WorkerFacade;
import com.Superlee.HR.Backend.Business.WorkerToSend;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class ShiftTests {
    private final WorkerFacade workerFacade = WorkerFacade.getInstance();
    private final ShiftFacade shiftFacade = ShiftFacade.getInstance();

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
        return shiftFacade.addNewShift("2025-01-01T08:00", "2025-01-01T16:00");
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
        int result = shiftFacade.addNewShift(null, "2025-01-01T16:00");
        assertEquals(-1, result);
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewShiftWithNullEnd() {
        int startSize = shiftFacade.getAllShifts().size();
        int result = shiftFacade.addNewShift("2025-01-01T08:00", null);
        assertEquals(-1, result);
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewShiftWithStartEqualToEnd() {
        int startSize = shiftFacade.getAllShifts().size();
        int result = shiftFacade.addNewShift("2025-01-01T08:00", "2025-01-01T08:00");
        assertEquals(-1, result);
        int endSize = shiftFacade.getAllShifts().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewShiftWithEndBeforeStart() {
        int startSize = shiftFacade.getAllShifts().size();
        int result = shiftFacade.addNewShift("2025-01-01T16:00", "2025-01-01T08:00");
        assertEquals(-1, result);
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
        ShiftToSend result = shiftFacade.getShift(-1);
        assertNull(result);
    }

    @Test
    public void testGetShiftWithNonExistingId() {
        ShiftToSend result = shiftFacade.getShift(0);
        assertNull(result);
    }

    @Test
    public void testAssignWorkerSuccess() {
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
        shiftFacade.addAvailability("0", sid);
        boolean result = shiftFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
        result = shiftFacade.assignWorker("1", sid, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureNotAvailable() {
        addWorker();
        addRole();
        int sid = addShift();
        boolean result = shiftFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureNonExistingShift() {
        addWorker();
        addRole();
        shiftFacade.addAvailability("0", 0);
        boolean result = shiftFacade.assignWorker("0", 0, "Manager");
        assertFalse(result);
        result = shiftFacade.assignWorker("0", -1, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureNonExistingRole() {
        addWorker();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        boolean result = shiftFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
    }

    @Test
    public void testAssignWorkerFailureAlreadyAssigned() {
        addWorker();
        addRole();
        int sid = addShift();
        shiftFacade.addAvailability("0", sid);
        shiftFacade.assignWorker("0", sid, "Manager");
        boolean result = shiftFacade.assignWorker("0", sid, "Manager");
        assertFalse(result);
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
        boolean result = shiftFacade.unassignWorker("0", 0);
        assertFalse(result);
    }

    @Test
    public void testUnassignWorkerFailureNonAssigned() {
        addWorker();
        int sid = addShift();
        boolean result = shiftFacade.unassignWorker("0", sid);
        assertFalse(result);
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
        List<WorkerToSend> result = shiftFacade.getWorkersByShift(-1);
        assertNull(result);
    }

    @Test
    public void testGetWorkersByShiftWithNonExistingId() {
        List<WorkerToSend> result = shiftFacade.getWorkersByShift(0);
        assertNull(result);
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
        List<WorkerToSend> result = shiftFacade.getAssignableWorkersForShift(-1);
        assertNull(result);
    }

    @Test
    public void testGetAssignableWorkersForShiftWithNonExistingId() {
        List<WorkerToSend> result = shiftFacade.getAssignableWorkersForShift(0);
        assertNull(result);
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleSuccess() {
        int sid = addShift();
        boolean result = shiftFacade.setShiftRequiredWorkersOfRole(sid, "Manager", 1);
        assertTrue(result);
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleFailureNonExistingShift() {
        boolean result = shiftFacade.setShiftRequiredWorkersOfRole(0, "Manager", 1);
        assertFalse(result);
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleFailureNonExistingRole() {
        int sid = addShift();
        boolean result = shiftFacade.setShiftRequiredWorkersOfRole(sid, "Emperor", 1);
        assertFalse(result);
    }

    @Test
    public void testSetShiftRequiredWorkersOfRoleFailureNegativeAmount() {
        int sid = addShift();
        boolean result = shiftFacade.setShiftRequiredWorkersOfRole(sid, "Manager", -1);
        assertFalse(result);
    }
}
