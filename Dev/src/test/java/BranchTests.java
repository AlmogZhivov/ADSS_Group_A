import com.Superlee.HR.Backend.Business.BranchFacade;
import com.Superlee.HR.Backend.Business.WorkerFacade;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BranchTests {
    private final BranchFacade branchFacade = BranchFacade.getInstance();
    private final WorkerFacade workerFacade = WorkerFacade.getInstance();

    @Before
    public void setUp() {
        branchFacade.reset(0xC0FFEE);
        workerFacade.reset(0xC0FFEE);
    }

    void addWorker() {
        workerFacade.addNewWorker("0", "Super", "Lee");
    }

    void addRole() {
        workerFacade.addRole("0", "Manager");
    }

    boolean addBranch() {
        return branchFacade.addBranch("Hakol BeHinam", "Eliezer Kaplan 1, Jerusalem", "0");
    }

    @Test
    public void testAddNewBranchSuccess() {
        int startSize = branchFacade.getAllBranches().size();
        addWorker();
        addRole();
        boolean result = addBranch();
        assertTrue(result);
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize + 1, endSize);
    }

    @Test
    public void testAddNewBranchWithExistingName() {
        addWorker();
        addRole();
        addBranch();
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, this::addBranch);
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithNullName() {
        addWorker();
        addRole();
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.addBranch(null, "Eliezer Kaplan 1, Jerusalem", "0"));
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithNullAddress() {
        addWorker();
        addRole();
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.addBranch("Hakol BeHinam", null, "0"));
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithNullManager() {
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.addBranch("Hakol BeHinam", "Eliezer Kaplan 1, Jerusalem", null));
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithEmptyName() {
        addWorker();
        addRole();
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.addBranch("", "Eliezer Kaplan 1, Jerusalem", "0"));
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithEmptyAddress() {
        addWorker();
        addRole();
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.addBranch("Hakol BeHinam", "", "0"));
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithEmptyManager() {
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.addBranch("Hakol BeHinam", "Eliezer Kaplan 1, Jerusalem", ""));
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithNonExistingManager() {
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(NoSuchElementException.class, this::addBranch);
        int endSize = branchFacade.getAllBranches().size();
        assertEquals(startSize, endSize);
    }

    @Test
    public void testAddNewBranchWithNonManager() {
        addWorker();
        int startSize = branchFacade.getAllBranches().size();
        assertThrows(IllegalArgumentException.class, this::addBranch);
    }

    @Test
    public void testGetBranchSuccess() {
        addWorker();
        addRole();
        addBranch();
        assertNotNull(branchFacade.getBranch("Hakol BeHinam"));
    }

    @Test
    public void testGetBranchWithNonExistingName() {
        assertThrows(NoSuchElementException.class, () -> branchFacade.getBranch("Hakol BeHinam"));
    }

    @Test
    public void testUpdateManagerSuccess() {
        addWorker();
        addRole();
        addBranch();
        workerFacade.addNewWorker("1", "Mega", "Lee");
        workerFacade.addRole("1", "Manager");
        boolean result = branchFacade.updateManager("Hakol BeHinam", "1");
        assertEquals("1", branchFacade.getBranch("Hakol BeHinam").manager());
    }

    @Test
    public void testUpdateManagerWithNonExistingWorker() {
        addWorker();
        addRole();
        addBranch();
        assertThrows(NoSuchElementException.class, () -> branchFacade.updateManager("Hakol BeHinam", "1"));
    }

    @Test
    public void testUpdateManagerWithNonManager() {
        addWorker();
        addRole();
        addBranch();
        workerFacade.addNewWorker("1", "Mega", "Lee");
        assertThrows(IllegalArgumentException.class, () -> branchFacade.updateManager("Hakol BeHinam", "1"));
        assertNotEquals("1", branchFacade.getBranch("Hakol BeHinam").manager());
    }

    @Test
    public void testUpdateManagerWithNonExistingBranch() {
        addWorker();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.updateManager("Hakol BeHinam", "0"));
    }

    @Test
    public void testUpdateManagerWithNullBranch() {
        addWorker();
        assertThrows(IllegalArgumentException.class, () -> branchFacade.updateManager(null, "0"));
    }
}
