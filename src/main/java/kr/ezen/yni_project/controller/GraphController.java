package kr.ezen.yni_project.controller;

import kr.ezen.yni_project.domain.Adopt;
import kr.ezen.yni_project.domain.Pet;
import kr.ezen.yni_project.domain.Shelter;
import kr.ezen.yni_project.domain.User;
import kr.ezen.yni_project.service.AdoptService;
import kr.ezen.yni_project.service.PetService;
import kr.ezen.yni_project.service.ShelterService;
import kr.ezen.yni_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class GraphController {

    @Autowired
    private AdoptService adoptService;

    @Autowired
    private UserService userService;

    @Autowired
    private PetService petService;

    @Autowired
    private ShelterService shelterService;

    @GetMapping("/graph.do")
    public String home(Model model){

        List<Adopt> adoptGraphList = adoptService.getAdoptList();
//        String test1 = "1";
//        List<Adopt> adoptGraphList = adoptService.adoptGraph();
        List<User> userGraphList = userService.getUserList();
        List<Pet> petGraphList = petService.getListcnt();
        List<Shelter> shelterGraphList = shelterService.getListcnt();

        List<String> adoptGraph = new ArrayList<>();
        List<String> petSpecies = new ArrayList<>();
        List<String> userGraph = new ArrayList<>();
        List<String> shelterGraph = new ArrayList<>();
        List<String> petGraph = new ArrayList<>();

        for(Adopt statuslist : adoptGraphList){
            adoptGraph.add(statuslist.getAdoptStatus());
        }
        for(Adopt adoptPetList : adoptGraphList){
            petSpecies.add(adoptPetList.getPetSpecies());
        }
        for(User userList : userGraphList){
            userGraph.add(String.valueOf(userList.getNo()));
        }
        for(Shelter shelterList : shelterGraphList){
            shelterGraph.add(String.valueOf(shelterList.getNo()));
        }
        for(Pet petList : petGraphList){
            petGraph.add(String.valueOf(petList.getNo()));
        }

        int adoptStatus1 = 0;
        int adoptStatus2 = 0;
        int adoptStatus3 = 0;
        for(String list2 : adoptGraph){
            if(list2.equals("3")){
                adoptStatus1 += 1;
            } else if(list2.equals("2")){
                adoptStatus2 += 1;
            } else {
                adoptStatus3 += 1;
            }
        }

        int catCnt = 0;
        int dogCnt = 0;
        for(String adoptPetList2 : petSpecies){
            if(adoptPetList2.equals("강아지")){
                catCnt += 1;
            } else {
                dogCnt += 1;
            }
        }

        int userCnt = 0;
        for(String userList2 : userGraph){
            userCnt += 1;
        }
        int petCnt = 0;
        for(String petList2 : petGraph){
            petCnt  += 1;
        }
        int shelterCnt = 0;
        for(String shelterList2 : shelterGraph){
            shelterCnt += 1;
        }

        System.out.println("@@@@@@@@cnt = " + adoptStatus1);
        System.out.println("@@@@@@@@cnt = " + adoptStatus2);
        System.out.println("@@@@@@@@cnt = " + adoptStatus3);
        System.out.println("###############adoptGraph = " + adoptGraph);

        model.addAttribute("adoptStatus1", adoptStatus1);
        model.addAttribute("adoptStatus2", adoptStatus2);
        model.addAttribute("adoptStatus3", adoptStatus3);
        model.addAttribute("catCnt", catCnt);
        model.addAttribute("dogCnt", dogCnt);
        model.addAttribute("userCnt", userCnt);
        model.addAttribute("petCnt", petCnt);
        model.addAttribute("shelterCnt", shelterCnt);

        return "/user/graph";
    }

}
