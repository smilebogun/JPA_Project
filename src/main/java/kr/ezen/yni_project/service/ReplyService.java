package kr.ezen.yni_project.service;

import kr.ezen.yni_project.domain.Post;
import kr.ezen.yni_project.domain.Reply;
import kr.ezen.yni_project.repository.PostRepository;
import kr.ezen.yni_project.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepo;
    @Autowired
    private PostRepository postRepo;

    public void insertRly(int bno, Reply reply) {
        Post post = postRepo.findById(bno).get();
        post.setPostViewCnt(post.getPostViewCnt()-1);
        reply.setPost(post);
//        reply.setUser(user);
        replyRepo.save(reply);
    }

    public void delRly(int rno) {
        replyRepo.deleteById(rno);
    }
    @Transactional
    public void updateRly(int rno, Reply reply) {
        Reply findRly = replyRepo.findById(rno).get();
        findRly.setReplyContent(reply.getReplyContent());

    }


//    public void insertRly(Reply reply, int bno) {
//
//
//    }
}
