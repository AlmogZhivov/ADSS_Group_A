//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Date;
//
//public class ShiftControllerTest {
//
//    private ShiftController shiftController;
//    private ShiftDTO shiftDTO;
//
//    @Before
//    public void setUp() {
//        shiftController = new ShiftController();
//        shiftDTO = mock(ShiftDTO.class);
//    }
//
//    @Test
//    public void testCreateShift() {
//        ShiftDTO shift = new ShiftDTO(1, new Date(), new Date(), "Branch1");
//        boolean result = shiftController.createShift(shift);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetShift() {
//        when(shiftDTO.getId()).thenReturn(1);
//        when(shiftDTO.getStartTime()).thenReturn(new Date());
//        when(shiftDTO.getEndTime()).thenReturn(new Date());
//        when(shiftDTO.getBranch()).thenReturn("Branch1");
//
//        ShiftDTO shift = shiftController.getShift(1);
//
//        assertEquals(1, shift.getId());
//        assertEquals("Branch1", shift.getBranch());
//    }
//
//    @Test
//    public void testUpdateShift() {
//        ShiftDTO shift = new ShiftDTO(1, new Date(), new Date(), "Branch1");
//        shiftController.createShift(shift);
//
//        ShiftDTO updatedShift = new ShiftDTO(1, new Date(), new Date(), "Branch2");
//        boolean result = shiftController.updateShift(1, updatedShift);
//        assertTrue(result);
//
//        ShiftDTO fetchedShift = shiftController.getShift(1);
//        assertEquals("Branch2", fetchedShift.getBranch());
//    }
//
//    @Test
//    public void testDeleteShift() {
//        ShiftDTO shift = new ShiftDTO(1, new Date(), new Date(), "Branch1");
//        shiftController.createShift(shift);
//
//        boolean result = shiftController.deleteShift(1);
//        assertTrue(result);
//
//        ShiftDTO fetchedShift = shiftController.getShift(1);
//        assertNull(fetchedShift);
//    }
//
//    @Test
//    public void testLoadAllShifts() {
//        ShiftDTO shift1 = new ShiftDTO(1, new Date(), new Date(), "Branch1");
//        ShiftDTO shift2 = new ShiftDTO(2, new Date(), new Date(), "Branch2");
//
//        shiftController.createShift(shift1);
//        shiftController.createShift(shift2);
//
//        List<ShiftDTO> shifts = shiftController.loadAll();
//        assertEquals(2, shifts.size());
//    }
//}
