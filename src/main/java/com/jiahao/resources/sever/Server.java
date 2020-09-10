package com.jiahao.resources.sever;

import com.jiahao.resources.sever.dto.Detail;
import com.jiahao.resources.sever.dto.Good;
import com.jiahao.resources.sever.service.DetailService;
import com.jiahao.resources.sever.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class Server {
    private GoodService goodService;
    private DetailService detailService;

    @Autowired
    public Server(GoodService goodService,DetailService detailService){
        this.goodService = goodService;
        this.detailService = detailService;
    }

    @CrossOrigin
    @GetMapping("/index")
    @ResponseBody
    public List<Good> showPage(Integer pageNo, String type){
        return goodService.findTopNByType(type,pageNo);
    }

    @CrossOrigin
    @GetMapping("/detail")
    @ResponseBody
    public Detail showDetail(@RequestParam("id") Integer id){
        return detailService.getDetailsByHomeId(id);
    }
}
