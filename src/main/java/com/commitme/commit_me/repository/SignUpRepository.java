package com.commitme.commit_me.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.commitme.commit_me.model.SignUp;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Integer>{

}
