package com.app.milestone.repository;

import com.app.milestone.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


//JpaRepository는 Spring JPA에서 제공하는 Repository라는 인터페이스를 정의해 해당 Entity의 데이터를 사용 할 수 있다
//JpaRepository통해 기본적인 CRUD 메소드 사용가능하다. ex) save,find등등이 여기 안에 있다고 생각하면 되고 더 궁금하면 ctrl누르고 JpaRepository들어가보면 된다.
@Component
public interface TalentRepository extends JpaRepository<Talent, Long>, TalentCustomRepository { //<관리대상 엔티티 , 대표(ID)값의 타입>

}
