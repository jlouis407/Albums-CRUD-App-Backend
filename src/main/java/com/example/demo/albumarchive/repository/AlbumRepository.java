package com.example.demo.albumarchive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.albumarchive.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
	
	

}
