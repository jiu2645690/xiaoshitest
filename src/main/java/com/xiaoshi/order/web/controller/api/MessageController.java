package com.xiaoshi.order.web.controller.api;

import com.xiaoshi.order.constant.OrderCode;
import com.xiaoshi.order.exception.OrderException;
import com.xiaoshi.order.pojo.dto.MessageInfo;
import com.xiaoshi.order.pojo.entity.*;
import com.xiaoshi.order.service.*;
import com.xiaoshi.order.util.FileUtils;
import com.xiaoshi.order.util.ParameterCalibrationUtil;
import com.xiaoshi.order.util.ResultUtil;
import com.xiaoshi.order.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.xiaoshi.order.constant.OrderCode.INFO_NOT_EXISTS;
import static com.xiaoshi.order.constant.OrderCode.PARM_NOT_EMPTY;
import static com.xiaoshi.order.constant.OrderCode.YZM_NOT_EQUAL;

/**
 * 商店Controller
 */
@RestController
@RequestMapping("/v1")
@EnableTransactionManagement
public class MessageController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private FeedbackPictureAssociationService feedbackPictureAssociationService;
    @Autowired
    private MessageDetailService messageDetailService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private ReviewPictureAssociationService reviewPictureAssociationService;
    private static String VERRIFICATIONCODE = "VERRIFICATIONCODE";

    /**
     * 编号：7-1-001,信息中心统计
     */
    @GetMapping("/message/statistics")
    public ResponseEntity messageStatistics(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("amount:"+messageService.getMyMessageAmount(token));
    }

    /**
     * 编号：7-1-002,统计反馈信息
     */
    @GetMapping("/messge/feedback")
    public ResponseEntity messageFeedback(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("amount:"+messageService.getMessageFeedbackAmount(token));
    }

    /**
     * 编号：7-0-003, 用户和商家对话列表
     */
    @GetMapping("/message/conversation")
    public ResponseEntity messageConversation(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+messageService.messageConversation(token));
    }

    /**
     * 编号：7-1-004,统计反馈信息
     */
    @GetMapping("/backmessge/feedback")
    public ResponseEntity backMessageFeedback(@RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        return ResultUtil.success("list:"+messageService.backMessageFeedback(token));
    }

    /**
     * 编号：7-0-005,用户和商家对话详情 （这里 点击进入后，messageId 下的 消息 变更为已读状态）
     */
    @GetMapping("/message/conversation/{messageId}")
    public ResponseEntity backMessageFeedback(@PathVariable("messageId") Long messageId,
                                              @RequestParam(value = "token", required = true) String token) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(messageId);
        TokenUtil.getTokenModel(token);
        Message message=messageService.get(messageId);
        if(message==null)
            throw new OrderException(INFO_NOT_EXISTS);
        Map map=new HashMap();
        map.put("messageId",message.getMessageId());
        List<MessageDetail> messageDetails = messageDetailService.getMessageDetailByParameter(map);
        if(messageDetails==null)
            throw new OrderException(INFO_NOT_EXISTS);
        if(messageDetails.size()==0)
            throw new OrderException(INFO_NOT_EXISTS);
        MessageDetail messageDetail=messageDetails.get(0);
        messageDetail.setReceiveIsRead(true);
        messageDetailService.updateMessageDetail(messageDetail);
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setMessageContent(messageDetail.getMessageContent());
        messageInfo.setSendPeople(message.getStore().getStoreName());
        messageInfo.setSendTime(messageDetail.getSendTime());
        return ResultUtil.success(messageInfo);
    }

    /**
     * 编号：7-1-006,提交举报信息
     */
    @Transactional
    @PostMapping("/feedback")
    public ResponseEntity backMessageFeedback(@RequestParam(value = "token", required = true) String token,
                                              @RequestParam(value = "feedbackContent", required = true) String feedbackContent,
                                              @RequestParam(value = "storeId", required = false) Long storeId,
                                              @RequestParam(value = "pictureList", required = true) MultipartFile pictureList[]) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(feedbackContent);
        Customer customer=TokenUtil.getTokenModel(token).getCustomer();
        Store store=TokenUtil.getTokenModel(token).getStore();
        Store feedbackstore=null;
        if(storeId != null){
           feedbackstore=storeService.get(storeId);
        }
        if(pictureList==null)
            throw new OrderException(PARM_NOT_EMPTY);
        if(pictureList.length==0)
            throw new OrderException(PARM_NOT_EMPTY);
        Feedback feedback=new Feedback();
        feedback.setFeedbackContent(feedbackContent);
        if(customer!=null){
            feedback.setSendPeopleId(customer.getCustomerId());
            feedback.setSendPeopleType(1);
            feedback.setSendIsRead(true);
        }
        if(store!=null){
            feedback.setSendPeopleId(store.getStoreId());
            feedback.setSendPeopleType(2);
            feedback.setSendIsRead(true);
        }
        feedback.setReceivePeopleId(0L);
        feedback.setReceivePeopleType(3);
        if(feedbackstore !=null)
            feedback.setBeReportedStoreId(feedbackstore.getStoreId());
        feedbackService.insertFeedback(feedback);
        for(int i=0;i< pictureList.length;i++){
            String path=FileUtils.upload(pictureList[i], pictureList[i].getOriginalFilename());
            FeedbackPictureAssociation feedbackPictureAssociation=new FeedbackPictureAssociation();
            Picture picture=new Picture();
            picture.setPictureUrl(path);
            pictureService.insertPicture(picture);
            feedbackPictureAssociation.setFeedback(feedback);
            feedbackPictureAssociation.setPicture(picture);
            feedbackPictureAssociationService.insertFeedbackPictureAssociation(feedbackPictureAssociation);
        }
        return ResultUtil.success("status:"+true);
    }

    /**
     * 编号：7-0-007, 联系商家或用户建立对话
     */
    @Transactional
    @GetMapping("/contact")
    public ResponseEntity messageConversation(@RequestParam(value = "token", required = true) String token,
                                              @RequestParam(value = "storeId", required = true) Long storeId,
                                              @RequestParam(value = "customerId", required = true) Long customerId) {
        ParameterCalibrationUtil.IsEmpty(token);
        ParameterCalibrationUtil.IsEmpty(storeId);
        ParameterCalibrationUtil.IsEmpty(customerId);
        Customer customer=customerService.get(customerId);
        if(customer==null)
            throw new OrderException(INFO_NOT_EXISTS);
        Store store=storeService.get(storeId);
        if(store==null)
            throw new OrderException(INFO_NOT_EXISTS);
        Message message=new Message();
        message.setCustomer(customer);
        message.setStore(store);
        message.setCreatedAt(new Date());
        messageService.insertMessage(message);
        return ResultUtil.success("messageId:"+message.getMessageId());
    }

}
