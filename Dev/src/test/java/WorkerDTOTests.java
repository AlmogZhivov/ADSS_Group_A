//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Date;
//
//public class WorkerControllerTest {
//
//    private WorkerController workerController;
//    private WorkerDTO workerDTO;
//
//    @Before
//    public void setUp() {
//        workerController = new WorkerController();
//        workerDTO = mock(WorkerDTO.class);
//    }
//
//    @Test
//    public void testCreateWorker() {
//        WorkerDTO worker = new WorkerDTO("1", "John", "Doe", "john.doe@example.com", "123456789", "password", "123456789", 1000, new Date(), "permanent", "Branch1");
//        boolean result = workerController.createWorker(worker);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetWorker() {
//        when(workerDTO.getId()).thenReturn("1");
//        when(workerDTO.getFirstname()).thenReturn("John");
//        when(workerDTO.getSurname()).thenReturn("Doe");
//        when(workerDTO.getEmail()).thenReturn("john.doe@example.com");
//
//        WorkerDTO worker = workerController.getWorker("1");
//
//        assertEquals("1", worker.getId());
//        assertEquals("John", worker.getFirstname());
//        assertEquals("Doe", worker.getSurname());
//    }
//
//    @Test
//    public void testUpdateWorker() {
//        WorkerDTO worker = new WorkerDTO("1", "John", "Doe", "john.doe@example.com", "123456789", "password", "123456789", 1000, new Date(), "permanent", "Branch1");
//        workerController.createWorker(worker);
//
//        WorkerDTO updatedWorker = new WorkerDTO("1", "Jane", "Doe", "jane.doe@example.com", "987654321", "newpassword", "987654321", 2000, new Date(), "contract", "Branch2");
//        boolean result = workerController.updateWorker("1", updatedWorker);
//        assertTrue(result);
//
//        WorkerDTO fetchedWorker = workerController.getWorker("1");
//        assertEquals("Jane", fetchedWorker.getFirstname());
//        assertEquals("Doe", fetchedWorker.getSurname());
//        assertEquals("jane.doe@example.com", fetchedWorker.getEmail());
//    }
//
//    @Test
//    public void testDeleteWorker() {
//        WorkerDTO worker = new WorkerDTO("1", "John", "Doe", "john.doe@example.com", "123456789", "password", "123456789", 1000, new Date(), "permanent", "Branch1");
//        workerController.createWorker(worker);
//
//        boolean result = workerController.deleteWorker("1");
//        assertTrue(result);
//
//        WorkerDTO fetchedWorker = workerController.getWorker("1");
//        assertNull(fetchedWorker);
//    }
//
//    @Test
//    public void testLoadAllWorkers() {
//        WorkerDTO worker1 = new WorkerDTO("1", "John", "Doe", "john.doe@example.com", "123456789", "password", "123456789", 1000, new Date(), "permanent", "Branch1");
//        WorkerDTO worker2 = new WorkerDTO("2", "Jane", "Doe", "jane.doe@example.com", "987654321", "password", "987654321", 2000, new Date(), "contract", "Branch2");
//
//        workerController.createWorker(worker1);
//        workerController.createWorker(worker2);
//
//        List<WorkerDTO> workers = workerController.loadAll();
//        assertEquals(2, workers.size());
//    }
//}
