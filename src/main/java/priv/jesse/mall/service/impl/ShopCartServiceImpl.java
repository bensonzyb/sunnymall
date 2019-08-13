package priv.jesse.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.jesse.mall.entity.OrderItem;
import priv.jesse.mall.entity.Product;
import priv.jesse.mall.entity.User;
import priv.jesse.mall.service.ProductService;
import priv.jesse.mall.service.ShopCartService;
import priv.jesse.mall.service.exception.LoginException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author hfb
 * @date 2017/11/21
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ProductService productService;
    
    private final static String CARTQUALITY="cartQuality";

    /**
     * 加购物车
     * 将商品id保存到Session中List<Integer>中
     *
     * @param productId
     * @param request
     */
    @Override
    public void addCart(int productId, int quantity,HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("Not logged in,please log in again!");
        List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        //数量session
        Map<Integer, Integer> mapQuantity=new HashMap<Integer,Integer>();
        List<Map<Integer, Integer>> quantitys = (List<Map<Integer, Integer>>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId()+CARTQUALITY);

        if (productIds == null) {
            productIds = new ArrayList<>();
            request.getSession().setAttribute(NAME_PREFIX + loginUser.getId(), productIds);
            
            quantitys= new ArrayList<Map<Integer, Integer>>();
            request.getSession().setAttribute(NAME_PREFIX + loginUser.getId()+CARTQUALITY, quantitys);
        }
        productIds.add(productId);
        //添加数量
        mapQuantity.put(productId, quantity);
        quantitys.add(mapQuantity);
    }

    /**
     * 移除
     *
     * 移除session List中对应的商品Id
     *
     * @param productId
     * @param request
     */
    @Override
    public void remove(int productId, int quantity,HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("Not logged in,please log in again!");
        List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        Iterator<Integer> iterator = productIds.iterator();
        while (iterator.hasNext()) {
            if (productId == iterator.next()) {
                iterator.remove();
            }
        }
        
        List<Map<Integer, Integer>> quantitys = (List<Map<Integer, Integer>>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId()+CARTQUALITY);
        Iterator<Map<Integer, Integer>> iterator2 = quantitys.iterator();
        while (iterator.hasNext()) {
            if (productId == iterator.next()) {
                iterator.remove();
            }
        }
        
        
    }

    /**
     * 查看购物车
     *
     * 查询出session的List中所有的商品Id,并封装成List<OrderItem>返回
     *
     * @param request
     * @return
     */
    @Override
    public List<OrderItem> listCart(HttpServletRequest request) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null)
            throw new Exception("Not logged in,please log in again!");
        List<Integer> productIds = (List<Integer>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId());
        // key: productId value:OrderItem
        Map<Integer, OrderItem> productMap = new HashMap<>();
        if (productIds == null){
            return new ArrayList<>();
        }
        List<Map<Integer, Integer>> quantitys = (List<Map<Integer, Integer>>) request.getSession().getAttribute(NAME_PREFIX + loginUser.getId()+CARTQUALITY);
        // 遍历List中的商品id，每个商品Id对应一个OrderItem
        for (Integer productId : productIds) {
        	int totalQuantity=0;
        	for(Map<Integer, Integer> map:quantitys) {
        		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
        			Integer mapKey = entry.getKey();
        			Integer mapValue = entry.getValue();
        			if(null!=productId && null!=mapKey && productId==mapKey) {
        				totalQuantity=totalQuantity+mapValue;
            		}
            	}
        	}
            if (productMap.get(productId) == null) {
                Product product = productService.findById(productId);
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(product);
                orderItem.setProductId(productId);
                //orderItem.setCount(1);
                orderItem.setCount(totalQuantity);
                orderItem.setSubTotal(product.getShopPrice());
                productMap.put(productId, orderItem);
            } else {
                OrderItem orderItem = productMap.get(productId);
                int count = orderItem.getCount();
                //orderItem.setCount(++count);
                orderItem.setCount(totalQuantity);
                Double subTotal = orderItem.getSubTotal();
                orderItem.setSubTotal(orderItem.getSubTotal()+subTotal);
                productMap.put(productId, orderItem);
            }
        }
        List<OrderItem> orderItems = new ArrayList<>(productMap.values());
        return orderItems;
    }
}
