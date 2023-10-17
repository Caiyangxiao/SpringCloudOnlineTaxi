package com.bigbone.account;

import com.bigbone.account.service.DriverService;
import com.bigbone.account.service.PassengerService;
import com.bigbone.common.entity.Driver;
import com.bigbone.common.entity.Passenger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class AccountApplicationTests {
    @Autowired
    DriverService driverService;

    @Autowired
    PassengerService passengerService;

    public static String DRIVER_NAME="driver";
    public static String PASSENGER_NAME="passenger";
    public static String PASSWORD="123456";

    /**
     * 司机登陆功能
     */
    @Test
   public void testDriverLogin() {
        Driver driver = driverService.login(DRIVER_NAME, PASSWORD);
        Assert.isTrue(driver.getUsername().equals(DRIVER_NAME),"司机登陆成功！");
        System.out.println("司机登陆成功！");
    }

    /**
     * 乘客登陆功能
     */
    @Test
    public void testPassengerLogin() {
        Passenger passenger = passengerService.login(PASSENGER_NAME, PASSWORD);
        Assert.isTrue(passenger.getUsername().equals(PASSENGER_NAME),"乘客登陆成功！");
        System.out.println("乘客登陆成功！");
    }

    /**
     * 按照姓名查询司机/乘客
     */
    @Test
    public void testFindByUsername(){
        //按照姓名查询司机
        Driver driver = driverService.findByUsername(DRIVER_NAME);
        Assert.isTrue(driver.getUsername().equals(DRIVER_NAME),"按照姓名查询司机成功！");
        System.out.println("按照姓名查询司机成功！");
        Passenger passenger = passengerService.findByUsername(PASSENGER_NAME);
        Assert.isTrue(passenger.getUsername().equals(PASSENGER_NAME),"按照姓名查询乘客成功！");
        System.out.println("按照姓名查询乘客成功！");
    }

    /**
     * 创建司机
     */
    @Test
    public void testSaveAndUpdateDriver() {
        //创建司机
        Driver driver =new Driver().setUsername("driver66").setPassword(PASSWORD).setAddress("A3");
        driverService.save(driver);
        System.out.println("创建司机信息成功");
        //更新司机
        driver.setUsername("driver77");
        driverService.update(driver);
        System.out.println("更新司机信息成功");
        //删除司机
        Driver driverTest = driverService.findByUsername("driver66");
        driverService.delete(driverTest.getId());
        System.out.println("删除司机信息成功");
    }

    /**
     * 创建乘客
     */
    @Test
    public void testSaveAndUpdatePassenger() {
        //创建乘客
        Passenger passenger = new Passenger().setUsername("passenger66").setPassword(PASSWORD).setAddress("A3");
       passengerService.save(passenger);
        System.out.println("创建乘客信息成功");
        //更新乘客
        passenger.setUsername("passenger77");
        passengerService.update(passenger);
        System.out.println("更新乘客信息成功");
        //删除乘客
        Passenger passengerTest = passengerService.findByUsername("passenger66");
        passengerService.delete(passengerTest.getId());
        System.out.println("删除乘客信息成功");
    }
}
