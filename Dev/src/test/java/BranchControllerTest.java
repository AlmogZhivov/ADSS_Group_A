import static org.junit.Assert.*;


import com.Superlee.HR.Backend.DataAccess.BranchController;
import com.Superlee.HR.Backend.DataAccess.BranchDTO;
import org.junit.Before;
import org.junit.Test;

public class BranchControllerTest {

    private BranchController branchController;
    private BranchDTO mockBranchDTO;

    @Before
    public void setUp() {
        branchController = new BranchController();
        mockBranchDTO = new BranchDTO("MockBranch", "123 Mock Sr", "MockManager");
    }

//    @Test
//    public void testCreateBranch() {
//        BranchDTO branch = new BranchDTO("Branch1", "123 Main St", "Manager1");
//        boolean result = branchController.createBranch(branch);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetBranch() {
//        when(mockBranchDTO.getName()).thenReturn("Branch1");
//        when(mockBranchDTO.getAddress()).thenReturn("123 Main St");
//        when(mockBranchDTO.getManager()).thenReturn("Manager1");
//
//        BranchDTO branch = branchController.getBranch("Branch1");
//
//        assertEquals("Branch1", branch.getName());
//        assertEquals("123 Main St", branch.getAddress());
//        assertEquals("Manager1", branch.getManager());
//    }
//
//    @Test
//    public void testUpdateBranch() {
//        BranchDTO branch = new BranchDTO("Branch1", "123 Main St", "Manager1");
//        branchController.createBranch(branch);
//
//        BranchDTO updatedBranch = new BranchDTO("Branch1", "456 Elm St", "Manager2");
//        boolean result = branchController.updateBranch("Branch1", updatedBranch);
//        assertTrue(result);
//
//        BranchDTO fetchedBranch = branchController.getBranch("Branch1");
//        assertEquals("456 Elm St", fetchedBranch.getAddress());
//        assertEquals("Manager2", fetchedBranch.getManager());
//    }
//
//    @Test
//    public void testDeleteBranch() {
//        BranchDTO branch = new BranchDTO("Branch1", "123 Main St", "Manager1");
//        branchController.createBranch(branch);
//
//        boolean result = branchController.deleteBranch("Branch1");
//        assertTrue(result);
//
//        BranchDTO fetchedBranch = branchController.getBranch("Branch1");
//        assertNull(fetchedBranch);
//    }
//
//    @Test
//    public void testLoadAllBranches() {
//        BranchDTO branch1 = new BranchDTO("Branch1", "123 Main St", "Manager1");
//        BranchDTO branch2 = new BranchDTO("Branch2", "456 Oak St", "Manager2");
//
//        branchController.createBranch(branch1);
//        branchController.createBranch(branch2);
//
//        List<BranchDTO> branches = branchController.loadAll();
//        assertEquals(2, branches.size());
//    }
}
