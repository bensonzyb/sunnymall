package priv.jesse.mall.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.yoyosys.tools.core.util.StrUtil;
import cn.com.yoyosys.tools.system.HostInfo;
import cn.com.yoyosys.tools.system.SystemUtil;
import priv.jesse.mall.dao.OrderDao;
import priv.jesse.mall.dao.OrderItemDao;
import priv.jesse.mall.dao.ProductDao;
import priv.jesse.mall.entity.Order;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.SmsRecord;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.service.OrderService;
import priv.jesse.mall.service.ShopCartService;
import priv.jesse.mall.service.SmsRecordService;
import priv.jesse.mall.service.exception.LoginException;
import priv.jesse.mall.utils.PropertySet;
import priv.jesse.mall.utils.SendSmsTool;
import priv.jesse.mall.utils.SystemTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ShopCartService shopCartService;
    
    @Autowired
    private SmsRecordService smsRecordService;


    @Override
    public Order findById(int id) {
        return orderDao.getOne(id);
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderDao.findAll(pageable);
    }

    @Override
    public List<Order> findAllExample(Example<Order> example) {
        return orderDao.findAll(example);
    }

    @Override
    public void update(Order order) {
        orderDao.save(order);
    }

    @Override
    public int create(Order order) {
        Order order1 = orderDao.save(order);
        return order1.getId();
    }

    @Override
    public void delById(int id) {
        orderDao.delete(id);
    }

    /**
     * 查询订单项详情
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> findItems(int orderId) {
        List<OrderItem> list = orderItemDao.findByOrderId(orderId);
        for (OrderItem orderItem : list) {
            Product product = productDao.findOne(orderItem.getProductId());
            orderItem.setProduct(product);
        }
        return list;
    }

    /**
     * 更改订单状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(int id, int status) {
        Order order = orderDao.findOne(id);
        order.setState(status);
        orderDao.save(order);
    }

    /**
     * 查找用户的订单列表
     *
     * @param request
     * @return
     */
    @Override
    public List<Order> findUserOrder(HttpServletRequest request) {
        //从session中获取登录用户的id，查找他的订单
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("请登录！");
        User loginUser = (User) user;
        List<Order> orders = orderDao.findByUserId(loginUser.getId());
        return orders;
    }

    /**
     * 支付
     *
     * @param orderId
     */
    @Override
    public void pay(int orderId) {
        //具体逻辑就不实现了，直接更改状态为 待发货
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("订单不存在");
        orderDao.updateState(STATE_WAITE_SEND,order.getId());
    }

    /**
     * 提交订单
     *
     * @param name
     * @param phone
     * @param addr
     * @param request
     * @param response
     */
    @Override
    //@Transactional
    public void submit(String name, String phone, String addr,String email, String message, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user == null)
            throw new LoginException("Not signed in,Please signed in again!");
        User loginUser = (User) user;
        Order order = new Order();
        order.setName(name);
        order.setPhone(phone);
        order.setAddr(addr);
        order.setOrderTime(new Date());
        order.setUserId(loginUser.getId());
        order.setState(STATE_NO_PAY);
        order.setEmail(email);
        order.setMessage(message);
        List<OrderItem> orderItems = shopCartService.listCart(request);
        Double total = 0.0;
        order.setTotal(total);
        order = orderDao.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getId());
            total += orderItem.getSubTotal();
            orderItemDao.save(orderItem);
        }
        order.setTotal(total);
        orderDao.save(order);
        //发送短信给卖家，有客户提交了产品咨询
        String sendContent=addr;
        if(StringUtils.isNotEmpty(message)) {
        	sendContent=sendContent+","+message;
        }
    	int num=0;
    	SmsRecord smsRecord=new  SmsRecord();
    	smsRecord.setSendDate(new Date());
    	//获取访问系统的iP个机器名称
    	HostInfo hostinfo=SystemUtil.getHostInfo();
    	if(hostinfo!=null && StrUtil.isNotEmpty(hostinfo.getAddress())  && StrUtil.isNotEmpty(hostinfo.getName())) {
    		smsRecord.setHostAddress(SystemTool.getIpAddr(request));
    		//smsRecord.setHostName(hostinfo.getName());
    	    num =smsRecordService.findBySmsRecord(smsRecord);
    	}
    	smsRecord.setSendContent(sendContent);
    	smsRecord.setPhone(PropertySet.notify_phone);
    	smsRecord.setSendTime(new Date());
    	smsRecordService.create(smsRecord);
    	if(num<=2) {//同一个IP当天超过2次发送，不在发送
    		String  threeContent="1-3个工作日内处理,";
    		Boolean flag=SendSmsTool.sendsms(email,sendContent,threeContent,PropertySet.notify_phone);
    	}
        //重定向到订单列表页面
        response.sendRedirect("/order/toList.html");///sunnymall
    }

    /**
     * 确认收货
     *
     * @param orderId
     */
    @Override
    public void receive(int orderId) {
        Order order = orderDao.findOne(orderId);
        if (order == null)
            throw new RuntimeException("订单不存在");
        orderDao.updateState(STATE_COMPLETE,order.getId());
    }
}
