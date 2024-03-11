package kr.ezen.yni_project.service;

import kr.ezen.yni_project.domain.QA;
import kr.ezen.yni_project.repository.QARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class QAService {
    @Autowired
    private QARepository qaRepo;


    public Page<QA> findByUserId(Pageable pageable, int id) {
      return qaRepo.findByUserNo(id,pageable);
    }

    public void insertQA(QA qa) {qaRepo.save(qa);
    }

    public Page<QA> findByUserNo(Pageable pageable, int userNo) {
        return qaRepo.findByUserNo(userNo,pageable);
    }

    public QA findById(int id) {
        return qaRepo.findById(id).get();
    }

    public Page<QA> findAll(Pageable pageable) {
        return qaRepo.findAll(pageable);
    }
    @Transactional
    public void answerQA(QA findQA) {
        QA byId = qaRepo.findById(findQA.getId()).get();
        byId.setAnswer(findQA.getAnswer());
    }
    @Transactional
    public void updateQA(QA qa) {
        QA findQA = qaRepo.findById(qa.getId()).get();
        findQA.setQaContent(qa.getQaContent());
        findQA.setQaTitle(qa.getQaTitle());
    }

    public void deleteQA(int id) {
        qaRepo.deleteById(id);
    }
}



