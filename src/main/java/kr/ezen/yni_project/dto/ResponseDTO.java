package kr.ezen.yni_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // 게터 세터 투스트링 이퀄스 등
@NoArgsConstructor  // 기본생성자 만들기
@AllArgsConstructor // 모든 필드를 매개변수로 하는 인자생성자 만들기
public class ResponseDTO<T> {   // 제네릭 방식

    // ResponseDTO를 이용하면 일관된 응답처리를 할 수 있다.

    // 응답 상태코드
    private int status;

    // 실제 응답코드
    private T data; // T는 String List 등 데이터 타입에 따라서 변경이 됌



}
