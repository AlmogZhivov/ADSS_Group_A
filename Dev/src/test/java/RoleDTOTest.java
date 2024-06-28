import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

//public class RoleControllerTest {

//    private RoleController roleController;
//    private RoleDTO roleDTO;
//
//    @Before
//    public void setUp() {
//        roleController = new RoleController();
//        roleDTO = mock(RoleDTO.class);
//    }
//
//    @Test
//    public void testCreateRole() {
//        RoleDTO role = new RoleDTO(1, "Manager");
//        boolean result = roleController.createRole(role);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetRole() {
//        when(roleDTO.getValue()).thenReturn(1);
//        when(roleDTO.getName()).thenReturn("Manager");
//
//        RoleDTO role = roleController.getRole(1);
//
//        assertEquals(1, role.getValue());
//        assertEquals("Manager", role.getName());
//    }
//
//    @Test
//    public void testUpdateRole() {
//        RoleDTO role = new RoleDTO(1, "Manager");
//        roleController.createRole(role);
//
//        RoleDTO updatedRole = new RoleDTO(1, "HR Manager");
//        boolean result = roleController.updateRole(1, updatedRole);
//        assertTrue(result);
//
//        RoleDTO fetchedRole = roleController.getRole(1);
//        assertEquals("HR Manager", fetchedRole.getName());
//    }
//
//    @Test
//    public void testDeleteRole() {
//        RoleDTO role = new RoleDTO(1, "Manager");
//        roleController.createRole(role);
//
//        boolean result = roleController.deleteRole(1);
//        assertTrue(result);
//
//        RoleDTO fetchedRole = roleController.getRole(1);
//        assertNull(fetchedRole);
//    }
//
//    @Test
//    public void testLoadAllRoles() {
//        RoleDTO role1 = new RoleDTO(1, "Manager");
//        RoleDTO role2 = new RoleDTO(2, "Cashier");
//
//        roleController.createRole(role1);
//        roleController.createRole(role2);
//
//        List<RoleDTO> roles = roleController.loadAll();
//        assertEquals(2, roles.size());
//    }
//}
