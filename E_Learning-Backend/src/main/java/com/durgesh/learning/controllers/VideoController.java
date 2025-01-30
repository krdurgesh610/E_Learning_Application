package com.durgesh.learning.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.learning.dtos.CustomMessage;
import com.durgesh.learning.dtos.VideoDto;
import com.durgesh.learning.services.VideoService;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

	private VideoService service;

	public VideoController(VideoService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<VideoDto> create(@RequestBody VideoDto videoDto) {
		VideoDto createdDto = service.create(videoDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@GetMapping("/{videoId}")
	public ResponseEntity<VideoDto> getOne(@PathVariable String videoId) {
		VideoDto getById = service.get(videoId);
		return ResponseEntity.status(HttpStatus.FOUND).body(getById);
	}

	@DeleteMapping("/{videoId}")
	public ResponseEntity<CustomMessage> delete(@PathVariable String videoId) {
		service.delete(videoId);
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage("Video Delete !!");
		customMessage.setSuccess(true);
		return ResponseEntity.status(HttpStatus.OK).body(customMessage);
	}

	@PutMapping("/{videoId}")
	public ResponseEntity<VideoDto> update(@RequestBody VideoDto videoDto, @PathVariable String videoId) {
		VideoDto update = service.update(videoDto, videoId);
		return ResponseEntity.status(HttpStatus.OK).body(update);
	}

	@GetMapping
	public ResponseEntity<Page<VideoDto>> getAllVideos(Pageable pageable) {
		Page<VideoDto> allVideos = service.getAllVideos(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(allVideos);
	}

	@GetMapping("/search")
	public ResponseEntity<List<VideoDto>> searchVideos(@RequestParam String keyword) {
		List<VideoDto> searchVideo = service.searchVideo(keyword);
		return ResponseEntity.status(HttpStatus.OK).body(searchVideo);
	}
}
