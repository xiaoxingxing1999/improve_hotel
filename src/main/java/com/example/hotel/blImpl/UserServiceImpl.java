package com.example.hotel.blImpl;

import com.example.hotel.Form.Client.ClientVIPForm;
import com.example.hotel.Form.Hotel.ReplyForm;
import com.example.hotel.Form.User.ChangeInfoForm;
import com.example.hotel.Form.User.ChangePwdForm;
import com.example.hotel.Form.User.LoginForm;
import com.example.hotel.bl.CommentService;
import com.example.hotel.bl.Hotel.BizRegionService;
import com.example.hotel.bl.Hotel.HotelServeService;
import com.example.hotel.bl.Hotel.HotelService;
import com.example.hotel.bl.ReplyService;
import com.example.hotel.bl.Role.ClientService;
import com.example.hotel.bl.UserService;
import com.example.hotel.data.user.UserMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.enums.VIPType;
import com.example.hotel.po.Hotel.bizregion;
import com.example.hotel.po.Hotel.hotelserve;
import com.example.hotel.po.Order.comment;
import com.example.hotel.po.Order.reply;
import com.example.hotel.po.User.user;
import com.example.hotel.util.Response.ServiceException;
import com.example.hotel.util.UserHelper;
import com.example.hotel.util.password.MD5Encryption;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.LoginVO;
import com.example.hotel.vo.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @Author stormbroken
 * Create by 2020/05/25
 * @Version 1.0
 **/

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    BizRegionService bizRegionService;
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;
    @Autowired
    HotelService hotelService;
    @Autowired
    HotelServeService hotelServeService;

    private Integer LEVELS[] = {0, 200, 1500, 4500, 10800, 28800};

    @Override
    public boolean addUser(user user)
            throws ServiceException{
        if(findByEmail(user.getEmail()) != null){
            throw new ServiceException("???????????????????????????????????????");
        }
        //??????????????????????????????????????????
        isNameValid(user.getUserName(), user.getUserType());
        isPhoneNumberValid(user.getPhoneNumber());
        isPasswordValid(user.getPassword());

        //??????????????????
        user.setPassword(MD5Encryption.encrypt(user.getPassword()));
        try{
            userMapper.save(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("???????????????????????????");
        }
        return true;
    }

    @Override
    public LoginVO login(LoginForm loginForm)
            throws ServiceException{
        user user = findByEmail(loginForm.getEmail());
        if(user == null){
            throw new ServiceException("???????????????????????????");
        }
        if(!user.getPassword().equals(MD5Encryption.encrypt(loginForm.getPassword()))){
            throw new ServiceException("?????????????????????????????????");
        }
        //??????token
        String token= UUID.randomUUID().toString().replace("-","");
        LoginVO loginVO = new LoginVO(user.getUserName(),token);
        //????????????
        UserHelper.login(token,user);
        return loginVO;
    }

    @Override
    public boolean changePwd(ChangePwdForm changePwdForm)
            throws ServiceException{
        user user = UserHelper.getByToken(changePwdForm.getToken());
        if(!user.getPassword().equals(MD5Encryption.encrypt(changePwdForm.getOldPassword()))){
            throw new ServiceException("?????????????????????????????????");
        }
        //????????????????????????
        isPasswordValid(changePwdForm.getNewPassword());
        try{
            userMapper.updatePassWord(user.getId(),changePwdForm.getNewPassword());
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("??????????????????????????????");
        }
        UserHelper.logOut(changePwdForm.getToken());
        return true;
    }

    @Override
    public boolean updateInfo(ChangeInfoForm changeInfoForm)
            throws ServiceException{
        user user = UserHelper.getByToken(changeInfoForm.getToken());
        //?????????????????????????????????????????????
        isNameValid(changeInfoForm.getUserName(), user.getUserType());
        isPhoneNumberValid(changeInfoForm.getPhoneNumber());
        try{
            userMapper.updateUser(changeInfoForm.getUrl(),
                    changeInfoForm.getUserName(), changeInfoForm.getPhoneNumber(),user.getId());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public user getUserInfo(String token)
            throws ServiceException{
        user user = UserHelper.getByToken(token);
        return findById(user.getId());//??????????????????
    }

    @Override
    public user findByEmail(String email)
            throws ServiceException{
        user user = null;
        try{
            user = userMapper.findByEmail(email);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("??????Email???????????????????????????");
        }
        return user;
    }

    @Override
    public user findById(Integer userId)
            throws ServiceException{
        user user = null;
        try{
            user = userMapper.findById(userId).orElse(null);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("???????????????????????????????????????");
        }
        if(user == null){
            throw new ServiceException("????????????????????????");
        }
        return user;
    }

    @Override
    public List<bizregion> getBizRegions()
            throws ServiceException{
        return bizRegionService.findAllBizRegions();
    }

    @Override
    public CommentVO getOrderComments(String token, Integer orderId)
            throws ServiceException{
        UserHelper.getByToken(token);
        CommentVO commentVO = null;
        try{
            commentVO = commentService.findByOrder(orderId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return commentVO;
    }

    @Override
    public List<ReplyVO> getReplyReply(String token, Integer replyId, Integer page)
            throws ServiceException{
        UserHelper.getByToken(token);
        replyService.findById(replyId);
        return replyService.findByReply(replyId, page);
    }

    @Override
    public List<ReplyVO> getMyReply(String token, Integer page)
            throws ServiceException{
        user user = UserHelper.getByToken(token);
        return replyService.findByUser(user.getId(), page);
    }

    @Override
    public boolean addReply(ReplyForm replyForm)
            throws ServiceException{
        return replyService.addReply(replyForm);
    }

    @Override
    public List<ReplyVO> findByComment(Integer commentId, Integer page)
            throws ServiceException {
        return replyService.findByComment(commentId, page);
    }

    /**
     * ????????????????????????
     * @param userId
     * @param credit ???????????????????????????
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateCredit(Integer userId, Double credit)
            throws ServiceException{
        try{
            userMapper.updateCredit(userId, credit);
            if(credit >= 0){
                //?????????????????????
                userMapper.updateTotalCredit(userId, credit);
                user User = findById(userId);
                if(User == null){
                    return false;
                }else if(!User.getVipType().equals(VIPType.ENTERPRISE)){
                    int level = getUserVIPLevel(User.getTotalcredit() + credit);
                    userMapper.updateVIPLevel(userId, level);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("???????????????????????????");
        }
        return true;
    }

    @Override
    public List<user> getHotelManager() throws ServiceException {
        List<user> hotelManagers = null;
        try{
            hotelManagers = userMapper.getAllManagers();
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("????????????????????????????????????");
        }
        return hotelManagers;
    }

    @Override
    public List<CommentVO> getHotelComments(String token, Integer hotelId, Integer page) throws ServiceException {
        UserHelper.getByToken(token);
        hotelService.findById(hotelId);
        List<CommentVO> comments = commentService.findByHotelId(hotelId, page);
        return comments;
    }

    @Override
    public boolean updateVIP(ClientVIPForm clientVIPForm) throws ServiceException {
        user user = UserHelper.checkRole(clientVIPForm.getToken(), UserType.Client);
        Integer userId = user.getId();
        if(!VIPType.isVip(user.getVipType())){
            throw new ServiceException("??????????????????");
        }
        if(user.getVipInfo() != null && !user.getVipInfo().equals("") && !user.getVipType().equals(VIPType.ENTERPRISE)){
            throw new ServiceException("????????????????????????????????????????????????");
        }
        try{
            if(clientVIPForm.getVipType().equals(VIPType.ENTERPRISE)){
                userMapper.updateVIPEnterprise(userId, clientVIPForm.getVipType().ordinal(), clientVIPForm.getVipInfo());
            }else{
                userMapper.updateVIP(userId,clientVIPForm.getVipInfo());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("???????????????VIP?????????????????????");
        }
        return true;
    }

    @Override
    public List<hotelserve> getAllServe(String token) throws ServiceException {
        UserHelper.getByToken(token);
        return hotelServeService.findAllServes();
    }

    /**
     * ???????????????????????????11????????????
     * @param phoneNumber
     * @return
     * @throws ServiceException
     */
    private boolean isPhoneNumberValid(String phoneNumber) throws ServiceException{
        if(phoneNumber.length() != 11){
            throw new ServiceException("?????????????????????");
        }
        String phonePattern = "\\d*";
        if(!Pattern.matches(phonePattern, phoneNumber)){
            throw new ServiceException("???????????????????????????");
        }
        return true;
    }

    /**
     * ?????????????????????6-20?????????????????????
     * @param password
     * @return
     * @throws ServiceException
     */
    private boolean isPasswordValid(String password) throws ServiceException{
        if(!(6 <= password.length() &&  password.length() <= 20)){
            throw new ServiceException("?????????????????????6-20???");
        }
        String passwordPattern = "[a-z0-9A-Z]*";
        if(!Pattern.matches(passwordPattern, password)){
            throw new ServiceException("????????????????????????????????????");
        }
        return true;
    }

    /**
     * ?????????????????????????????????????????????
     * @param name
     * @param userType
     * @return
     * @throws ServiceException
     */
    private boolean isNameValid(String name, UserType userType) throws ServiceException{
        if(name.length() < 2){
            throw new ServiceException("?????????????????????2??????????????????");
        }
        if( !userType.equals(UserType.Client)){
            char[] nameChars = name.toCharArray();
            for(char c : nameChars){
                if (c < 0x4E00 || c > 0x9FA5){
                    throw new ServiceException("????????????????????????");
                }
            }
        }
        return true;
    }

    /**
     * ?????????????????????????????????
     * @param credit
     * @return
     */
    private int getUserVIPLevel(double credit){
        for(int i = 0 ; i < LEVELS.length - 1; i ++){
            if(LEVELS[i] <= credit && credit < LEVELS[i + 1]){
                return i;
            }
        }
        return LEVELS.length - 1;
    }


}
