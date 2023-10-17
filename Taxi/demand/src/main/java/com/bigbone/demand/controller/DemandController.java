package com.bigbone.demand.controller;

import com.bigbone.common.entity.Demand;
import com.bigbone.common.entity.Result;
import com.bigbone.demand.service.DemandService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("demand")
public class DemandController {
    @Autowired
    DemandService demandService;

    @GetMapping("getPassengerDemand/{name}/{start}/{row}")
    public List<Demand> getPassengerDemand(@PathVariable("name") String name, @PathVariable("start") int start, @PathVariable("row") int row) {
        List<Demand> demandList = demandService.findAll(name, start, row);
        for (Demand demand : demandList) {
            demand.setTypeValue(demand.getType());
            if(demand.getType().equals("own")){
                demand.setType("约车");
            }else {
                demand.setType("拼车");
            }
        }
        return demandList;
    }

    @GetMapping("getDriverDemand/{address}/{start}/{row}")
    public List<Demand> getDriverDemand(@PathVariable("address") String address, @PathVariable("start") int start, @PathVariable("row") int row) {
        List<Demand> demandList = demandService.findAll1(address, start, row);
        for (Demand demand : demandList) {
            demand.setTypeValue(demand.getType());
            demand.setType("约车");
        }
        return demandList;
    }

    @GetMapping("getDriverShareDemand/{address}/{start}/{row}")
    public List<Demand> getDriverShareDemand(@PathVariable("address") String address, @PathVariable("start") int start, @PathVariable("row") int row) {
        List<Demand> demandList = demandService.findAll2(address, start, row);
        for (Demand demand : demandList) {
            demand.setTypeValue(demand.getType());
            demand.setType("拼车");
        }
        return demandList;
    }

    @GetMapping("getTotalCounts")
    public Integer getTotalCounts(){
        return demandService.count();
    }


    @GetMapping("deleteById/{id}")
    public void deleteById(@PathVariable("id") int id){
        demandService.deleteById(id);
    }



    @PostMapping("save")
    public Result save(@RequestBody Demand demand){
        demandService.save(demand);
        return new Result().setCode(200).setMsg("添加成功");
    }

    @PostMapping("save/share")
    public Result saveShare(@RequestBody Demand demand){
        demandService.saveShare(demand);
        return new Result().setCode(200).setMsg("添加成功");
    }

//    @PostMapping("update")
//    @HystrixCommand(fallbackMethod = "hystrixMethod") // 失败时调用指定方法
//    public Result update(@RequestBody Demand demand){
//        if(demand.getPrice()<0){
//            System.out.println("-----Hystrix-------");
//            throw new RuntimeException("价格不允许小于0");
//        }
//        demandService.update(demand);
//        return new Result().setCode(200).setMsg("添加成功");
//    }

//    @GetMapping("findById/{id}")
//    public Demand findById(@PathVariable("id") int id){
//        return demandService.findById(id);
//    }
}
