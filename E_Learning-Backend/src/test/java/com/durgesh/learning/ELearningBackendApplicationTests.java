package com.durgesh.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.durgesh.learning.services.CategoryService;

@SpringBootTest
class ELearningBackendApplicationTests {
	
	@Autowired
	private CategoryService categoryService;

	@Test
	void contextLoads() {
	}

	@Test
	public void testCategoryCourseReletion() {
		categoryService.addCourseToCategory("c246a50e-3725-41a8-a4be-692c33415746", "548fb46f-fd9c-43d2-8bb8-36fd29c0cbf5");
	}
}
