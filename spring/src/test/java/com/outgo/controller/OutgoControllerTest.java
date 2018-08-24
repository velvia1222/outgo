package com.outgo.controller;

import com.outgo.OutgoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest(SpringApplication.class)
@PowerMockIgnore({"javax.management.*"})
@SpringBootTest
public class OutgoControllerTest {

    @Test(expected = RuntimeException.class)
    public void testSearchOutgoTest() throws Exception {
        DefaultApplicationArguments defaultApplicationArguments = mock(DefaultApplicationArguments.class);
        when(defaultApplicationArguments.getSourceArgs()).thenThrow(new RuntimeException("errrrrrrrrrrrrrrrr!"));
        PowerMockito.whenNew(DefaultApplicationArguments.class).withAnyArguments().thenReturn(defaultApplicationArguments);
        OutgoApplication.main(new String[]{});
    }
}
