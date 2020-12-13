package com.zyl.mapper;

import com.zyl.domain.User;
import com.zyl.interfaces.DynamicTable;
import com.zyl.interfaces.Dynamics;
import com.zyl.sqlprovider.UserProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.springframework.stereotype.Repository;

/**
 * @author zyl
 */
@Repository
@Dynamics(values = {@DynamicTable(tableName = "t_user", targetName = "t_user_aa")})
public interface UserMapper {

    /**
     * 保存用户
     *
     * @param user 用户信息
     * @return 新增数量
     */
    @InsertProvider(type = UserProvider.class, method = "saveSql")
    int save(User user);
}
