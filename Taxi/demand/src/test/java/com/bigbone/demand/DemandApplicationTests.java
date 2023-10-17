package com.bigbone.demand;

import com.bigbone.common.entity.Demand;
import com.bigbone.demand.service.DemandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class DemandApplicationTests {

    @Autowired
    DemandService demandService;

    public static String PASSENGER_NAME="passenger";
    public static String PASSENGER_NAME1="passenger1";

    /**
     * 处理订单信息
     */
    @Test
    public void testHandleDemand() {
        //生成订单
        Demand demand=new Demand();
        demand.setDeparture("A1");
        demand.setDestination("A2");
        demand.setName(PASSENGER_NAME);
        demand.setType("own");
        demandService.save(demand);
        System.out.println("生成订单成功！");
        //按乘客名称查询订单信息
        List<Demand> demandList = demandService.findAll(PASSENGER_NAME, 0, 10);
        Assert.isTrue(demandList.size()>0,"查询订单列表成功");
        //按地址查询订单信息
        List<Demand> demandList1 = demandService.findAll1("A1", 0, 10);
        Assert.isTrue(demandList1.size()>0,"查询订单列表成功");
        Demand passengerDemand= demandList.get(0);
        demandService.deleteById(passengerDemand.getId());
        System.out.println("删除订单信息成功！");
    }

    /**
     * 查询订单数量
     */
    @Test
    public void testCount(){
        Integer count = demandService.count();
        System.out.println("客户订单的数量是:"+count);
    }
}
