package com.itec.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.itec.pojo.User;
import com.itec.pojo.vo.UserQuaryCondition;

@Mapper
public interface UserMapper {

	// 登录
	@Select("select * from user_tbl where USER_MAILADDRESS =#{userMailaddress} and USER_PWD =#{userPwd} and USER_DEL_FLG=0")
	User login(String userMailaddress, String userPwd);

	// 通过Username、邮箱、权限、用户锁查找信息
	List<User> selectUserInfo(@Param("startIndex") Integer startIndex, @Param("limit") Integer limit,
			@Param("userQuaryCondition") UserQuaryCondition userQuaryCondition);

	// 查询条数
	Integer selectUserNum(@Param("userQuaryCondition") UserQuaryCondition userQuaryCondition);

	// 通过邮箱查找信息
	@Select("select * from user_tbl where USER_MAILADDRESS =#{userMailaddress}  and USER_DEL_FLG=0")
	User selectUserInfoByMail(String userMailaddress);

	// 逻辑删除
	@Delete("update user_tbl set USER_DEL_FLG = 1 where USER_DEL_FLG = 0 and USER_ID = #{userId}")
	int deleteUserById(String userId);

	// 添加
	int insertUser(User user);

	// 通过id查找
	@Select("select * from user_tbl where USER_ID =#{userId}  and USER_DEL_FLG=0")
	User selectUserById(String userId);

	// 修改
	int updateUserByUserId(User user);

	// 修改密码
	@Update("update user_tbl set USER_PWD=#{userPwd},USER_EX_PW_DAY = #{userExPwDay},MANAGER_UPD_USR = #{managerUpdUsr},MANAGER_UPD_DT = #{managerUpdDt} where USER_ID =#{userId}  and USER_DEL_FLG=0")
	int updatePwdByUserId(User user);

	// 锁定账户
	@Update("update user_tbl set USER_LOCK_FLG = 1 where USER_MAILADDRESS =#{userMailaddress} and USER_DEL_FLG=0")
	int updateUserLockFlg(String userMailaddress);
}