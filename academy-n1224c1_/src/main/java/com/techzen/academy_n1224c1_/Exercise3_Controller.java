package com.techzen.academy_n1224c1_;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Exercise3_Controller {
    @GetMapping("/cal")
    public ResponseEntity<String> cal(@RequestParam(defaultValue = "") String a
            , @RequestParam(defaultValue = "") String b
            , @RequestParam(defaultValue = "") String operater) {

        //kiẻm tra
        if (a.isEmpty()) {
            return ResponseEntity.badRequest().body("Vui lòng nhập số a");
        }
        if (b.isEmpty()) {
            return ResponseEntity.badRequest().body("Vui lòng nhập số b");
        }
        if (!isDouble(a)) {
            return ResponseEntity.badRequest().body("Vui lòng nhập đúng định dạng số a");
        }
        if (!isDouble(b)) {
            return ResponseEntity.badRequest().body("Vui lòng nhập đúng định dạng số b");
        }

        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        double kq;

        switch (operater) {
            case "cộng":
                kq = a1 + b1;
                break;
            case "trừ":
                kq = a1 - b1;
                break;
            case "nhân":
                kq = a1 * b1;
                break;
            case "chia":
                if (a1 == 0) {
                    return ResponseEntity.badRequest().body("Vui lòng nhập số a khác 0");
                } else {
                    kq = a1 / b1;
                }
                break;
            default:
                return ResponseEntity.badRequest().body("Vui lòng chọn phép tính");
        }

        return ResponseEntity.ok("Kết quả: " + kq);
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
