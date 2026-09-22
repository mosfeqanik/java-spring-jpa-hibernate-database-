package com.memberqueryUsage.service;

import com.memberqueryUsage.dto.MemberDTO;
import com.memberqueryUsage.entity.Member;
import com.memberqueryUsage.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(MemberDTO dto) {

        if (memberRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        Member member = new Member(
                dto.getName(),
                dto.getEmail()
        );

        return memberRepository.save(member);
    }

    // Soft Delete Concept
    public void deleteMember(Long id) {

        Optional<Member> optionalMember =
                memberRepository.findById(id);

        if (optionalMember.isEmpty()) {
            throw new RuntimeException("Member not found");
        }

        Member member = optionalMember.get();

        member.setDeleted(true);

        memberRepository.save(member);
    }

    // Get Active Members
    public List<Member> getAllActiveMembers() {
        return memberRepository.findAllActiveMembers();
    }
}