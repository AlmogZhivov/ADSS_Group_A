//import org.junit.Before;
//import org.junit.Test;
//
//import com.Superlee.HR.Backend.Business.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//
//public class ShiftTests {
//
//
//    private WorkerService workerService;
//
//    @Before
//    public void setUp() {
//        workerService = new WorkerService();
//    }
//
//    @Test
//    public void testGetAllWorkers() {
//        String result = workerService.getAllWorkers();
//        assertEquals("All workers", result);
//    }
//
//    @Test
//    public void testGetWorkersByRole() {
//        int role = 1;
//        String result = workerService.getWorkersByRole(role);
//        assertEquals("Workers with role " + role, result);
//    }
//
//    @Test
//    public void testGetWorkersByName() {
//        String name = "John";
//        String result = workerService.getWorkersByName(name);
//        assertEquals("Workers with name " + name, result);
//    }
//
//    @Test
//    public void testGetWorkersById() {
//        String id = "123";
//        String result = workerService.getWorkersById(id);
//        assertEquals("Worker with ID " + id, result);
//    }
//
//    @Test
//    public void testAssignWorker() {
//        String workerId = "123";
//        String shiftId = "456";
//        int role = 1;
//        String result = workerService.assignWorker(workerId, shiftId, role);
//        assertEquals("Assigned worker " + workerId + " to shift " + shiftId + " with role " + role, result);
//    }
//
//    @Test
//    public void testUnassignWorker() {
//        String workerId = "123";
//        String shiftId = "456";
//        String result = workerService.unassignWorker(workerId, shiftId);
//        assertEquals("Unassigned worker " + workerId + " from shift " + shiftId, result);
//    }
//
//    @Test
//    public void testAddNewWorker() {
//        String id = "789";
//        List<Integer> roles = Arrays.asList(1, 2, 3);
//        String result = workerService.addNewWorker(id, roles);
//        assertEquals("Added new worker with ID " + id, result);
//    }
//
//    @Test
//    public void testLoadData() {
//        String result = workerService.loadData();
//        assertEquals("Data loaded", result);
//    }
//}
//
