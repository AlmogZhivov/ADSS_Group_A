import com.Superlee.HR.Backend.Business.BranchFacade;
import com.Superlee.HR.Backend.Business.ShiftFacade;
import com.Superlee.HR.Backend.Business.WorkerFacade;
import org.junit.Before;
import org.junit.Test;

public class DataTests {
    private final BranchFacade branchFacade = BranchFacade.getInstance();
    private final ShiftFacade shiftFacade = ShiftFacade.getInstance();
    private final WorkerFacade workerFacade = WorkerFacade.getInstance();

    @Before
    public void setUp() {
    }
}
