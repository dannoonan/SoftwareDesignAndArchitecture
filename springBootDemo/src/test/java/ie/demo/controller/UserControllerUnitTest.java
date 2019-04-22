package ie.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import ie.demo.domain.StandardAdmin;
import ie.demo.service.impl.AdminUserFactory;
import ie.demo.service.impl.UserServiceImpl;
import ie.util.MsgResponse;

import ie.util.StateCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    @Mock
    private StandardAdmin mockUser;

    @Mock
    private AdminUserFactory factory;

    @Mock
    private UserServiceImpl userServiceMock;

    @InjectMocks
    private UserController controller;

    private List<String> result = new ArrayList<>();


    @Before
    public void setUp() {
        result.add("1");
        result.add("1");
        result.add("test");
        result.add("email");
        result.add("0");
        result.add("1");
    }


    @Test
    public void insertUserFailure() {
        MsgResponse msgResponse = controller.insertUser("unittest","test", 12,
                3, "test@gmail.com");
        assertEquals(0, msgResponse.getCode());
    }

    @Test
    public void loginSuccess() {
        result.add(0,"" + StateCode.PROCESS_SUCCESS.getCode());
        when(userServiceMock.login("test","password")).thenReturn(result);
        MsgResponse msgResponse = controller.login("test","password");
        assertEquals(200, msgResponse.getCode());
    }

    @Test
    public void loginNotFound() {
        result.add(0,"" + StateCode.BAD_REQUEST.getCode());
        when(userServiceMock.login("test","password")).thenReturn(result);
        MsgResponse msgResponse = controller.login("test","password");
        assertEquals(404, msgResponse.getCode());
    }

    @Test
    public void loginFailure() {
        result.add(0,"" + StateCode.ERROR.getCode());
        when(userServiceMock.login("test","password")).thenReturn(result);
        MsgResponse msgResponse = controller.login("test","password");
        assertEquals(400, msgResponse.getCode());
    }

}
