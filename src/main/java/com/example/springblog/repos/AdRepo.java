package com.example.springblog.repos;

import com.example.springblog.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepo extends JpaRepository<Long, Ad> {
}
