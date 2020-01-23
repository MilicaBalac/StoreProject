package test.projekat.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.projekat.dto.AdDTO;
import test.projekat.model.Ad;
import test.projekat.service.AdService;

@RestController
public class AdController {

	@Autowired
	AdService adService;

	@GetMapping(value = "api/ads")
	public ResponseEntity<List<AdDTO>> getAllPages(@RequestParam(defaultValue = "", name = "title") String title,
			Pageable page) {
		final Page<Ad> ads = adService.findAllPages(title, page);
		final List<AdDTO> retVal = ads.getContent().stream().map(AdDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}

	@GetMapping(value = "api/ads/{id}")
	public ResponseEntity<AdDTO> getProject(@PathVariable Long id) {
		Ad ad = adService.findOne(id);

		if (ad == null) {
			return new ResponseEntity<AdDTO>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<AdDTO>((new AdDTO(ad)), HttpStatus.OK);
		}
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "api/ads")
	public ResponseEntity<AdDTO> create(@RequestBody AdDTO adDTO) {

		Ad ad = new Ad();

		ad.setTitle(adDTO.getTitle());
		ad.setDescription(adDTO.getDescription());
		ad.setLocation(adDTO.getLocation());
		ad.setPrice(adDTO.getPrice());
		ad.setReadme(adDTO.getReadme());
		adService.save(ad);
		AdDTO retVal = new AdDTO(ad);

		return new ResponseEntity<AdDTO>(retVal, HttpStatus.CREATED);
	}

	@PreAuthorize("isAuthenticated()")
	@PutMapping(value = "api/ads/{id}")
	public ResponseEntity<AdDTO> update(@RequestBody AdDTO adDTO, @PathVariable Long id) {

		Ad ad = adService.findOne(id);
		if (ad == null) {
			return new ResponseEntity<AdDTO>(HttpStatus.NOT_FOUND);
		}

		ad.setId(id);
		ad.setTitle(adDTO.getTitle());
		ad.setDescription(adDTO.getDescription());
		ad.setLocation(adDTO.getLocation());
		ad.setPrice(adDTO.getPrice());
		ad.setReadme(adDTO.getReadme());
		adService.save(ad);
		AdDTO retVal = new AdDTO(ad);

		return new ResponseEntity<AdDTO>(retVal, HttpStatus.CREATED);
	}

	@PreAuthorize("isAuthenticated()")
	@DeleteMapping(value = "api/ads/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Ad ad = adService.findOne(id);

		if (ad == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			adService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
