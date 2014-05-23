package fr.ippon.running.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.ippon.running.domain.Result;
import fr.ippon.running.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Result.
 */
@RestController
@RequestMapping("/app")
public class ResultResource {

	private final Logger log = LoggerFactory.getLogger(ResultResource.class);

	@Inject
	private ResultRepository resultRepository;

	/**
	 * POST /rest/results -> Create a new result.
	 */
	@RequestMapping(value = "/rest/results", method = RequestMethod.POST, produces = "application/json")
	@Timed
	public void create(@RequestBody Result result) {
		log.debug("REST request to save Result : {}", result);
		resultRepository.save(result);
	}

	/**
	 * GET /rest/results -> get all the results.
	 */
	@RequestMapping(value = "/rest/results", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public List<Result> getAll() {
		log.debug("REST request to get all Results");
		return resultRepository.findAll();
	}

	/**
	 * GET /rest/results/:id -> get the "id" result.
	 */
	@RequestMapping(value = "/rest/results/{id}", method = RequestMethod.GET, produces = "application/json")
	@Timed
	public Result get(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to get Result : {}", id);
		Result result = resultRepository.findOne(id);
		if (result == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return result;
	}

	/**
	 * DELETE /rest/results/:id -> delete the "id" result.
	 */
	@RequestMapping(value = "/rest/results/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@Timed
	public void delete(@PathVariable Long id, HttpServletResponse response) {
		log.debug("REST request to delete Result : {}", id);
		resultRepository.delete(id);
	}
}
