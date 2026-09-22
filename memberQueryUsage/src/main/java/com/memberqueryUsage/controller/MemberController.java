package com.memberqueryUsage.controller;

import com.memberqueryUsage.dto.MemberDTO;
import com.memberqueryUsage.entity.Member;
import com.memberqueryUsage.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // Constructor injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // POST /api/register
    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody MemberDTO memberDTO,
            BindingResult result) {

        // Validation errors
        if (result.hasErrors()) {
            return new ResponseEntity<>(
                    result.getFieldError().getDefaultMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            return ResponseEntity.ok(
                    memberService.registerMember(memberDTO)
            );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    // DELETE /api/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        try {
            memberService.deleteMember(id);

            return ResponseEntity.ok("Member deleted successfully.");

        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    // GET /api/active-members
    @GetMapping("/active-members")
    public ResponseEntity<List<Member>> getActiveMembers() {

        return ResponseEntity.ok(
                memberService.getAllActiveMembers()
        );
    }
}