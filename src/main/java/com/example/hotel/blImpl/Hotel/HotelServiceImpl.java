package com.example.hotel.blImpl.Hotel;

import com.example.hotel.bl.CommentService;
import com.example.hotel.bl.Hotel.BizRegionService;
import com.example.hotel.bl.Hotel.HotelRoomService;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.Hotel.HotelSituationService;
import com.example.hotel.bl.Order.OrderService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel.bizregion;
import com.example.hotel.po.Hotel.hotel;
import com.example.hotel.po.Hotel.hoteledit;
import com.example.hotel.po.Hotel.hotelroom;
import com.example.hotel.po.User.user;
import com.example.hotel.util.ArrayHelper;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.TimeHelper;
import com.example.hotel.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    HotelMapper hotelMapper;
    @Autowired
    UserService userService;
    @Autowired
    HotelRoomService hotelRoomService;
    @Autowired
    CommentService commentService;
    @Autowired
    OrderService orderService;
    @Autowired
    BizRegionService bizRegionService;
    @Autowired
    HotelSituationService hotelSituationService;

    @Override
    public boolean addHotel(hotel hotel)
            throws ServiceException{
        checkPhoneNumber(hotel.getPhoneNum());
        if(findByName(hotel.getHotelName()) != null){
            throw new ServiceException("已经有重名的酒店了");
        }
        //检查是否存在对应商圈
        bizregion BizRegion = bizRegionService.findByName(hotel.getBizRegion());
        if(BizRegion == null){
            throw new ServiceException("没有找到对应商圈");
        }
        try{
            hotelMapper.save(hotel);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加酒店信息数据库失败");
        }
        hotel Hotel = findByName(hotel.getHotelName());
        hotelSituationService.createLeastSituation(Hotel.getId());
        return true;
    }

    @Override
    public Integer findByManagerId(Integer managerId)
            throws ServiceException{
        Integer hotelId = null;
        try{
            hotelId = hotelMapper.selectByManagerId(managerId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("根据管理员的编号查找酒店号数据库失败");
        }
        if(hotelId == null){
            throw new ServiceException("酒店工作人员还没有被绑定酒店");
        }
        return hotelId;
    }

    /**
     * 如果这个酒店有管理员了，就更新，如果没有，就加入
     * @param hotelId
     * @param hotelManagerId
     * @return
     */
    @Override
    public boolean addHotelManager(Integer hotelId, Integer hotelManagerId)
            throws ServiceException{
        try{
            hotelMapper.updateManager(hotelId, hotelManagerId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加酒店管理员数据库失败");
        }
        return true;
    }

    @Override
    public hotel findHotelByManagerId(Integer managerId) throws ServiceException {
        Integer hotelId = findByManagerId(managerId);
        hotel Hotel = findById(hotelId);
        return Hotel;
    }

    @Override
    public hotel findById(Integer hotelId)
            throws ServiceException{
        hotel hotel = null;
        try{
            hotel = hotelMapper.selectById(hotelId);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找酒店数据库失败");
        }
        if(hotel == null){
            throw new ServiceException("没有找到对应的酒店信息");
        }
        return hotel;
    }


    @Override
    public List<HotelVO> findHotelsInCondition(Long timeStart, Long timeEnd, ArrayList<String> star, Integer lowerPrice
            , Integer highPrice, ArrayList<String> roomType, Double rate, String name, ArrayList<String> serves)
            throws ServiceException{
        if(timeStart == null || timeEnd == null || timeStart > timeEnd){
            throw new ServiceException("入住时间或者退房时间有误");
        }
        List<HotelVO> hotels = new ArrayList<HotelVO>();
        if(name != null){
            hotel Hotel = findByName(name);
            if(Hotel == null){
                return null;
            }
            hotels.add(findDetails(Hotel.getId(), null));
        }else{
            hotels = findAllHotels(null);
        }
        List<HotelVO> hotelVOS = new ArrayList<HotelVO>();
        //进行筛选，替换为流
        if(hotels == null){
            return null;
        }
        for(HotelVO hotelVO: hotels){
            if(isValidHotel(hotelVO,timeStart,timeEnd,star,lowerPrice
                    ,highPrice,roomType,rate,serves)){
                hotelVOS.add(hotelVO);
            }
        }
        return hotelVOS;
    }

    @Override
    public List<HotelVO> findAllHotels(Integer userId)
            throws ServiceException{
        List<hotel> hotels = null;
        try{
            hotels = hotelMapper.selectAllHotel();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有酒店数据库失败");
        }
        ArrayList<HotelVO> hotelVOS = new ArrayList<>();
        for(hotel Hotel: hotels){
            HotelVO hotelVO;
            try{
                hotelVO = findDetails(Hotel.getId(), userId);
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
            hotelVOS.add(hotelVO);
        }
        return hotelVOS;
    }

    @Override
    public void editHotel(hoteledit hotelEdit) throws ServiceException {
        if(findByName(hotelEdit.getHotelName()) != null){
            throw new ServiceException("已经有重名的酒店了");
        }
        try{
            hotelMapper.updateHotel(hotelEdit.getHotelName(),hotelEdit.getHotelDescription(),
                    hotelEdit.getPhoneNum(),hotelEdit.getHotelServe(),hotelEdit.getPicture(),hotelEdit.getHotelId());
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新酒店数据库失败");
        }
    }

    @Override
    public HotelVO findDetails(Integer hotelId, Integer userId) throws ServiceException{
        hotel hotel = findById(hotelId);
        List<hotelroom> rooms = hotelRoomService.findByHotelId(hotelId);
        //检查是否订过
        boolean isOrdered = userId == null ? false :
                orderService.isOrdered(userId,hotelId);
        HotelVO hotelVO = new HotelVO(hotel, rooms, isOrdered);
        return hotelVO;
    }

    @Override
    public boolean updateRate(Integer hotelId) throws ServiceException{
        Double rate = commentService.getAvgRate(hotelId);
        try{
            hotelMapper.updateHotelRate(hotelId, rate);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("更新酒店评分失败");
        }
        return true;
    }

    @Override
    public List<Integer> findHotelIds() throws ServiceException {
        List<Integer> hotelIds = null;
        try{
            hotelIds = hotelMapper.getHotelIds();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("查找所有的酒店ID失败");
        }
        return hotelIds;
    }

    @Override
    public Integer getHotelNumber(String day){
        Integer hotelNumber = 0;
        try{
            hotelNumber = hotelMapper.getHotelNumber(day);
        }catch (Exception e){
            e.printStackTrace();
        }
        return hotelNumber;
    }

    /**
     * 根据酒店名称查找酒店
     * @param hotelName
     * @return
     */
    private hotel findByName(String hotelName){
        hotel Hotel = null;
        try{
            Hotel = hotelMapper.selectByName(hotelName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return Hotel;
    }

    /**
     * 检查酒店的电话格式的正确性
     * @param password
     * @throws ServiceException
     */
    private void checkPhoneNumber(String password) throws ServiceException {
        if(password.length() != 11 && password.length() != 8){
            throw new ServiceException("电话长度应该为8或者11位");
        }
        String passwordPattern = "\\d*";
        if(!Pattern.matches(passwordPattern,password)){
            throw new ServiceException("酒店电话应该全为数字");
        }
    }

    /**
     * 检查一个酒店是否满足所有的条件
     * @param hotelVO
     * @param timeStart
     * @param timeEnd
     * @param star
     * @param lowerPrice
     * @param highPrice
     * @param roomType
     * @param rate
     * @param serves 这里指的是酒店提供的服务
     * @return
     */
    private boolean isValidHotel(HotelVO hotelVO, Long timeStart, Long timeEnd,
                                 ArrayList<String> star, Integer lowerPrice, Integer highPrice,
                                 ArrayList<String> roomType, Double rate, ArrayList<String> serves){
        String[] date = TimeHelper.dateToString(LocalDateTime.now()).split("-");
        long today = TimeHelper.getTimeStamp(LocalDateTime.of(Integer.valueOf(date[0])
                ,Integer.valueOf(date[1]), Integer.valueOf(date[2]),0,0));
        int startIndex = (int) (timeStart - today) / (24 * 60 * 60 * 1000);
        int endIndex = (int) (timeEnd - today) / (24 * 60 * 60 * 1000);
        //检查评分
        if(rate != null && rate > hotelVO.getRate()){
            return false;
        }
        //检查评星
        if(star != null && !star.contains(hotelVO.getHotelStar().name())){
            return false;
        }
        //检查酒店的所有服务
        if(serves != null){
            if(hotelVO.getHotelServe() == null){
                return false;
            }
            ArrayList<String> hotelServes = ArrayHelper.StringToArray(hotelVO.getHotelServe());
            for(String serve: serves){
                if(!hotelServes.contains(serve)){
                    return false;
                }
            }
        }

        return isValidRooms(hotelVO.getRoom(), startIndex, endIndex, lowerPrice, highPrice, roomType);
    }

    /**
     * 检查一个酒店的所有房间中有没有一个满足所有的条件
     * @param hotelRooms
     * @param startIndex
     * @param endIndex
     * @param lowerPrice
     * @param highPrice
     * @param roomType
     * @return
     */
    private boolean isValidRooms(List<hotelroom> hotelRooms, Integer startIndex, Integer endIndex,
                                 Integer lowerPrice, Integer highPrice, ArrayList<String> roomType){
        if(hotelRooms == null){
            return false;
        }
        for(hotelroom HotelRoom: hotelRooms){
            if(isValidRoom(HotelRoom, startIndex, endIndex, lowerPrice, highPrice, roomType)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查特定一个房间是否满足所有条件
     * @param HotelRoom
     * @param startIndex
     * @param endIndex
     * @param lowerPrice
     * @param highPrice
     * @param roomType
     * @return
     */
    private boolean isValidRoom(hotelroom HotelRoom, Integer startIndex, Integer endIndex,
                                Integer lowerPrice, Integer highPrice, ArrayList<String> roomType){
        ArrayList<String> days = ArrayHelper.StringToArray(HotelRoom.getCurNum());
        //注意不能有一个非空
        if(lowerPrice != null && highPrice != null
                && !(lowerPrice <= HotelRoom.getPrice() && HotelRoom.getPrice() <= highPrice)){
            //价格不在这个区间范围内则不符合
            return false;
        }
        if(roomType != null && !roomType.contains(HotelRoom.getRoomType())){
            //房间类型不在查询的列表中则不符合
            return false;
        }
        for(int i = startIndex; i < endIndex && i < days.size(); i ++){
            if(Integer.valueOf(days.get(i)) <= 0){
                //只要有一天没有房间则不符合
                return false;
            }
        }
        return true;
    }
}
