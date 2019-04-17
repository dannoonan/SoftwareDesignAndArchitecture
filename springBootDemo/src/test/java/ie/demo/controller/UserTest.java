package ie.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import ie.demo.domain.StandardAdmin;
import ie.demo.service.AbstractUserFactory;
import ie.demo.service.impl.FactoryProvider;
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
public class UserTest {

    @Mock
    private AbstractUserFactory factory;

    @Mock
    private FactoryProvider fp;

    @Mock
    private UserServiceImpl userServiceMock;

    @Mock
    private StandardAdmin mockUser;

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


//    @Test
//    public void insertUserSuccess() {
////        User mockUser = new StandardAdmin(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
////                Mockito.anyInt(), Mockito.anyString());
//        when(fp.getFactory(1)).thenReturn(new AdminUserFactory());
//        when(factory.createUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
//                Mockito.anyInt(), Mockito.anyString())).thenReturn(mockUser);
////        when(factory.createUser("unittest","test", 12,
////                1, "test@gmail.com")).thenReturn(mockUser);
//        when(userServiceMock.register(mockUser)).thenReturn(1);
//        MsgResponse msgResponse = controller.insertUser("unittest","test", 12,
//                1, "test@gmail.com");
//        assertEquals(200, msgResponse.getCode());
//    }
//
//    @Test
//    public void insertUserConflict() {
//        when(factory.createUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
//                Mockito.anyInt(), Mockito.anyString())).thenReturn(mockUser);
//        when(userServiceMock.register(mockUser)).thenReturn(409);
//        MsgResponse msgResponse = controller.insertUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
//                Mockito.anyInt(), Mockito.anyString());
//        assertEquals(409, msgResponse.getCode());
//    }
//
//    @Test
//    public void insertUserFailure() {
//        when(factory.createUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
//                Mockito.anyInt(), Mockito.anyString())).thenReturn(mockUser);
//        when(userServiceMock.register(mockUser)).thenReturn(400);
//        MsgResponse msgResponse = controller.insertUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
//                Mockito.anyInt(), Mockito.anyString());
//        assertEquals(400, msgResponse.getCode());
//    }

    @Test
    public void loginSuccess() {
        result.add(0,"" + StateCode.PROCESS_SUCCESS.getCode());
        when(userServiceMock.login("test","password")).thenReturn(result);
        MsgResponse msgResponse = controller.login("test","password");
        assertEquals(200, msgResponse.getCode());
    }

    @Test
    public void loginNotFound() {
        result.add(0,"" + StateCode.USER_NOT_FOUND.getCode());
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
