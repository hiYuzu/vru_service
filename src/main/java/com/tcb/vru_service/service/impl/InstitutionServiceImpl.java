package com.tcb.vru_service.service.impl;
import com.tcb.vru_service.dao.InstitutionDao;
import com.tcb.vru_service.service.IInstitutionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author hiYuzu
 * @version V1.0
 * @date 2020/8/31 11:41
 */
@Service("institutionService")
public class InstitutionServiceImpl implements IInstitutionService {
    @Resource
    private InstitutionDao institutionDao;

    @Override
    public List<Map> getInstitutionHead() {
        return institutionDao.getInstitutionHead();
    }
}
