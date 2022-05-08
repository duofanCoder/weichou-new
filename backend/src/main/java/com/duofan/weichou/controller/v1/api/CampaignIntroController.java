package com.duofan.weichou.controller.v1.api;


import com.duofan.weichou.controller.v1.condition.business.CampaignIntroCondition;
import com.duofan.weichou.controller.v1.request.CampaignIntroRequest;
import com.duofan.weichou.dto.model.business.CampaignIntroDto;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.service.business.CampaignIntroService;
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
@RequestMapping("/api/v1/campaignIntro")
@Api(value = "application")
public class CampaignIntroController {

    @Autowired
    private CampaignIntroService campaignIntroService;
    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("remove")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response remove(Long[] primaryKeys) {
        campaignIntroService.removeByPrimaryKey(primaryKeys);
        return Response.ok();
    }

    @PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("update")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response update(@RequestBody CampaignIntroRequest campaignIntroRequest) {
        return Response.ok().setData(campaignIntroService.update(modelMapper.map(campaignIntroRequest, CampaignIntroDto.class)));
    }

    @PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody CampaignIntroRequest campaignIntroRequest) {
        return Response.ok().setData(campaignIntroService.save(modelMapper.map(campaignIntroRequest, CampaignIntroDto.class)));
    }

    @PostMapping("query")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody CampaignIntroCondition condition) {
        return Response.ok().setData(campaignIntroService.findPageByCondition(condition));
    }
    @PostMapping("me")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response get() {
        return Response.ok().setData(campaignIntroService.getCurrentUserCampaignIntro());
    }
}
