package com.example.demo.test;

import com.example.demo.model.RoleType;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.EmptyStackException;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입
    private UserRepository userRepository;

    @DeleteMapping("dummy/user/{id}")
    public String deleteUser(@PathVariable int id){

        try {
            userRepository.deleteById(id);
        }catch (EmptyStackException e){
            return "삭제실패";
        }
        return "삭제완료";
    }

    @Transactional //함수 종료시 자동 커밋
    @PutMapping("dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){

        User user = userRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("수정 실패");
        });
        //람다식으로 사용가능
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);
        //아이디를 전달하지 않으면 insert
        //아이디를 전달했을때 데이터가 있으면 update 없으면 insert

        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //한페이지당 두건의 데이터를 리턴
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUsers= userRepository.findAll(pageable);

        List<User> users = pagingUsers.getContent();
        return users;
    }

    //{id}주소로 파라미터를 전달 받을 수 있다.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("회원 없음");
            }
        });
        //user 객체 = 자바 오브젝트

        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
}
