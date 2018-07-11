package com.sfu.cmpt470.DAO;

import com.sfu.cmpt470.database.DatabaseConnector;
import com.sfu.cmpt470.pojo.Order;
import jersey.repackaged.com.google.common.collect.ImmutableList;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class OrderDAOTest {
    @Spy
    private
    OrderDAO _dao;

    @Rule
    public final ExpectedException _exception = ExpectedException.none();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        _dao._db = mock(DatabaseConnector.class);
    }

    @SuppressWarnings("unused")
    private Object[] getOrderForVerification(){
        ArrayList<Object[]> result = new ArrayList<>();

        Order.Builder builder = Order.newBuilder();
        builder.set_time(OffsetDateTime.of(2018,5,2,11,1,1,0, ZoneOffset.ofHours(5)));

        result.add(new Object[]{builder.build(), ImmutableList.of("message:restaurant name must exist\n")});

        return result.toArray();
    }

    @Test
    @Parameters(method = "getOrderForVerification")
    public void verifyNewOrder_withOrder_shouldReturnNonEmptyStringWithMessage(Order order, List<String> error){
        ArrayList<String> result = _dao.verifyNewOrder(order);
        assert(result.containsAll(error));
    }
}
