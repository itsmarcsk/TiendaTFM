/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tfm.tiendaonline.controller;

/**
 *
 * @author colos
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VideoController {

    @GetMapping("/video")
    public ModelAndView showVideo(@RequestParam String filename) {
        ModelAndView modelAndView = new ModelAndView("video"); // Nombre de la vista HTML
        modelAndView.addObject("filename", filename); // Pasar el nombre del archivo a la vista
        return modelAndView;
    }
}
