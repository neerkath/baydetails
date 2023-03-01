package com.kgisl.baydetails.controller;

import java.util.List;
import java.util.Map;
// import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kgisl.baydetails.model.Baydetails;
import com.kgisl.baydetails.repository.Baydetailrepository;

@RestController
@RequestMapping("/bays")
public class Baydetailcontroller {

    @Autowired
    public Baydetailrepository baydetailrepository;

    @GetMapping("/")
    public @ResponseBody ResponseEntity<List<Baydetails>> getAll() {
        return new ResponseEntity<>(baydetailrepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Baydetails> getTeamById(@PathVariable("id") int id) {
        Baydetails baydetail = baydetailrepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not Found"));
        if (baydetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(baydetail, HttpStatus.OK);
    }

    @PostMapping(value = "/", headers = "Accept=application/json")
    public ResponseEntity<Baydetails> createTeam(@RequestBody Baydetails baydetail) {
        Baydetails actualBayDetail = baydetailrepository.save(baydetail);
        HttpHeaders headers = new HttpHeaders();
        // headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(team.getTeamid()).toUri()); 
        //    
        return new ResponseEntity<>(actualBayDetail, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<Baydetails> updateBayDetail(@PathVariable("id") long id,
            @RequestBody Baydetails currentBayDetail) {
        Baydetails baydetail = baydetailrepository.save(currentBayDetail);
        return new ResponseEntity<>(baydetail, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public void deleteBayDetail(@PathVariable("id") int id) {
        // BayDetail baydetail = bayDetailRepository.findById(id)
        //         .orElseThrow(() -> new IllegalArgumentException("Not found"));
        // if (baydetail == null) {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);        // }
        baydetailrepository.deleteById(id);
        // return new ResponseEntity<>(HttpStatus.NO_CONTENT);    }
        // @GetMapping("/floorwisecount") 
        // public @ResponseBody ResponseEntity<?> floorwisecount() {   
        //     return (ResponseEntity<?>) bayDetailRepository.countByWing();        
        // }
    }

    @GetMapping("/floorwisecount")
    public @ResponseBody Map<Integer, Long> floorwisecount() {
        Map<Integer, Long> requirementCountMap = baydetailrepository.findAll().stream()
                .collect(Collectors.groupingBy(Baydetails::getFloorno, Collectors.counting()));

        // .parallelStream()
        // .map(bay -> bay.getFloor_no())
        // .collect(
        // Collectors.toMap(
        // sum -> sum.getFloor_no,
        // Function.identity(),
        // (sum1, sum2) -> new BayDetail(
        // sum1.getFloor_no,
        // sum1.getSmallSum().add(sum2.getSmallSum()),
        // sum1.getMajorSum().add(sum2.getMajorSum()),
        // sum1.getTotalSum().add(sum2.getTotalSum())
        // )
        // )
        // )
        // .values()
        // .stream()
        // .sorted(Comparator.comparing(Baydetails::getFloor_no))
        // .collect(Collectors.toList())
        return requirementCountMap;
    }

    @GetMapping(value = "/associateName/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Baydetails> findbyname(@PathVariable("name") String name) {
        Baydetails bay = baydetailrepository.findByassociatename(name);
        return new ResponseEntity<Baydetails>(bay, HttpStatus.OK);
    }

    @GetMapping("/baytype")
    public @ResponseBody Map<String, Long> baytype() {
        Map<String, Long> requirement = baydetailrepository.findAll().stream()
                .collect(Collectors.groupingBy(Baydetails::getBaytype, Collectors.counting()));
        return requirement;
    }

    @GetMapping("/getForecastTotals")
    public Integer getForecastTotals() {
        return baydetailrepository.selectTotals();
    }

    @GetMapping("/getbaytypeTotals")
    public Integer getbaytypetotal() {

        return baydetailrepository.selectbaytype();
    }

    @GetMapping("/getcstatusTotals")
    public Integer getcstatustotal() {
        return baydetailrepository.selectcstatus();
    }

    @GetMapping(value = "/cstatus/{vacant}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Baydetails>> findbystatus(@PathVariable("vacant") String vacant) {
        // List<Baydetails> bay = baydetailrepository.findBycstatus();
        return new ResponseEntity<>(baydetailrepository.findBycstatus(vacant), HttpStatus.OK);
    }

    @GetMapping("/floorbay")
    public @ResponseBody Map<Integer, Map<String, Long>> floorBay() {
        Map<Integer, Map<String, Long>> floorbayCount = baydetailrepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Baydetails::getFloorno,
                        Collectors.groupingBy(Baydetails::getBaytype, Collectors.counting())));
        return floorbayCount;
    }

    @GetMapping("/paging/{offset}/{pagesize}")
    public ResponseEntity<Page<Baydetails>> pagination(@PathVariable int offset, @PathVariable int pagesize) {
    Page<Baydetails> pageBayDetail = baydetailrepository.findAll(PageRequest.of(offset, pagesize));
        return new ResponseEntity<>(pageBayDetail, HttpStatus.OK); 
    }
}