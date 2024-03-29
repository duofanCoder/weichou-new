package com.duofan.weichou.controller.v1.api;


import com.duofan.weichou.controller.v1.condition.business.JournalCondition;
import com.duofan.weichou.controller.v1.request.JournalRequest;
import com.duofan.weichou.dto.model.business.JournalDto;
import com.duofan.weichou.dto.response.Response;
import com.duofan.weichou.service.business.JournalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author duofan
 * @version 1.0
 * @email 2441051071@qq.com
 * @website duofan.top
 * @date 2022/3/29
 */
@RestController
@RequestMapping("/api/v1/journal")
@Api(value = "application")
public class JournalController {

    @Autowired
    private JournalService journalService;
    @Autowired
    private ModelMapper modelMapper;

@PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("remove")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response remove(Long[] primaryKeys) {
        journalService.removeByPrimaryKey(primaryKeys);
        return Response.ok();
    }

@PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("update")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response update(@RequestBody JournalRequest journalRequest) {
        return Response.ok().setData(journalService.update(modelMapper.map(journalRequest, JournalDto.class)));
    }

@PreAuthorize("hasAuthority('COMMON')")
    @PostMapping("save")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody JournalRequest journalRequest) {
        return Response.ok().setData(journalService.save(modelMapper.map(journalRequest, JournalDto.class)));
    }

    @PostMapping("query")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response save(@RequestBody JournalCondition condition) {
        return Response.ok().setData(journalService.findPageByCondition(condition));
    }
    @PostMapping("{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response get(@PathVariable("id") Long id) {
        return Response.ok().setData(journalService.getByPrimaryKey(id));
    }
}
