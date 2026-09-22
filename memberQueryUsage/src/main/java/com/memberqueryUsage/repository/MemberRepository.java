package com.memberqueryUsage.repository;

import com.memberqueryUsage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find member by email
    Optional<Member> findByEmail(String email);

    // Find only active members
    @Query("SELECT m FROM Member m WHERE m.isDeleted = false")
    List<Member> findAllActiveMembers();
}