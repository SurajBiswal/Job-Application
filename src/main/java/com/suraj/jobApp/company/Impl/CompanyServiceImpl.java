package com.suraj.jobApp.company.Impl;

import com.suraj.jobApp.company.Company;
import com.suraj.jobApp.company.CompanyRepository;
import com.suraj.jobApp.company.CompanyService;
import com.suraj.jobApp.job.Job;
import com.suraj.jobApp.job.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    CompanyRepository companyRepository;
    JobRepository jobRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Boolean updateCompany(Long id, Company updatedCompany) {
        Optional<Company>companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompany(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if(company != null){
            List<Job>jobs = company.getJobs();
            for(int i=0;i<jobs.size();i++){
                jobRepository.deleteById(jobs.get(i).getId());
            }
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
