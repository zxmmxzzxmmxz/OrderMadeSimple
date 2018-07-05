package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.Util.LoginException;
import com.sfu.cmpt470.Util.StringUtil;
import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.database.RowMapper.RowMapper;
import com.sfu.cmpt470.pojo.SessionToken;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class LoginDAOTest {
    @Spy
    private LoginDAO _loginDAO;
    @Rule
    public final ExpectedException _exception = ExpectedException.none();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        _loginDAO._db = mock(DatabaseConnector.class);
    }

    @SuppressWarnings("unused")
    private Object[] getWrongLoginInformation(){
        return new Object[]{
                new Object[]{"username","matchingPassword","matchingPassword1"},
                new Object[]{"anotherUser","somePassword","wrongPassword"}
        };
    }

    @Test
    @Parameters(method = "getWrongLoginInformation")
    public void login_passwordDoesNotMatch_shouldThrowException(String userName,
                                                                String storedPassword,
                                                                String passwordToVerify) throws SQLException, LoginException {
        String wrongPassword = StringUtil.SHA1Hash(passwordToVerify);
        String hashedPassword = StringUtil.SHA1Hash(storedPassword);
        //noinspection unchecked
        doReturn(hashedPassword).when(_loginDAO._db).queryOneRecord(any(RowMapper.class));

        _exception.expectMessage("password or username is incorrect!");

        _loginDAO.login(userName,wrongPassword);
    }

    @SuppressWarnings("unused")
    private Object[] getCorrectLoginInformation(){
        return new Object[]{
                new Object[]{"username","matchingPassword","matchingPassword"},
                new Object[]{"anotherUser","somePassword","somePassword"}
        };
    }

    @Test
    @Parameters(method = "getCorrectLoginInformation")
    public void login_passwordMatches_shouldReturnToken(String userName,
                                                                String storedPassword,
                                                                String passwordToVerify) throws SQLException, LoginException {
        String wrongPassword = StringUtil.SHA1Hash(passwordToVerify);
        String hashedPassword = StringUtil.SHA1Hash(storedPassword);
        //noinspection unchecked
        doReturn(hashedPassword).when(_loginDAO._db).queryOneRecord(any(RowMapper.class));

        SessionToken token = _loginDAO.login(userName, wrongPassword);
        assert(token.getToken() != null);
        assert (token.getUserName().equals(userName));
    }

    @Test
    public void login_sqlProblem_shouldThrowException() throws SQLException, LoginException {
        doThrow(new SQLException("")).when(_loginDAO._db).supplyQuery(any(String.class));

        _exception.expect(LoginException.class);
        _exception.expectMessage("password or username is incorrect!");

        _loginDAO.login("aaa", "ha");
    }
}
