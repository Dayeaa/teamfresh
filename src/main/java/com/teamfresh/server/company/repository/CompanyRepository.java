package com.teamfresh.server.company.repository;

import com.teamfresh.server.company.repository.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
