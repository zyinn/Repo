package com.finruntech.frt.fits.pledge.service;

import com.finruntech.frt.fits.pledge.repository.FitsRepoPldgMgtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yinan.zhang on 2018/1/11.
 */
@Service
public class FitsRepoPldgMgtService<T> {
    @Autowired
    private FitsRepoPldgMgtMapper repoPldgMgtMapper;
    @Transactional
    public int saveRepoPldgMgtEntity(List<T> list) {
        return repoPldgMgtMapper.saveRepoPldgMgtEntity(list);
    }

    public List<T> findRepoPldgMgtEntity(T t) {
        return repoPldgMgtMapper.findRepoPldgMgtEntity(t);
    }
    public List<T> findRepoPldgSMgt(T t) {
        return repoPldgMgtMapper.findRepoPldgSMgt(t);
    }

    public List<T> findPldgMgtEntity(T t) {
        return repoPldgMgtMapper.findPldgMgtEntity(t);
    }

    public int deleteRepoPldgMgtEntity(T t) {
        return repoPldgMgtMapper.deleteRepoPldgMgtEntity(t);
    }
}
