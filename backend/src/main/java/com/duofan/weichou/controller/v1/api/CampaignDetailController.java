package com.duofan.weichou.controller.v1.api;


import com.duofan.weichou.controller.v1.condition.business.CampaignDetailCondition;
import com.duofan.weichou.controller.v1.request.CampaignDetailRequest;
import com.duofan.weichou.dto.model.business.CampaignDetailDto;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.service.business.CampaignDetailService;
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
@RequestMapping("/api/v1/campaignDetail")
@Api(value = "application")
public class CampaignDetailController {

    @Autowired
    private CampaignDetailService campaignDetailService;
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("remove")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response remove(Long[] primaryKeys) {
        campaignDetailService.removeByPrimaryKey(primaryKeys);
        return Response.ok();
    }

    @PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("update")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response update(@RequestBody CampaignDetailRequest campaignDetailRequest) {
        return Response.ok().setData(campaignDetailService.update(modelMapper.map(campaignDetailRequest, CampaignDetailDto.class)));
    }

    @PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody CampaignDetailRequest campaignDetailRequest) {
        return Response.ok().setData(campaignDetailService.save(modelMapper.map(campaignDetailRequest, CampaignDetailDto.class)));
    }

    @PostMapping("query")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody CampaignDetailCondition condition) {
        return Response.ok().setData(campaignDetailService.findPageByCondition(condition));
    }

}
