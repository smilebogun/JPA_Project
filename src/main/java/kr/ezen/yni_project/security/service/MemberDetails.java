package kr.ezen.yni_project.security.service;

import kr.ezen.yni_project.domain.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// DB에서 Security 사용하기
public class MemberDetails extends User {
    // 알트 엔터로 MemberDetails를 불러와서 username과 password를 Memeber 타입으로 수정

    private final Admin admin;
    // collection<GrantedAuthority> authorities 권한목록
    public MemberDetails(Admin admin, Collection<? extends GrantedAuthority> authorities) {
        super(admin.getAdminId(), admin.getAdminPw(), authorities);
        this.admin = admin;
    }
    public Admin getAdmin(){
        return admin;
    }
}
