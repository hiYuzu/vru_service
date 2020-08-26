package com.tcb.vru_service.dao;

import com.tcb.vru_service.pojo.UtilFiledDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 监测领域DAO
 */
@Mapper
public interface FieldDao {

    /**
     * 查询监测领域个数
     *
     * @param filedDO
     * @return
     */
    int countFiled(@Param("filedDO") UtilFiledDO filedDO);

    /**
     * 查询监测领域数据
     *
     * @param filedDO
     * @return
     */
    List<UtilFiledDO> listFiled(@Param("filedDO") UtilFiledDO filedDO);

    /**
     * 插入监测领域数据
     *
     * @param filedDO
     * @return
     */
    int insertField(@Param("filedDO") UtilFiledDO filedDO);

    /**
     * 更新监测领域数据
     *
     * @param filedDO
     * @return
     */
    int updateField(@Param("filedDO") UtilFiledDO filedDO);

    /**
     * 删除监测设备数据
     *
     * @param listFieldId
     * @return
     */
    int deleteListField(@Param("listFieldId") List<Integer> listFieldId);

}
