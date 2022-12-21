package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends JpaRepository<Expert,String> {
}
