package com.example.demo.albumarchive.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.albumarchive.exception.ResourceNotFoundException;
import com.example.demo.albumarchive.model.Album;
import com.example.demo.albumarchive.repository.AlbumRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	// get all albums
	@GetMapping("/albums")
	public List<Album> getAllAlbums(){
		return albumRepository.findAll();
	}
	
	// create album rest api
	@PostMapping("/albums")
	public Album createAlbum(@RequestBody Album album) {
		return albumRepository.save(album);
	}
	
	// get album by id rest api
	@GetMapping("/albums/{id}")
	public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
		
		Album album = albumRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Album did not exist with id: " + id));
		return ResponseEntity.ok(album);	
	}

	
	// update album by rest api
	@PutMapping("/albums/{id}")
	public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails){
		
		Album album = albumRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Album did not exist with id: " + id));
		
		album.setTitle(albumDetails.getTitle());
		album.setArtist(albumDetails.getArtist());
		album.setGenre(albumDetails.getGenre());
		album.setLabel(albumDetails.getLabel());
		album.setYear(albumDetails.getYear());
		
		Album updatedAlbum = albumRepository.save(album);
		return ResponseEntity.ok(updatedAlbum);
		
	}
	
	@DeleteMapping("/albums/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteAlbum(@PathVariable Long id){
		Album album = albumRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Album did not exist with id: " + id));
		
		albumRepository.delete(album);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return ResponseEntity.ok(response);
	}
	
	
		
	}


