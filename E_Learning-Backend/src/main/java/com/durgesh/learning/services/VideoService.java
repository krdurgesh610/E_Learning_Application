package com.durgesh.learning.services;

import java.util.List;

import com.durgesh.learning.dtos.VideoDto;

public interface VideoService {

	VideoDto create(VideoDto videoDto);

	List<VideoDto> getAll();

	VideoDto get(String videoId);

	void delete(String videoId);

	VideoDto update(VideoDto videoDto, String videoId);

}
