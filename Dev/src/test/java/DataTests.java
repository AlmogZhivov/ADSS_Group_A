import com.Superlee.HR.Backend.Business.BranchFacade;
import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.Superlee.HR.Backend.Business.WorkerFacade;
import com.Superlee.HR.Backend.Business.WorkerToSend;
import com.Superlee.HR.Backend.DataAccess.BranchDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.Superlee.HR.Backend.DataAccess.ShiftDTO;
import com.Superlee.HR.Backend.DataAccess.WorkerDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class DataTests {
    private final BranchFacade bf = BranchFacade.getInstance().setTestMode(true);
    private final ShiftFacade sf = ShiftFacade.getInstance().setTestMode(true);
    private final WorkerFacade wf = WorkerFacade.getInstance().setTestMode(true);
    private List<BranchDTO> mockBranches;
    private Map<String, Integer> mockRoles;
    private List<ShiftDTO> mockShifts;
    private List<WorkerDTO> mockWorkers;


    @Before
    public void setUp() {
        bf.reset(0xC0FFEE);
        sf.reset(0xC0FFEE);
        wf.reset(0xC0FFEE);
    }

    @After
    public void cleanUp() {
        wf.clearData();
    }

    @Test
    public void TestLoadWorkerSuccess() {
        addWorker();
        wf.reset(0xC0FFEE);
        wf.loadData();
        assertNotNull(wf.login("0", "0"));
    }

    @Test
    public void TestLoadShiftSuccess() {
        addShift();
        sf.reset(0xC0FFEE);
        sf.loadData();
        assertNotNull(sf.getShift(0));
    }

    @Test
    public void TestLoadBranchSuccess() {
        addWorker();
        fakeLogin(true);
        wf.addWorkerRole("0","Manager");
        bf.addBranch("Branch1", "123 Elm Street", "0");
        fakeLogout();
        bf.reset(0xC0FFEE);
        bf.loadData();
        fakeLogin(true);
        assertNotNull(bf.getBranch("Branch1"));
        fakeLogout();
    }

    @Test
    public void TestLoadRoleSuccess() {
        fakeLogin(true);
        wf.addNewRole("automation");
        fakeLogout();
        wf.reset(0xC0FFEE);
        wf.loadData();
        fakeLogin(true);
        assertTrue(wf.getAllRoles().stream().toList().contains("automation"));
        addWorker();
        wf.addWorkerRole("0", "automation");
        fakeLogout();
        wf.reset(0xC0FFEE);
        wf.loadData();
        fakeLogin(true);
        assertTrue(wf.getWorkerById("0").roles().contains(wf.getAllRoles().size()));
        fakeLogout();
    }

    @Test
    public void TestLoadMockBranchesSuccess() {
        createMockData();
        insertMockData();
        bf.reset(0xC0FFEE);
        bf.loadData();
        fakeLogin(true);
        mockBranches.forEach(b -> assertNotNull(bf.getBranch(b.getName())));
        fakeLogout();
    }

    @Test
    public void TestLoadMockWorkersSuccess() {
        createMockData();
        insertMockData();
        wf.reset(0xC0FFEE);
        wf.loadData();
        fakeLogin(true);

        mockWorkers.forEach(w -> {
            assertNotNull(wf.getWorkerById(w.getId()));
            assertTrue(wf.getWorkerById(w.getId()).roles().containsAll(w.getRoles()));
        });
        fakeLogout();
    }

    @Test
    public void TestLoadMockShiftsSuccess() {
        createMockData();
        insertMockData();
        sf.reset(0xC0FFEE);
        sf.loadData();
        fakeLogin(true);
        mockShifts.forEach(s -> {
            int id = s.getId();
            assertNotNull(sf.getShift(id));
            assertTrue(LocalDateTime.parse(sf.getShift(id).startTime()).equals(s.getStartTime()));
            assertTrue(LocalDateTime.parse(sf.getShift(id).endTime()).equals(s.getEndTime()));
            assertTrue(sf.getShiftRequiredWorkersOfRole(id).equals(s.getRequiredRoles()));

            assertTrue(sf.getAssignableWorkersForShift(id).stream().map(WorkerToSend::id).toList().containsAll(s.getAvailableWorkers()));
            assertTrue(s.getAvailableWorkers().containsAll(sf.getAssignableWorkersForShift(id).stream().map(WorkerToSend::id).toList()));

            assertTrue(sf.getWorkersByShift(id).stream().map(WorkerToSend::id).toList().containsAll(s.getAssignedWorkers()));
            assertTrue(s.getAssignedWorkers().containsAll(sf.getWorkersByShift(id).stream().map(WorkerToSend::id).toList()));

//            Map <String, Integer> ActualWorkerRoles = sf.getWorkerRolesByShift(id);
//            Map <String, Integer> ExpectedWorkerRoles = s.getWorkerRoles();
//
//            for (int key : ExpectedWorkerRoles.values()) {
//
//                System.out.println("key:" + wf.getRoleName(key));
//                System.out.println("Actual val: " +ActualWorkerRoles.get(wf.getRoleName(key)));
//                System.out.println("should be: " + ExpectedWorkerRoles.get(""+key));
//                System.out.println("====================================");
//
//                assertTrue(ActualWorkerRoles.get(wf.getRoleName(key)).equals(ExpectedWorkerRoles.get(""+key)));
//            }

//            System.out.println("Actual: " + sf.getWorkerRolesByShift(id));
//            System.out.println("Expected: " + s.getWorkerRoles());
//            System.out.println("====================================");
            assertTrue(sf.getWorkerRolesByShift(id).equals(s.getWorkerRoles()));
        });
        fakeLogout();
    }

//    @Test
//    public void TestLoadShiftsAssignedWorkers() {
//        createMockData();
//        mockShifts.forEach(sf::addNewShift);
//        mockWorkers.forEach(wf::addWorker);
//        mockRoles.forEach(wf::addNewRole);
//        mockWorkers.forEach(w -> w.roles().forEach(r -> wf.addWorkerRole(w.id(), r)));
//        mockShifts.forEach(s -> s.assignedWorkers().forEach(w -> sf.assignWorker(s.id(), w)));
//        sf.reset(0xC0FFEE);
//        sf.loadData();
//        mockShifts.forEach(s -> {
//            ShiftDTO shift = sf.getShift(s.id());
//            assertNotNull(shift);
//            assertTrue(shift.assignedWorkers().containsAll(s.assignedWorkers()));
//        });
//    }



    // =================================================================================
    // Helper methods
    // =================================================================================
    private int addShift() {
        boolean loggedIn = wf.isLoggedInHRManager();
        if (!loggedIn)
            fakeLogin(true);
        int result = sf.addNewShift("2025-01-01T08:00", "2025-01-01T16:00", "Hakol BeHinam");
        if (!loggedIn)
            fakeLogout();
        return result;
    }

    private boolean addWorker() {
        boolean loggedIn = wf.isLoggedInHRManager();
        if (!loggedIn)
            fakeLogin(true);
        boolean result = wf.addNewWorker("0", "Super", "Lee");
        if (!loggedIn)
            fakeLogout();
        return result;
    }

    private boolean addRoleManager() {
        boolean loggedIn = wf.isLoggedInHRManager();
        if (!loggedIn)
            fakeLogin(true);
        boolean result = wf.addWorkerRole("0", "Manager");
        if (!loggedIn)
            fakeLogout();
        return result;
    }

    private void login() {
        wf.login("0", "0");
    }

    private boolean addSecondWorker() {
        boolean loggedIn = wf.isLoggedInHRManager();
        if (!loggedIn)
            fakeLogin(true);
        boolean result = wf.addNewWorker("1", "Avi", "Ron");
        if (!loggedIn)
            fakeLogout();
        return result;
    }

    private void fakeLogin(boolean hrm) {
        wf.fakeLogin(hrm, "000");
    }

    private void fakeLogout() {
        wf.fakeLogout();
    }

    public void createMockData() {

        mockRoles = Map.of(
                "HRManager", 0,
                "Manager", 1,
                "Cashier", 2,
                "Cleaner", 3,
                "Storekeeper", 4,
                "Security", 5,
                "Driver", 6
        );

        mockWorkers = Arrays.asList(
                new WorkerDTO("0", "Mr", "Poopybutthole", "mr.poopybutthole@company.com", "555-1234", "password", "123456789", 100000, Arrays.asList(0), new ArrayList<>(), Arrays.asList(1, 2, 3), LocalDateTime.now(), "permanent", "Head Office"),
                new WorkerDTO("1", "Homer", "Simpson", "homer.simpson@company.com", "555-2345", "password", "234567890", 50000, Arrays.asList(1, 2), Arrays.asList(1), Arrays.asList(1, 2), LocalDateTime.now(), "contract", "Branch1"),
                new WorkerDTO("2", "Peter", "Griffin", "peter.griffin@company.com", "555-3456", "password", "345678901", 48000, Arrays.asList(2, 3), Arrays.asList(1, 2), Arrays.asList(1, 2, 3), LocalDateTime.now(), "contract", "Branch1"),
                new WorkerDTO("3", "SpongeBob", "SquarePants", "spongebob.squarepants@company.com", "555-4567", "password", "456789012", 52000, Arrays.asList(3, 4), Arrays.asList(2), Arrays.asList(1, 2, 3), LocalDateTime.now(), "contract", "Branch2"),
                new WorkerDTO("4", "Daffy", "Duck", "daffy.duck@company.com", "555-5678", "password", "567890123", 49000, Arrays.asList(4, 5), Arrays.asList(2), Arrays.asList(1, 2), LocalDateTime.now(), "contract", "Branch2"),
                new WorkerDTO("5", "Bugs", "Bunny", "bugs.bunny@company.com", "555-6789", "password", "678901234", 51000, Arrays.asList(5, 6), Arrays.asList(3), Arrays.asList(2, 3), LocalDateTime.now(), "contract", "Branch3"),
                new WorkerDTO("6", "Shrek", "3D", "shrek@company.com", "555-7890", "password", "789012345", 47000, Arrays.asList(0, 1), Arrays.asList(3), Arrays.asList(2, 3), LocalDateTime.now(), "contract", "Branch3"),
                new WorkerDTO("7", "Charlie", "Brown", "charlie.brown@company.com", "555-8901", "password", "890123456", 53000, Arrays.asList(2, 3), Arrays.asList(1), Arrays.asList(1, 2), LocalDateTime.now(), "contract", "Branch1"),
                new WorkerDTO("8", "Scooby", "Doo", "scooby.doo@company.com", "555-9012", "password", "901234567", 48000, Arrays.asList(3, 4), Arrays.asList(2), Arrays.asList(2, 3), LocalDateTime.now(), "contract", "Branch2"),
                new WorkerDTO("9", "Unity", "Noob", "unity@company.com", "555-0123", "password", "012345678", 49000, Arrays.asList(4, 5), Arrays.asList(3), Arrays.asList(2, 3), LocalDateTime.now(), "contract", "Branch3"),
                new WorkerDTO("10", "Noob", "Noob", "noob.noob@company.com", "555-1230", "password", "123456789", 50000, Arrays.asList(5, 6), Arrays.asList(4), Arrays.asList(1, 3), LocalDateTime.now(), "contract", "Branch3")
        );


        mockBranches = Arrays.asList(
                new BranchDTO("Branch1", "123 Elm Street", "Manager1"),
                new BranchDTO("Branch2", "456 Oak Avenue", "Manager2"),
                new BranchDTO("Branch3", "789 Pine Road", "Manager3")
        );

        mockShifts = Arrays.asList(
                new ShiftDTO(1, "Branch1", LocalDateTime.of(2024, 7, 1, 8, 0, 0), LocalDateTime.of(2024, 7, 1, 12, 0, 0), Map.of("Manager", 1, "Cashier", 2), Arrays.asList("1", "2", "7"), Arrays.asList("1", "2"), Map.of("1", 1, "2", 2)),
                new ShiftDTO(2, "Branch2", LocalDateTime.of(2024, 7, 1, 12, 0, 0), LocalDateTime.of(2024, 7, 1, 16, 0, 0), Map.of("Manager", 1, "Cashier", 1, "Cleaner", 3), Arrays.asList("3", "4", "8"), Arrays.asList("3", "4", "8"), Map.of("3", 1, "4", 2, "8", 3)),
                new ShiftDTO(3, "Branch1", LocalDateTime.of(2024, 7, 1, 16, 0, 0), LocalDateTime.of(2024, 7, 1, 20, 0, 0), Map.of("Manager", 1, "Storekeeper", 1), Arrays.asList("5", "6", "9"), Arrays.asList("5", "6"), Map.of("5", 1, "6", 4)),
                new ShiftDTO(4, "Branch3", LocalDateTime.of(2024, 7, 2, 8, 0, 0), LocalDateTime.of(2024, 7, 2, 12, 0, 0), Map.of("Manager", 1, "Cashier", 1), Arrays.asList("10", "1", "2"), Arrays.asList("10", "1"), Map.of("10", 1, "1", 2))
        );

                                                                                                                                                                                                                            // Map<String, Integer> requiredRoles,
                                                                                                                                                                                                                                                                                    //    List<String> availableWorkers,
                                                                                                                                                                                                                                                                                                                       //    List<String> assignedWorkers,
                                                                                                                                                                                                                                                                                                                                        //    Map<String, Integer> workerRoles
    }

    public void  insertMockData() {
        mockBranches.forEach(branchDTO -> {
            bf.setDTO(branchDTO);
            bf.getDTO().insert();
        });
        //mockRoles.forEach(wf::addNewRole);
        mockWorkers.forEach(workerDTO -> {
            wf.setDTO(workerDTO);
            wf.getDTO().insert();
        });
        //mockWorkers.forEach(w -> w. roles().forEach(r -> wf.addWorkerRole(w.id(), r)));
        mockShifts.forEach(shiftDTO -> {
            sf.setDTO(shiftDTO);
            sf.getDTO().insert();
        });
    }

}
