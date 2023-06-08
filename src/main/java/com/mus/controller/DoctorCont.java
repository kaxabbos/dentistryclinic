package com.mus.controller;

import com.mus.controller.main.Attributes;
import com.mus.model.Doctors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/doctor")
public class DoctorCont extends Attributes {
    @GetMapping
    public String Doctor(Model model) {
        AddAttributesDoctor(model);
        return "doctor";
    }

    @PostMapping("/edit/photo")
    public String DoctorEditPhoto(Model model, @RequestParam MultipartFile photo) {
        if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
            String res = "";
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = "doctors/" + uuidFile + "_" + photo.getOriginalFilename();
                    photo.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (Exception e) {
                model.addAttribute("message", "Некорректный данные!");
                AddAttributesDoctor(model);
                return "doctor";
            }
            Doctors doctor = getUser().getDoctor();
            doctor.setPhoto(res);
            doctorsRepo.save(doctor);
        }
        return "redirect:/doctor";
    }

    @PostMapping("/edit")
    public String DoctorEdit(@RequestParam String fio, @RequestParam Long category, @RequestParam String tel, @RequestParam byte experience) {
        Doctors doctor = getUser().getDoctor();
        doctor.setFio(fio);
        doctor.setCategory(categoryRepo.getReferenceById(category).getName());
        doctor.setTel(tel);
        doctor.setExperience(experience);
        doctorsRepo.save(doctor);
        return "redirect:/doctor";
    }
}
