package com.durgesh.learning.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.durgesh.learning.dtos.VideoDto;
import com.durgesh.learning.entities.Video;
import com.durgesh.learning.exceptions.ResourceNotFoundException;
import com.durgesh.learning.repositories.VideoRepo;

@Service
public class VideoServiceImpl implements VideoService {
	private VideoRepo videoRepo;

	private ModelMapper modelMapper;

	public VideoServiceImpl(VideoRepo videoRepo, ModelMapper modelMapper) {
		super();
		this.videoRepo = videoRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public VideoDto create(VideoDto videoDto) {
		String videoId = UUID.randomUUID().toString();
		videoDto.setId(videoId);

		Video video = modelMapper.map(videoDto, Video.class);
		Video saveVideo = videoRepo.save(video);
		return modelMapper.map(saveVideo, VideoDto.class);
	}

	@Override
	public VideoDto get(String videoId) {
		Video video = videoRepo.findById(videoId)
				.orElseThrow(() -> new ResourceNotFoundException("Video not Found !!"));
		return modelMapper.map(video, VideoDto.class);
	}

	@Override
	public Page<VideoDto> getAllVideos(Pageable pageable) {
		Page<Video> videos = videoRepo.findAll(pageable);
		List<VideoDto> dtos = videos.getContent().stream().map(video -> modelMapper.map(video, VideoDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(dtos, pageable, videos.getTotalElements());
	}

	@Override
	public void delete(String videoId) {
		videoRepo.deleteById(videoId);

	}

	@Override
	public VideoDto update(VideoDto videoDto, String videoId) {
		Video video = videoRepo.findById(videoId)
				.orElseThrow(() -> new ResourceNotFoundException("Video Not Found !!"));
		modelMapper.map(videoDto, video);
		Video updateVideo = videoRepo.save(video);
		return modelMapper.map(updateVideo, VideoDto.class);
	}

	// Ye me bnaya tha sir ka trika alag h uper !!

//	video.setContentType(videoDto.getContentType());
//	video.setDesc(videoDto.getDesc());
//	video.setFilePath(videoDto.getFilePath());
//	video.setTitle(videoDto.getTitle());
//	Video saveVideo = videoRepo.save(video);
//	return modelMapper.map(saveVideo, VideoDto.class);
//}

	@Override
	public List<VideoDto> searchVideo(String keyword) {
		List<Video> videos = videoRepo.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword, keyword);
		return videos.stream().map(video -> modelMapper.map(video, VideoDto.class)).collect(Collectors.toList());
	}

}
