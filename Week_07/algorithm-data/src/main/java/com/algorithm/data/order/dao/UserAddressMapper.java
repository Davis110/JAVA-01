package com.algorithm.data.order.dao;

import com.algorithm.data.order.bean.UserAddress;
import com.algorithm.data.order.bean.UserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    long countByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int deleteByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long addressId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int insert(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int insertSelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    UserAddress selectOneByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    UserAddress selectOneByExampleSelective(@Param("example") UserAddressExample example, @Param("selective") UserAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<UserAddress> selectByExampleSelective(@Param("example") UserAddressExample example, @Param("selective") UserAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    List<UserAddress> selectByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    UserAddress selectByPrimaryKeySelective(@Param("addressId") Long addressId, @Param("selective") UserAddress.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    UserAddress selectByPrimaryKey(Long addressId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<UserAddress> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_address_0
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<UserAddress> list, @Param("selective") UserAddress.Column ... selective);
}