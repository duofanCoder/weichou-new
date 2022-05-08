package com.duofan.weichou.controller.v1.api;


import com.duofan.weichou.controller.v1.condition.business.PayOrderCondition;
import com.duofan.weichou.controller.v1.request.PayOrderRequest;
import com.duofan.weichou.dto.model.business.PayOrderDto;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.service.business.PayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@RestController
@RequestMapping("/api/v1/payOrder")
@Api(value = "application")
public class PayOrderController {

    @Autowired
    private PayOrderService payOrderService;
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("remove")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response remove(Long[] primaryKeys) {
        payOrderService.removeByPrimaryKey(primaryKeys);
        return Response.ok();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("update")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response update(@RequestBody PayOrderRequest payOrderRequest) {
        return Response.ok().setData(payOrderService.update(modelMapper.map(payOrderRequest, PayOrderDto.class)));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody PayOrderRequest payOrderRequest) {
        return Response.ok().setData(payOrderService.save(modelMapper.map(payOrderRequest, PayOrderDto.class)));
    }

    @PostMapping("query")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody PayOrderCondition condition) {
        return Response.ok().setData(payOrderService.findPageByCondition(condition));
    }

}
