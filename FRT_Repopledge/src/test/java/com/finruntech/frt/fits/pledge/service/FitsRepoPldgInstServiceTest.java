package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.repository.SystemStatusMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitsRepoPldgInstServiceTest {

    @Autowired
    private FitsRepoPldgInstService fitsRepoPldgInstService;

    @Test
    public void repoTermLimitDays() throws Exception {
        String repoTermLimitDays = fitsRepoPldgInstService.repoTermLimitDays();
        System.out.println("repoTermLimitDays:" + repoTermLimitDays);
    }

}