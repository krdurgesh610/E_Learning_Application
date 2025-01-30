package com.durgesh.learning.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.durgesh.learning.dtos.VideoDto;

public interface VideoService {

	VideoDto create(VideoDto videoDto);

	VideoDto get(String videoId);

	Page<VideoDto> getAllVideos(Pageable pageable);

	void delete(String videoId);

	VideoDto update(VideoDto videoDto, String videoId);

	List<VideoDto> searchVideo(String keyword);

}
