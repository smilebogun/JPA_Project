package kr.ezen.yni_project.security.service;

import kr.ezen.yni_project.domain.Admin;
import kr.ezen.yni_project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
// DB에서 Security 사용하기
public class MemberDetailService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        // String AdminId = username;
        // DB에서 Member 객체를 가져오기
        Admin admin = adminRepository.findByAdminId(adminId).orElseGet(() -> new Admin());
        //Admin admin = adminRepository.findByAdminId(adminId);
        System.out.println("admin.getAdminId() = " + admin.getAdminId());

        // 아이디 검증 이후 커스텀어센티케이션핸들러로 메세지 발생을 위해 instance of 진행
        if(admin.getAdminId() == null){
            throw new UsernameNotFoundException("Not Found : " + adminId);
        }

        // 여러개의 권한을 담는 리스트 (ROLE_USER,...)
        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        System.out.println("################# :" + admin.getAdminRole());
        roles.add(new SimpleGrantedAuthority(admin.getAdminRole()));

        MemberDetails memberDetails = new MemberDetails(admin, roles);
        return memberDetails;
    }
}
