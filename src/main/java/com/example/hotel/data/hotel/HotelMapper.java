package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel.hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface HotelMapper extends JpaRepository<hotel, Integer> {

    @Query(value = "Select id from hotel_new4 where manager_id = :managerId limit 1", nativeQuery = true)
    Integer selectByManagerId(Integer managerId);

    @Query(value = "Select * from hotel_new4 where id = :id limit 1", nativeQuery = true)
    hotel selectById(Integer id);
    @Query(value = "Select * from hotel_new4", nativeQuery = true)
    List<hotel> selectAllHotel();

    @Transactional
    @Modifying
    @Query(value = "Update hotel_new4 set hotel_name = :hotelName, hotel_description = :hotelDescription," +
            "phone_num = :phoneNum, hotel_serve = :hotelServe, picture = :picture " +
            "where id = :id", nativeQuery = true)
    int updateHotel(String hotelName, String hotelDescription,String phoneNum,
                        String hotelServe, String picture, Integer id);
    @Transactional
    @Modifying
    @Query(value = "Update hotel_new4 set manager_id = :managerId where id = :id", nativeQuery = true)
    int updateManager(int id, int managerId);

    @Transactional
    @Modifying
    @Query(value = "Update hotel_new4 set rate = :avgRate where id = :id", nativeQuery = true)
    int updateHotelRate(int id, double avgRate);

    @Query(value = "Select * from hotel_new4 where hotel_name = :hotelName", nativeQuery = true)
    hotel selectByName(String hotelName);

    @Query(value = "Select id from hotel_new4", nativeQuery = true)
    List<Integer> getHotelIds();

    @Query(value = "SELECT count(id) FROM hotel_new4 where date_format(create_time, '%Y-%m-%d') = :day"
            , nativeQuery = true)
    Integer getHotelNumber(String day);
}
