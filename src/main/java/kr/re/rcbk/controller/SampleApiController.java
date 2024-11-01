package kr.re.rcbk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rcbk/sample")
public class SampleApiController {

    @GetMapping("/get")
    public ResponseEntity<?> getTitles(String getData) {
        return ResponseEntity.ok(getData);
    }

}