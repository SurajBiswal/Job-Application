package com.suraj.jobApp.company;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    List<Company> getAllCompanies();
    Boolean updateCompany(Long id, Company Updatedcompany);
    void addCompany(Company company);
    Company getCompanyById(Long id);
    boolean deleteCompany(Long id);
}
