package com.dglee.mini_prj.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findByTitle(String title);
    Optional<Question> findByIdAndTitle(Integer id,String title);
    Optional<List<Question>> findByTitleLike(String title);

}