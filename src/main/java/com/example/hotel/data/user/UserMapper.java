package com.example.hotel.data.user;

import com.example.hotel.po.User.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.List;


public interface UserMapper extends JpaRepository<user, Integer> {

    @Query(value = "Select * from user_new4 where email = :email", nativeQuery = true)
    user findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "Update user_new4 " +
            "set url = :url, user_name = :userName, phone_number =:phoneNumber " +
            "where id = :id", nativeQuery = true)
    int updateUser(String url, String userName, String phoneNumber, Integer id);

    @Transactional
    @Modifying
    @Query(value = "Update user_new4 " +
            "set password = :password where id = :id", nativeQuery = true)
    int updatePassWord(Integer id, String password);

    /*
     * 酒店用户适用
     */
    @Transactional
    @Modifying
    @Query(value = "Update user_new4 " +
            "set credit = credit + :credit " +
            "where id = :id", nativeQuery = true)
    int updateCredit(Integer id, double credit);

    @Transactional
    @Modifying
    @Query(value = "Update `user_new4` " +
            "set totalcredit = totalcredit + :credit " +
            "where id = :id", nativeQuery = true)
    int updateTotalCredit(Integer id, double credit);

    @Transactional
    @Modifying
    @Query(value = "Update user_new4 " +
            "set vip_info = :vipInfo " +
            "where id = :id", nativeQuery = true)
    int updateVIP(Integer id, String vipInfo);

    @Transactional
    @Modifying
    @Query(value = "Update user_new4 " +
            "set vip_type = :vipType, vip_info = :vipInfo " +
            "where id = :id", nativeQuery = true)
    int updateVIPEnterprise(Integer id,Integer vipType, String vipInfo);

    @Transactional
    @Modifying
    @Query(value = "Update user_new4 " +
            "set vip_type = :vipType " +
            "where id = :id", nativeQuery = true)
    int updateVIPLevel(Integer id, Integer vipType);

    /*
     * 酒店管理员适用
     */

    @Query(value = "select * from user_new4 " +
            "where user_type='1'", nativeQuery = true)
    List<user> getAllManagers();
}
