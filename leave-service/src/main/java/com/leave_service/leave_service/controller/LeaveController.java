package com.leave_service.leave_service.controller;

import com.leave_service.leave_service.dto.BaseLeaveDto;
import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.dto.LeaveStatusDto;
import com.leave_service.leave_service.entity.Leave;
import com.leave_service.leave_service.service.LeaveService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/leave")
public class LeaveController {
    private LeaveService leaveService;
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @PostMapping("/apply")
    public ResponseEntity<Leave> applyLeave(@Valid @RequestBody LeaveDto leaveDto) {
        log.trace("Leave Apply Request");
        Leave leave=leaveService.applyLeave(leaveDto);
        log.info("Leave: {}",leave);

        return new ResponseEntity<>(leave,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LeaveStatusDto> updateLeave(@Valid @RequestBody LeaveStatusDto leaveStatusDto, @PathVariable Long id) {
        log.debug("REST request to update Leave status : {}", leaveStatusDto);
        LeaveStatusDto leaveStatusDto1 = leaveService.changeStatus(leaveStatusDto, id);
        return new ResponseEntity<>(leaveStatusDto1, HttpStatus.OK);
    }
    @GetMapping("/view/leaves/{mId}")
    public ResponseEntity<?> viewLeaveManager(@PathVariable Long mId) {

        log.debug("REST request to view Leave by manager id: {}", mId);

        List<BaseLeaveDto> baseLeaveDtos= leaveService.getLeaveManager(mId);

        return new ResponseEntity<>(baseLeaveDtos, HttpStatus.OK);
    }
    @GetMapping("/view/leaves/{eId}")
    public ResponseEntity<?> viewLeaveEmployee(@PathVariable Long eId) {
        log.debug("REST request to view Leave by employee id: {}", eId);

        List<BaseLeaveDto> baseLeaveDtos=leaveService.getLeaveEmployee(eId);

        return new ResponseEntity<>(baseLeaveDtos, HttpStatus.OK);
    }
    @GetMapping("/view/leave/{id}")
    public ResponseEntity<?> getLeave(@PathVariable Long id) {
        log.debug("REST request to get Leave : {}", id);
        BaseLeaveDto baseLeaveDto=leaveService.getLeave(id);
        return new ResponseEntity<>(baseLeaveDto, HttpStatus.OK);
    }

}
