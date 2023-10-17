package com.bigbone.order;

import com.bigbone.common.entity.Comment;
import com.bigbone.common.entity.Order;
import com.bigbone.order.service.CommentService;
import com.bigbone.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class OrderApplicationTests {

    @Autowired
    CommentService commentService;
    @Autowired
    OrderService orderService;

    public static String DRIVER_NAME="driver";
    public static String PASSENGER_NAME="passenger";

    /**
     * 处理评论信息
     */
    @Test
    public void testHandleComment() {
        //保存评论信息
        Comment comment=new Comment();
        comment.setName(DRIVER_NAME);
        comment.setContent("very good");
        commentService.save(comment);
        System.out.println("保存评论成功");
        //查询评论列表信息
        List<Comment> commentList = commentService.getCommentByName(DRIVER_NAME);
        Assert.isTrue(commentList.size()>0,"评论列表信息不为空");
    }

    /**
     * 处理订单信息
     */
    @Test
    public void testHandleOrder() {
        //保存订单
        Order order=new Order();
        order.setDriver(DRIVER_NAME);
        order.setPassenger(PASSENGER_NAME);
        order.setPrice(10);
        order.setDeparture("A1");
        order.setDestination("A2");
        order.setState("0");
        order.setType("own");
        order.setId(20);
        orderService.save(order);
        System.out.println("保存订单成功！");
        //修改订单状态
        orderService.updateState(20);
        System.out.println("修改订单状态成功");
    }

    @Test
    public void testFindByDriver(){
        List<Order> orderList = orderService.findByDriver(DRIVER_NAME, 0, 10);
        Assert.isTrue(orderList.size()>0,"查询司机订单信息成功！");
        System.out.println("查询司机订单信息成功！");
    }


    @Test
    public void testFindByPassenger(){
        List<Order> orderList = orderService.findByPassenger(PASSENGER_NAME, 0, 10);
        Assert.isTrue(orderList.size()>0,"查询乘客订单信息成功！");
        System.out.println("查询乘客订单信息成功！");
    }

    @Test
    public void testGetTotalCountsByDriver(){
        int count = orderService.getTotalCountsByDriver(DRIVER_NAME);
        Assert.isTrue(count>0,"查询司机订单数量");
        System.out.println("查询司机订单数量");
    }

    @Test
    public void testGetTotalCountsByPassenger(){
        int count = orderService.getTotalCountsByPassenger(PASSENGER_NAME);
        Assert.isTrue(count>0,"查询乘客订单数量");
        System.out.println("查询乘客订单数量");
    }


    @Test
    public void testGetScore(){
        int score = orderService.getScore(PASSENGER_NAME);
        System.out.println("乘客等级分数为:"+score);
    }

}
