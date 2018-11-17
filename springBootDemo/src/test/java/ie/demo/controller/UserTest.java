package ie.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import ie.demo.domain.User;
import ie.demo.service.UserFactory;
import ie.demo.service.impl.UserServiceImpl;

import ie.response.MsgResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Mock
    private UserFactory userFactoryMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @InjectMocks
    private UserController controller;

    @Test
    public void insertUserSuccess() {
        User mockUser = new User();
        when(userFactoryMock.createUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString())).thenReturn(mockUser);
        when(userServiceMock.register(mockUser)).thenReturn(1);
        MsgResponse msgResponse = controller.insertUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString());
        assertEquals(200, msgResponse.getCode());
    }

    @Test
    public void insertUserConflict() {
        User mockUser = new User();
        when(userFactoryMock.createUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString())).thenReturn(mockUser);
        when(userServiceMock.register(mockUser)).thenReturn(409);
        MsgResponse msgResponse = controller.insertUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString());
        assertEquals(409, msgResponse.getCode());
    }

    @Test
    public void insertUserFailure() {
        User mockUser = new User();
        when(userFactoryMock.createUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString())).thenReturn(mockUser);
        when(userServiceMock.register(mockUser)).thenReturn(400);
        MsgResponse msgResponse = controller.insertUser(Mockito.anyString(),Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyInt(), Mockito.anyString());
        assertEquals(400, msgResponse.getCode());
    }

    @Test
    public void loginSuccess() {
        when(userServiceMock.login(Mockito.anyString(),Mockito.anyString())).thenReturn(200);
        MsgResponse msgResponse = controller.login(Mockito.anyString(),Mockito.anyString());
        assertEquals(200, msgResponse.getCode());
    }

    @Test
    public void loginNotFound() {
        when(userServiceMock.login(Mockito.anyString(),Mockito.anyString())).thenReturn(404);
        MsgResponse msgResponse = controller.login(Mockito.anyString(),Mockito.anyString());
        assertEquals(404, msgResponse.getCode());
    }

    @Test
    public void loginFailure() {
        when(userServiceMock.login(Mockito.anyString(),Mockito.anyString())).thenReturn(400);
        MsgResponse msgResponse = controller.login(Mockito.anyString(),Mockito.anyString());
        assertEquals(400, msgResponse.getCode());
    }

}
