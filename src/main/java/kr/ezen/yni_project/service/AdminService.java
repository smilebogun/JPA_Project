package kr.ezen.yni_project.service;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepo;

    public Admin getAdmins(String adminId) {
        Admin findAdmin = adminRepo.findByAdminId(adminId).orElseGet(() -> new Admin());
        //Admin findAdmin = adminRepo.findByAdminId(adminId);

        return findAdmin;
    }

    // 등록
    public void adminInsert(Admin admin) {
        adminRepo.save(admin);
    }

//    // 리스트
//    public List<Admin> getAdminList() {
//        List<Admin> adminList = adminRepo.findAll();
//        return adminList;
//    }
    // 리스트
    public Page<Admin> getAdminList(Pageable pageable) {
        return adminRepo.findAll(pageable);
    }

    // 삭제
    @Transactional
    public void adminDelete(int no) {
        adminRepo.deleteById(no);
    }

    // 상세보기
    public Admin adminDetail(int no) {
        Admin admin = adminRepo.findById(no).orElseGet(() -> new Admin());
        return admin;
    }

    // 수정하기
    @Transactional
    public void adminUpdate(Admin admin) {
        Admin findAdmin = adminRepo.findById(admin.getNo()).orElseGet(() -> new Admin());
          findAdmin.setAdminPw(admin.getAdminPw());
          findAdmin.setAdminName(admin.getAdminName());
          findAdmin.setAdminTel(admin.getAdminTel());
          findAdmin.setAdminEmail(admin.getAdminEmail());
    }


}
