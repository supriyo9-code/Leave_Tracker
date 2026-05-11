package com.leave_service.leave_service.controller;

import com.leave_service.leave_service.dto.LeaveDto;
import com.leave_service.leave_service.dto.LeaveStatusDto;
import com.leave_service.leave_service.service.LeaveService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/api/v1/leave")
public class LeaveController {
    private LeaveService leaveService;
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @PostMapping("/apply")
    public ResponseEntity<?> applyLeave(@Valid @RequestBody LeaveDto leaveDto) {
        leaveService.applyLeave(leaveDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<LeaveStatusDto> updateLeave(@Valid @RequestBody LeaveStatusDto leaveStatusDto, @PathVariable Long id) {
        log.debug("REST request to update Leave status : {}", leaveStatusDto);
        LeaveStatusDto leaveStatusDto1 = leaveService.changeStatus(leaveStatusDto, id);
        return new ResponseEntity<>(leaveStatusDto1, HttpStatus.OK);
    }
    @GetMapping("/view/leave/{mId}")
    public ResponseEntity<?> viewLeaveManager(@PathVariable Long mId) {
        return null;
    }
    @GetMapping("/view/leave/{eId}")
    public ResponseEntity<?> viewLeaveEmployee(@PathVariable Long eId) {
        return null;
    }

}
