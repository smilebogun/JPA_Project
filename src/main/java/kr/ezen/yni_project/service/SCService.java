package kr.ezen.yni_project.service;

import kr.ezen.yni_project.Specification.SCSpecification;
import kr.ezen.yni_project.domain.SC;
import kr.ezen.yni_project.repository.SCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SCService {
    @Autowired
    private SCRepository scRepo;

    public void insertAnno(SC sc) {
        scRepo.save(sc);
    }


    public Page<SC> findAll(Pageable pageable) {
        return scRepo.findAll(pageable);
    }

    public SC getAnno(int id) {

        return scRepo.findById(id).get();
    }

    public Page<SC> searchList(String keyword, String searchCategory, Pageable pageable) {
        switch (searchCategory) {
            case "all":
                if (!keyword.equals("null")) {
                    Specification<SC> spec = Specification.where(SCSpecification.likeTitle(keyword)).or(SCSpecification.likeContent(keyword));
                    return scRepo.findAll(spec, pageable);
                }

                break;
            case "title":
                if (!keyword.equals("null")) {
                    Specification<SC> spec = Specification.where(SCSpecification.likeTitle(keyword));
                    return scRepo.findAll(spec, pageable);
                }
                break;
            case "content":
                if (!keyword.equals("null")) {
                    Specification<SC> spec = Specification.where(SCSpecification.likeContent(keyword));;
                    return scRepo.findAll(spec, pageable);
                }
                break;
        }
        return scRepo.findAll(pageable);
    }

    public SC findById(int id) {
        return scRepo.findById(id).get();
    }
    @Transactional
    public void updateSC(SC sc) {
        SC findSC = scRepo.findById(sc.getId()).get();
        findSC.setScContent(sc.getScContent());
        findSC.setScTitle(sc.getScTitle());
        findSC.setScImage(sc.getScImage());

    }

    public void delSC(int id) {
        scRepo.deleteById(id);
    }
}



