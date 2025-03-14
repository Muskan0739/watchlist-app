package com.example.muskan.WatchList.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.muskan.WatchList.App.entity.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Integer>{

}
