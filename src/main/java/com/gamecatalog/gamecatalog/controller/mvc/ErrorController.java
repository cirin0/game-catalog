//package com.gamecatalog.gamecatalog.controller.mvc;
//
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
//  @RequestMapping("/error")
//  public String handleError(HttpServletRequest request, Model model) {
//    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//    String errorPage = "error/error";
//
//    if (status != null) {
//      int statusCode = Integer.parseInt(status.toString());
//
//      errorPage = switch (statusCode) {
//        case 404 -> "error/404";
//        case 403 -> "error/403";
//        case 500 -> "error/500";
//        default -> errorPage;
//      };
//    }
//    return errorPage;
//  }
//}
