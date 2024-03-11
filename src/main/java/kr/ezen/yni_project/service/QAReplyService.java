package kr.ezen.yni_project.service;

import kr.ezen.yni_project.domain.QA;
import kr.ezen.yni_project.domain.QAReply;
import kr.ezen.yni_project.repository.QAReplyRepository;
import kr.ezen.yni_project.repository.QARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class QAReplyService {
    @Autowired
    private QAReplyRepository qaReplyRepo;
    @Autowired
    private QARepository QARepo;

    public void insertRly(int id, QAReply qaReply) {
        QA qa = QARepo.findById(id).get();
        qaReply.setQa(qa);
//        reply.setUser(user);
        qaReplyRepo.save(qaReply);
    }

    public void delRly(int rid) {
        qaReplyRepo.deleteById(rid);
    }
    @Transactional
    public void updateRly(int rno, String reply) {
        QAReply findRly = qaReplyRepo.findById(rno).get();
        findRly.setQAReplyContent(reply);

    }
    
}
