package vn.com.demo.commonsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.com.demo.commonsearch.commons.search.manager.FieldEntityManger;
import vn.com.demo.commonsearch.commons.search.manager.proxy.FieldEntityManagerProxy;
import vn.com.demo.commonsearch.commons.search.service.SearchSpecification;
import vn.com.demo.commonsearch.commons.search.util.PredicateUtil;
import vn.com.demo.commonsearch.modules.worksite.repository.WorkdayRepository;
import vn.com.demo.commonsearch.modules.worksite.repository.WorksiteRepository;
import vn.com.demo.commonsearch.modules.worksite.service.WorksiteService;

@SpringBootTest
class CommonSearchApplicationTests {

	@Autowired
	private FieldEntityManagerProxy fieldEntityManagerProxy;

	@Autowired
	private FieldEntityManger fieldEntityManger;

	@Autowired
	private SearchSpecification searchSpecification;

	@Autowired
	private PredicateUtil predicateUtil;

	@Autowired
	private WorksiteService worksiteService;

	@Autowired
	private WorkdayRepository workdayRepository;

	@Autowired
	private WorksiteRepository worksiteRepository;


	@Test
	void contextLoads() {
		Assertions.assertNotNull(fieldEntityManagerProxy);
		Assertions.assertNotNull(fieldEntityManger);
		Assertions.assertNotNull(searchSpecification);
		Assertions.assertNotNull(predicateUtil);
		Assertions.assertNotNull(worksiteService);
		Assertions.assertNotNull(workdayRepository);
		Assertions.assertNotNull(worksiteRepository);
	}

}
