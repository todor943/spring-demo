package com.todor.api.demo.repositories;
import com.todor.api.demo.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("contentRepository")
public interface ContentRepository extends JpaRepository<Content, Long>
{

}